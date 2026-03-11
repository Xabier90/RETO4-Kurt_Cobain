package interfazes;

import workbench.ConexionJoseba;
import workbench.TablasSql;

import java.sql.*;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class MostrarDatos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable Tabla_base;
    private JComboBox<String> comboTablas;
    private Timer tiempo;
    private JScrollPane scroll;

    private Connection conn;       // conexión guardada como atributo
    private TablasSql tablasSql;   // instancia de tu clase

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MostrarDatos frame = new MostrarDatos();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MostrarDatos() {
        // 1. Abrir conexión al construir la ventana
        ConexionJoseba conexion = new ConexionJoseba();
        conn = conexion.ActivarConexion();
        tablasSql = new TablasSql();

        // 2. Configurar ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
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
        cargarTablas(); // rellena el combo desde la BD

        // 5. Tabla
        Tabla_base = new JTable();
        Tabla_base.setBounds(10, 66, 600, 150);
        contentPane.add(Tabla_base);
        
        // 6. Scroll
        JScrollPane scroll = new JScrollPane(Tabla_base);
        scroll.setBounds(10, 66, 600, 400);
        contentPane.add(scroll);

        // 7. Cuando cambia el combo, actualiza la tabla
        comboTablas.addActionListener(e -> actualizacionDatos());

        // 8. Cerrar conexión al cerrar la ventana
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                conexion.CerrarConexion(conn);
            }
        });

        // 9. Timer de refresco automático
        tiempo = new Timer(2000, e -> actualizacionDatos());
        tiempo.start();
    }

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

    public void actualizacionDatos() {
        String tablaSeleccionada = (String) comboTablas.getSelectedItem();
        if (tablaSeleccionada != null) {
            Tabla_base.setModel(tablasSql.SeleccionTabla(tablaSeleccionada, conn));
            
        }
    }
    
    
}