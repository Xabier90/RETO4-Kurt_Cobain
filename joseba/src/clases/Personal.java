package clases;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Personal {

    private int idPersonal;
    private int idCentro;
    private String nombre;
    private String puesto;
    private double salario;
    private Date fechaContrato;
    private String tipoContrato;

    public Personal() {}

    public Personal(int idPersonal, int idCentro, String nombre, String puesto,
                    double salario, Date fechaContrato, String tipoContrato) {
        this.idPersonal = idPersonal;
        this.idCentro = idCentro;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaContrato = fechaContrato;
        this.tipoContrato = tipoContrato;
    }

    public int getIdPersonal() { return idPersonal; }
    public void setIdPersonal(int idPersonal) { this.idPersonal = idPersonal; }
    public int getIdCentro() { return idCentro; }
    public void setIdCentro(int idCentro) { this.idCentro = idCentro; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPuesto() { return puesto; }
    public void setPuesto(String puesto) { this.puesto = puesto; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
    public Date getFechaContrato() { return fechaContrato; }
    public void setFechaContrato(Date fechaContrato) { this.fechaContrato = fechaContrato; }
    public String getTipoContrato() { return tipoContrato; }
    public void setTipoContrato(String tipoContrato) { this.tipoContrato = tipoContrato; }

    @Override
    public String toString() {
        return "Personal{id=" + idPersonal + ", nombre=" + nombre + ", puesto=" + puesto + ", salario=" + salario + "}";
    }

    // ---- CRUD ----

    public static List<Personal> getAll(Connection conn) throws SQLException {
        List<Personal> lista = new ArrayList<>();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Personal");
        while (rs.next()) {
            lista.add(new Personal(
                    rs.getInt("id_personal"), rs.getInt("id_centro"),
                    rs.getString("nombre"), rs.getString("puesto"),
                    rs.getDouble("salario"), rs.getDate("fecha_contrato"),
                    rs.getString("tipo_contrato")
            ));
        }
        return lista;
    }

    public static Personal getById(Connection conn, int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Personal WHERE id_personal = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Personal(
                    rs.getInt("id_personal"), rs.getInt("id_centro"),
                    rs.getString("nombre"), rs.getString("puesto"),
                    rs.getDouble("salario"), rs.getDate("fecha_contrato"),
                    rs.getString("tipo_contrato")
            );
        }
        return null;
    }

    public void insert(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Personal (id_centro, nombre, puesto, salario, fecha_contrato, tipo_contrato) VALUES (?,?,?,?,?,?)");
        ps.setInt(1, idCentro);
        ps.setString(2, nombre);
        ps.setString(3, puesto);
        ps.setDouble(4, salario);
        ps.setDate(5, fechaContrato);
        ps.setString(6, tipoContrato);
        ps.executeUpdate();
    }

    public void update(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "UPDATE Personal SET id_centro=?, nombre=?, puesto=?, salario=?, fecha_contrato=?, tipo_contrato=? WHERE id_personal=?");
        ps.setInt(1, idCentro);
        ps.setString(2, nombre);
        ps.setString(3, puesto);
        ps.setDouble(4, salario);
        ps.setDate(5, fechaContrato);
        ps.setString(6, tipoContrato);
        ps.setInt(7, idPersonal);
        ps.executeUpdate();
    }

    public void delete(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM Personal WHERE id_personal = ?");
        ps.setInt(1, idPersonal);
        ps.executeUpdate();
    }
}