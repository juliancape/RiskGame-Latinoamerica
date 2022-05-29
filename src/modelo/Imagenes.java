/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Image;

/**
 *
 * @author User
 */
public class Imagenes {
    private Image img;
    private int x;
    private int y;
    private int dimx;
    private int dimy;

    public Imagenes(Image img, int x, int y, int dimx, int dimy) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.dimx = dimx;
        this.dimy = dimy;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
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

    public int getDimx() {
        return dimx;
    }

    public void setDimx(int dimx) {
        this.dimx = dimx;
    }

    public int getDimy() {
        return dimy;
    }

    public void setDimy(int dimy) {
        this.dimy = dimy;
    }
    
    
}
