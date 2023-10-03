package turismogoro.Entidades;

/**
 *
 * @author Susana
 */
public class Pasaje {
    private int idPasajero;
    private String tipoTransporte;
    private double importe;
    private Ciudad nombreCiudad;
    private boolean estado;

    public Pasaje() {
    }

    public Pasaje(String tipoTransporte, double importe, Ciudad nombreCiudad, boolean estado) {
        this.tipoTransporte = tipoTransporte;
        this.importe = importe;
        this.nombreCiudad = nombreCiudad;
        this.estado = estado;
    }

    public Pasaje(int idPasajero, String tipoTransporte, double importe, Ciudad nombreCiudad, boolean estado) {
        this.idPasajero = idPasajero;
        this.tipoTransporte = tipoTransporte;
        this.importe = importe;
        this.nombreCiudad = nombreCiudad;
        this.estado = estado;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public String getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(String tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Ciudad getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(Ciudad nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        String titulo= "Pasaje: " + "idPasajero= " + idPasajero + ", Transporte= " + tipoTransporte + ", Importe= $" + importe + ", Ciudad= " + nombreCiudad ;
    return titulo;
    }
    
    
}
