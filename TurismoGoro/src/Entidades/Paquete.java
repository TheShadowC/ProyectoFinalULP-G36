package Entidades;

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
    private int nPersonas;
    private String tipoTemporada;
    private Double precio;
    private boolean estado;

    public Paquete() {
    }

    public Paquete(Ciudad origen, Ciudad destino, Alojamiento alojamiento, Pasaje pasaje, boolean estado) {
        this.origen = origen;
        this.destino = destino;
        this.alojamiento = alojamiento;
        this.pasaje = pasaje;
        this.estado = estado;
    }

    public Paquete(int idPaquete, Ciudad origen, Ciudad destino, Alojamiento alojamiento, Pasaje pasaje, boolean estado) {
        this.idPaquete = idPaquete;
        this.origen = origen;
        this.destino = destino;
        this.alojamiento = alojamiento;
        this.pasaje = pasaje;
        this.estado = estado;
    }

    public Paquete(int idPaquete, Ciudad origen, Ciudad destino, Alojamiento alojamiento, Pasaje pasaje, int nPersonas, String tipoTemporada, Double precio, boolean estado) {
        this.idPaquete = idPaquete;
        this.origen = origen;
        this.destino = destino;
        this.alojamiento = alojamiento;
        this.pasaje = pasaje;
        this.nPersonas = nPersonas;
        this.tipoTemporada = tipoTemporada;
        this.precio = precio;
        this.estado = estado;
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

    public int getnPersonas() {
        return nPersonas;
    }

    public void setnPersonas(int nPersonas) {
        this.nPersonas = nPersonas;
    }

    public String getTipoTemporada() {
        return tipoTemporada;
    }

    public void setTipoTemporada(String tipoTemporada) {
        this.tipoTemporada = tipoTemporada;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public boolean getEstado() {
        return estado;
    }

    public void isEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        String titulo = "Paquete: " + "Id " + idPaquete + ", Origen= " + origen + ", Destino= " + destino + ", Alojamiento= " + alojamiento + ", Pasaje= " + pasaje;
        ;
        return titulo;
    }

}
