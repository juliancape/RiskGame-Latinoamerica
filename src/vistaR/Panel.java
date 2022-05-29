/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaR;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import modelo.Imagenes;
import modelo.PuntoPais;

/**
 *
 * @author Julian Cardenas - Daniel Velasquez
 */
public class Panel extends JPanel{
    //Lista de puntos
    private ArrayList<PuntoPais> paises = new ArrayList<>();
    private ArrayList<PuntoPais> paisesAliados = new ArrayList<>();
    private ArrayList<PuntoPais> paisesEnemigos = new ArrayList<>();
    private ArrayList<Imagenes> imgs = new ArrayList<>();
    
    private JButton botonColombia = new JButton("Colombia");
    private JButton botonVenezuela = new JButton("Venezuela");
    private JButton botonBrasil = new JButton("Brasil");
    private JButton botonEcuador = new JButton("Ecuador");
    private JButton botonPeru = new JButton("Peru");
    private JButton botonGuyana = new JButton("Guyana");
    private JButton botonSuriname = new JButton("Suriname");
    private JButton botonGuyanaFrancesa = new JButton("GuyanaFrancesa");
    private JButton botonBolivia = new JButton("Bolivia");
    private JButton botonParaguay = new JButton("Paraguay");
    private JButton botonChile = new JButton("Chile");
    private JButton botonArgentina = new JButton("Argentina");
    private JButton botonUruguay = new JButton("Uruguay");
    
    public Panel(){
        this.setSize(650, 690);
        this.setVisible(true);
    }
    
