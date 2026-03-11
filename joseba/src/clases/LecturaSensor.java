package clases;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LecturaSensor {

    private int idLectura;
    private int idSensor;
    private Timestamp fechaHora;
    private double valor;
    private String unidad;

    public LecturaSensor() {}

    public LecturaSensor(int idLectura, int idSensor, Timestamp fechaHora, double valor, String unidad) {
        this.idLectura = idLectura;
        this.idSensor = idSensor;
        this.fechaHora = fechaHora;
        this.valor = valor;
        this.unidad = unidad;
    }

    public int getIdLectura() { return idLectura; }
    public void setIdLectura(int idLectura) { this.idLectura = idLectura; }
    public int getIdSensor() { return idSensor; }
    public void setIdSensor(int idSensor) { this.idSensor = idSensor; }
    public Timestamp getFechaHora() { return fechaHora; }
    public void setFechaHora(Timestamp fechaHora) { this.fechaHora = fechaHora; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }

    @Override
    public String toString() {
        return "LecturaSensor{id=" + idLectura + ", sensor=" + idSensor + ", fecha=" + fechaHora + ", valor=" + valor + " " + unidad + "}";
    }

    // ---- CRUD ----

    public static List<LecturaSensor> getAll(Connection conn) throws SQLException {
        List<LecturaSensor> lista = new ArrayList<>();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Lecturas_Sensores");
        while (rs.next()) {
            lista.add(new LecturaSensor(
                    rs.getInt("id_lectura"), rs.getInt("id_sensor"),
                    rs.getTimestamp("fecha_hora"), rs.getDouble("valor"),
                    rs.getString("unidad")
            ));
        }
        return lista;
    }

    public static LecturaSensor getById(Connection conn, int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Lecturas_Sensores WHERE id_lectura = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new LecturaSensor(
                    rs.getInt("id_lectura"), rs.getInt("id_sensor"),
                    rs.getTimestamp("fecha_hora"), rs.getDouble("valor"),
                    rs.getString("unidad")
            );
        }
        return null;
    }

    public void insert(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Lecturas_Sensores (id_sensor, fecha_hora, valor, unidad) VALUES (?,?,?,?)");
        ps.setInt(1, idSensor);
        ps.setTimestamp(2, fechaHora);
        ps.setDouble(3, valor);
        ps.setString(4, unidad);
        ps.executeUpdate();
    }

    public void update(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "UPDATE Lecturas_Sensores SET id_sensor=?, fecha_hora=?, valor=?, unidad=? WHERE id_lectura=?");
        ps.setInt(1, idSensor);
        ps.setTimestamp(2, fechaHora);
        ps.setDouble(3, valor);
        ps.setString(4, unidad);
        ps.setInt(5, idLectura);
        ps.executeUpdate();
    }

    public void delete(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM Lecturas_Sensores WHERE id_lectura = ?");
        ps.setInt(1, idLectura);
        ps.executeUpdate();
    }
}