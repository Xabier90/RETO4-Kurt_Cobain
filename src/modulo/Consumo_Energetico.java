package modulo;

import java.time.LocalDate;
	
	
/** 
* Definimos los atributos de Consumo_Energetico según la base de datos que hemos generado.
* Dejando claro cada uno de los atributos que tipo de datos contiene.
* Clase que representa el consumo energético registrado en un sector determinado.
* Esta clase permite almacenar y gestionar los datos de consumo energético
* mediante el uso de métodos getters y setters.
* @author Xabier Iglesias
* @version 1.0
*/
public class Consumo_Energetico {
	private int id_consumo;
	private int id_sector;
	private LocalDate fecha;
	private float kwh_consumidos;
	private float coste_eur;
	private String fuente_energia;
	
	// Costructor
	/**
    * Constructor de la clase Consumo_Energetico.
    * 
    * Inicializa todos los atributos necesarios para crear un registro
    * de consumo energético.
    * En este punto se meten diferentes parametros para poder crear los objetos
    * @param id_consumo Identificador único del consumo.
    * @param id_sector Identificador del sector donde se produce el consumo.
    * @param fecha Fecha del registro del consumo.
    * @param kwh_consumidos Cantidad de energía consumida en kWh.
    * @param coste_eur Coste del consumo en euros.
    * @param fuente_energia Fuente de energía utilizada.
    */
	public Consumo_Energetico (int id_consumo, int id_sector, LocalDate fecha, float kwh_consumidos, float coste_eur, String fuente_energia) {
		this.id_consumo = id_consumo;
		this.id_sector = id_sector;
		this.fecha = fecha;
		this.kwh_consumidos = kwh_consumidos;
		this.coste_eur = coste_eur;
		this.fuente_energia = fuente_energia;
	}
	
	//Getters y Setters
	  /**
     * @return fecha en la que se registró el consumo energético.
     */
	public LocalDate getFecha() {
		return fecha;
	}
	/**
     * @param fecha nueva fecha del consumo energético.
     */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	/** 
     * @return energía consumida en kilovatios hora (kWh).
     */
	public float getKwh_Consumidos () {
		return kwh_consumidos;
	}
    /**
     * @param kwh_consumidos nueva cantidad de energía consumida en kWh.
     */
	public void setKwh_Consumidos (float kwh_consumidos) {
		this.kwh_consumidos = kwh_consumidos;
	}
	

    /**
     * @return coste del consumo en euros.
     */
	public float getCoste_Eur() {
		return coste_eur;
	}
    /** 
     * @param coste_eur nuevo coste del consumo en euros.
     */
	public void setCoste_Eur(float coste_eur) {
		this.coste_eur = coste_eur;
	}
	

    /** 
     * @return fuente de energía del consumo.
     */
	public String getFuente_Energia () {
		return fuente_energia;
	}
	   /**
     * @param fuente_energia nueva fuente de energía.
     */
	public void setFuente_Energia(String fuente_energia) {
		this.fuente_energia = fuente_energia;
	}
		
}
