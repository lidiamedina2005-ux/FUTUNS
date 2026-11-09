/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FUTUNS;

/**
 *
 * @author LIDIA
 */
public class Estudiante {
    private String nombresApellidos;
    private String facultad;
    private String escuelaProfesional;
    private String codigoMatricula;
    private String direccion;
    private String telefono;

    public Estudiante(String nombresApellidos, String facultad, String escuelaProfesional,
                      String codigoMatricula, String direccion, String telefono) {
        this.nombresApellidos = validar(nombresApellidos, "Nombres y apellidos");
        this.facultad = validar(facultad, "Facultad");
        this.escuelaProfesional = validar(escuelaProfesional, "Escuela profesional");
        this.codigoMatricula = validar(codigoMatricula, "Código de matrícula");
        this.direccion = validar(direccion, "Dirección");
        this.telefono = validar(telefono, "Teléfono");
    }

    private String validar(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío.");
        }
        return valor.trim();
    }

    public String getNombresApellidos() { return nombresApellidos; }
    public String getFacultad() { return facultad; }
    public String getEscuelaProfesional() { return escuelaProfesional; }
    public String getCodigoMatricula() { return codigoMatricula; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
}
