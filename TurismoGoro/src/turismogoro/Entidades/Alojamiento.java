package turismogoro.Entidades;

import java.util.*;

/**
 *
 * @author Susana
 */
public class Alojamiento {

    private int idAlojamiento;
    private Date fechaIn;
    private Date fechaOn;
    private boolean estado;
    private String servicio;
    private double importeDiario;
    private Ciudad ciudadDest;

    public Alojamiento() {
    }

    public Alojamiento(Date fechaIn, Date fechaOn, boolean estado, String servicio, double importeDiario, Ciudad ciudadDest) {
        this.fechaIn = fechaIn;
        this.fechaOn = fechaOn;
        this.estado = estado;
        this.servicio = servicio;
        this.importeDiario = importeDiario;
        this.ciudadDest = ciudadDest;
    }

    public Alojamiento(int idAlojamiento, Date fechaIn, Date fechaOn, boolean estado, String servicio, double importeDiario, Ciudad ciudadDest) {
        this.idAlojamiento = idAlojamiento;
        this.fechaIn = fechaIn;
        this.fechaOn = fechaOn;
        this.estado = estado;
        this.servicio = servicio;
        this.importeDiario = importeDiario;
        this.ciudadDest = ciudadDest;
    }

    public int getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(int idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    public Date getFechaIn() {
        return fechaIn;
    }

    public void setFechaIn(Date fechaIn) {
        this.fechaIn = fechaIn;
    }

    public Date getFechaOn() {
        return fechaOn;
    }

    public void setFechaOn(Date fechaOn) {
        this.fechaOn = fechaOn;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public double getImporteDiario() {
        return importeDiario;
    }

    public void setImporteDiario(double importeDiario) {
        this.importeDiario = importeDiario;
    }

    public Ciudad getCiudadDest() {
        return ciudadDest;
    }

    public void setCiudadDest(Ciudad ciudadDest) {
        this.ciudadDest = ciudadDest;
    }

    @Override
    public String toString() {
        return "Alojamiento{" + "idAlojamiento=" + idAlojamiento + ", fechaIn=" + fechaIn + ", fechaOn=" + fechaOn + ", estado=" + estado + ", servicio=" + servicio + ", importeDiario=" + importeDiario + ", ciudadDest=" + ciudadDest + '}';
    }

}
