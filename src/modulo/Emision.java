package modulo;

import java.time.LocalDate;

/**
 * Representa una emisión de gases registrada en el sistema de sostenibilidad.
 * <p>
 * Almacena la información asociada a una emisión concreta, incluyendo el sector
 * al que pertenece, la fecha de registro, la cantidad de CO₂ emitida en
 * kilogramos, otros gases contaminantes y la certificación verde obtenida.
 * </p>
 *
 * @author Aaron
 * @version 1.0
 * @since 1.0
 */
public class Emision {

	/** Identificador único de la emisión. */
	private int idEmision;

	/** Identificador del sector al que pertenece la emisión. */
	private int idSector;

	/** Fecha en la que se registró la emisión. */
	private LocalDate fecha;

	/** Cantidad de CO₂ emitida, expresada en kilogramos. */
	private double co2Kg;

	/** Descripción de otros gases contaminantes emitidos además del CO₂. */
	private String otrosGases;

	/** Certificación verde asociada a la emisión, si la hubiera. */
	private String certificacionVerde;

	/**
	 * Construye una nueva instancia de {@code Emision} con todos sus atributos.
	 *
	 * @param idEmision          identificador único de la emisión
	 * @param idSector           identificador del sector emisor
	 * @param fecha              fecha de registro de la emisión
	 * @param co2Kg              cantidad de CO₂ emitida en kilogramos
	 * @param otrosGases         descripción de otros gases emitidos
	 * @param certificacionVerde certificación verde asociada, o {@code null} si no
	 *                           aplica
	 */
	public Emision(int idEmision, int idSector, LocalDate fecha, double co2Kg, String otrosGases,
			String certificacionVerde) {
		this.idEmision = idEmision;
		this.idSector = idSector;
		this.fecha = fecha;
		this.co2Kg = co2Kg;
		this.otrosGases = otrosGases;
		this.certificacionVerde = certificacionVerde;
	}

	/**
	 * Devuelve el identificador único de la emisión.
	 *
	 * @return identificador de la emisión
	 */
	public int getIdEmision() {
		return idEmision;
	}

	/**
	 * Devuelve el identificador del sector asociado a esta emisión.
	 *
	 * @return identificador del sector
	 */
	public int getIdSector() {
		return idSector;
	}

	/**
	 * Devuelve la fecha en que se registró la emisión.
	 *
	 * @return fecha de la emisión
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Devuelve la cantidad de CO₂ emitida en kilogramos.
	 *
	 * @return CO₂ emitido en kg
	 */
	public double getCo2Kg() {
		return co2Kg;
	}

	/**
	 * Devuelve la descripción de otros gases contaminantes emitidos.
	 *
	 * @return descripción de otros gases, o {@code null} si no se registraron
	 */
	public String getOtrosGases() {
		return otrosGases;
	}

	/**
	 * Devuelve la certificación verde asociada a esta emisión.
	 *
	 * @return nombre de la certificación verde, o {@code null} si no aplica
	 */
	public String getCertificacionVerde() {
		return certificacionVerde;
	}
}