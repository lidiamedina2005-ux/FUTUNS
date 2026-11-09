 
package FUTUNS;

/**
 *
 * @author LIDIA
 */
import java.time.LocalDate;
import java.util.List;

public class SolicitudFUT {
    private final String codigo;
    private final Estudiante estudiante;
    private final String destinatario;
    private final String cargoDestinatario;
    private final List<Tramite> tramites;
    private final String detalleOtros;
    private final String fundamento;
    private final String requisitosAdjuntos;
    private final LocalDate fecha;

    public SolicitudFUT(String codigo, Estudiante estudiante, String destinatario,
                        String cargoDestinatario, List<Tramite> tramites,
                        String detalleOtros, String fundamento,
                        String requisitosAdjuntos, LocalDate fecha) {
        if (tramites == null || tramites.isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionar al menos un trámite.");
        }
        if (fundamento == null || fundamento.isBlank()) {
            throw new IllegalArgumentException("El fundamento es obligatorio.");
        }
        this.codigo = codigo;
        this.estudiante = estudiante;
        this.destinatario = destinatario;
        this.cargoDestinatario = cargoDestinatario;
        this.tramites = List.copyOf(tramites);
        this.detalleOtros = detalleOtros == null ? "" : detalleOtros.trim();
        this.fundamento = fundamento.trim();
        this.requisitosAdjuntos = requisitosAdjuntos == null ? "" : requisitosAdjuntos.trim();
        this.fecha = fecha;
    }

    public String getCodigo() { return codigo; }
    public Estudiante getEstudiante() { return estudiante; }
    public String getDestinatario() { return destinatario; }
    public String getCargoDestinatario() { return cargoDestinatario; }
    public List<Tramite> getTramites() { return tramites; }
    public String getDetalleOtros() { return detalleOtros; }
    public String getFundamento() { return fundamento; }
    public String getRequisitosAdjuntos() { return requisitosAdjuntos; }
    public LocalDate getFecha() { return fecha; }
}
