package turismogoro;

import AccesoADatos.*;
import java.util.List;
import turismogoro.Entidades.Ciudad;

/**
 *
 * @author USUARIO
 */
public class TurismoGoro {

    public static void main(String[] args) {
        
        Ciudad_Data c = new Ciudad_Data();
        
        List<Ciudad> ciudad = c.obtenerCiudades();
        
        for (Ciudad ciudades : ciudad) {
            System.out.println("Ciudades: " + ciudades);
            
        }
        
        //Creamos la Ciudad y la Agregamos a la BD
        /*Ciudad ciudad1 = new Ciudad();
        ciudad1.setNombre("Corrientes");
        ciudad1.setProvincia("Corrientes");
        ciudad1.setPais("Argentina");
        ciudad1.setEstado(true);
        
        c.guardarCiudad(ciudad1);*/
        
        //Eliminamos la Ciudad (Borrado Logico)
        //c.eliminarCiudad(ciudad1);
    }

}
