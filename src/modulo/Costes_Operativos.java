package modulo;

import java.time.LocalDate;

/**
 * Representa los costes operativos asociados a un sector en una fecha concreta.
 * <p>
 * Almacena los diferentes gastos operativos de un sector, desglosados en coste
 * de energía, mantenimiento y personal, junto con el coste total resultante.
 * Permite consultar y modificar estos datos mediante métodos getters y setters.
 * </p>
 *
 * @author Xabier Iglesias
 * @version 1.0
 * @since 1.0
 */
public class Costes_Operativos {

	/** Identificador único del registro de coste operativo. */
	private int id_coste;

	/** Identificador del sector al que pertenecen los costes. */
	private int id_sector;

	/** Fecha en la que se registran los costes operativos. */
	private LocalDate fecha;

	/** Coste asociado al consumo de energía. */
	private float coste_energia;

	/** Coste asociado a las labores de mantenimiento. */
	private float coste_mantenimiento;

	/** Coste asociado al personal del sector. */
	private float coste_personal;

	/** Coste total de la operación, suma de los costes parciales. */
	private float coste_total;

	/**
	 * Construye una nueva instancia de {@code Costes_Operativos} con todos sus
	 * atributos.
	 *
	 * @param id_coste            identificador único del registro de coste
	 * @param id_sector           identificador del sector asociado
	 * @param fecha               fecha de registro de los costes
	 * @param coste_energia       coste correspondiente al consumo energético
	 * @param coste_mantenimiento coste correspondiente al mantenimiento
	 * @param coste_personal      coste correspondiente al personal
	 * @param coste_total         coste total de la operación
	 */
	public Costes_Operativos(int id_coste, int id_sector, LocalDate fecha, float coste_energia,
			float coste_mantenimiento, float coste_personal, float coste_total) {
		this.id_coste = id_coste;
		this.id_sector = id_sector;
		this.fecha = fecha;
		this.coste_energia = coste_energia;
		this.coste_mantenimiento = coste_mantenimiento;
		this.coste_personal = coste_personal;
		this.coste_total = coste_total;
	}

	/**
	 * Devuelve la fecha en la que se registraron los costes operativos.
	 *
	 * @return fecha del registro de costes
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Establece una nueva fecha para el registro de costes operativos.
	 *
	 * @param fecha nueva fecha del registro
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/**
	 * Devuelve el coste asociado al consumo de energía.
	 *
	 * @return coste energético
	 */
	public float getCoste_energia() {
		return coste_energia;
	}

	/**
	 * Establece un nuevo valor para el coste de energía.
	 *
	 * @param coste_energia nuevo coste energético
	 */
	public void setCoste_Energia(float coste_energia) {
		this.coste_energia = coste_energia;
	}

	/**
	 * Devuelve el coste asociado a las labores de mantenimiento.
	 *
	 * @return coste de mantenimiento
	 */
	public float getCoste_Mantenimiento() {
		return coste_mantenimiento;
	}

	/**
	 * Establece un nuevo valor para el coste de mantenimiento.
	 *
	 * @param coste_mantenimiento nuevo coste de mantenimiento
	 */
	public void setCoste_Mantenimiento(float coste_mantenimiento) {
		this.coste_mantenimiento = coste_mantenimiento;
	}

	/**
	 * Devuelve el coste asociado al personal del sector.
	 *
	 * @return coste de personal
	 */
	public float getCoste_Personal() {
		return coste_personal;
	}

	/**
	 * Establece un nuevo valor para el coste de personal.
	 *
	 * @param coste_personal nuevo coste de personal
	 */
	public void setCoste_Personal(float coste_personal) {
		this.coste_personal = coste_personal;
	}

	/**
	 * Devuelve el coste total de la operación para la fecha registrada.
	 *
	 * @return coste total operativo
	 */
	public float getCoste_Total() {
		return coste_total;
	}

	/**
	 * Establece un nuevo valor para el coste total de la operación.
	 *
	 * @param coste_total nuevo coste total
	 */
	public void setCoste_Total(float coste_total) {
		this.coste_total = coste_total;
	}
}