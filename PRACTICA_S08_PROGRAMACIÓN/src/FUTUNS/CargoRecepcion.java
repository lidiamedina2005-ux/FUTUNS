package FUTUNS;

import java.time.LocalDate;

/**
 * Representa el bloque de "SELLO DE RECEPCION" que aparece al pie del FUT oficial.
 * La fecha de recepción es la misma fecha de la solicitud.
 */
public class CargoRecepcion {
    private final String codigoSolicitud;
    private final String nombreSolicitante;
    private final LocalDate fechaRecepcion;
    private final String selloRecepcion;

    public CargoRecepcion(SolicitudFUT solicitud, String selloRecepcion) {
        if (solicitud == null) {
            throw new IllegalArgumentException("La solicitud es obligatoria.");
        }
        this.codigoSolicitud = solicitud.getCodigo();
        this.nombreSolicitante = solicitud.getEstudiante().getNombresApellidos();
        this.fechaRecepcion = solicitud.getFecha();
        this.selloRecepcion = (selloRecepcion == null || selloRecepcion.isBlank())
                ? "Pendiente de sello" : selloRecepcion.trim();
    }

    /** Devuelve el bloque tal como aparece en el PDF oficial. */
    public String generarTexto() {
        return """
                Nombres y Apellidos : %s
                Solicita            : %s
                Fecha de recepción  : %s

                +-------------------------+
                |      SELLO DE           |
                |      RECEPCION          |
                |   %s
                +-------------------------+
                """.formatted(nombreSolicitante, codigoSolicitud,
                fechaRecepcion, selloRecepcion);
    }
}