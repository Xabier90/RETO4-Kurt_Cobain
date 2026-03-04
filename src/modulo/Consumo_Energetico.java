package modulo;

import java.time.LocalDate;
	
	

public class Consumo_Energetico {
	private int id_consumo;
	private int id_sector;
	private LocalDate fecha;
	private float kwh_consumidos;
	private float coste_eur;
	private String fuente_energia;
	
	// Costructor
	public Consumo_Energetico (int id_consumo, int id_sector, LocalDate fecha, float kwh_consumidos, float coste_eur, String fuente_energia) {
		this.id_consumo = id_consumo;
		this.id_sector = id_sector;
		this.fecha = fecha;
		this.kwh_consumidos = kwh_consumidos;
		this.coste_eur = coste_eur;
		this.fuente_energia = fuente_energia;
	}
	
	//Getters y Setters
		
	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	
	public float getKwh_Consumidos () {
		return kwh_consumidos;
	}
	
	public void setKwh_Consumidos (float kwh_consumidos) {
		this.kwh_consumidos = kwh_consumidos;
	}
	
	
	public float getCoste_Eur() {
		return coste_eur;
	}
	
	public void setCoste_Eur(float coste_eur) {
		this.coste_eur = coste_eur;
	}
	
	
	public String getFuente_Energia () {
		return fuente_energia;
	}
	
	public void setFuente_Energia(String fuente_energia) {
		this.fuente_energia = fuente_energia;
	}
		
}
