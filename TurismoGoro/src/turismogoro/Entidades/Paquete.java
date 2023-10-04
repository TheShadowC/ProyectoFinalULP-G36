
package turismogoro.Entidades;

/**
 *
 * @author Susana
 */
public class Paquete {
     private int idPaquete;
     private Ciudad origen;
     private Ciudad destino;
     private Alojamiento alojamiento;
     private Pasaje pasaje;

    public Paquete() {
    }

    public Paquete(Ciudad origen, Ciudad destino, Alojamiento alojamiento, Pasaje pasaje) {
        this.origen = origen;
        this.destino = destino;
        this.alojamiento = alojamiento;
        this.pasaje = pasaje;
    }

    public Paquete(int idPaquete, Ciudad origen, Ciudad destino, Alojamiento alojamiento, Pasaje pasaje) {
        this.idPaquete = idPaquete;
        this.origen = origen;
        this.destino = destino;
        this.alojamiento = alojamiento;
        this.pasaje = pasaje;
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    public Pasaje getPasaje() {
        return pasaje;
    }

    public void setPasaje(Pasaje pasaje) {
        this.pasaje = pasaje;
    }

    @Override
    public String toString() {
        String titulo="Paquete: " + "Id " + idPaquete + ", Origen= " + origen + ", Destino= " + destino + ", Alojamiento= " + alojamiento + ", Pasaje= " + pasaje ;
 ;
        return titulo;
    }
     
     
}
