package AccesoADatos;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {

//    private static final String URL = "jdbc:mysql://192.168.1.82/";
    private static final String URL = "jdbc:mysql://localhost/";
    private static final String DB = "turismoAgencia";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "p21Mu61354";

    private static Connection connection;

    private Conexion() {
    }

    public static Connection getConexion() {

        if (connection == null) {
            try {
                //Class.forName("org.mariadb.jdbc.Driver"); //MariaDB
                Class.forName("com.mysql.cj.jdbc.Driver"); //MySQLDB
                connection = DriverManager
                        .getConnection(URL + DB + "?useLegacyDatetimeCode=false&serverTimezone=UTC" + "&user=" + USUARIO + "&password=" + PASSWORD);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al conectarse a la Base de Datos " + ex.getMessage());
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar los Drivers de la Base de Datos " + ex.getMessage());
            }
        }
        return connection;
    }

    public static void cerrarConexion() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Error cerrando la conexion");
        } finally{
            System.out.println("Conexión a Base de Datos cerrada.");
        }
    }
}