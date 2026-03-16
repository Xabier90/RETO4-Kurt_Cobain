package clases;

import java.sql.*;

/**
 * Clase encargada de gestionar la conexión con la base de datos MySQL.
 * <p>
 * Proporciona métodos para abrir y cerrar conexiones, así como para ejecutar
 * consultas de prueba sobre la base de datos remota.
 * </p>
 *
 * @author Filip
 * @version 1.0
 */
public class ConexionJoseba {

	/** URL de conexión JDBC apuntando al servidor MySQL remoto. */
	private String url = "jdbc:mysql://localhost:3306/futuretech_db";

	/** Nombre de usuario para autenticarse en la base de datos. */
	private String user = "filip";

	/** Contraseña para autenticarse en la base de datos. */
	private String pass = "1234";

	/**
	 * Abre y devuelve una conexión activa con la base de datos.
	 * <p>
	 * Utiliza las credenciales definidas como atributos de la clase. En caso de
	 * error, imprime la excepción por consola y devuelve {@code null}.
	 * </p>
	 *
	 * @return un objeto {@link Connection} si la conexión se establece
	 *         correctamente, o {@code null} si ocurre algún error.
	 */
	public Connection ActivarConexion() {

		try {

			// Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection(url, user, pass);

			return conn;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	/**
	 * Cierra la conexión con la base de datos proporcionada.
	 * <p>
	 * Si ocurre un error al cerrar, se imprime el stack trace por consola.
	 * </p>
	 *
	 * @param conn el objeto {@link Connection} que se desea cerrar.
	 */
	public void CerrarConexion(Connection conn) {

		try {
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/**
	 * Realiza una consulta de prueba a la tabla {@code Emisiones} y muestra por
	 * consola los identificadores obtenidos.
	 * <p>
	 * Este método carga explícitamente el driver de MySQL, abre una conexión
	 * independiente y ejecuta un {@code SELECT} sobre la columna
	 * {@code id_emision}. Útil para verificar que la conexión y la tabla funcionan
	 * correctamente.
	 * </p>
	 * <p>
	 * En caso de error, imprime la excepción por consola.
	 * </p>
	 */
	public void MostrarUsuarios() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection(url, "filip", "1234");

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery("SELECT id_emision FROM Emisiones");

			while (result.next()) {

				int code = result.getInt("id_emision");

				System.out.println("id_cliente: " + code);

			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
