import clases.ConexionDB;
import clases.Personal;
import clases.LecturaSensor;
import clases.Refrigeracion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            Connection conn = ConexionDB.getConnection();
            System.out.println("✅ Conexión establecida correctamente\n");

            // ============================
            //        PERSONAL
            // ============================
            System.out.println("========== PERSONAL - TODOS ==========");
            List<Personal> listaPersonal = Personal.getAll(conn);
            for (Personal p : listaPersonal) {
                System.out.println(p);
            }

            System.out.println("\n========== PERSONAL - BUSCAR POR ID ==========");
            Personal personalById = Personal.getById(conn, 1);
            if (personalById != null) {
                System.out.println(personalById);
            } else {
                System.out.println("No se encontró ningún registro con ese ID.");
            }

            // ============================
            //      LECTURAS SENSORES
            // ============================
            System.out.println("\n========== LECTURAS SENSORES - TODOS ==========");
            List<LecturaSensor> listaLecturas = LecturaSensor.getAll(conn);
            for (LecturaSensor l : listaLecturas) {
                System.out.println(l);
            }

            System.out.println("\n========== LECTURAS SENSORES - BUSCAR POR ID ==========");
            LecturaSensor lecturaById = LecturaSensor.getById(conn, 1);
            if (lecturaById != null) {
                System.out.println(lecturaById);
            } else {
                System.out.println("No se encontró ningún registro con ese ID.");
            }

            // ============================
            //       REFRIGERACION
            // ============================
            System.out.println("\n========== REFRIGERACION - TODOS ==========");
            List<Refrigeracion> listaRefrigeracion = Refrigeracion.getAll(conn);
            for (Refrigeracion r : listaRefrigeracion) {
                System.out.println(r);
            }

            System.out.println("\n========== REFRIGERACION - BUSCAR POR ID ==========");
            Refrigeracion refrigeracionById = Refrigeracion.getById(conn, 1);
            if (refrigeracionById != null) {
                System.out.println(refrigeracionById);
            } else {
                System.out.println("No se encontró ningún registro con ese ID.");
            }

            conn.close();

        } catch (SQLException e) {
            System.out.println("❌ Error de conexión o consulta: " + e.getMessage());
            e.printStackTrace();
        }
    }
}