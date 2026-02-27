import javax.swing.*;
import java.sql.*;
import java.awt.Color;


public class Acceso_a_GreenData {
	
	  // Configura aquí tus datos de MySQL
	/* Definimos aqui el enlace a la base de datos, y el usuario y contraseña que hace que conecte con la base de datos.
	 * Todo esto hace posible que podamos trabajar con la base de datos.
	 */
    private static final String URL = "jdbc:mysql://nas.latorreg.es:3306/futuretech_db";
    private static final String USER = "xabi";
    private static final String PASS = "1234";
    /* Definimos la variable que nos servirá para acotar los intentos de entrar al programa de monitoreo*/
    private static int   intentos = 0;
    
    
    /*El método para confirmar el acceso a GreenData*/
    private static boolean validarEnMySQL(String nombre, String codigo) {
        // Ajusta 'usuarios', 'nombre_usuario' y 'clave' a tus nombres reales en la tabla
        String consulta = "SELECT * FROM usuarios WHERE username = ? AND passwd = ?";
        
        /*  La estructura try (...) asegura que la conexión (conn) y el objeto ps se cierren automáticamente al terminar,
         *  evitando fugas de memoria.
         */
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
        		// Se usa ps para preparar la consulta de forma segura
             PreparedStatement ps = conn.prepareStatement(consulta)) {
            /* ps.setString(1, nombre) y ps.setString(2, codigo) asignan valores a los signos de interrogación ? 
             * con lo introducido por el usuario.
             */
            ps.setString(1, nombre);
            ps.setString(2, codigo);
            /*ps.executeQuery() lanza la consulta a la base de datos y guarda los datos devueltos en el ResultSet.*/
            ResultSet rs = ps.executeQuery();
            return rs.next(); // return rs.next() devuelve true si la base de datos encontró al menos un registro que coincida, y false si no hay coincidencias.
            
            /*: El bloque catch captura cualquier fallo de conexión o sintaxis SQL, 
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
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Uso de JPanel con posicionamiento de todo el espacio del JFrame 
        JPanel panel = new JPanel();
        panel.setLayout(null); 
        frame.add(panel);

        // Etiqueta Usuario
        JLabel LabelUsuario = new JLabel("Usuario:");
        LabelUsuario.setBounds(40, 45, 100, 40);
        panel.add(LabelUsuario);
        
        // Espacio para escribir el nombre del Usuario
        JTextField usuarioField = new JTextField();
        usuarioField.setBounds(140, 50, 150, 30);
        panel.add(usuarioField);

        // Etiqueta Contraseña
        JLabel LabelContraseña = new JLabel("Contraseña:");
        LabelContraseña.setBounds(40, 120, 100, 40);
        panel.add(LabelContraseña);

        // JPasswordField para ocultar los caracteres de la contraseña mientras se escriben
        JPasswordField contraseñaField = new JPasswordField();
        contraseñaField.setBounds(140, 125, 150, 30);
        panel.add(contraseñaField);

        // Etiqueta para mostrar un comentario en la interfaz
        JLabel infoLabel = new JLabel("Introduce tu usuario y contraseña para tener acceso a GreenData!");
        infoLabel.setBounds(10, 250, 450, 40);
        panel.add(infoLabel);

        // Botón de acción
        JButton botonAcceso = new JButton("Enviar Datos");
        botonAcceso.setBounds(125, 200, 150, 40); 
        panel.add(botonAcceso);

        // Lógica de Acceso con MySQL 
        /*Añade un ActionListener al botón.
         *Este bloque de código se ejecuta cada vez que el usuario hace clic en el botón.
         *e es el evento que se genera al pulsar. Esto es muy parecido a JavaScript
         */
        botonAcceso.addActionListener(e -> {
        	/*Obtiene el texto que el usuario escribió en la cuadricula (fieldtext) de usuario.*/
            String personaAutorizada = usuarioField.getText();
            /*Se obtiene la contraseña escrita y se convierte a String para poder compararla en la base de datos.*/
            String contraseñaAcceso = new String(contraseñaField.getPassword());
            
			/*Hace una consulta en la base de datos y comprueba si el usuario y contraseña existe en la base de datos,
			 *  si no existe, manda un false, y si existe un true. 
			 *  Por eso creamos una variable booleana para guardar el resultado de la consulta
			 */
            boolean accesoValido = validarEnMySQL(personaAutorizada, contraseñaAcceso);
            
            /* Si el usuario y contraseña son correctos, cambia el texto informativo tanto en contenido como en color "verde".*/
            if (accesoValido) {
                infoLabel.setText("¡Acceso Concedido!");
                infoLabel.setForeground(Color.GREEN);
                // Aqui se puede añadir la funcionalidad de enviarnos al programa de monitoreo.
                 
            } 
            /* Si el usuario o contraseña son incorrectas, la variable intentos se incrementa o suma 1 y
             *  te sale un mensaje de color rojo, diciendo los intentos que te quedan para poder acceder al programa de monitoreo
             *  (para meter el usuario y contraseña correctos).
             */
            else {
                intentos++;
                infoLabel.setText("Usuario o contraseña incorrectos. Intento " + intentos + " de 3");
                infoLabel.setForeground(Color.RED);

                /* La condición de que introduzcas mal o el nombre de usuario o la contraseña hará que se desactiva el botón. 
                 * El usuario ya no puede volver a intentar acceder y le aparecerá un mensaje dejando claro que se ha bloqueado el acceso.
                 */
                if (intentos >= 3) {
                    botonAcceso.setEnabled(false);
                    infoLabel.setText("Has superado el número máximo de intentos.");
                }
            }
        });
        

        /* Llamamos a la interfaz haciendo visible la ventana*/
        frame.setVisible(true);
    }
}
