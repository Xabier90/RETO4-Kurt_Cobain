package modulo;


import java.time.LocalDate;

/**
*  Clase que representa los costes operativos asociados a un sector determinado. 
* Esta clase almacena información relacionada con los diferentes gastos
* operativos, como el coste de energía, mantenimiento y personal, además
* del coste total en una fecha concreta.
* Permite acceder y modificar estos datos mediante métodos getters y setters.
* @author Xabier Iglesias
* @version 1.0
*/
public class Costes_Operativos {
	private int id_coste;
	private int id_sector;
	private LocalDate fecha;
	private float coste_energia;
	private float coste_mantenimiento;
	private float coste_personal;
	private float coste_total;
	
	// Constructor
	/**
     * Constructor de la clase Costes_Operativos.
     * Permite crear un objeto inicializando todos los atributos relacionados
     * con los costes operativos de un sector en una fecha determinada.
     * @param id_coste identificador único del coste.
     * @param id_sector identificador del sector.
     * @param fecha fecha en la que se registran los costes
     * @param coste_energia coste correspondiente a la energía
     * @param coste_mantenimiento coste de mantenimiento.
     * @param coste_personal coste asociado al personal
     * @param coste_total coste total de la operación
     */
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

    /**
     * @return Obtenemos la fecha del registro.
     */
	public LocalDate getFecha() {
		return fecha;
	}
	/**
     * @param fecha Nueva fecha del registro de costes.
     */
	public void setFecha (LocalDate fecha) {
		this.fecha = fecha;
	}
	 /**
     * @return Obtenemos los coste asociado al consumo de energía.
     */
	public float getCoste_energia() {
		return coste_energia;
	}

     /**
     * @param coste_energia Nuevo coste de energía.
     */
	public void setCoste_Energia(float coste_energia) {
		this.coste_energia = coste_energia;
	}
	
	
	 /**
     * @return Obtenemos los coste de mantenimiento.
     */
	public float getCoste_Mantenimiento () {
		return coste_mantenimiento;
	}
    /**
     * @param coste_mantenimiento Nuevo coste de mantenimiento.
     */
	public void setCoste_Mantenimiento(float coste_mantenimiento) {
		this.coste_mantenimiento = coste_mantenimiento;
	}
	
	 /** 
     * @return Obtenemos los coste del personal.
     */
	public float getCoste_Personal () {
		return coste_personal;
	}
	 /** 
     * @param coste_personal Nuevo coste del personal.
     */
	public void setCoste_Personal(float coste_personal) {
		this.coste_personal = coste_personal;
	}
	
	 /** 
     * @return Obtenemos el coste total (creo que de un momento concreto).
     */
	public float getCoste_Total () {
		return coste_total;
	}

    /**
     * @param coste_total Nuevo coste total.
     */
	public void setCoste_Total(float coste_total) {
		this.coste_total = coste_total;
	}
		
}
