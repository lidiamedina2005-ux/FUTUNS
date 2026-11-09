package FUTUNS;

/**
 *
 * @author LIDIA
 */
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        GestorTramites gestor = new GestorTramites();
        System.out.println("SISTEMA DE GESTIÓN DE TRÁMITES FUT - UNS");
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n1. Registrar solicitud FUT");
            System.out.println("2. Listar solicitudes registradas");
            System.out.println("0. Salir");
            int opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> registrarSolicitud(gestor);
                case 2 -> listarSolicitudes(gestor);
                case 0 -> continuar = false;
                default -> System.out.println("Opción no válida.");
            }
        }
        System.out.println("Programa finalizado.");
    }

    /**
     * Registra una solicitud siguiendo EXACTAMENTE el orden del FUT oficial (PDF).
     */
    private static void registrarSolicitud(GestorTramites gestor) {
        try {
            System.out.println("\n===== FORMATO ÚNICO DE TRÁMITE - UNS =====\n");

            // 1) Señor(a) (Dr., Mg., Ing., Lic.)
            String destinatario = leerTexto("Señor(a) (Dr., Mg., Ing., Lic.) : ");

            // 2) Cargo
            String cargo = leerTexto("Cargo : ");

            // 3) Nombres y Apellidos
            String nombres = leerTexto("Nombres y Apellidos : ");

            // 4) Facultad de ___ Escuela Profesional ___
            String facultad = leerTexto("Facultad de : ");
            String escuela = leerTexto("Escuela Profesional : ");

            // 5) Código de matrícula Nº ___, solicito a usted...
            String codigoMatricula = leerTexto("Código de matrícula Nº : ");
            System.out.println("..., solicito a usted, ordene a quien corresponda, lo siguiente:");

            // 6) Marcar con un aspa (x) lo que se va a solicitar
            System.out.println("\nMarcar con un aspa (x) lo que se va a solicitar");
            List<Tramite> seleccionados = seleccionarTramites();
            String otros = "";
            if (seleccionados.contains(Tramite.OTROS)) {
                otros = leerTexto("Detalle de OTROS : ");
            }

            // 7) Fundamento de la Solicitud
            String fundamento = leerTexto("\nFundamento de la Solicitud : ");

            // 8) Para lo cual adjunto... requisitos TUPA
            System.out.println("Para lo cual adjunto al presente los requisitos exigidos "
                    + "por el TUPA de la Universidad Nacional del Santa.");
            String requisitos = leerTexto("Requisitos adjuntos : ");

            // 9) Dirección / Teléfono / Celular (van al pie del FUT)
            String direccion = leerTexto("\nDirección : ");
            String telefono = leerTexto("Teléfono / Celular : ");

            // --- Construcción de objetos ---
            Estudiante estudiante = new Estudiante(
                    nombres, facultad, escuela, codigoMatricula, direccion, telefono);

            LocalDate fecha = LocalDate.now();

            SolicitudFUT solicitud = new SolicitudFUT(
                    gestor.generarCodigo(), estudiante, destinatario, cargo,
                    seleccionados, otros, fundamento, requisitos, fecha);

            gestor.registrar(solicitud);
            Path archivo = Path.of("Reporte_" + solicitud.getCodigo() + ".txt");
            gestor.generarReporte(solicitud, archivo);

            System.out.println("\nSolicitud registrada correctamente.");
            System.out.println("Código: " + solicitud.getCodigo());
            System.out.println("Reporte generado en: " + archivo.toAbsolutePath());
            System.out.println(new CargoRecepcion(solicitud, "Pendiente de sello").generarTexto());

        } catch (Exception ex) {
            System.out.println("No se pudo registrar la solicitud: " + ex.getMessage());
        }
    }

    /**
     * Muestra el catálogo con casillas [ ] / [x] igual que el FUT.
     * El usuario ingresa los números separados por coma.
     */
    private static List<Tramite> seleccionarTramites() {
        Tramite[] opciones = Tramite.values();
        List<Tramite> seleccionados = new ArrayList<>();

        System.out.println();
        for (int i = 0; i < opciones.length; i++) {
            System.out.printf("[ ] %2d. %s%n", i + 1, opciones[i].getDescripcion());
            if (opciones[i].getNota() != null) {
                System.out.println("        " + opciones[i].getNota());
            }
        }
        System.out.println("\nIngrese los números separados por coma (ejemplo: 11,26):");

        String[] entradas = leerTexto("Trámites : ").split(",");
        for (String entrada : entradas) {
            int numero = Integer.parseInt(entrada.trim());
            if (numero < 1 || numero > opciones.length) {
                throw new IllegalArgumentException("Número de trámite fuera del catálogo: " + numero);
            }
            Tramite tramite = opciones[numero - 1];
            if (!seleccionados.contains(tramite)) {
                seleccionados.add(tramite);
            }
        }
        if (seleccionados.isEmpty()) {
            throw new IllegalArgumentException("Seleccione al menos un trámite.");
        }
        return seleccionados;
    }

    private static void listarSolicitudes(GestorTramites gestor) {
        if (gestor.listar().isEmpty()) {
            System.out.println("Todavía no hay solicitudes registradas.");
            return;
        }
        System.out.println(" SOLICITUDES REGISTRADAS ");
        for (SolicitudFUT s : gestor.listar()) {
            System.out.printf("%s | %s | %s | %s%n",
                    s.getCodigo(), s.getEstudiante().getNombresApellidos(),
                    s.getEstudiante().getCodigoMatricula(), s.getFecha());
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        String valor = sc.nextLine().trim();
        if (valor.isBlank()) {
            throw new IllegalArgumentException("El campo no puede quedar vacío.");
        }
        return valor;
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Integer.parseInt(sc.nextLine().trim());
    }
}