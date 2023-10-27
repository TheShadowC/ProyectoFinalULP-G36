package AccesoADatos;

import AccesoADatos.Conexion;
import Entidades.Logueo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Susana
 */
public class LogueoData {

    private Connection conn = null;

    public LogueoData() {
        conn = Conexion.getConexion();
    }

    public boolean buscarUsuarioPorContraseña(String usuario, String contrasenia) {
        Logueo loguin = null;
        boolean logOk = false;
        String sql = "SELECT usuario, pasword FROM logueo WHERE usuario=? AND pasword =?";
        PreparedStatement ps = null;
        try {

            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, contrasenia);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                loguin = new Logueo();
                loguin.setUsuario(rs.getString("usuario"));
                loguin.setContrasenia(rs.getString("pasword"));
                logOk = true;
            } else {
                JOptionPane.showMessageDialog(null, "Error. Usuario o Contraseña incorrecta.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla de Logueo " + ex.getMessage());

        }

        return logOk;
    }

}
