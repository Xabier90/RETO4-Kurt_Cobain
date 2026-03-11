package interfazes;

import clases.TablasSql;

import java.sql.*;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.ConexionJoseba;
import clases.TablasSql;

import java.awt.Color;
import java.awt.Font;

/**
 * Ventana principal de la aplicación que permite visualizar y gestionar los
 * datos de cualquier tabla de la base de datos mediante una interfaz gráfica.
 * <p>
 * Proporciona funcionalidades de selección de tabla, actualización automática,
 * inserción, edición y borrado de filas, exportación a CSV y búsqueda/filtrado
 * por columna. La conexión se abre al construir la ventana y se cierra al salir.
 * </p>
 *
 * @author Filip
 * @version 1.0
 */
public class MostrarDatos extends JFrame {

	/** Identificador de serialización de la clase. */
	private static final long serialVersionUID = 1L;

	/** Panel principal que contiene todos los componentes de la ventana. */
	private JPanel contentPane;

	/** Tabla Swing que muestra los registros de la tabla seleccionada. */
	private JTable Tabla_base;

	/** ComboBox que lista los nombres de las tablas disponibles en la BD. */
	private JComboBox<String> comboTablas;

	/** Timer para el refresco automático de datos (actualmente desactivado). */
	private Timer tiempo;

	/** ComboBox para seleccionar la columna sobre la que se aplica el filtro de búsqueda. */
	private JComboBox<String> ColumnaBuscar;

	/** Modelo original sin filtrar, conservado para poder restaurar la vista completa. */
	private DefaultTableModel modelOriginal;

	/**
	 * Rutas de los iconos utilizados en los botones de la interfaz.
	 * Orden: delete, refresh, update, export, lupa.
	 */
	private String[] imagenes = { "/Recursos/delete.png", "/Recursos/refresh.png", "/Recursos/update.png",
			"/Recursos/export.png", "/Recursos/lupa.png" };

	/** Conexión activa con la base de datos, compartida por todos los métodos. */
	private Connection conn;

	/** Instancia de {@link TablasSql} que gestiona las operaciones sobre la BD. */
	private TablasSql tablasSql;

	/** Campo de texto donde el usuario introduce el texto de búsqueda. */
	private JTextField textBusqueda;

