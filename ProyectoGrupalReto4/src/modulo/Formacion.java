package modulo;

import java.time.LocalDate;

public class Formacion {

    private int idFormacion;
    private int idCentro;
    private String curso;
    private int horas;
    private LocalDate fecha;
    private String certificacion;
    private String proveedor;

    public Formacion(int idFormacion, int idCentro, String curso,
                     int horas, LocalDate fecha,
                     String certificacion, String proveedor) {
        this.idFormacion = idFormacion;
        this.idCentro = idCentro;
        this.curso = curso;
        this.horas = horas;
        this.fecha = fecha;
        this.certificacion = certificacion;
        this.proveedor = proveedor;
    }

    public int getIdFormacion() { return idFormacion; }
    public int getIdCentro() { return idCentro; }
    public String getCurso() { return curso; }
    public int getHoras() { return horas; }
    public LocalDate getFecha() { return fecha; }
    public String getCertificacion() { return certificacion; }
    public String getProveedor() { return proveedor; }
}