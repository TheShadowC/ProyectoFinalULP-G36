package AccesoADatos;

import Entidades.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan
 *//*


 */
public class Alojamiento_Data {

    private Connection conn = null;
    private Ciudad ciudad = null;
    private Ciudad_Data ciudadData = null;
    private Alojamiento a1 = null;
    private Alojamiento_Data alojamiento = null;

    //Iniciamos la Conexion
    public Alojamiento_Data() {
        conn = Conexion.getConexion();
    }

    public void guardarAlojamiento(Alojamiento alojamiento) {
        String sql = "INSERT INTO alojamiento(tipoAlojamiento, fechaIn, fechaFin, servicio, importeDiario, idCiudad, estado) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alojamiento.getTipoAlojamiento());
            ps.setDate(2, Date.valueOf(alojamiento.getFechaIn().toString()));
            ps.setDate(3, Date.valueOf(alojamiento.getFechaOut().toString()));
            ps.setString(4, alojamiento.getTipoServicio());
            ps.setDouble(5, alojamiento.getImporteDiario());
            ps.setInt(6, alojamiento.getCiudadDest().getIdCiudad());
            ps.setBoolean(7,alojamiento.isEstado());
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

    

    public List<Alojamiento> seleccionarAlojamiento(String nombreProvincia ) {
        List<Alojamiento> alojamientos = new ArrayList<>();
         // Construir la consulta SQL utilizando provincia de origen para el destino no funciona nada de lo que trate
        String sql = "SELECT DISTINCT c.* FROM ciudad c JOIN alojamiento a ON c.provincia = (SELECT provincia FROM ciudad WHERE idCiudad = a.idCiudad) WHERE c.provincia=?";
               
        try {
           // String provincia=ciudad.getProvincia();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreProvincia);
              ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Alojamiento alojamiento = new Alojamiento();
                alojamiento.setIdAlojamiento(rs.getInt("idAlojamiento"));
                alojamiento.setTipoAlojamiento(rs.getString("tipoAlojamiento"));
                alojamiento.setFechaIn(rs.getDate("fechaIn").toLocalDate());
                alojamiento.setFechaOut(rs.getDate("fechaFin").toLocalDate());
                alojamiento.setTipoServicio(rs.getString("servicio"));
                alojamiento.setImporteDiario((Double) rs.getDouble("importeDiario"));
                alojamiento.setCiudadDest(ciudad);
                alojamiento.setEstado(true);
                alojamientos.add(alojamiento);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Acceder a la Tabla Alojamientos: " + ex.getMessage());
        }
        return alojamientos;
    }
    
    public List<Alojamiento> listarAlojamientos(){
        List<Alojamiento> alojamientos = new ArrayList<>();
        String sql = "SELECT * FROM alojamiento WHERE estado = 1";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Alojamiento al = new Alojamiento();
                al.setIdAlojamiento(rs.getInt("idAlojamiento"));
                al.setTipoAlojamiento(rs.getString("tipoAlojamiento"));
                al.setFechaIn(rs.getDate("fechaIn").toLocalDate());
                al.setFechaOut(rs.getDate("fechaFin").toLocalDate());
                al.setTipoServicio(rs.getString("servicio"));
                al.setImporteDiario((Double) rs.getDouble("importeDiario"));
                Ciudad_Data ciudad =new Ciudad_Data();
                Ciudad city = new Ciudad();
                city = ciudad.buscarCiudad(rs.getInt("idCiudad"));
                al.setCiudadDest(city);
                al.setEstado(true);
                alojamientos.add(al);
            }
            rs.close();
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al Acceder a la Tabla Alojamientos: " + ex.getMessage());
        }
        return alojamientos;
    }
    
 
    public void modificarAlojamiento(Alojamiento alojamiento) {
        String sql = "UPDATE alojamiento SET tipoAlojamiento=?,fechaIn=?,fechaFin=?,servicio=?,importeDiario=?,idCiudad=? WHERE idAlojamiento=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, alojamiento.getTipoAlojamiento());
            ps.setDate(2, Date.valueOf(alojamiento.getFechaIn()));
            ps.setDate(3, Date.valueOf(alojamiento.getFechaOut()));
            ps.setString(4, alojamiento.getTipoServicio());
            ps.setDouble(5, alojamiento.getImporteDiario());
            ps.setInt(6, alojamiento.getCiudadDest().getIdCiudad());
            ps.setInt(7, alojamiento.getIdAlojamiento());
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
            String sql = "UPDATE alojamiento SET estado = 0 WHERE idAlojamiento = ?";

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

    public Alojamiento buscarAlojamiento(int id) {
        Ciudad_Data ciudadData=new Ciudad_Data();
        Alojamiento alojamiento = null;
        String sql = "SELECT idAlojamiento, tipoAlojamiento, fechaIn, fechaFin, servicio, importeDiario, idCiudad, estado\n" +
                      "FROM alojamiento WHERE estado=1 AND idAlojamiento=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                alojamiento = new Alojamiento();
                alojamiento.setIdAlojamiento(rs.getInt("idAlojamiento"));
                alojamiento.setTipoAlojamiento(rs.getString("tipoAlojamiento"));
                alojamiento.setFechaIn(rs.getDate("fechaIn").toLocalDate());
                alojamiento.setFechaOut(rs.getDate("fechaFin").toLocalDate());
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
    
        public List<Alojamiento> listarAlojamientos2(String prov){
        List<Alojamiento> alojamientos = new ArrayList<>();
        String sql = "SELECT * FROM alojamiento a, ciudad c WHERE a.idCiudad = c.idCiudad AND c.provincia=? AND a.estado = 1";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, prov);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Alojamiento al = new Alojamiento();
                al.setIdAlojamiento(rs.getInt("idAlojamiento"));
                al.setTipoAlojamiento(rs.getString("tipoAlojamiento"));
                al.setFechaIn(rs.getDate("fechaIn").toLocalDate());
                al.setFechaOut(rs.getDate("fechaFin").toLocalDate());
                al.setTipoServicio(rs.getString("servicio"));
                al.setImporteDiario((Double) rs.getDouble("importeDiario"));
                Ciudad_Data ciudad =new Ciudad_Data();
                Ciudad city = new Ciudad();
                city = ciudad.buscarCiudad(rs.getInt("idCiudad"));
                al.setCiudadDest(city);
                al.setEstado(true);
                alojamientos.add(al);
            }
            rs.close();
            stmt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al Acceder a la Tabla Alojamientos: " + ex.getMessage());
        }
        return alojamientos;
    }
    
     
}
