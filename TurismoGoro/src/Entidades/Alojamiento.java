package Entidades;

import java.time.*;
import java.util.*;

/**
 *
 * @author Susana
 */
public class Alojamiento {

    private int idAlojamiento;
    private String tipoAlojamiento;
    private LocalDate fechaIn;
    private LocalDate fechaOut;
    private boolean estado;
    private String tipoServicio;
    private double importeDiario;
    private Ciudad ciudadDest;

    public Alojamiento(String tipoAlojamiento, String tipoServicio, double importeDiario, Ciudad ciudadDest, boolean estado) {
        this.tipoAlojamiento = tipoAlojamiento;
        this.tipoServicio = tipoServicio;
        this.importeDiario = importeDiario;
        this.ciudadDest = ciudadDest;
        this.estado = estado;
    }

    public Alojamiento() {
    }

    public Alojamiento(int idAlojamiento, String tipoAlojamiento, LocalDate fechaIn, LocalDate fechaOut, boolean estado, String tipoServicio, double importeDiario, Ciudad ciudadDest) {
        this.idAlojamiento = idAlojamiento;
        this.tipoAlojamiento = tipoAlojamiento;
        this.fechaIn = fechaIn;
        this.fechaOut = fechaOut;
        this.estado = estado;
        this.tipoServicio = tipoServicio;
        this.importeDiario = importeDiario;
        this.ciudadDest = ciudadDest;
    }

    public Alojamiento(String tipoAlojamiento, LocalDate fechaIn, LocalDate fechaOut, boolean estado, String tipoServicio, double importeDiario, Ciudad ciudadDest) {
        this.tipoAlojamiento = tipoAlojamiento;
        this.fechaIn = fechaIn;
        this.fechaOut = fechaOut;
        this.estado = estado;
        this.tipoServicio = tipoServicio;
        this.importeDiario = importeDiario;
        this.ciudadDest = ciudadDest;
    }

    public Alojamiento(int idAlojamiento, String tipoAlojamiento, String tipoServicio, Ciudad ciudadDest, double importeDiario) {
        this.idAlojamiento = idAlojamiento;
        this.tipoAlojamiento = tipoAlojamiento;
        this.tipoServicio = tipoServicio;
        this.importeDiario = importeDiario;
        
    }
    
    public int getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(int idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    public String getTipoAlojamiento() {
        return tipoAlojamiento;
    }

    public void setTipoAlojamiento(String tipoAlojamiento) {
        this.tipoAlojamiento = tipoAlojamiento;
    }

    public LocalDate getFechaIn() {
        return fechaIn;
    }

    public void setFechaIn(LocalDate fechaIn) {
        this.fechaIn = fechaIn;
    }

    public LocalDate getFechaOut() {
        return fechaOut;
    }

    public void setFechaOut(LocalDate fechaOut) {
        this.fechaOut = fechaOut;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
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
//        String titulo= tipoAlojamiento.toUpperCase()+" - "+tipoServicio+" | "+ciudadDest.getNombre()+" $"+importeDiario;
        String titulo="aca tiene que ir el string";
        return titulo;
    }

}

