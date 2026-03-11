package workbench;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import workbench.ConexionJoseba;


public class TablasSql {

	public DefaultTableModel SeleccionTabla(String tabla, Connection conn) {

		DefaultTableModel modelo = new DefaultTableModel();

		String querry_eleccion = "SELECT * FROM " + tabla;

		try (Statement sta = conn.createStatement(); ResultSet rs = sta.executeQuery(querry_eleccion)) {

			ResultSetMetaData meta = rs.getMetaData();
			int numCol = meta.getColumnCount();

			for (int i = 1; i <= numCol; i++) {

				modelo.addColumn(meta.getColumnName(i));
			}

			while (rs.next()) {
				Object[] row = new Object[numCol];
				for (int i = 1; i <= numCol; i++) {
					row[i - 1] = rs.getObject(i);
				}
				modelo.addRow(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelo;

	}
	
	public String[] nombreTablas(Connection conn) throws SQLException {
		List<String> tablas = new ArrayList<>();
		
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet rs = meta.getTables(null, null, "%", new String[] {"TABLE"});
		
		while (rs.next()) {
			tablas.add(rs.getString("TABLE_NAME"));
		}
		
		return tablas.toArray(new String[0]);
	}


}
