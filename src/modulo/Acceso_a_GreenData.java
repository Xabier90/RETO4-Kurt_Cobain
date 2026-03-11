package modulo;

import javax.swing.*;
import java.sql.*;
import java.awt.Color;

/**
 * Clase que gestiona la interfaz de acceso (Login) al sistema GreenData.
 * Conecta con una base de datos MySQL para validar el acceso según los usuarios y contraseñas que tenemos 
 * en la base de datos.
 * @author Xabier Iglesias
 * @version 2.0
 */

public class Acceso_a_GreenData {
	
	  // Configura aquí tus datos de MySQL
	/** Definimos aqui el enlace a la base de datos, y el usuario y contraseña que hace que conecte con la base de datos.
	 * Todo esto hace posible que podamos trabajar con la base de datos.
	 */
    private static final String URL = "jdbc:mysql://nas.latorreg.es:3306/futuretech_db";
    private static final String USER = "xabi";
    private static final String PASS = "1234";
    /** Definimos la variable que nos servirá para acotar los intentos de entrar al programa de monitoreo*/
    private static int   intentos = 0;
    /** Decimos cuales son los puntos que queremos cambiar de idioma*/           
    private static JLabel LabelUsuario, LabelContraseña, infoLabel;
    private static JButton botonAcceso;
    /** Metodo para cambiar de idioma del Castellano al Euskara. Usamos una variable booleana para,
     * cambiar de idioma ESP == true en este caso los mensajes se verán en castellano y si es false en Euskara.*/
 // Modificamos el método para que sepa qué texto poner según el contexto
    private static void cambiarIdioma(boolean ESP) {
        if (ESP) {
            LabelUsuario.setText("Usuario: ");
            LabelContraseña.setText("Contraseña: ");
            botonAcceso.setText("Enviar Datos");
            
            // CORRECCIÓN: Si ya hubo intentos, mantenemos el mensaje de error en castellano
            if (intentos == 0) {
                infoLabel.setText("Introduce tu usuario y contraseña para tener acceso a GreenData!");
            } else if (intentos >= 3) {
                infoLabel.setText("Has superado el número máximo de intentos.");
            } else {
                infoLabel.setText("Usuario o contraseña incorrectos. Intento " + intentos + " de 3");
            }
        } else {
            LabelUsuario.setText("Erabiltzailea: ");
            LabelContraseña.setText("Pasahitza: ");
            botonAcceso.setText("Datuak Bidali");
            
            // CORRECCIÓN: Si ya hubo intentos, mantenemos el mensaje de error en euskara
            if (intentos == 0) {
                infoLabel.setText("Sar itzazu erabiltzailea eta pasahitza GreenDatara sartu al izateko !");
            } else if (intentos >= 3) {
                infoLabel.setText("Dagoeneko saiakera guztiak erabili dituzu.");
            } else {
                infoLabel.setText("Erabiltzailea edo pasahitza okerrak. " + intentos + ". saiakera (3tik)");
            }
        }
    }
    
