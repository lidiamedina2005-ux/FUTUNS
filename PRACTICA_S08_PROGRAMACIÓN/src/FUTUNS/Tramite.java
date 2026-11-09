package FUTUNS;

public enum Tramite {
    ACCESO_INFORMACION("Acceso a la información que posee o produce la UNS", null),
    CERTIFICADOS_ESTUDIOS_NOTAS("Certificados de estudios y/o notas", null),
    CERTIFICADOS_POR_NIVEL("Certificados por nivel", null),
    CONSTANCIA_ADEUDO_ASIGNATURA("Constancia de adeudo de asignatura", null),
    CONSTANCIA_APROBACION_TESIS("Constancia de aprobación de Tesis", null),
    CONSTANCIA_CONDUCTA("Constancia de conducta", null),
    CONSTANCIA_EGRESADO("Constancia de egresado", null),
    CONSTANCIA_EXPEDITO("Constancia de expedito", null),
    CONSTANCIA_GRADOS_TITULOS("Constancia de Grados y Títulos Profesionales", null),
    CONSTANCIA_HORAS("Constancia de horas teóricas - prácticas", null),
    CONSTANCIA_INGRESO("Constancia de ingreso", "(especificar en OTROS la modalidad)"),
    CONSTANCIA_MATRICULA("Constancia de matrícula", null),
    CONSTANCIA_NO_ADEUDOS_UNS("Constancia de no adeudos a la UNS", null),
    CONSTANCIA_NO_ADEUDO_ASIGNATURA("Constancia de no adeudo de asignatura", null),
    CONSTANCIA_NO_MEDIDAS_DISCIPLINARIAS("Constancia de no estar sometido a medidas disciplinarias", null),
    CONSTANCIA_NOTAS("Constancia de notas", null),
    CONSTANCIA_ORDEN_MERITO("Constancia de orden de mérito", null),
    CONSTANCIA_PRACTICAS("Constancia de prácticas preprofesionales", null),
    CONSTANCIA_TERCIO_SUPERIOR("Constancia de tercio superior", null),
    CONSTANCIA_PROMEDIO_PONDERADO("Constancia de promedio ponderado", null),
    CONVALIDACION_HOMOLOGACION("Convalidación u homologación de cursos", null),
    CURSOS_NIVELACION("Cursos de nivelación y avance académico", null),
    DUPLICADO_CARNE_POSTULANTE("Duplicado de carné de postulante", null),
    DUPLICADO_CARNE_UNIVERSITARIO("Duplicado de carné universitario", null),
    DUPLICADO_CONSTANCIA_INGRESO("Duplicado de constancia de ingreso", "(especificar en OTROS la modalidad)"),
    ENTREGA_GRADO_TITULO("Entrega de Grado o Título por mesa de partes", null),
    EXAMEN_APLAZADOS("Examen de aplazados", null),
    EXAMEN_EXPERIENCIA("Examen de experiencia profesional", null),
    EXAMEN_SUFCIENCIA("Examen de suficiencia profesional", null),
    EXAMEN_UBICACION("Examen de ubicación", null),
    EXAMEN_EXTEMPORANEO("Examen extemporáneo y/o exposición", null),
    FOTOCOPIA_CERTIFICADO("Fotocopia de certificado de estudios", null),
    MATRICULA_ESPECIAL("Matrícula especial (examen de suficiencia)", null),
    MATRICULA_EXTEMPORANEA("Matrícula extemporánea", null),
    NOMBRAMIENTO_ASESOR("Nombramiento de Asesor del Proyecto de Trabajo de Investigación (Tesis)", null),
    NOMBRAMIENTO_JURADO("Nombramiento de Jurado evaluador del Proyecto de Trabajo de Investigación (Tesis)", null),
    DUPLICADO_CARNE_BIBLIOTECA("Obtención de duplicado de carné único de biblioteca", null),
    PROGRAMA_NIVELACION("Programa de nivelación y afianzamiento", null),
    REANUDACION_ESTUDIOS("Reanudación de estudios", null),
    RECORD_ACADEMICO("Record académico", null),
    RECTIFICACION_NOMBRE("Rectificación de nombre(s) y/o apellido(s)", null),
    RESERVA_MATRICULA("Reserva de matrícula", null),
    AUTOSEGURO_ESTUDIANTIL("Servicio de auto seguro estudiantil", null),
    BOLSA_TRABAJO("Servicio de bolsa de trabajo", null),
    COMEDOR_UNIVERSITARIO("Servicio de comedor universitario", null),
    SUSTENTACION_TESIS("Sustentación de Tesis", null),
    TRASLADO_CEE("Traslado al CEE - Trámite administrativo", null),
    OTROS("OTROS", null);

    private final String descripcion;
    private final String nota;

    Tramite(String descripcion, String nota) {
        this.descripcion = descripcion;
        this.nota = nota;
    }

    public String getDescripcion() { return descripcion; }
    public String getNota() { return nota; }
}