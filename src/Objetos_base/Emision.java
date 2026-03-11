package modulo;

import java.time.LocalDate;

public class Emision {

    private int idEmision;
    private int idSector;
    private LocalDate fecha;
    private double co2Kg;
    private String otrosGases;
    private String certificacionVerde;
    
    /** @param Desglose de los atributos de la tabla Emisión. Tenemos dos enteros, uno de fecha, otro de double decimal y 
     * dos de texto */

    public Emision(int idEmision, int idSector, LocalDate fecha,
                   double co2Kg, String otrosGases, String certificacionVerde) {
        this.idEmision = idEmision;
        this.idSector = idSector;
        this.fecha = fecha;
        this.co2Kg = co2Kg;
        this.otrosGases = otrosGases;
        this.certificacionVerde = certificacionVerde;
    }
/** @return Devuelven valores por tipo a cada uno de los atributos  */
    public int getIdEmision() { return idEmision; }
    public int getIdSector() { return idSector; }
    public LocalDate getFecha() { return fecha; }
    public double getCo2Kg() { return co2Kg; }
    public String getOtrosGases() { return otrosGases; }
    public String getCertificacionVerde() { return certificacionVerde; }
}
    public String getCertificacionVerde() { return certificacionVerde; }

}
