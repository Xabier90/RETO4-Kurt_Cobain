package modulo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un registro del sistema de refrigeración de un sector.
 * <p>
 * Almacena la información sobre el funcionamiento del sistema de refrigeración
 * en una fecha concreta, incluyendo la temperatura media registrada, el tipo de
 * sistema utilizado y su porcentaje de eficiencia. Incluye operaciones CRUD
 * para gestionar los registros de la tabla {@code Refrigeracion} de la base de
 * datos.
 * </p>
 *
 * @author Joseba
 * @version 1.0
 * @since 1.0
 */
public class Refrigeracion {

	/** Identificador único del registro de refrigeración. */
	private int idRefrigeracion;

	/** Identificador del sector al que pertenece el registro. */
	private int idSector;

	/** Fecha en la que se tomó el registro de refrigeración. */
	private Date fecha;

	/**
	 * Temperatura media registrada durante el período, expresada en grados Celsius.
	 */
	private double temperaturaMedia;

	/** Nombre o descripción del sistema de refrigeración utilizado. */
	private String sistema;

	/**
	 * Porcentaje de eficiencia del sistema de refrigeración (valor entre 0.0 y
	 * 100.0).
	 */
	private double eficienciaPorcentaje;

	/**
	 * Construye una instancia de {@code Refrigeracion} sin inicializar atributos.
	 * Necesario para frameworks que requieren un constructor por defecto.
	 */
	public Refrigeracion() {
	}

	/**
	 * Construye una nueva instancia de {@code Refrigeracion} con todos sus
	 * atributos.
	 *
	 * @param idRefrigeracion      identificador único del registro
	 * @param idSector             identificador del sector asociado
	 * @param fecha                fecha del registro de refrigeración
	 * @param temperaturaMedia     temperatura media registrada en grados Celsius
	 * @param sistema              nombre o descripción del sistema de refrigeración
	 * @param eficienciaPorcentaje porcentaje de eficiencia del sistema
	 */
	public Refrigeracion(int idRefrigeracion, int idSector, Date fecha, double temperaturaMedia, String sistema,
			double eficienciaPorcentaje) {
		this.idRefrigeracion = idRefrigeracion;
		this.idSector = idSector;
		this.fecha = fecha;
		this.temperaturaMedia = temperaturaMedia;
		this.sistema = sistema;
		this.eficienciaPorcentaje = eficienciaPorcentaje;
	}

	/**
	 * Devuelve el identificador único del registro de refrigeración.
	 *
	 * @return identificador del registro
	 */
	public int getIdRefrigeracion() {
		return idRefrigeracion;
	}

	/**
	 * Establece el identificador único del registro de refrigeración.
	 *
	 * @param idRefrigeracion nuevo identificador del registro
	 */
	public void setIdRefrigeracion(int idRefrigeracion) {
		this.idRefrigeracion = idRefrigeracion;
	}

	/**
	 * Devuelve el identificador del sector al que pertenece el registro.
	 *
	 * @return identificador del sector
	 */
	public int getIdSector() {
		return idSector;
	}

	/**
	 * Establece el identificador del sector asociado al registro.
	 *
	 * @param idSector nuevo identificador del sector
	 */
	public void setIdSector(int idSector) {
		this.idSector = idSector;
	}

	/**
	 * Devuelve la fecha en la que se tomó el registro de refrigeración.
	 *
	 * @return fecha del registro
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Establece una nueva fecha para el registro de refrigeración.
	 *
	 * @param fecha nueva fecha del registro
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Devuelve la temperatura media registrada durante el período.
	 *
	 * @return temperatura media en grados Celsius
	 */
	public double getTemperaturaMedia() {
		return temperaturaMedia;
	}

	/**
	 * Establece la temperatura media del registro.
	 *
	 * @param temperaturaMedia nueva temperatura media en grados Celsius
	 */
	public void setTemperaturaMedia(double temperaturaMedia) {
		this.temperaturaMedia = temperaturaMedia;
	}

	/**
	 * Devuelve el nombre o descripción del sistema de refrigeración utilizado.
	 *
	 * @return nombre del sistema de refrigeración
	 */
	public String getSistema() {
		return sistema;
	}

