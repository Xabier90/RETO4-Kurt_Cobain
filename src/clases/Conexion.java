/*


Esta parte eran pruebas para probar la conexion al principio, ignorar esto 


package workbench;

import java.sql.*;

public class Conexion {

	public static void main(String[] args) throws Exception {
		
		String url = "jdbc:mysql://localhost:3307/BaseDataCenter";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(url, "root", "root123");
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery("SELECT * FROM clientes");
			
			while(result.next()) {
				
				String code = result.getString("id_cliente");
				String nombre = result.getString("nombre_cliente");
				
				System.out.println("id_cliente: " + code + " | nombre: " +nombre);
				
			}
			
			
			
			
			result.close();
			statement.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
**/