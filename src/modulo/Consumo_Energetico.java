package modulo;

import java.time.LocalDate;

/**
 * Representa el consumo energético registrado en un sector en una fecha
 * concreta.
 * <p>
 * Almacena los datos de consumo energético de un sector, incluyendo la cantidad
 * de energía consumida en kilovatios hora, el coste asociado en euros y la
 * fuente de energía utilizada. Permite consultar y modificar estos datos
 * mediante métodos getters y setters.
 * </p>
 *
 * @author Xabier Iglesias
 * @version 1.0
 * @since 1.0
 */
public class Consumo_Energetico {

	/** Identificador único del registro de consumo energético. */
	private int id_consumo;

	/** Identificador del sector donde se produce el consumo energético. */
	private int id_sector;

	/** Fecha en la que se registró el consumo energético. */
	private LocalDate fecha;

	/** Cantidad de energía consumida expresada en kilovatios hora (kWh). */
	private float kwh_consumidos;

	/** Coste económico del consumo energético expresado en euros. */
	private float coste_eur;

	/** Fuente de energía utilizada (p. ej., solar, eólica, red eléctrica). */
	private String fuente_energia;

	/**
	 * Construye un nuevo {@code Consumo_Energetico} con todos sus atributos.
	 *
	 * @param id_consumo     identificador único del registro de consumo
	 * @param id_sector      identificador del sector donde se produce el consumo
	 * @param fecha          fecha del registro del consumo energético
	 * @param kwh_consumidos cantidad de energía consumida en kWh
	 * @param coste_eur      coste del consumo expresado en euros
	 * @param fuente_energia fuente de energía utilizada
	 */
	public Consumo_Energetico(int id_consumo, int id_sector, LocalDate fecha, float kwh_consumidos, float coste_eur,
			String fuente_energia) {
		this.id_consumo = id_consumo;
		this.id_sector = id_sector;
		this.fecha = fecha;
		this.kwh_consumidos = kwh_consumidos;
		this.coste_eur = coste_eur;
		this.fuente_energia = fuente_energia;
	}

	/**
	 * Devuelve la fecha en la que se registró el consumo energético.
	 *
	 * @return fecha del registro de consumo
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Establece una nueva fecha para el registro de consumo energético.
	 *
	 * @param fecha nueva fecha del consumo energético
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/**
	 * Devuelve la cantidad de energía consumida en kilovatios hora (kWh).
	 *
	 * @return energía consumida en kWh
	 */
	public float getKwh_Consumidos() {
		return kwh_consumidos;
	}

	/**
	 * Establece una nueva cantidad de energía consumida en kilovatios hora.
	 *
	 * @param kwh_consumidos nueva cantidad de energía consumida en kWh
	 */
	public void setKwh_Consumidos(float kwh_consumidos) {
		this.kwh_consumidos = kwh_consumidos;
	}

	/**
	 * Devuelve el coste económico del consumo energético en euros.
	 *
	 * @return coste del consumo en euros
	 */
	public float getCoste_Eur() {
		return coste_eur;
	}

	/**
	 * Establece un nuevo coste económico para el consumo energético.
	 *
	 * @param coste_eur nuevo coste del consumo en euros
	 */
	public void setCoste_Eur(float coste_eur) {
		this.coste_eur = coste_eur;
	}

	/**
	 * Devuelve la fuente de energía utilizada en el consumo registrado.
	 *
	 * @return fuente de energía del consumo
	 */
	public String getFuente_Energia() {
		return fuente_energia;
	}

	/**
	 * Establece una nueva fuente de energía para el consumo registrado.
	 *
	 * @param fuente_energia nueva fuente de energía
	 */
	public void setFuente_Energia(String fuente_energia) {
		this.fuente_energia = fuente_energia;
	}
}