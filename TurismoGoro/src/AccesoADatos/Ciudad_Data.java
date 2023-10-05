package AccesoADatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import turismogoro.Entidades.Ciudad;

/**
 *
 * @author Juan
 */
public class Ciudad_Data {

    private Connection conn = null;
    private Ciudad ciudad;
    private Ciudad_Data ciudadData;

    //Iniciamos la Conexion
    public Ciudad_Data() {
        conn = Conexion.getConexion();
    }
    
    public void guardarCiudad(Ciudad ciudad) {
        String sql = "INSERT INTO ciudad(nombre,provincia,pais,estado) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ciudad.getNombre());
            ps.setString(2, ciudad.getProvincia());
            ps.setString(3, ciudad.getPais());
            ps.setBoolean(4, ciudad.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ciudad.setIdCiudad(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Ciudad añadida con exito");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Ciudad " + ex.getMessage());
        }
    }

    public List<Ciudad> obtenerCiudades() {
        List<Ciudad> ciudades = new ArrayList<>();

        try {
            // Obtener la lista de ciudades
            String sql = "SELECT * FROM Ciudad";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            
            while (rs.next()) {
                Ciudad ciudad = new Ciudad();
                
                ciudad.setIdCiudad(rs.getInt("idCiudad"));
                ciudad.setNombre(rs.getString("nombre"));
                ciudad.setProvincia(rs.getString("provincia"));
                ciudad.setPais(rs.getString("pais"));
                ciudad.setEstado(rs.getBoolean("estado"));

                ciudades.add(ciudad);
            }

            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Acceder a la Tabla Ciudad");
        }

        return ciudades;
    }
    
    public void modificarCiudad(Ciudad ciudad) {
        String sql = "UPDATE ciudad SET nombre =?, provincia =?, pais=?, estado=? WHERE idCiudad=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, ciudad.getNombre());
            ps.setString(2, ciudad.getProvincia());
            ps.setString(3, ciudad.getPais());
            ps.setBoolean(4, ciudad.isEstado());
            ps.setInt(5, ciudad.getIdCiudad());
            int exito=ps.executeUpdate();
            
            if (exito ==1){
                JOptionPane.showMessageDialog(null, "Modificado exitosamente.");

            }else {
                 JOptionPane.showMessageDialog(null, "La Ciudad no existe en la Base de Datos.");

            
        }} catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Ciudad" + ex.getMessage());
        }
    }
    
    
    public void eliminarCiudad(Ciudad c) {
        
        try{
        String sql="UPDATE ciudad SET estado = 0 WHERE nombre = ?";
        
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setString(1, c.getNombre());
        int fila=ps.executeUpdate();
        if(fila==1){
               JOptionPane.showMessageDialog(null, "Se elimino la Ciudad." );

        }
        ps.close();
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error al acceder a la atabla Ciudad " + ex.getMessage());
        }
    }
    
}
