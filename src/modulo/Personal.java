package modulo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un miembro del personal vinculado a un centro del sistema.
 * <p>
 * Almacena la información laboral de cada empleado, incluyendo el centro al que
 * pertenece, su puesto, salario, fecha de contratación y tipo de contrato.
 * Incluye operaciones CRUD para gestionar los registros de la tabla
 * {@code Personal} de la base de datos.
 * </p>
 *
 * @author Joseba
 * @version 1.0
 * @since 1.0
 */
public class Personal {

	/** Identificador único del miembro del personal. */
	private int idPersonal;

	/** Identificador del centro al que está asignado el empleado. */
	private int idCentro;

	/** Nombre completo del empleado. */
	private String nombre;

	/** Puesto o cargo que ocupa el empleado en el centro. */
	private String puesto;

	/** Salario bruto del empleado. */
	private double salario;

	/** Fecha en la que se formalizó el contrato del empleado. */
	private Date fechaContrato;

	/** Tipo de contrato del empleado (p. ej., indefinido, temporal, etc.). */
	private String tipoContrato;

	/**
	 * Construye una instancia de {@code Personal} sin inicializar atributos.
	 * Necesario para frameworks que requieren un constructor por defecto.
	 */
	public Personal() {
	}

	/**
	 * Construye una nueva instancia de {@code Personal} con todos sus atributos.
	 *
	 * @param idPersonal    identificador único del empleado
	 * @param idCentro      identificador del centro al que pertenece
	 * @param nombre        nombre completo del empleado
	 * @param puesto        puesto o cargo del empleado
	 * @param salario       salario bruto del empleado
	 * @param fechaContrato fecha de formalización del contrato
	 * @param tipoContrato  tipo de contrato del empleado
	 */
	public Personal(int idPersonal, int idCentro, String nombre, String puesto, double salario, Date fechaContrato,
			String tipoContrato) {
		this.idPersonal = idPersonal;
		this.idCentro = idCentro;
		this.nombre = nombre;
		this.puesto = puesto;
		this.salario = salario;
		this.fechaContrato = fechaContrato;
		this.tipoContrato = tipoContrato;
	}

	/**
	 * Devuelve el identificador único del empleado.
	 *
	 * @return identificador del empleado
	 */
	public int getIdPersonal() {
		return idPersonal;
	}

	/**
	 * Establece el identificador único del empleado.
	 *
	 * @param idPersonal nuevo identificador del empleado
	 */
	public void setIdPersonal(int idPersonal) {
		this.idPersonal = idPersonal;
	}

	/**
	 * Devuelve el identificador del centro al que está asignado el empleado.
	 *
	 * @return identificador del centro
	 */
	public int getIdCentro() {
		return idCentro;
	}

	/**
	 * Establece el identificador del centro al que pertenece el empleado.
	 *
	 * @param idCentro nuevo identificador del centro
	 */
	public void setIdCentro(int idCentro) {
		this.idCentro = idCentro;
	}

	/**
	 * Devuelve el nombre completo del empleado.
	 *
	 * @return nombre del empleado
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre completo del empleado.
	 *
	 * @param nombre nuevo nombre del empleado
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el puesto o cargo del empleado en el centro.
	 *
	 * @return puesto del empleado
	 */
	public String getPuesto() {
		return puesto;
	}

	/**
	 * Establece el puesto o cargo del empleado.
	 *
	 * @param puesto nuevo puesto del empleado
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	/**
	 * Devuelve el salario bruto del empleado.
	 *
	 * @return salario del empleado
	 */
	public double getSalario() {
		return salario;
	}

	/**
	 * Establece el salario bruto del empleado.
	 *
	 * @param salario nuevo salario del empleado
	 */
	public void setSalario(double salario) {
		this.salario = salario;
	}

	/**
	 * Devuelve la fecha en la que se formalizó el contrato del empleado.
	 *
	 * @return fecha de contratación
	 */
	public Date getFechaContrato() {
		return fechaContrato;
	}

