package modulo;

import java.time.LocalDate;
	
public class Centro_de_Datos {
	
	private int id_centro;
	private String nombre;
	private String ubicacion;
	private String pais;
	private LocalDate fecha_apertura;
	private int superficie_m2;
	private int num_servidores;
	
	// Constructor
	public Centro_de_Datos(int id_centro, String nombre, String ubicacion, String pais, LocalDate fecha_apertura, int superficie_m2, int num_servidores){
		this.id_centro = id_centro;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.pais = pais;
		this.fecha_apertura = fecha_apertura;
		this.superficie_m2 = superficie_m2;
		this.num_servidores = num_servidores;
	
	}
	
	// Getters y Setters (Necesarios para acceder  a cada dato)

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	
	public LocalDate getFecha_apertura() {
		return fecha_apertura;
	}

	public void setFecha_apertura(LocalDate fecha_apertura) {
		this.fecha_apertura = fecha_apertura;
	}

	
	public int getSuperficie_m2() {
		return superficie_m2;
	}

	public void setSuperficie_m2(int superficie_m2) {
		this.superficie_m2 = superficie_m2;
	}

	
	public int getNum_servidores() {
		return num_servidores;
	}

	public void setNum_servidores(int num_servidores) {
		this.num_servidores = num_servidores;
	}
	
	
}



