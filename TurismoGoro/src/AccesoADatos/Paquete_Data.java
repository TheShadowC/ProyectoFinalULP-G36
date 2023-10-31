package AccesoADatos;

import Entidades.*;
import AccesoADatos.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Susana
 */
public class Paquete_Data {

    private Connection conn = null;
    private Ciudad CO;
    private Ciudad_Data ciudadData;
    private Paquete paquete;
    private Paquete_Data PaqueteData;
    private Alojamiento alojamiento;
    private Alojamiento_Data alojamientoData;
    private Pasaje pasaje;
    private Pasaje_Data pasajeData;

    public Paquete_Data() {
        conn = Conexion.getConexion();
    }

    public Paquete buscarPaquete(int id) {
        String sql = "SELECT * FROM paquete   WHERE estado =1 AND idPaquete=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            do {
                paquete = new Paquete();
                alojamiento = alojamientoData.buscarAlojamiento(rs.getInt("idAlojamiento"));
                Ciudad CO = ciudadData.buscarCiudad(rs.getInt("idCiudadOrigen"));
                Ciudad CD = ciudadData.buscarCiudad(rs.getInt("idCiudadDestino"));
                pasaje = pasajeData.buscarPasaje(rs.getInt("idPasaje"));
                paquete.setIdPaquete(rs.getInt("idPaquete"));
                paquete.setAlojamiento(alojamiento);
                paquete.setDestino(CD);
                paquete.setOrigen(CO);
                paquete.setPasaje(pasaje);
                paquete.setnPersonas(rs.getInt("nPersonas"));
                paquete.setTipoTemporada(rs.getString("tipoTemporada"));
                paquete.setPrecio(rs.getDouble("precio"));
                paquete.isEstado(rs.getBoolean("estado"));
            } while (rs.next());
            // } else {
            //    JOptionPane.showMessageDialog(null, "No existe ese Paquete ");
            // }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Paquete " + ex.getMessage());
        }
        return paquete;
    }

    public Paquete buscarPaquete2(int id) {

        String sql = "SELECT * FROM paquete WHERE estado = 1 AND idPaquete=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            //System.out.println("Resulset " + rs.next());
            while (rs.next()) {
                paquete = new Paquete();
                ciudadData = new Ciudad_Data();
                alojamientoData = new Alojamiento_Data();
                pasajeData = new Pasaje_Data();
                paquete.setIdPaquete(rs.getInt("idPaquete"));
                paquete.setOrigen(ciudadData.buscarCiudad(rs.getInt("idCiudadOrigen")));
                paquete.setDestino(ciudadData.buscarCiudad(rs.getInt("idCiudadDestino")));
                paquete.setAlojamiento(alojamientoData.buscarAlojamiento(rs.getInt("idAlojamiento")));
                paquete.setPasaje(pasajeData.buscarPasaje(rs.getInt("idPasaje")));
                paquete.setTipoTemporada(rs.getString("tipoTemporada"));
                paquete.setnPersonas(rs.getInt("nPersonas"));
                paquete.setTipoTemporada(rs.getString("tipoTemporada"));
                paquete.setPrecio(rs.getDouble("precio"));
                paquete.isEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Paquete " + ex.getMessage());
        }
        return paquete;
    }

    public void guardarPaquete(Paquete paquete) {
        String sql = " INSERT INTO paquete(idCiudadOrigen, idCiudadDestino, idAlojamiento, idPasaje, nPersonas, tipoTemporada, precio, estado) VALUES (?,?,?,?,?,?,?,?) ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, paquete.getOrigen().getIdCiudad());
            ps.setInt(2, paquete.getDestino().getIdCiudad());
            ps.setInt(3, paquete.getAlojamiento().getIdAlojamiento());
            ps.setInt(4, paquete.getPasaje().getIdPasajero());
            ps.setInt(5, paquete.getnPersonas());
            ps.setString(6, paquete.getTipoTemporada());
            ps.setDouble(7, paquete.getPrecio());
            ps.setBoolean(8, paquete.getEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                paquete.setIdPaquete(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Se generó presupuesto para el paquete id " + paquete.getIdPaquete());
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Paquete " + ex.getMessage());
        }
    }

    public void modificarPaquete(Paquete paquete) {
        String sql = "UPDATE paquete SET idCiudadOrigen=?,idCiudadDestino=?,idAlojamiento=?,idPasaje=?  "
                + " WHERE estado=1 AND idPaquete=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, paquete.getOrigen().getIdCiudad());
            ps.setInt(2, paquete.getDestino().getIdCiudad());
            ps.setInt(3, paquete.getAlojamiento().getIdAlojamiento());
            ps.setInt(4, paquete.getPasaje().getIdPasajero());
            ps.setInt(5, paquete.getIdPaquete());
            int exito = ps.executeUpdate();
            if (exito > 1) {
                JOptionPane.showMessageDialog(null, "Se modifico el Paquete ");
            } else {
                JOptionPane.showMessageDialog(null, "No existe ese Paquete.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Paquete " + ex.getMessage());
        }
    }

    public void eliminarPaquete(int id) {

        try {
            String sql = "UPDATE paquete SET estado=0 WHERE idPaquete=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            if (fila > 1) {
                JOptionPane.showMessageDialog(null, "Se elimino el paquete.");
            }
            ps.close();
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Paquete " + ex.getMessage());
        }
    }

    public List<Paquete> listarPaquetesActivos() {
        List<Paquete> lista = new ArrayList<>();
        String sql = "SELECT * FROM paquete WHERE estado = 1";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Paquete paquete = new Paquete();
                Ciudad_Data c = new Ciudad_Data();
                Pasaje_Data p = new Pasaje_Data();
                Alojamiento_Data a = new Alojamiento_Data();
                paquete.setIdPaquete(rs.getInt("idPaquete"));
                paquete.setOrigen(c.buscarCiudad(rs.getInt("idCiudadOrigen")));
                paquete.setDestino(c.buscarCiudad(rs.getInt("idCiudadDestino")));
                paquete.setPasaje(p.buscarPasaje(rs.getInt("idPasaje")));
                paquete.setAlojamiento(a.buscarAlojamiento(rs.getInt("idAlojamiento")));
                paquete.setnPersonas(rs.getInt("nPersonas"));
                paquete.setTipoTemporada(rs.getString("tipotemporada"));
                paquete.setPrecio(rs.getDouble("precio"));
                paquete.isEstado(true);
                lista.add(paquete);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Acceder a la Tabla Paquete " + ex.getMessage());
        }
        return lista;
    }

    public List<Paquete> listarPaquetesInactivos() {
        List<Paquete> lista = new ArrayList<>();
        String sql = "SELECT * FROM paquete WHERE estado = 0";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Paquete paquete = new Paquete();
                Ciudad_Data c = new Ciudad_Data();
                Pasaje_Data p = new Pasaje_Data();
                Alojamiento_Data a = new Alojamiento_Data();
                paquete.setIdPaquete(rs.getInt("idPaquete"));
                paquete.setOrigen(c.buscarCiudad(rs.getInt("idCiudadOrigen")));
                paquete.setDestino(c.buscarCiudad(rs.getInt("idCiudadDestino")));
                paquete.setPasaje(p.buscarPasaje(rs.getInt("idPasaje")));
                paquete.setAlojamiento(a.buscarAlojamiento(rs.getInt("idAlojamiento")));
                paquete.setnPersonas(rs.getInt("nPersonas"));
                paquete.setTipoTemporada(rs.getString("tipotemporada"));
                paquete.setPrecio(rs.getDouble("precio"));
                paquete.isEstado(true);
                lista.add(paquete);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Acceder a la Tabla Paquete " + ex.getMessage());
        }
        return lista;
    }

    public void activarPaquete(int id) {

        try {
            String sql = "UPDATE paquete SET estado=1 WHERE idPaquete=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            if (fila > 1) {
                JOptionPane.showMessageDialog(null, "Se elimino el paquete.");
            }
            ps.close();
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Paquete " + ex.getMessage());
        }

    }
}
