package clases;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Refrigeracion {

    private int idRefrigeracion;
    private int idSector;
    private Date fecha;
    private double temperaturaMedia;
    private String sistema;
    private double eficienciaPorcentaje;

    public Refrigeracion() {}

    public Refrigeracion(int idRefrigeracion, int idSector, Date fecha,
                         double temperaturaMedia, String sistema, double eficienciaPorcentaje) {
        this.idRefrigeracion = idRefrigeracion;
        this.idSector = idSector;
        this.fecha = fecha;
        this.temperaturaMedia = temperaturaMedia;
        this.sistema = sistema;
        this.eficienciaPorcentaje = eficienciaPorcentaje;
    }

    public int getIdRefrigeracion() { return idRefrigeracion; }
    public void setIdRefrigeracion(int idRefrigeracion) { this.idRefrigeracion = idRefrigeracion; }
    public int getIdSector() { return idSector; }
    public void setIdSector(int idSector) { this.idSector = idSector; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public double getTemperaturaMedia() { return temperaturaMedia; }
    public void setTemperaturaMedia(double temperaturaMedia) { this.temperaturaMedia = temperaturaMedia; }
    public String getSistema() { return sistema; }
    public void setSistema(String sistema) { this.sistema = sistema; }
    public double getEficienciaPorcentaje() { return eficienciaPorcentaje; }
    public void setEficienciaPorcentaje(double eficienciaPorcentaje) { this.eficienciaPorcentaje = eficienciaPorcentaje; }

    @Override
    public String toString() {
        return "Refrigeracion{id=" + idRefrigeracion + ", sector=" + idSector + ", temp=" + temperaturaMedia + ", eficiencia=" + eficienciaPorcentaje + "%}";
    }

    // ---- CRUD ----

    public static List<Refrigeracion> getAll(Connection conn) throws SQLException {
        List<Refrigeracion> lista = new ArrayList<>();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Refrigeracion");
        while (rs.next()) {
            lista.add(new Refrigeracion(
                    rs.getInt("id_refrigeracion"), rs.getInt("id_sector"),
                    rs.getDate("fecha"), rs.getDouble("temperatura_media"),
                    rs.getString("sistema"), rs.getDouble("eficiencia_porcentaje")
            ));
        }
        return lista;
    }

    public static Refrigeracion getById(Connection conn, int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Refrigeracion WHERE id_refrigeracion = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Refrigeracion(
                    rs.getInt("id_refrigeracion"), rs.getInt("id_sector"),
                    rs.getDate("fecha"), rs.getDouble("temperatura_media"),
                    rs.getString("sistema"), rs.getDouble("eficiencia_porcentaje")
            );
        }
        return null;
    }

    public void insert(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Refrigeracion (id_sector, fecha, temperatura_media, sistema, eficiencia_porcentaje) VALUES (?,?,?,?,?)");
        ps.setInt(1, idSector);
        ps.setDate(2, fecha);
        ps.setDouble(3, temperaturaMedia);
        ps.setString(4, sistema);
        ps.setDouble(5, eficienciaPorcentaje);
        ps.executeUpdate();
    }

    public void update(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "UPDATE Refrigeracion SET id_sector=?, fecha=?, temperatura_media=?, sistema=?, eficiencia_porcentaje=? WHERE id_refrigeracion=?");
        ps.setInt(1, idSector);
        ps.setDate(2, fecha);
        ps.setDouble(3, temperaturaMedia);
        ps.setString(4, sistema);
        ps.setDouble(5, eficienciaPorcentaje);
        ps.setInt(6, idRefrigeracion);
        ps.executeUpdate();
    }

    public void delete(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM Refrigeracion WHERE id_refrigeracion = ?");
        ps.setInt(1, idRefrigeracion);
        ps.executeUpdate();
    }
}