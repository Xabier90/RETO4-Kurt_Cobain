package modulo;

import java.time.LocalDate;

/**
 * Representa un centro de datos con su información técnica y de ubicación.
 * <p>
 * Permite gestionar los detalles básicos de una infraestructura de servidores,
 * incluyendo su localización geográfica, fecha de apertura, superficie total y
 * número de servidores disponibles.
 * </p>
 *
 * @author Xabier Iglesias
 * @version 1.0
 * @since 1.0
 */
public class Centro_de_Datos {

	/**
	 * Identificador único del centro de datos. Se autoincrementa en base de datos.
	 */
	private int id_centro;

	/** Nombre descriptivo del centro de datos. */
	private String nombre;

	/** Dirección física donde se encuentra el centro de datos. */
	private String ubicacion;

	/** País en el que está ubicado el centro de datos. */
	private String pais;

	/** Fecha de inauguración o apertura del centro de datos. */
	private LocalDate fecha_apertura;

	/** Superficie total del centro de datos expresada en metros cuadrados. */
	private int superficie_m2;

	/** Número total de servidores instalados en el centro de datos. */
	private int num_servidores;

	/**
	 * Construye un nuevo {@code Centro_de_Datos} con todos sus atributos.
	 *
	 * @param id_centro      identificador único del centro (autoincremental)
	 * @param nombre         nombre descriptivo del centro de datos
	 * @param ubicacion      dirección física del centro de datos
	 * @param pais           país donde se ubica el centro de datos
	 * @param fecha_apertura fecha de inauguración del centro de datos
	 * @param superficie_m2  superficie total en metros cuadrados
	 * @param num_servidores número de servidores instalados
	 */
	public Centro_de_Datos(int id_centro, String nombre, String ubicacion, String pais, LocalDate fecha_apertura,
			int superficie_m2, int num_servidores) {
		this.id_centro = id_centro;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.pais = pais;
		this.fecha_apertura = fecha_apertura;
		this.superficie_m2 = superficie_m2;
		this.num_servidores = num_servidores;
	}

	/**
	 * Devuelve el nombre del centro de datos.
	 *
	 * @return nombre del centro de datos
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece un nuevo nombre para el centro de datos.
	 *
	 * @param nombre nuevo nombre del centro de datos
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve la dirección física del centro de datos.
	 *
	 * @return dirección del centro de datos
	 */
	public String getUbicacion() {
		return ubicacion;
	}

	/**
	 * Establece una nueva dirección física para el centro de datos.
	 *
	 * @param ubicacion nueva dirección del centro de datos
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * Devuelve el país donde se encuentra el centro de datos.
	 *
	 * @return país del centro de datos
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Establece el país donde se encuentra el centro de datos.
	 *
	 * @param pais nuevo país del centro de datos
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * Devuelve la fecha de inauguración del centro de datos.
	 *
	 * @return fecha de apertura del centro de datos
	 */
	public LocalDate getFecha_apertura() {
		return fecha_apertura;
	}

	/**
	 * Establece la fecha de inauguración del centro de datos.
	 *
	 * @param fecha_apertura nueva fecha de apertura del centro de datos
	 */
	public void setFecha_apertura(LocalDate fecha_apertura) {
		this.fecha_apertura = fecha_apertura;
	}

	/**
	 * Devuelve la superficie total del centro de datos en metros cuadrados.
	 *
	 * @return superficie del centro de datos en m²
	 */
	public int getSuperficie_m2() {
		return superficie_m2;
	}

	/**
	 * Establece la superficie total del centro de datos en metros cuadrados.
	 *
	 * @param superficie_m2 nueva superficie del centro de datos en m²
	 */
	public void setSuperficie_m2(int superficie_m2) {
		this.superficie_m2 = superficie_m2;
	}

	/**
	 * Devuelve el número total de servidores instalados en el centro de datos.
	 *
	 * @return número de servidores del centro de datos
	 */
	public int getNum_servidores() {
		return num_servidores;
	}

	/**
	 * Establece el número total de servidores del centro de datos.
	 *
	 * @param num_servidores nuevo número de servidores del centro de datos
	 */
	public void setNum_servidores(int num_servidores) {
		this.num_servidores = num_servidores;
	}
}