    @Override
    public void paint(Graphics g){
        g.clearRect(0,0,650,690);
        g.setColor(new Color(37, 36, 64));
        g.fillRect(0, 0, 650,690);
        Image fondo;
        //fondo = new ImageIcon("Image/south-35734_1280").getImage();
        fondo = new ImageIcon("src/Images/south_America.png").getImage();
        g.drawImage(fondo, 50, 10, 570, 650, this);
        //g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        //super.paint(g);
        //initComponents();
        g.setColor(Color.BLACK);
        g.drawLine(41, 90, 80, 155);
        g.drawLine(349, 15, 325, 90);
        g.drawLine(380, 40, 360, 90);
        g.drawLine(505, 64, 398, 85);
        g.drawLine(135, 405, 210, 390);
        g.drawLine(403, 475, 360, 440);
        g.drawLine(505, 385, 340, 350);
        
        g.setColor(Color.BLACK);
        g.fillOval(490, 500, 20, 20);
        g.drawString("Territorio vacio", 515, 510);
        g.setColor(Color.GREEN);
        g.fillOval(490, 540, 20, 20);
        g.drawString("Territorio aliado", 515, 550);
        g.setColor(Color.RED);
        g.fillOval(490, 580, 20, 20);
        g.drawString("Territorio enemigo", 515, 590);
        //for (int i = 0; i < imgs.size(); i++) {
            //g.drawImage(imgs.get(i).getImg(), imgs.get(i).getX(), imgs.get(i).getY(), imgs.get(i).getDimx(), imgs.get(i).getDimy(), this);
        //}
        for (int i = 0; i < paises.size(); i++) {
            g.setColor(Color.BLACK);
            g.fillOval(paises.get(i).getX(), paises.get(i).getY(),25,25);
            g.setColor(Color.RED);
            g.drawString(String.valueOf(paises.get(i).getSoldados()), paises.get(i).getX()+10, paises.get(i).getY()+20);
        }
        for (int i = 0; i < paisesAliados.size(); i++) {
            g.setColor(Color.GREEN);
            g.fillOval(paisesAliados.get(i).getX(), paisesAliados.get(i).getY(),25,25);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(paisesAliados.get(i).getSoldados()), paisesAliados.get(i).getX()+5, paisesAliados.get(i).getY()+20);
        }
        for (int i = 0; i < paisesEnemigos.size(); i++) {
            g.setColor(Color.RED);
            g.fillOval(paisesEnemigos.get(i).getX(), paisesEnemigos.get(i).getY(),25,25);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(paisesEnemigos.get(i).getSoldados()), paisesEnemigos.get(i).getX()+5, paisesEnemigos.get(i).getY()+20);
        }
        
        initComponents();
        
        
    }
    
    public void initComponents(){
        this.botonColombia.setBounds(100, 75, 88, 20);
        this.botonColombia.setBackground(Color.WHITE);
        this.botonColombia.setForeground(Color.BLACK);
        this.botonColombia.setFocusPainted(false);
        this.botonColombia.setBorderPainted(false);
        this.add(botonColombia);
        //2
        this.botonVenezuela.setBounds(200, 42, 100, 20);
        this.botonVenezuela.setBackground(Color.WHITE);
        this.botonVenezuela.setForeground(Color.BLACK);
        this.botonVenezuela.setFocusPainted(false);
        this.botonVenezuela.setBorderPainted(false);
        this.add(botonVenezuela);
        //3
        this.botonBrasil.setBounds(350, 210, 70, 20);
        this.botonBrasil.setBackground(Color.WHITE);
        this.botonBrasil.setForeground(Color.BLACK);
        this.botonBrasil.setFocusPainted(false);
        this.botonBrasil.setBorderPainted(false);
        this.add(botonBrasil);
        //4
        this.botonEcuador.setBounds(0, 90, 82, 20);
        this.botonEcuador.setHorizontalAlignment(SwingConstants.LEFT);
        this.botonEcuador.setBackground(Color.WHITE);
        this.botonEcuador.setForeground(Color.BLACK);
        this.botonEcuador.setFocusPainted(false);
        this.botonEcuador.setBorderPainted(false);
        this.add(botonEcuador);
        //5
        this.botonPeru.setBounds(95, 225, 68, 20);
        this.botonPeru.setBackground(Color.WHITE);
        this.botonPeru.setForeground(Color.BLACK);
        this.botonPeru.setFocusPainted(false);
        this.botonPeru.setBorderPainted(false);
        this.add(botonPeru);
        //6
        this.botonGuyana.setBounds(310, 15, 78, 20);
        this.botonGuyana.setBackground(Color.WHITE);
        this.botonGuyana.setForeground(Color.BLACK);
        this.botonGuyana.setFocusPainted(false);
        this.botonGuyana.setBorderPainted(false);
        this.add(botonGuyana);
        //7
        this.botonSuriname.setBounds(360, 40, 90, 20);
        this.botonSuriname.setBackground(Color.WHITE);
        this.botonSuriname.setForeground(Color.BLACK);
        this.botonSuriname.setFocusPainted(false);
        this.botonSuriname.setBorderPainted(false);
        this.add(botonSuriname);
        //8
        this.botonGuyanaFrancesa.setBounds(440, 64, 130, 20);
        this.botonGuyanaFrancesa.setBackground(Color.WHITE);
        this.botonGuyanaFrancesa.setForeground(Color.BLACK);
        this.botonGuyanaFrancesa.setFocusPainted(false);
        this.botonGuyanaFrancesa.setBorderPainted(false);
        this.add(botonGuyanaFrancesa);
        //9
        this.botonBolivia.setBounds(220, 270, 73, 20);
        this.botonBolivia.setBackground(Color.WHITE);
        this.botonBolivia.setForeground(Color.BLACK);
        this.botonBolivia.setFocusPainted(false);
        this.botonBolivia.setBorderPainted(false);
        this.add(botonBolivia);
        //10
        this.botonParaguay.setBounds(460, 380, 90, 20);
        this.botonParaguay.setBackground(Color.WHITE);
        this.botonParaguay.setForeground(Color.BLACK);
        this.botonParaguay.setFocusPainted(false);
        this.botonParaguay.setBorderPainted(false);
        this.add(botonParaguay);
        //11
        this.botonChile.setBounds(100, 400, 70, 20);
        this.botonChile.setBackground(Color.WHITE);
        this.botonChile.setForeground(Color.BLACK);
        this.botonChile.setFocusPainted(false);
        this.botonChile.setBorderPainted(false);
        this.add(botonChile);
        //12
        this.botonArgentina.setBounds(230, 420, 90, 20);
        this.botonArgentina.setBackground(Color.WHITE);
        this.botonArgentina.setForeground(Color.BLACK);
        this.botonArgentina.setFocusPainted(false);
        this.botonArgentina.setBorderPainted(false);
        this.add(botonArgentina);
        //13
        this.botonUruguay.setBounds(360, 470, 85, 20);
        this.botonUruguay.setBackground(Color.WHITE);
        this.botonUruguay.setForeground(Color.BLACK);
        this.botonUruguay.setFocusPainted(false);
        this.botonUruguay.setBorderPainted(false);
        this.add(botonUruguay);
    }

    public ArrayList<Imagenes> getImgs() {
        return imgs;
    }
    public ArrayList<PuntoPais> getPaises(){
        return this.paises;
    }
    public ArrayList<PuntoPais> getPaisesAliados() {
        return paisesAliados;
    }
    public ArrayList<PuntoPais> getPaisesEnemigos() {
        return paisesEnemigos;
    }
    
    
    //Getters de los botones de los paises
    public JButton getBotonColombia() {
        return botonColombia;
    }
    public JButton getBotonVenezuela() {
        return botonVenezuela;
    }
    public JButton getBotonBrasil() {
        return botonBrasil;
    }
    public JButton getBotonEcuador() {
        return botonEcuador;
    }
    public JButton getBotonPeru() {
        return botonPeru;
    }
    public JButton getBotonGuyana() {
        return botonGuyana;
    }
    public JButton getBotonSuriname() {
        return botonSuriname;
    }
    public JButton getBotonGuyanaFrancesa() {
        return botonGuyanaFrancesa;
    }
    public JButton getBotonBolivia() {
        return botonBolivia;
    }
    public JButton getBotonParaguay() {
        return botonParaguay;
    }
    public JButton getBotonChile() {
        return botonChile;
    }
    public JButton getBotonArgentina() {
        return botonArgentina;
    }
    public JButton getBotonUruguay() {
        return botonUruguay;
    }
}
