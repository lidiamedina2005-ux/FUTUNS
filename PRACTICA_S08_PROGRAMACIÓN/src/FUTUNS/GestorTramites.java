package FUTUNS;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class GestorTramites {
    private final List<SolicitudFUT> solicitudes = new ArrayList<>();
    private static int correlativo = 1;

    public String generarCodigo() {
        return "FUT-UNS-" + String.format("%04d", correlativo++);
    }

    public void registrar(SolicitudFUT solicitud) {
        if (solicitud == null) throw new IllegalArgumentException("Solicitud nula.");
        solicitudes.add(solicitud);
    }

    public List<SolicitudFUT> listar() {
        return List.copyOf(solicitudes);
    }

    public void generarReporte(SolicitudFUT s, Path destino) throws IOException {
        StringBuilder r = new StringBuilder();
        Estudiante e = s.getEstudiante();
        Set<Tramite> marcados = EnumSet.copyOf(s.getTramites());

        // --- Encabezado del FUT (tal cual el PDF) ---
        r.append("FORMATO ÚNICO DE TRÁMITE - UNS\n\n");
        r.append("Señor(a) (Dr., Mg., Ing., Lic.) : ").append(s.getDestinatario()).append('\n');
        r.append("Cargo : ").append(s.getCargoDestinatario()).append("\n\n");
        r.append("Nombres y Apellidos : ").append(e.getNombresApellidos()).append('\n');
        r.append("Facultad de ").append(e.getFacultad())
         .append("    Escuela Profesional : ").append(e.getEscuelaProfesional()).append('\n');
        r.append("Código de matrícula Nº ").append(e.getCodigoMatricula())
         .append(", solicito a usted, ordene a quien corresponda, lo siguiente:\n\n");

        r.append("Marcar con un aspa (x) lo que se va a solicitar\n\n");

        // --- Catálogo completo con casillas, en el orden exacto del PDF ---
        Tramite[] todos = Tramite.values();
        for (Tramite t : todos) {
            // El PDF intercala "Fundamento de la Solicitud" entre los trámites,
            // justo después de "Entrega de Grado o Título por mesa de partes".
            if (t == Tramite.EXAMEN_APLAZADOS) {
                r.append("\nFundamento de la Solicitud: ").append(s.getFundamento()).append("\n\n");
            }

            r.append(marcados.contains(t) ? "[x] " : "[ ] ");
            r.append(t.getDescripcion());
            if (t.getNota() != null) {
                r.append("\n    ").append(t.getNota());
            }
            // "OTROS" lleva el detalle a continuación
            if (t == Tramite.OTROS && marcados.contains(Tramite.OTROS)) {
                r.append(": ").append(s.getDetalleOtros());
            }
            r.append('\n');
        }

        // --- Cláusula TUPA ---
        r.append("\nPara lo cual adjunto al presente los requisitos exigidos por el TUPA ")
         .append("de la Universidad Nacional del Santa.\n\n");

        // --- Firma y lugar/fecha (el PDF deja espacios en blanco: "__ de __ de 20__") ---
        r.append("Firma del interesado(a): ______________________________    ")
         .append("Nuevo Chimbote, ____ de ________________ de 20____\n\n");

        // --- Dirección / Teléfono al pie ---
        r.append("Dirección: ").append(e.getDireccion())
         .append("    Teléfono / Celular: ").append(e.getTelefono()).append("\n\n");

        // --- Observaciones ---
        r.append("OBSERVACIONES :\n");
        r.append("________________________________________________________________________\n\n");

        // --- Bloque de desglose (dos veces, como en el PDF) ---
        r.append("Desglosar de la solicitud        Desglosar de la solicitud\n\n");
        r.append("Nombres y Apellidos : ").append(e.getNombresApellidos()).append('\n');
        r.append("Solicita            : ").append(s.getCodigo()).append("\n\n");

        // --- Sello de recepción al pie ---
        r.append(new CargoRecepcion(s, "Pendiente de sello").generarTexto());

        Files.writeString(destino, r.toString());
    }
}