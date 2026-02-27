package workbench;

import java.sql.*;

public class ConexionJoseba {

	public void MostrarUsuarios() {

		String url = "jdbc:mysql://nas.latorreg.es:3306/futuretech_db";
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
	
	public void InsertarSector(int id_sec, int id_centro, String nombre, String tipo, int m2, float temp_media) {
		
		
		
	}
	
}