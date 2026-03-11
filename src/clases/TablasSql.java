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

/**
 * Clase que proporciona operaciones CRUD y utilidades sobre tablas de una base
 * de datos MySQL, integrando los resultados con componentes Swing.
 * <p>
 * Incluye métodos para seleccionar, insertar, editar, borrar y exportar datos,
 * así como funcionalidades de búsqueda y filtrado sobre {@link JTable}.
 * </p>
 *
 * @author Filip
 * @version 1.0
 */
public class TablasSql {

	/**
	 * Carga todos los registros de la tabla indicada y los devuelve como un
	 * {@link DefaultTableModel} no editable, listo para asignarse a un
	 * {@link JTable}.
	 * <p>
	 * Las columnas del modelo se generan dinámicamente a partir de los metadatos
	 * del {@link ResultSet}.
	 * </p>
	 *
	 * @param tabla nombre de la tabla SQL que se desea consultar.
	 * @param conn  conexión activa con la base de datos.
	 * @return un {@link DefaultTableModel} con los datos de la tabla, o un modelo
	 *         vacío si ocurre algún error.
	 */
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

	/**
	 * Obtiene los nombres de todas las tablas disponibles en la base de datos.
	 *
	 * @param conn conexión activa con la base de datos.
	 * @return array de {@link String} con los nombres de las tablas encontradas.
	 * @throws SQLException si ocurre un error al acceder a los metadatos de la BD.
	 */
	public String[] nombreTablas(Connection conn) throws SQLException {
		List<String> tablas = new ArrayList<>();

		DatabaseMetaData meta = conn.getMetaData();
		ResultSet rs = meta.getTables(null, null, "%", new String[] { "TABLE" });

		while (rs.next()) {
			tablas.add(rs.getString("TABLE_NAME"));
		}

		return tablas.toArray(new String[0]);
	}

	/**
	 * Exporta el contenido actual de un {@link JTable} a un archivo CSV.
	 * <p>
	 * El archivo se guarda en la carpeta {@code CSV_Guardados} (creada
	 * automáticamente si no existe), con el nombre de la tabla como nombre de
	 * archivo. Muestra un diálogo informando la ruta final o el error.
	 * </p>
	 *
	 * @param tabla       el componente {@link JTable} cuyo contenido se exportará.
	 * @param nombreTabla nombre que se usará para el archivo CSV generado.
	 */
	public void exportarCSV(JTable tabla, String nombreTabla) {

		java.io.File carpeta = new java.io.File("CSV_Guardados");

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

	/**
	 * Elimina de la base de datos la fila actualmente seleccionada en el
	 * {@link JTable}.
	 * <p>
	 * Usa el valor de la primera columna como clave primaria para construir el
	 * {@code DELETE}. Solicita confirmación al usuario antes de proceder. Si no hay
	 * fila seleccionada, muestra un aviso. Actualiza el modelo visual eliminando la
	 * fila si la operación es exitosa.
	 * </p>
	 *
	 * @param tabla       el componente {@link JTable} con la fila a eliminar.
	 * @param conn        conexión activa con la base de datos.
	 * @param nombreTabla nombre de la tabla SQL sobre la que se ejecutará el
	 *                    {@code DELETE}.
	 */
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

	/**
	 * Permite editar todos los campos de la fila seleccionada en el {@link JTable}
	 * mediante diálogos de entrada, y actualiza el registro correspondiente en la
	 * BD.
	 * <p>
	 * La primera columna se trata como clave primaria y no se modifica. Si el
	 * usuario cancela cualquier campo, la operación se cancela por completo. Al
	 * terminar, actualiza el modelo visual con los nuevos valores.
	 * </p>
	 *
	 * @param tabla       el componente {@link JTable} con la fila a editar.
	 * @param conn        conexión activa con la base de datos.
	 * @param nombreTabla nombre de la tabla SQL sobre la que se ejecutará el
	 *                    {@code UPDATE}.
	 */
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

	/**
	 * Inserta una nueva fila en la tabla SQL solicitando al usuario cada valor
	 * mediante diálogos de entrada.
	 * <p>
	 * La primera columna (clave primaria) se omite en la entrada, ya que se asume
	 * que la BD la genera automáticamente. Tras el {@code INSERT}, recupera el ID
	 * generado y añade la fila completa al modelo visual. Si el usuario cancela
	 * cualquier campo, la operación se cancela.
	 * </p>
	 *
	 * @param tabla       el componente {@link JTable} al que se añadirá la nueva
	 *                    fila.
	 * @param conn        conexión activa con la base de datos.
	 * @param nombreTabla nombre de la tabla SQL sobre la que se ejecutará el
	 *                    {@code INSERT}.
	 */
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

	/**
	 * Filtra las filas visibles del {@link JTable} según un texto de búsqueda
	 * aplicado a una columna específica.
	 * <p>
	 * Crea un nuevo {@link DefaultTableModel} con solo las filas que contienen el
	 * texto buscado (sin distinción de mayúsculas/minúsculas) y lo asigna al
	 * {@link JTable}. El modelo original no se modifica, lo que permite restaurarlo
	 * posteriormente.
	 * </p>
	 *
	 * @param tabla         el componente {@link JTable} en el que se mostrará el
	 *                      filtro.
	 * @param modelOriginal el modelo completo sin filtrar, usado como fuente de
	 *                      datos.
	 * @param textoBusqueda texto a buscar dentro de la columna indicada.
	 * @param columna       índice de la columna sobre la que se aplica el filtro.
	 */
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

	/**
	 * Carga los nombres de todas las columnas del {@link JTable} en un
	 * {@link JComboBox}, reemplazando cualquier contenido previo.
	 * <p>
	 * Útil para actualizar el selector de columnas tras cambiar la tabla mostrada.
	 * </p>
	 *
	 * @param tabla el {@link JTable} del que se leerán los nombres de columna.
	 * @param combo el {@link JComboBox} que se actualizará con dichos nombres.
	 */
	public void cargarColumnasEnCombo(JTable tabla, JComboBox<String> combo) {
		combo.removeAllItems();
		for (int i = 0; i < tabla.getColumnCount(); i++) {
			combo.addItem(tabla.getColumnName(i));
		}
	}
}