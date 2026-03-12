package modulo;

import java.time.LocalDate;

/**
 * Representa una acción formativa realizada en un centro del sistema.
 * <p>
 * Recoge los datos de un curso de formación, incluyendo el centro donde se
 * imparte, la duración en horas, la fecha de realización, la certificación
 * obtenida y el proveedor que lo ofrece.
 * </p>
 *
 * @author Aaron
 * @version 1.0
 * @since 1.0
 */
public class Formacion {

	/** Identificador único del registro de formación. */
	private int idFormacion;

	/** Identificador del centro en el que se imparte la formación. */
	private int idCentro;

	/** Nombre o título del curso de formación. */
	private String curso;

	/** Duración total del curso expresada en horas. */
	private int horas;

	/** Fecha en la que se realizó o inició la formación. */
	private LocalDate fecha;

	/** Certificación obtenida al completar la formación, si la hubiera. */
	private String certificacion;

	/** Nombre del proveedor o entidad que imparte la formación. */
	private String proveedor;

	/**
	 * Construye una nueva instancia de {@code Formacion} con todos sus atributos.
	 *
	 * @param idFormacion   identificador único del registro de formación
	 * @param idCentro      identificador del centro donde se realiza la formación
	 * @param curso         nombre o título del curso
	 * @param horas         duración del curso en horas
	 * @param fecha         fecha de realización o inicio de la formación
	 * @param certificacion certificación obtenida al completar el curso, o
	 *                      {@code null} si no aplica
	 * @param proveedor     nombre del proveedor que imparte la formación
	 */
	public Formacion(int idFormacion, int idCentro, String curso, int horas, LocalDate fecha, String certificacion,
			String proveedor) {
		this.idFormacion = idFormacion;
		this.idCentro = idCentro;
		this.curso = curso;
		this.horas = horas;
		this.fecha = fecha;
		this.certificacion = certificacion;
		this.proveedor = proveedor;
	}

	/**
	 * Devuelve el identificador único del registro de formación.
	 *
	 * @return identificador de la formación
	 */
	public int getIdFormacion() {
		return idFormacion;
	}

	/**
	 * Devuelve el identificador del centro donde se imparte la formación.
	 *
	 * @return identificador del centro
	 */
	public int getIdCentro() {
		return idCentro;
	}

	/**
	 * Devuelve el nombre o título del curso de formación.
	 *
	 * @return nombre del curso
	 */
	public String getCurso() {
		return curso;
	}

	/**
	 * Devuelve la duración total del curso expresada en horas.
	 *
	 * @return número de horas del curso
	 */
	public int getHoras() {
		return horas;
	}

	/**
	 * Devuelve la fecha de realización o inicio de la formación.
	 *
	 * @return fecha de la formación
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Devuelve la certificación obtenida al completar la formación.
	 *
	 * @return nombre de la certificación, o {@code null} si no se obtuvo ninguna
	 */
	public String getCertificacion() {
		return certificacion;
	}

	/**
	 * Devuelve el nombre del proveedor que imparte la formación.
	 *
	 * @return nombre del proveedor
	 */
	public String getProveedor() {
		return proveedor;
	}
}