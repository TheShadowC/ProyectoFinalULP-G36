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
        String sql = "INSERT INTO alojamiento(fechaIn, fechaFin, servicio, tipo, importeDiario, idCiudad) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(alojamiento.getfInicio().toString()));
            ps.setDate(2, Date.valueOf(alojamiento.getfFin().toString()));
            ps.setString(3, alojamiento.getTipoServicio());
            ps.setString(4, alojamiento.getTipoAlojamiento());
            ps.setDouble(5, alojamiento.getImporteDiario());
            ps.setInt(6, alojamiento.getCiudadDest().getIdCiudad());

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

    public List<Alojamiento> seleccionarAlojamiento(Ciudad ciudadOrigen, Ciudad ciudadDestino) {
        List<Alojamiento> alojamientos = new ArrayList<>();

        // Construir la consulta SQL utilizando los idCiudad de origen y destino
        String sql = "SELECT * FROM alojamiento WHERE idCiudad = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ciudadOrigen.getNombre());
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Alojamiento alojamiento = new Alojamiento();
                alojamiento.setIdAlojamiento(rs.getInt("idAlojamiento"));
                alojamiento.setfInicio(rs.getDate("fechaIn"));
                alojamiento.setfFin(rs.getDate("fechaFin"));
                alojamiento.setTipoServicio(rs.getString("servicio"));
                alojamiento.setTipoAlojamiento(rs.getString("tipo"));
                alojamiento.setImporteDiario((Double) rs.getDouble("importeDiario"));

                alojamientos.add(alojamiento);
            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Acceder a la Tabla Alojamientos: " + ex.getMessage());
        }
        return alojamientos;
    }

}
