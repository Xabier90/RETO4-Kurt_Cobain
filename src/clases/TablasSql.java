package clases;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

import clases.ConexionJoseba;

public class TablasSql {

	public DefaultTableModel SeleccionTabla(String tabla, Connection conn) {

		DefaultTableModel modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

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
		ResultSet rs = meta.getTables(null, null, "%", new String[] { "TABLE" });

		while (rs.next()) {
			tablas.add(rs.getString("TABLE_NAME"));
		}

		return tablas.toArray(new String[0]);
	}

	public void exportarCSV(JTable tabla, String nombreTabla) {

		java.io.File carpeta = new java.io.File("CSV_generados");

		if (!carpeta.exists()) {
			carpeta.mkdirs();
		}

		java.io.File archivo = new java.io.File(carpeta, nombreTabla + ".csv");

		try (java.io.PrintWriter pw = new java.io.PrintWriter(archivo)) {

			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
			int numCols = modelo.getColumnCount();
			int numFilas = modelo.getRowCount();

			for (int i = 0; i < numCols; i++) {
				pw.print(modelo.getColumnName(i));
				if (i < numCols - 1)
					pw.print(",");
			}
			pw.println();

			for (int fila = 0; fila < numFilas; fila++) {
				for (int col = 0; col < numCols; col++) {
					pw.print(modelo.getValueAt(fila, col));
					if (col < numCols - 1)
						pw.print(",");
				}
				pw.println();
			}

			JOptionPane.showMessageDialog(null, "CSV guardado en: " + archivo.getAbsolutePath());

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al exportar: " + ex.getMessage());
		}
	}

	// BORRAR - elimina la fila seleccionada en la tabla
	public void borrarFila(JTable tabla, Connection conn, String nombreTabla) {
		int filaSeleccionada = tabla.getSelectedRow();

		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Selecciona una fila para borrar.");
			return;
		}

		int confirmar = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres borrar esta fila?", "Confirmar",
				JOptionPane.YES_NO_OPTION);

		if (confirmar == JOptionPane.YES_OPTION) {
			// Coge el valor de la primera columna como ID
			Object id = tabla.getValueAt(filaSeleccionada, 0);
			String colId = tabla.getColumnName(0);

			String sql = "DELETE FROM " + nombreTabla + " WHERE " + colId + " = ?";

			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setObject(1, id);
				ps.executeUpdate();

				// Quita la fila del modelo visual
				((DefaultTableModel) tabla.getModel()).removeRow(filaSeleccionada);
				JOptionPane.showMessageDialog(null, "Fila borrada correctamente.");

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error al borrar: " + e.getMessage());
			}
		}
	}

	// EDITAR - edita la celda seleccionada
	public void editarFila(JTable tabla, Connection conn, String nombreTabla) {
		int filaSeleccionada = tabla.getSelectedRow();

		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Selecciona una fila para editar.");
			return;
		}

		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		int numCols = modelo.getColumnCount();

		Object id = tabla.getValueAt(filaSeleccionada, 0);
		String colId = tabla.getColumnName(0);

		// Pide el nuevo valor de cada columna excepto la primera (primary key)
		Object[] nuevosValores = new Object[numCols];
		nuevosValores[0] = id; // el ID no cambia

		for (int i = 1; i < numCols; i++) {
			String nombreColumna = tabla.getColumnName(i);
			Object valorActual = tabla.getValueAt(filaSeleccionada, i);

			String nuevoValor = JOptionPane.showInputDialog(null, "Editar " + nombreColumna + ":", valorActual);

			if (nuevoValor == null)
				return; // si cancela en cualquier campo, para todo

			nuevosValores[i] = nuevoValor;
		}

		// Construye el UPDATE con todas las columnas menos la primera
		StringBuilder sql = new StringBuilder("UPDATE " + nombreTabla + " SET ");
		for (int i = 1; i < numCols; i++) {
			sql.append(tabla.getColumnName(i)).append(" = ?");
			if (i < numCols - 1)
				sql.append(", ");
		}
		sql.append(" WHERE ").append(colId).append(" = ?");

		try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {
			for (int i = 1; i < numCols; i++) {
				ps.setString(i, (String) nuevosValores[i]);
			}
			ps.setObject(numCols, id); // el ID va al final del WHERE
			ps.executeUpdate();

			// Actualiza toda la fila en el modelo visual
			for (int i = 1; i < numCols; i++) {
				modelo.setValueAt(nuevosValores[i], filaSeleccionada, i);
			}
			JOptionPane.showMessageDialog(null, "Fila actualizada correctamente.");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al editar: " + e.getMessage());
		}
	}

	// INSERTAR - añade una fila nueva pidiendo cada campo
	public void insertarFila(JTable tabla, Connection conn, String nombreTabla) {
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		int numCols = modelo.getColumnCount();

		Object[] nuevaFila = new Object[numCols];
		StringBuilder columnas = new StringBuilder();
		StringBuilder valores = new StringBuilder();

		// Empieza en 1 saltando la primera columna (primary key)
		for (int i = 1; i < numCols; i++) {
			String nombreCol = modelo.getColumnName(i);
			String valor = JOptionPane.showInputDialog(null, "Introduce valor para: " + nombreCol);

			if (valor == null)
				return; // cancelado

			nuevaFila[i] = valor;
			columnas.append(nombreCol);
			valores.append("?");
			if (i < numCols - 1) {
				columnas.append(", ");
				valores.append(", ");
			}
		}

		String sql = "INSERT INTO " + nombreTabla + " (" + columnas + ") VALUES (" + valores + ")";

		try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			for (int i = 1; i < numCols; i++) {
				ps.setString(i, (String) nuevaFila[i]);
			}
			ps.executeUpdate();

			// Recupera el ID que ha generado la BD
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				nuevaFila[0] = rs.getObject(1); // mete el ID generado en la primera columna
			}

			modelo.addRow(nuevaFila);
			JOptionPane.showMessageDialog(null, "Fila insertada correctamente.");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al insertar: " + e.getMessage());
		}
	}

	public void buscarEnTabla(JTable tabla, DefaultTableModel modelOriginal, String textoBusqueda, int columna) {
		int numFilas = modelOriginal.getRowCount();
		int numCols = modelOriginal.getColumnCount();

		DefaultTableModel modeloFiltrado = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		for (int i = 0; i < numCols; i++) {
			modeloFiltrado.addColumn(modelOriginal.getColumnName(i));
		}

		for (int fila = 0; fila < numFilas; fila++) {
			Object valor = modelOriginal.getValueAt(fila, columna);
			if (valor != null && valor.toString().toLowerCase().contains(textoBusqueda.toLowerCase())) {
				Object[] row = new Object[numCols];
				for (int col = 0; col < numCols; col++) {
					row[col] = modelOriginal.getValueAt(fila, col);
				}
				modeloFiltrado.addRow(row);
			}
		}

		tabla.setModel(modeloFiltrado);
	}

	public void cargarColumnasEnCombo(JTable tabla, JComboBox<String> combo) {
		combo.removeAllItems();
		for (int i = 0; i < tabla.getColumnCount(); i++) {
			combo.addItem(tabla.getColumnName(i));
		}
	}
}
