package modulo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa la lectura registrada por un sensor en un instante concreto.
 * <p>
 * Almacena el valor medido por el sensor junto con su unidad de medida y la
 * marca temporal del registro. Incluye operaciones CRUD para gestionar los
 * registros de la tabla {@code Lecturas_Sensores} de la base de datos.
 * </p>
 *
 * @author Joseba
 * @version 1.0
 * @since 1.0
 */
public class LecturaSensor {

	/** Identificador único de la lectura. */
	private int idLectura;

	/** Identificador del sensor que generó la lectura. */
	private int idSensor;

	/** Marca temporal con la fecha y hora exacta de la lectura. */
	private Timestamp fechaHora;

	/** Valor numérico registrado por el sensor. */
	private double valor;

	/** Unidad de medida del valor registrado (p. ej., "°C", "kW", "ppm"). */
	private String unidad;

	/**
	 * Construye una instancia de {@code LecturaSensor} sin inicializar atributos.
	 * Necesario para frameworks que requieren un constructor por defecto.
	 */
	public LecturaSensor() {
	}

	/**
	 * Construye una nueva instancia de {@code LecturaSensor} con todos sus
	 * atributos.
	 *
	 * @param idLectura identificador único de la lectura
	 * @param idSensor  identificador del sensor que generó la lectura
	 * @param fechaHora marca temporal de la lectura
	 * @param valor     valor numérico registrado
	 * @param unidad    unidad de medida del valor registrado
	 */
	public LecturaSensor(int idLectura, int idSensor, Timestamp fechaHora, double valor, String unidad) {
		this.idLectura = idLectura;
		this.idSensor = idSensor;
		this.fechaHora = fechaHora;
		this.valor = valor;
		this.unidad = unidad;
	}

	/**
	 * Devuelve el identificador único de la lectura.
	 *
	 * @return identificador de la lectura
	 */
	public int getIdLectura() {
		return idLectura;
	}

	/**
	 * Establece el identificador único de la lectura.
	 *
	 * @param idLectura nuevo identificador de la lectura
	 */
	public void setIdLectura(int idLectura) {
		this.idLectura = idLectura;
	}

	/**
	 * Devuelve el identificador del sensor que generó la lectura.
	 *
	 * @return identificador del sensor
	 */
	public int getIdSensor() {
		return idSensor;
	}

	/**
	 * Establece el identificador del sensor asociado a la lectura.
	 *
	 * @param idSensor nuevo identificador del sensor
	 */
	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}

	/**
	 * Devuelve la marca temporal con la fecha y hora de la lectura.
	 *
	 * @return fecha y hora de la lectura
	 */
	public Timestamp getFechaHora() {
		return fechaHora;
	}

	/**
	 * Establece una nueva marca temporal para la lectura.
	 *
	 * @param fechaHora nueva fecha y hora de la lectura
	 */
	public void setFechaHora(Timestamp fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * Devuelve el valor numérico registrado por el sensor.
	 *
	 * @return valor de la lectura
	 */
	public double getValor() {
		return valor;
	}

	/**
	 * Establece el valor numérico de la lectura.
	 *
	 * @param valor nuevo valor de la lectura
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}

	/**
	 * Devuelve la unidad de medida del valor registrado.
	 *
	 * @return unidad de medida
	 */
	public String getUnidad() {
		return unidad;
	}

	/**
	 * Establece la unidad de medida del valor registrado.
	 *
	 * @param unidad nueva unidad de medida
	 */
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	/**
	 * Devuelve una representación textual de la lectura del sensor.
	 *
	 * @return cadena con el identificador, sensor, fecha, valor y unidad de la
	 *         lectura
	 */
	@Override
	public String toString() {
		return "LecturaSensor{id=" + idLectura + ", sensor=" + idSensor + ", fecha=" + fechaHora + ", valor=" + valor
				+ " " + unidad + "}";
	}

	// ---- CRUD ----

	/**
	 * Recupera todos los registros de lecturas de sensores de la base de datos.
	 *
	 * @param conn conexión activa a la base de datos
	 * @return lista con todos los objetos {@code LecturaSensor} almacenados
	 * @throws SQLException si ocurre un error al acceder a la base de datos
	 */
	public static List<LecturaSensor> getAll(Connection conn) throws SQLException {
		List<LecturaSensor> lista = new ArrayList<>();
		ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Lecturas_Sensores");
		while (rs.next()) {
			lista.add(new LecturaSensor(rs.getInt("id_lectura"), rs.getInt("id_sensor"), rs.getTimestamp("fecha_hora"),
					rs.getDouble("valor"), rs.getString("unidad")));
		}
		return lista;
	}

	/**
	 * Recupera una lectura de sensor por su identificador único.
	 *
	 * @param conn conexión activa a la base de datos
	 * @param id   identificador de la lectura a recuperar
	 * @return el objeto {@code LecturaSensor} correspondiente, o {@code null} si no
	 *         existe
	 * @throws SQLException si ocurre un error al acceder a la base de datos
	 */
	public static LecturaSensor getById(Connection conn, int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Lecturas_Sensores WHERE id_lectura = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return new LecturaSensor(rs.getInt("id_lectura"), rs.getInt("id_sensor"), rs.getTimestamp("fecha_hora"),
					rs.getDouble("valor"), rs.getString("unidad"));
		}
		return null;
	}

	/**
	 * Inserta el registro actual de la lectura en la base de datos.
	 *
	 * @param conn conexión activa a la base de datos
	 * @throws SQLException si ocurre un error al ejecutar la inserción
	 */
	public void insert(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO Lecturas_Sensores (id_sensor, fecha_hora, valor, unidad) VALUES (?,?,?,?)");
		ps.setInt(1, idSensor);
		ps.setTimestamp(2, fechaHora);
		ps.setDouble(3, valor);
		ps.setString(4, unidad);
		ps.executeUpdate();
	}

	/**
	 * Actualiza el registro de la lectura en la base de datos con los valores
	 * actuales del objeto.
	 *
	 * @param conn conexión activa a la base de datos
	 * @throws SQLException si ocurre un error al ejecutar la actualización
	 */
	public void update(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(
				"UPDATE Lecturas_Sensores SET id_sensor=?, fecha_hora=?, valor=?, unidad=? WHERE id_lectura=?");
		ps.setInt(1, idSensor);
		ps.setTimestamp(2, fechaHora);
		ps.setDouble(3, valor);
		ps.setString(4, unidad);
		ps.setInt(5, idLectura);
		ps.executeUpdate();
	}

	/**
	 * Elimina el registro de la lectura de la base de datos según su identificador.
	 *
	 * @param conn conexión activa a la base de datos
	 * @throws SQLException si ocurre un error al ejecutar la eliminación
	 */
	public void delete(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("DELETE FROM Lecturas_Sensores WHERE id_lectura = ?");
		ps.setInt(1, idLectura);
		ps.executeUpdate();
	}
}