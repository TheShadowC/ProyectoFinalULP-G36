package AccesoADatos;

import Entidades.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Disponible_Data {

    private Connection conn;

    //Iniciamos la Conexion
    public Disponible_Data() {
        conn = Conexion.getConexion();
    }

    public Alojamiento buscarAlojamiento(int id) {
        Ciudad_Data ciudadData = new Ciudad_Data();
        Alojamiento alojamiento = null;
        String sql = "SELECT idAlojamiento, tipoAlojamiento, servicio, importeDiario, idCiudad, estado\n"
                + "FROM alojamientoDisponible WHERE estado=1 AND idAlojamiento=?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                alojamiento = new Alojamiento();
                alojamiento.setIdAlojamiento(rs.getInt("idAlojamiento"));
                alojamiento.setTipoAlojamiento(rs.getString("tipoAlojamiento"));
                alojamiento.setTipoServicio(rs.getString("servicio"));
                alojamiento.setImporteDiario(rs.getDouble("importeDiario"));
                Ciudad ciudad = new Ciudad();
                ciudad = ciudadData.buscarCiudad(rs.getInt("idCiudad"));
                alojamiento.setCiudadDest(ciudad);
                alojamiento.setEstado(rs.getBoolean("estado"));

            } else {
                JOptionPane.showMessageDialog(null, "No existe ese alojamiento ] ");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alojamiento " + ex.getMessage());

        }

        return alojamiento;

    }

    public void guardarAlojamiento(Alojamiento alojamiento) {
        String sql = "INSERT INTO alojamientoDisponible(tipoAlojamiento, servicio, importeDiario, idCiudad, estado) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alojamiento.getTipoAlojamiento());
            ps.setString(2, alojamiento.getTipoServicio());
            ps.setDouble(3, alojamiento.getImporteDiario());
            ps.setInt(4, alojamiento.getCiudadDest().getIdCiudad());
            ps.setBoolean(5, alojamiento.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alojamiento.setIdAlojamiento(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Alojamiento añadido con exito");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alojamiento " + ex.getMessage());
        }
    }

    public void modificarAlojamiento(Alojamiento alojamiento) {
        String sql = "UPDATE alojamientoDisponible SET tipoAlojamiento=?,servicio=?,importeDiario=?,idCiudad=? WHERE idAlojamiento=?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, alojamiento.getTipoAlojamiento());
            ps.setString(2, alojamiento.getTipoServicio());
            ps.setDouble(3, alojamiento.getImporteDiario());
            ps.setInt(4, alojamiento.getCiudadDest().getIdCiudad());
            ps.setInt(5, alojamiento.getIdAlojamiento());
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Se modifico el Alojamiento ");
            } else {
                JOptionPane.showMessageDialog(null, "No existe ese Alojamiento.");
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alojamiento " + ex.getMessage());
        }
    }

    public void eliminarAlojamiento(int id) {

        try {
            String sql = "UPDATE alojamientoDisponible SET estado = 0 WHERE idAlojamiento = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            if (fila == 1) {
                JOptionPane.showMessageDialog(null, "Se elimino el alojamiento.");

            }
            ps.close();
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alojamiento " + ex.getMessage());
        }
    }

    public void activarAlojamiento(int id) {

        try {
            String sql = "UPDATE alojamientoDisponible SET estado = 1 WHERE idAlojamiento = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            if (fila == 1) {
                JOptionPane.showMessageDialog(null, "Se activó el alojamiento.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alojamiento " + ex.getMessage());
        }
    }

    public List<Alojamiento> listarAlojamientos() {
        List<Alojamiento> alojamientos = new ArrayList<>();
        String sql = "SELECT * FROM alojamientoDisponible WHERE estado = 1 Order by idCiudad";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Alojamiento al = new Alojamiento();
                al.setIdAlojamiento(rs.getInt("idAlojamiento"));
                al.setTipoAlojamiento(rs.getString("tipoAlojamiento"));
                al.setTipoServicio(rs.getString("servicio"));
                al.setImporteDiario((Double) rs.getDouble("importeDiario"));
                Ciudad_Data ciudad = new Ciudad_Data();
                Ciudad city = ciudad.buscarCiudad(rs.getInt("idCiudad"));
                al.setCiudadDest(city);
                al.setEstado(true);
                alojamientos.add(al);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Acceder a la Tabla Alojamientos: " + ex.getMessage());
        }
        return alojamientos;
    }

    public List<Alojamiento> listarInactivos() {
        List<Alojamiento> alojamientos = new ArrayList<>();
        String sql = "SELECT * FROM alojamientoDisponible WHERE estado = 0";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Alojamiento al = new Alojamiento();
                al.setIdAlojamiento(rs.getInt("idAlojamiento"));
                al.setTipoAlojamiento(rs.getString("tipoAlojamiento"));
                al.setTipoServicio(rs.getString("servicio"));
                al.setImporteDiario((Double) rs.getDouble("importeDiario"));
                Ciudad_Data ciudad = new Ciudad_Data();
                Ciudad city = ciudad.buscarCiudad(rs.getInt("idCiudad"));
                al.setCiudadDest(city);
                al.setEstado(true);
                alojamientos.add(al);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Acceder a la Tabla Alojamientos: " + ex.getMessage());
        }
        return alojamientos;
    }

    public List<Alojamiento> listarPorProvincia(String prov) {
        List<Alojamiento> alojamientos = new ArrayList<>();
        String sql = "SELECT * FROM alojamientoDisponible a, ciudad c WHERE a.idCiudad = c.idCiudad AND c.provincia=? AND a.estado = 1";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, prov);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Alojamiento al = new Alojamiento();
                al.setIdAlojamiento(rs.getInt("idAlojamiento"));
                al.setTipoAlojamiento(rs.getString("tipoAlojamiento"));
                al.setTipoServicio(rs.getString("servicio"));
                al.setImporteDiario((Double) rs.getDouble("importeDiario"));
                Ciudad_Data ciudad = new Ciudad_Data();
                Ciudad city = new Ciudad();
                city = ciudad.buscarCiudad(rs.getInt("idCiudad"));
                al.setCiudadDest(city);
                al.setEstado(true);
                alojamientos.add(al);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Acceder a la Tabla Alojamientos: " + ex.getMessage());
        }
        return alojamientos;
    }

}
