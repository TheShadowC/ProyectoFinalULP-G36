package AccesoADatos;

import entidades.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
        String sql = "INSERT INTO ciudad(nombre, provincia,pais) VALUES(?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ciudad.getNombre());
            ps.setString(2, ciudad.getProvincia());
            ps.setString(3, ciudad.getPais());
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

    public List<Ciudad> obtenerCiudades(Ciudad ciudadDestino) {
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

                ciudades.add(ciudad);
            }

            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Acceder a la Tabla Ciudad");
        }

        return ciudades;
    }

}
