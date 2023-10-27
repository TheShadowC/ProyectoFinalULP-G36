package AccesoADatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Entidades.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan
 */
public class Pasaje_Data {

    private Connection conn = null;
    private Ciudad ciudad = null;
    private Ciudad_Data ciudadData;
    private Pasaje pasaje = null;
    private Pasaje_Data PasajeData = null;

    //Iniciamos la Conexion
    public Pasaje_Data() {
        conn = Conexion.getConexion();
    }

    public Pasaje buscarPasaje(int id) {
        Ciudad_Data ciudadData = new Ciudad_Data();
        Pasaje pasaje = null;
        String sql = "SELECT idPasaje, tipoTransporte, importe, idCiudadOrigen, idCiudadDestino, estado FROM pasajes WHERE estado=1 AND idPasaje=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                pasaje = new Pasaje();
                pasaje.setIdPasajero(rs.getInt("idPasaje"));
                pasaje.setTipoTransporte(rs.getString("tipoTransporte"));
                pasaje.setImporte(rs.getDouble("importe"));
                int idCO = rs.getInt("idCiudadOrigen");
                Ciudad ciudadO = ciudadData.buscarCiudad(idCO);
                int idCD = rs.getInt("idCiudadDestino");
                Ciudad ciudadD = ciudadData.buscarCiudad(idCD);
                pasaje.setNombreCiudadO(ciudadO);
                pasaje.setNombreCiudadD(ciudadD);
                pasaje.setEstado(rs.getBoolean("estado"));

            } else {
                JOptionPane.showMessageDialog(null, "No existe ese pasaje ");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Pasaje " + ex.getMessage());

        }

        return pasaje;

    }

    public void guardarPasaje(Pasaje pasaje) {
        String sql = "INSERT INTO pasajes(tipoTransporte, importe, idCiudadOrigen, idCiudadDestino, estado) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pasaje.getTipoTransporte());
            ps.setDouble(2, pasaje.getImporte());
            ps.setInt(3, pasaje.getNombreCiudadO().getIdCiudad());
            ps.setInt(4, pasaje.getNombreCiudadD().getIdCiudad());
            ps.setBoolean(5, pasaje.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                pasaje.setIdPasajero(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Pasaje añadido con exito");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla pasaje " + ex.getMessage());
        }
    }

    public List<Pasaje> seleccionarPasaje() {
        List<Pasaje> pasajes = new ArrayList<>();
        // Construir la consulta SQL utilizando de todos los pasajes activos
        String sql = "SELECT  tipoTransporte, importe, idCiudadOrigen, idCiudadDestino FROM pasajes WHERE  estado =1";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pasaje pasaje = new Pasaje();
                pasaje.setIdPasajero(rs.getInt("idPasaje"));
                Ciudad ciudadO = ciudadData.buscarCiudad(rs.getInt("idCiudadOrigen"));
                Ciudad ciudadD = ciudadData.buscarCiudad(rs.getInt("idCiudadDestino"));
                pasaje.setNombreCiudadO(ciudadO);
                pasaje.setNombreCiudadD(ciudadD);
                pasaje.setEstado(true);
                pasajes.add(pasaje);

            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Acceder a la Tabla Pasaje " + ex.getMessage());
        }
        return pasajes;
    }

    public List<Pasaje> listarPasajes() {
        List<Pasaje> pasajes = new ArrayList<>();
        // Construir la consulta SQL utilizando de todos los pasajes activos
        String sql = "SELECT * FROM pasajes WHERE  estado =1";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pasaje pasaje = new Pasaje();
                Ciudad_Data ciudadData = new Ciudad_Data();
                pasaje.setIdPasajero(rs.getInt("idPasaje"));
                Ciudad ciudadO = ciudadData.buscarCiudad(rs.getInt("idCiudadOrigen"));
                Ciudad ciudadD = ciudadData.buscarCiudad(rs.getInt("idCiudadDestino"));
                pasaje.setNombreCiudadO(ciudadO);
                pasaje.setNombreCiudadD(ciudadD);
                pasaje.setEstado(true);
                pasajes.add(pasaje);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Acceder a la Tabla Pasaje " + ex.getMessage());
        }
        return pasajes;
    }

    public void modificarPasaje(Pasaje pasaje) {
        String sql = "UPDATE pasajes SET tipoTransporte=?,importe=?,idCiudadOrigen=?,idCiudadDestino=? "
                + " WHERE estado=1 AND idPasaje=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, pasaje.getTipoTransporte());
            ps.setDouble(2, pasaje.getImporte());
            ps.setInt(3, pasaje.getNombreCiudadO().getIdCiudad());
            ps.setInt(4, pasaje.getNombreCiudadD().getIdCiudad());
            ps.setInt(5, pasaje.getIdPasajero());
            int exito = ps.executeUpdate();
            System.out.println(exito);
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Se modifico el Pasaje ");
            } else {
                JOptionPane.showMessageDialog(null, "No existe ese Pasaje.");
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Pasaje " + ex.getMessage());
        }
    }

    public void eliminarPasaje(int id) {

        try {
            String sql = "UPDATE pasajes SET estado = 0 WHERE idPasaje = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            if (fila == 1) {
                JOptionPane.showMessageDialog(null, "Se elimino el pasaje.");

            }
            ps.close();
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Pasaje " + ex.getMessage());
        }
    }

}