	/**
	 * Constructor de la ventana {@code MostrarDatos}.
	 * <p>
	 * Inicializa la conexión a la base de datos, construye y configura todos los
	 * componentes Swing (tabla, combos, botones, etiquetas), registra los
	 * listeners de eventos y carga la lista de tablas disponibles.
	 * </p>
	 */
	public MostrarDatos() {

		// 1. Abrir conexión al construir la ventana
		ConexionJoseba conexion = new ConexionJoseba();
		conn = conexion.ActivarConexion();
		tablasSql = new TablasSql();

		// 2. Configurar ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 3. Label
		JLabel Labelmostrar = new JLabel("Elige la tabla que quieres mostrar");
		Labelmostrar.setBounds(25, 24, 220, 32);
		contentPane.add(Labelmostrar);

		// 4. ComboBox con tablas de la BD
		comboTablas = new JComboBox<>();
		comboTablas.setBounds(250, 24, 150, 32);
		contentPane.add(comboTablas);

		// 5. Tabla
		Tabla_base = new JTable();
		Tabla_base.setRowSelectionAllowed(true);
		Tabla_base.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Tabla_base.setBounds(10, 66, 600, 150);
		contentPane.add(Tabla_base);

		// 6. Scroll
		JScrollPane scroll = new JScrollPane(Tabla_base);
		scroll.setBounds(10, 66, 600, 400);
		contentPane.add(scroll);

		// 7. Declaracion Iconos
		ImageIcon iconoDelete = new ImageIcon(getClass().getResource(imagenes[0]));
		ImageIcon iconoRefresco = new ImageIcon(getClass().getResource(imagenes[1]));
		ImageIcon iconoCambio = new ImageIcon(getClass().getResource(imagenes[2]));
		ImageIcon iconoExport = new ImageIcon(getClass().getResource(imagenes[3]));
		ImageIcon iconoBusqueda = new ImageIcon(getClass().getResource(imagenes[4]));

		// 8. Declaracion Botones
		JButton btnActualizar = new JButton(iconoRefresco);
		btnActualizar.setBounds(410, 24, 48, 32);
		contentPane.add(btnActualizar);

		JButton btnCambio = new JButton(iconoCambio);
		btnCambio.setBounds(469, 24, 48, 32);
		contentPane.add(btnCambio);

		JButton btnbasura = new JButton(iconoDelete);
		btnbasura.setBounds(527, 24, 48, 32);
		contentPane.add(btnbasura);

		JButton btnImportar = new JButton(iconoExport);
		btnImportar.setBounds(644, 393, 68, 49);
		contentPane.add(btnImportar);

		JLabel lblExportar = new JLabel("Exportar a CSV");
		lblExportar.setBounds(630, 443, 96, 49);
		contentPane.add(lblExportar);

		JButton btnInsertar = new JButton("+");
		btnInsertar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnInsertar.setBounds(583, 24, 48, 32);
		contentPane.add(btnInsertar);

		JLabel lblFiltro = new JLabel("Filto");
		lblFiltro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFiltro.setBounds(620, 68, 84, 23);
		contentPane.add(lblFiltro);

		JLabel lblSeleccion = new JLabel("Selecciona la columna");
		lblSeleccion.setBounds(620, 101, 106, 12);
		contentPane.add(lblSeleccion);

		ColumnaBuscar = new JComboBox();
		ColumnaBuscar.setBounds(620, 130, 106, 20);
		contentPane.add(ColumnaBuscar);

		JLabel lblBuscar = new JLabel("Introduce busqueda");
		lblBuscar.setBounds(620, 176, 106, 12);
		contentPane.add(lblBuscar);

		textBusqueda = new JTextField();
		textBusqueda.setBounds(620, 198, 96, 18);
		contentPane.add(textBusqueda);
		textBusqueda.setColumns(10);

		JButton btnBuscar = new JButton(iconoBusqueda);
		btnBuscar.setBounds(620, 247, 48, 32);
		contentPane.add(btnBuscar);

		// Listener: al cambiar el combo de tablas, recarga los datos
		comboTablas.addActionListener(e -> actualizacionDatos());

		// Listener: cierra la conexión al cerrar la ventana
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				conexion.CerrarConexion(conn);
			}
		});

		// Timer de refresco automático (desactivado por defecto)
		// tiempo = new Timer(2000, e -> actualizacionDatos());
		// tiempo.start();

		// Listener duplicado para actualizar combo de columnas al cambiar tabla
		/*comboTablas.addActionListener(e -> {
			actualizacionDatos();
			tablasSql.cargarColumnasEnCombo(Tabla_base, ColumnaBuscar);
		});*/

		comboTablas.addActionListener(e -> actualizacionDatos());

		// Listeners de los botones de acción
		btnActualizar.addActionListener(e -> actualizacionDatos());

		btnImportar.addActionListener(e -> {
			tablasSql.exportarCSV(Tabla_base, (String) comboTablas.getSelectedItem());
		});

		btnbasura
				.addActionListener(e -> tablasSql.borrarFila(Tabla_base, conn, (String) comboTablas.getSelectedItem()));

		btnCambio
				.addActionListener(e -> tablasSql.editarFila(Tabla_base, conn, (String) comboTablas.getSelectedItem()));

		btnInsertar.addActionListener(
				e -> tablasSql.insertarFila(Tabla_base, conn, (String) comboTablas.getSelectedItem()));

		btnBuscar.addActionListener(e -> {
			String texto = textBusqueda.getText();
			int columnaSeleccionada = ColumnaBuscar.getSelectedIndex();

			if (texto.isEmpty()) {
				Tabla_base.setModel(modelOriginal); // restaura la vista completa
				return;
			}

			tablasSql.buscarEnTabla(Tabla_base, modelOriginal, texto, columnaSeleccionada);
		});

		SwingUtilities.invokeLater(() -> cargarTablas());
	}

	/**
	 * Carga en el {@link #comboTablas} los nombres de todas las tablas disponibles
	 * en la base de datos.
	 * <p>
	 * Debe llamarse desde el hilo de eventos de Swing. Muestra un diálogo de error
	 * si no se pueden obtener los nombres de las tablas.
	 * </p>
	 */
	private void cargarTablas() {
		try {
			String[] tablas = tablasSql.nombreTablas(conn);
			for (String t : tablas) {
				comboTablas.addItem(t);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error al cargar tablas: " + e.getMessage());
		}
	}

	/**
	 * Actualiza el contenido de {@link #Tabla_base} con los datos de la tabla
	 * actualmente seleccionada en {@link #comboTablas}.
	 * <p>
	 * También guarda el modelo cargado en {@link #modelOriginal} para permitir
	 * restaurar la vista completa tras un filtrado, y actualiza el
	 * {@link #ColumnaBuscar} con las columnas de la nueva tabla.
	 * </p>
	 */
	public void actualizacionDatos() {
		String tablaSeleccionada = (String) comboTablas.getSelectedItem();
		if (tablaSeleccionada != null) {
			modelOriginal = tablasSql.SeleccionTabla(tablaSeleccionada, conn);
			Tabla_base.setModel(modelOriginal);
			tablasSql.cargarColumnasEnCombo(Tabla_base, ColumnaBuscar);
		}
	}
}