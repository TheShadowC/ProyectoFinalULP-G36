package Entidades;

/**
 *
 * @author Susana
 */
public class Pasaje {
    private int idPasajero;
    private String tipoTransporte;
    private double importe;
    private Ciudad nombreCiudadO,nombreCiudadD;
    private boolean estado;

    public Pasaje() {
    }

    public Pasaje(int idPasajero, String tipoTransporte, double importe, Ciudad nombreCiudadO, Ciudad nombreCiudadD, boolean estado) {
        this.idPasajero = idPasajero;
        this.tipoTransporte = tipoTransporte;
        this.importe = importe;
        this.nombreCiudadO = nombreCiudadO;
        this.nombreCiudadD = nombreCiudadD;
        this.estado = estado;
    }

    public Pasaje(String tipoTransporte, double importe, Ciudad nombreCiudadO, Ciudad nombreCiudadD, boolean estado) {
        this.tipoTransporte = tipoTransporte;
        this.importe = importe;
        this.nombreCiudadO = nombreCiudadO;
        this.nombreCiudadD = nombreCiudadD;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
    public Ciudad getNombreCiudadO() {
        return nombreCiudadO;
    }

    public void setNombreCiudadO(Ciudad nombreCiudadO) {
        this.nombreCiudadO = nombreCiudadO;
    }

    public Ciudad getNombreCiudadD() {
        return nombreCiudadD;
    }

    public void setNombreCiudadD(Ciudad nombreCiudadD) {
        this.nombreCiudadD = nombreCiudadD;
    }
    

    @Override
    public String toString() {
        String titulo= "Id Pasaje=" + idPasajero + ", Tipo Transporte=" + tipoTransporte + ", Importe ida y vuelta =$ " + importe + ", Nombre Ciudad Origen=" + nombreCiudadO + ", Nombre Ciudad Destino : " + nombreCiudadD + ", Estado: " + estado  ;
    return titulo;
    }

    
    
}