	/**
	 * Establece la fecha de formalización del contrato del empleado.
	 *
	 * @param fechaContrato nueva fecha de contratación
	 */
	public void setFechaContrato(Date fechaContrato) {
		this.fechaContrato = fechaContrato;
	}

	/**
	 * Devuelve el tipo de contrato del empleado.
	 *
	 * @return tipo de contrato
	 */
	public String getTipoContrato() {
		return tipoContrato;
	}

	/**
	 * Establece el tipo de contrato del empleado.
	 *
	 * @param tipoContrato nuevo tipo de contrato
	 */
	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	/**
	 * Devuelve una representación textual del empleado.
	 *
	 * @return cadena con el identificador, nombre, puesto y salario del empleado
	 */
	@Override
	public String toString() {
		return "Personal{id=" + idPersonal + ", nombre=" + nombre + ", puesto=" + puesto + ", salario=" + salario + "}";
	}

	// ---- CRUD ----

	/**
	 * Recupera todos los registros de personal de la base de datos.
	 *
	 * @param conn conexión activa a la base de datos
	 * @return lista con todos los objetos {@code Personal} almacenados
	 * @throws SQLException si ocurre un error al acceder a la base de datos
	 */
	public static List<Personal> getAll(Connection conn) throws SQLException {
		List<Personal> lista = new ArrayList<>();
		ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Personal");
		while (rs.next()) {
			lista.add(new Personal(rs.getInt("id_personal"), rs.getInt("id_centro"), rs.getString("nombre"),
					rs.getString("puesto"), rs.getDouble("salario"), rs.getDate("fecha_contrato"),
					rs.getString("tipo_contrato")));
		}
		return lista;
	}

	/**
	 * Recupera un empleado por su identificador único.
	 *
	 * @param conn conexión activa a la base de datos
	 * @param id   identificador del empleado a recuperar
	 * @return el objeto {@code Personal} correspondiente, o {@code null} si no
	 *         existe
	 * @throws SQLException si ocurre un error al acceder a la base de datos
	 */
	public static Personal getById(Connection conn, int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Personal WHERE id_personal = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return new Personal(rs.getInt("id_personal"), rs.getInt("id_centro"), rs.getString("nombre"),
					rs.getString("puesto"), rs.getDouble("salario"), rs.getDate("fecha_contrato"),
					rs.getString("tipo_contrato"));
		}
		return null;
	}

	/**
	 * Inserta el registro del empleado actual en la base de datos.
	 *
	 * @param conn conexión activa a la base de datos
	 * @throws SQLException si ocurre un error al ejecutar la inserción
	 */
	public void insert(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO Personal (id_centro, nombre, puesto, salario, fecha_contrato, tipo_contrato) VALUES (?,?,?,?,?,?)");
		ps.setInt(1, idCentro);
		ps.setString(2, nombre);
		ps.setString(3, puesto);
		ps.setDouble(4, salario);
		ps.setDate(5, fechaContrato);
		ps.setString(6, tipoContrato);
		ps.executeUpdate();
	}

	/**
	 * Actualiza el registro del empleado en la base de datos con los valores
	 * actuales del objeto.
	 *
	 * @param conn conexión activa a la base de datos
	 * @throws SQLException si ocurre un error al ejecutar la actualización
	 */
	public void update(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(
				"UPDATE Personal SET id_centro=?, nombre=?, puesto=?, salario=?, fecha_contrato=?, tipo_contrato=? WHERE id_personal=?");
		ps.setInt(1, idCentro);
		ps.setString(2, nombre);
		ps.setString(3, puesto);
		ps.setDouble(4, salario);
		ps.setDate(5, fechaContrato);
		ps.setString(6, tipoContrato);
		ps.setInt(7, idPersonal);
		ps.executeUpdate();
	}

	/**
	 * Elimina el registro del empleado de la base de datos según su identificador.
	 *
	 * @param conn conexión activa a la base de datos
	 * @throws SQLException si ocurre un error al ejecutar la eliminación
	 */
	public void delete(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("DELETE FROM Personal WHERE id_personal = ?");
		ps.setInt(1, idPersonal);
		ps.executeUpdate();
	}
}