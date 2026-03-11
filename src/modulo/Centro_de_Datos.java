package modulo;

import java.time.LocalDate;
	
/**
 * Representa un Centro de Datos con su información técnica y de ubicación.
 * Esta clase permite gestionar los detalles básicos de una infraestructura de servidores.
 * @author Xabier
 * @version 1.0
 */
public class Centro_de_Datos {
	/** Definimos los atributos de Centro_de_Datos según la base de datos que hemos generado.
	 * Dejando claro cada uno de los atributos que tipo de datos contiene*/
	private int id_centro;
	private String nombre;
	private String ubicacion;
	private String pais;
	private LocalDate fecha_apertura;
	private int superficie_m2;
	private int num_servidores;
	
	// Constructor
	/**
     * Constructor para crear un nuevo Centro de Datos con todos sus atributos.
     * @param id_centro Código de identificación único que se autoincrementa.
     * @param nombre Nombre del centro de datos.
     * @param ubicacion La dirección del centro de datos.
     * @param pais Nombre del país.
     * @param fecha_apertura Fecha de inauguración.
     * @param superficie_m2 Área total en metros cuadrados de los distintos centros.
     * @param num_servidores Número de servidores.
     */
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
	
	/** @return El nombre del centro de datos */
	public String getNombre() {
		return nombre;
	}
	/** @param nombre Nuevo nombre para el centro */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/** @return La direccion del centro de datos */
	public String getUbicacion() {
		return ubicacion;
	}
	/** @param ubicacion Nueva direccion del centro.*/
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/** @return El pais donde se encuentra el centro de datos.*/
	public String getPais() {
		return pais;
	}
	/** @param pais El pais en el que se encuentra el centro de datos.*/
	public void setPais(String pais) {
		this.pais = pais;
	}

	/** @return La fecha en la que se inauguró el centro de datos.*/
	public LocalDate getFecha_apertura() {
		return fecha_apertura;
	}
	/** @param fecha_apertura La fecha en la que empezó a funcionar el centro de datos. */
	public void setFecha_apertura(LocalDate fecha_apertura) {
		this.fecha_apertura = fecha_apertura;
	}

	/** @return El tamaño de cada centro de datos.*/
	public int getSuperficie_m2() {
		return superficie_m2;
	}
	/** @param superficie_m2 Es el tamaño en m2 de cada centro de datos*/
	public void setSuperficie_m2(int superficie_m2) {
		this.superficie_m2 = superficie_m2;
	}

	/** @return La cantidad de servidores en cada centro de datos.*/
	public int getNum_servidores() {
		return num_servidores;
	}
	/** @param num_servidores Nos da la cifra de la cantidad de servidores que tenemos en cada centro de datos.*/
	public void setNum_servidores(int num_servidores) {
		this.num_servidores = num_servidores;
	}
	
	
}



