/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class PuntoPais {
    private String pais;
    private int x;
    private int y;
    private int soldados;
    private String propiedad;
    private ArrayList<String> vecinos;

    public PuntoPais(String pais, int x, int y, int soldados, String propiedad, ArrayList<String> vecinos) {
        this.pais = pais;
        this.x = x;
        this.y = y;
        this.soldados = soldados;
        this.propiedad = propiedad;
        this.vecinos = vecinos;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSoldados() {
        return soldados;
    }

    public void setSoldados(int soldados) {
        this.soldados = soldados;
    }

    public String getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(String propiedad) {
        this.propiedad = propiedad;
    }

    public ArrayList<String> getVecinos() {
        return vecinos;
    }

    public void setVecinos(ArrayList<String> vecinos) {
        this.vecinos = vecinos;
    }

    @Override
    public String toString() {
        return "PuntoPais{" + "pais=" + pais + ", soldados=" + soldados + ", propiedad=" + propiedad + ", vecinos=" + vecinos + '}';
    }
    
    
    
}
