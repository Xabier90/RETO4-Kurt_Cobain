package modulo;

public class IndicadorSostenibilidad {

    private int idIndicador;
    private int idCentro;
    private int anio;
    private double huellaCarbono;
    private double porcentajeRenovable;
    private double indiceSocial;
    private double puntuacionGlobal;

    public IndicadorSostenibilidad(int idIndicador, int idCentro, int anio,
                                   double huellaCarbono, double porcentajeRenovable,
                                   double indiceSocial, double puntuacionGlobal) {
        this.idIndicador = idIndicador;
        this.idCentro = idCentro;
        this.anio = anio;
        this.huellaCarbono = huellaCarbono;
        this.porcentajeRenovable = porcentajeRenovable;
        this.indiceSocial = indiceSocial;
        this.puntuacionGlobal = puntuacionGlobal;
    }

    public int getIdIndicador() { return idIndicador; }
    public int getIdCentro() { return idCentro; }
    public int getAnio() { return anio; }
    public double getHuellaCarbono() { return huellaCarbono; }
    public double getPorcentajeRenovable() { return porcentajeRenovable; }
    public double getIndiceSocial() { return indiceSocial; }
    public double getPuntuacionGlobal() { return puntuacionGlobal; }
}