    /**El método para confirmar el acceso a GreenData
     * Consulta la base de datos para verificar si los datos metidos por el usuario
     * (Usuario y Contraseña) son válidas.
     * @param nombre El nombre de usuario introducido.
     * @param codigo La contraseña introducida.
     * @return true si el usuario existe y la contraseña coincide, false en caso contrario.*/
    private static boolean validarEnMySQL(String nombre, String codigo) {
        // Ajusta 'usuarios', 'username' y 'clave', 'passwd' a tus nombres reales en la tabla
        String consulta = "SELECT * FROM usuarios WHERE username = ? AND passwd = ?";
        
        /**  La estructura try (...) asegura que la conexión (conn) y el objeto ps se cierren automáticamente al terminar,
         *  evitando fugas de memoria.
         */
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
        		// Se usa ps para preparar la consulta de forma segura
             PreparedStatement ps = conn.prepareStatement(consulta)) {
            /** ps.setString(1, nombre) y ps.setString(2, codigo) asignan valores a los signos de interrogación ? 
             * con lo introducido por el usuario.
             */
            ps.setString(1, nombre);
            ps.setString(2, codigo);
            /*ps.executeQuery() lanza la consulta a la base de datos y guarda los datos devueltos en el ResultSet.*/
            ResultSet rs = ps.executeQuery();
            return rs.next(); // return rs.next() devuelve true si la base de datos encontró al menos un registro que coincida, y false si no hay coincidencias.
            
            /** El bloque catch captura cualquier fallo de conexión o sintaxis SQL, 
             * imprime el error en consola y retorna false para que el programa no se detenga. 
             */
        } catch (SQLException ex) {
            System.out.println("Error de BD: " + ex.getMessage());
            return false;
        }
    }
	
    public static void main(String[] args) {
    	   
        // Configuración de la ventana principal
        JFrame frame = new JFrame("Acceso a GreenData");
        frame.setSize(425, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Uso de JPanel con posicionamiento de todo el espacio del JFrame 
        JPanel panel = new JPanel();
        panel.setLayout(null); 
        frame.add(panel);
        
        //Posición en la interfaz del selector para cambio de idioma
        // Este punto lo he tenido que buscar porque no me salía con los apuntes que teniamos
        // Y además me daba error en los label "JLabel LabelUsuario = new JLabel("Usuario:");"
        // y lo he dejado como me ha dicho la IA "LabelUsuario = new JLabel("Usuario:");" quitando JLabel
        // porque  ya la hemos creado antes.
        JRadioButton rbESP = new JRadioButton ("ESP", true);
        JRadioButton rbEUS = new JRadioButton ("EUS");
        rbESP.setBounds(275,20,50,20); 
        rbEUS.setBounds(325,20,50,20);
        
        ButtonGroup grupoIdioma = new ButtonGroup();
        grupoIdioma.add(rbESP);
        grupoIdioma.add(rbEUS);
        
        // Evento listener para el selector de idiomas: cambia los textos base al hacer clic
        rbESP.addActionListener(e -> cambiarIdioma(true));
        rbEUS.addActionListener(e -> cambiarIdioma(false));
        
        // Añadir el selector de idiomas al panel para que se vea en la interfaz
         panel.add(rbESP);
         panel.add(rbEUS);

        // Etiqueta Usuario
        LabelUsuario = new JLabel("Usuario:");
        LabelUsuario.setBounds(40, 65, 100, 40);
        panel.add(LabelUsuario);
        
        // Espacio para escribir el nombre del Usuario
        JTextField usuarioField = new JTextField();
        usuarioField.setBounds(140, 70, 150, 30);
        panel.add(usuarioField);

        // Etiqueta Contraseña
        LabelContraseña = new JLabel("Contraseña:");
        LabelContraseña.setBounds(40, 120, 100, 40);
        panel.add(LabelContraseña);

        // JPasswordField para ocultar los caracteres de la contraseña mientras se escriben
        JPasswordField contraseñaField = new JPasswordField();
        contraseñaField.setBounds(140, 125, 150, 30);
        panel.add(contraseñaField);

        // Etiqueta para mostrar un comentario en la interfaz
        infoLabel = new JLabel("Introduce tu usuario y contraseña para tener acceso a GreenData!");
        infoLabel.setBounds(15, 250, 450, 40);
        panel.add(infoLabel);

        // Botón de acción
        botonAcceso = new JButton("Enviar Datos");
        botonAcceso.setBounds(125, 200, 150, 40); 
        panel.add(botonAcceso);
        
        // Lógica de Acceso con MySQL 
        /**Añade un ActionListener al botón.
         *Este bloque de código se ejecuta cada vez que el usuario hace clic en el botón.
         *e es el evento que se genera al pulsar. Esto es muy parecido a JavaScript
         */
        botonAcceso.addActionListener(e -> {
        	/**Obtiene el texto que el usuario escribió en la cuadricula (fieldtext) de usuario.*/
            String personaAutorizada = usuarioField.getText();
            /**Se obtiene la contraseña escrita y se convierte a String para poder compararla en la base de datos.*/
            String contraseñaAcceso = new String(contraseñaField.getPassword());
            
			/**Hace una consulta en la base de datos y comprueba si el usuario y contraseña existe en la base de datos,
			 *  si no existe, manda un false, y si existe un true. 
			 *  Por eso creamos una variable booleana para guardar el resultado de la consulta
			 */
            boolean accesoValido = validarEnMySQL(personaAutorizada, contraseñaAcceso);
            
            /** Si el usuario y contraseña son correctos, cambia el texto informativo tanto en contenido como en color "verde".*/
            if (accesoValido) {
                infoLabel.setForeground(Color.GREEN);
                
                // Verificamos el idioma seleccionado para el mensaje de éxito
                if (rbESP.isSelected()) {
                    infoLabel.setText("¡Acceso Concedido!");
                } else {
                    infoLabel.setText("Sarrera Onartua!"); // Mensaje en euskera
                }
                
                // Aqui se puede añadir la funcionalidad de enviarnos al programa de monitoreo.
                // Abrir la nueva ventana
              //  new GreenData(); 

                // Cerrar la ventana de Login
                frame.dispose();
            } 
            /** Si el usuario o contraseña son incorrectas, la variable intentos se incrementa o suma 1 y
             *  te sale un mensaje de color rojo, diciendo los intentos que te quedan para poder acceder al programa de monitoreo
             *  (para meter el usuario y contraseña correctos).
             */
            else {
                intentos++;
                infoLabel.setForeground(Color.RED);

                /** La condición de que introduzcas mal o el nombre de usuario o la contraseña hará que se desactiva el botón. 
                 * El usuario ya no puede volver a intentar acceder y le aparecerá un mensaje dejando claro que se ha bloqueado el acceso.
                 */
             // Verificamos si está seleccionado el idioma español
                if (rbESP.isSelected()) {
                	// Mensaje en castellano
                	if (intentos >= 3) {
                		botonAcceso.setEnabled(false);
                		infoLabel.setText("Has superado el número máximo de intentos.");
                	}
                	else {
                        infoLabel.setText("Usuario o contraseña incorrectos. Intento " + intentos + " de 3");
                    }
                } 
                else {
                    // Mensajes en Euskera
                    if (intentos >= 3) {
                        botonAcceso.setEnabled(false);
                        infoLabel.setText("Dagoeneko saiakera guztiak erabili dituzu.");
                    } 
                    else {
                        infoLabel.setText("Erabiltzailea edo pasahitza okerrak. " + intentos + ". saiakera (3tik)");
                    }
                }
               
             }
                        
        });
        

        /** Llamamos a la interfaz haciendo visible la ventana*/
        frame.setVisible(true);
    }
}