	/**
	 * Establece el nombre o descripción del sistema de refrigeración.
	 *
	 * @param sistema nuevo nombre del sistema de refrigeración
	 */
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	/**
	 * Devuelve el porcentaje de eficiencia del sistema de refrigeración.
	 *
	 * @return eficiencia del sistema en porcentaje (entre 0.0 y 100.0)
	 */
	public double getEficienciaPorcentaje() {
		return eficienciaPorcentaje;
	}

	/**
	 * Establece el porcentaje de eficiencia del sistema de refrigeración.
	 *
	 * @param eficienciaPorcentaje nuevo porcentaje de eficiencia
	 */
	public void setEficienciaPorcentaje(double eficienciaPorcentaje) {
		this.eficienciaPorcentaje = eficienciaPorcentaje;
	}

	/**
	 * Devuelve una representación textual del registro de refrigeración.
	 *
	 * @return cadena con el identificador, sector, temperatura media y eficiencia
	 *         del registro
	 */
	@Override
	public String toString() {
		return "Refrigeracion{id=" + idRefrigeracion + ", sector=" + idSector + ", temp=" + temperaturaMedia
				+ ", eficiencia=" + eficienciaPorcentaje + "%}";
	}

	// ---- CRUD ----

	/**
	 * Recupera todos los registros de refrigeración de la base de datos.
	 *
	 * @param conn conexión activa a la base de datos
	 * @return lista con todos los objetos {@code Refrigeracion} almacenados
	 * @throws SQLException si ocurre un error al acceder a la base de datos
	 */
	public static List<Refrigeracion> getAll(Connection conn) throws SQLException {
		List<Refrigeracion> lista = new ArrayList<>();
		ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Refrigeracion");
		while (rs.next()) {
			lista.add(new Refrigeracion(rs.getInt("id_refrigeracion"), rs.getInt("id_sector"), rs.getDate("fecha"),
					rs.getDouble("temperatura_media"), rs.getString("sistema"), rs.getDouble("eficiencia_porcentaje")));
		}
		return lista;
	}

	/**
	 * Recupera un registro de refrigeración por su identificador único.
	 *
	 * @param conn conexión activa a la base de datos
	 * @param id   identificador del registro a recuperar
	 * @return el objeto {@code Refrigeracion} correspondiente, o {@code null} si no
	 *         existe
	 * @throws SQLException si ocurre un error al acceder a la base de datos
	 */
	public static Refrigeracion getById(Connection conn, int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Refrigeracion WHERE id_refrigeracion = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return new Refrigeracion(rs.getInt("id_refrigeracion"), rs.getInt("id_sector"), rs.getDate("fecha"),
					rs.getDouble("temperatura_media"), rs.getString("sistema"), rs.getDouble("eficiencia_porcentaje"));
		}
		return null;
	}

	/**
	 * Inserta el registro de refrigeración actual en la base de datos.
	 *
	 * @param conn conexión activa a la base de datos
	 * @throws SQLException si ocurre un error al ejecutar la inserción
	 */
	public void insert(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO Refrigeracion (id_sector, fecha, temperatura_media, sistema, eficiencia_porcentaje) VALUES (?,?,?,?,?)");
		ps.setInt(1, idSector);
		ps.setDate(2, fecha);
		ps.setDouble(3, temperaturaMedia);
		ps.setString(4, sistema);
		ps.setDouble(5, eficienciaPorcentaje);
		ps.executeUpdate();
	}

	/**
	 * Actualiza el registro de refrigeración en la base de datos con los valores
	 * actuales del objeto.
	 *
	 * @param conn conexión activa a la base de datos
	 * @throws SQLException si ocurre un error al ejecutar la actualización
	 */
	public void update(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(
				"UPDATE Refrigeracion SET id_sector=?, fecha=?, temperatura_media=?, sistema=?, eficiencia_porcentaje=? WHERE id_refrigeracion=?");
		ps.setInt(1, idSector);
		ps.setDate(2, fecha);
		ps.setDouble(3, temperaturaMedia);
		ps.setString(4, sistema);
		ps.setDouble(5, eficienciaPorcentaje);
		ps.setInt(6, idRefrigeracion);
		ps.executeUpdate();
	}

	/**
	 * Elimina el registro de refrigeración de la base de datos según su
	 * identificador.
	 *
	 * @param conn conexión activa a la base de datos
	 * @throws SQLException si ocurre un error al ejecutar la eliminación
	 */
	public void delete(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("DELETE FROM Refrigeracion WHERE id_refrigeracion = ?");
		ps.setInt(1, idRefrigeracion);
		ps.executeUpdate();
	}
}