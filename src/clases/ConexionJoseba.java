package clases;

import java.sql.*;

public class ConexionJoseba {

	private String url = "jdbc:mysql://nas.latorreg.es:3306/futuretech_db";
	private String user = "filip";
	private String pass = "1234";

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

	public void CerrarConexion(Connection conn) {

		try {
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

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