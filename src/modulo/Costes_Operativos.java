package modulo;


import java.time.LocalDate;

public class Costes_Operativos {
	private int id_coste;
	private int id_sector;
	private LocalDate fecha;
	private float coste_energia;
	private float coste_mantenimiento;
	private float coste_personal;
	private float coste_total;
	
	// Constructor
	public Costes_Operativos(int id_coste, int id_sector, LocalDate fecha, float coste_energia, float coste_mantenimiento, float coste_personal, float coste_total) {
		this.id_coste = id_coste;
		this.id_sector = id_sector;
		this.fecha = fecha;
		this.coste_energia = coste_energia;
		this.coste_mantenimiento = coste_mantenimiento;
		this.coste_personal = coste_personal;
		this.coste_total = coste_total;
		
	}
	// Getters y Setters
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public void setFecha (LocalDate fecha) {
		this.fecha = fecha;
	}

	public float getCoste_energia() {
		return coste_energia;
	}

	public void setCoste_Energia(float coste_energia) {
		this.coste_energia = coste_energia;
	}
	
	
	
	public float getCoste_Mantenimiento () {
		return coste_mantenimiento;
	}
	
	public void setCoste_Mantenimiento(float coste_mantenimiento) {
		this.coste_mantenimiento = coste_mantenimiento;
	}
	
	
	public float getCoste_Personal () {
		return coste_personal;
	}
	
	public void setCoste_Personal(float coste_personal) {
		this.coste_personal = coste_personal;
	}
	
	
	public float getCoste_Total () {
		return coste_total;
	}
	
	public void setCoste_Total(float coste_total) {
		this.coste_total = coste_total;
	}
	
		
}
