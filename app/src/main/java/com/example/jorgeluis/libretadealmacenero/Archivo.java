package com.example.jorgeluis.libretadealmacenero;

import java.io.Serializable;

/**
 * Created by Jorge Luis on 14/09/2016.
 */
public class Archivo implements Serializable{

    private String nombre;
    private String fecha;
    private int fondoDeNota;


    public Archivo(String n, String f){
        this.nombre=n;
        this.fecha=f;
    }

    public Archivo(String n, String f,int fondoDeNota){
        this.nombre=n;
        this.fecha=f;
        this.fondoDeNota=fondoDeNota;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getFondoDeNota() {
        return fondoDeNota;
    }

    public void setFondoDeNota(int fondoDeNota) {
        this.fondoDeNota = fondoDeNota;
    }
}
