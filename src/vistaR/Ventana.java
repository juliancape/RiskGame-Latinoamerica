/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaR;

import controlador.Controler;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Julian Cardenas - Daniel Velasquez
 */
public class Ventana extends JFrame{
    private JButton boton = new JButton("Cambio Turno");
    private JButton accion = new JButton("Accion");
    private JButton cartaSoldados= new JButton("Carta: Soldados");
    private Controler c;
    private JLabel r = new JLabel("R");
    private JLabel i = new JLabel("I");
    private JLabel s = new JLabel("S");
    private JLabel k = new JLabel("K");
    private JLabel turno= new JLabel("  Turno Aliado");
    private JLabel de= new JLabel("");
    private JLabel hasta= new JLabel("");
    private JLabel img= new JLabel("");
    
    private Panel panel = new Panel();//Se crea panel y se inicializa

    
    public Ventana(){
        //establece el tamaño de la ventana emergente
        this.setBounds(250, 20, 800, 700);
        this.getContentPane().setBackground(new Color(37, 36, 64));
        //para cerrar la ventana cuando se oprime la x de la ventana
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        //invoca los componentes para aparecer en la pantalla
        this.initComponents();
        this.setVisible(true);
        
        
    }
    
    private void initComponents(){
        this.panel.setBounds(150,0,650,690);
        this.add(panel);
        
        this.boton.setBounds(10, 20, 120, 30);
        this.boton.setBackground(Color.BLACK);
        this.boton.setForeground(Color.WHITE);
        this.add(boton);
        
        this.accion.setBounds(10,80, 120, 30);
        this.accion.setBackground(Color.RED);
        this.accion.setForeground(Color.WHITE);
        this.add(accion);
               
        Font letra1= new Font("Niagara Solid",Font.CENTER_BASELINE,60);
        r.setForeground(Color.red);
        r.setFont(letra1);
        r.setBounds(70, 440, 100, 60);
        this.add(r);
        i.setForeground(Color.red);
        i.setFont(letra1);
        i.setBounds(70, 490, 100, 60);
        this.add(i);
        s.setForeground(Color.red);
        s.setFont(letra1);
        s.setBounds(70, 540, 100, 60);
        this.add(s);
        k.setForeground(Color.red);
        k.setFont(letra1);
        k.setBounds(70, 590, 100, 60);
        this.add(k);
        
        this.de.setForeground(Color.WHITE);
        this.de.setBounds(20, 120, 300, 20);
        this.add(de);
        this.hasta.setForeground(Color.WHITE);
        this.hasta.setBounds(20, 140, 300, 20);
        this.add(hasta);
        
        this.cartaSoldados.setForeground(Color.BLACK);
        this.cartaSoldados.setBackground(Color.ORANGE);
        this.cartaSoldados.setBounds(10,175,140,30);
        this.add(cartaSoldados);
    }

    public JButton getCartaSoldados() {
        return cartaSoldados;
    }

    public void setCartaSoldados(JButton cartaSoldados) {
        this.cartaSoldados = cartaSoldados;
    }
    public JButton getBoton() {
        return boton;
    }

    public JButton getAccion() {
        return accion;
    }
    
    public void updateDe(String s){
        this.de.setText(s);
    }
    public void updateHasta(String s){
        this.hasta.setText(s);
    }
    
    //Recibe el controlador y se le entrega al boton
    public void setContoller(Controler cont){
        this.c=cont;
        this.boton.addActionListener(this.c);
        this.accion.addActionListener(this.c);
        this.panel.getBotonArgentina().addActionListener(this.c);
        this.panel.getBotonBolivia().addActionListener(this.c);
        this.panel.getBotonBrasil().addActionListener(this.c);
        this.panel.getBotonChile().addActionListener(this.c);
        this.panel.getBotonColombia().addActionListener(this.c);
        this.panel.getBotonEcuador().addActionListener(this.c);
        this.panel.getBotonGuyana().addActionListener(this.c);
        this.panel.getBotonGuyanaFrancesa().addActionListener(this.c);
        this.panel.getBotonParaguay().addActionListener(this.c);
        this.panel.getBotonPeru().addActionListener(this.c);
        this.panel.getBotonSuriname().addActionListener(this.c);
        this.panel.getBotonUruguay().addActionListener(this.c);
        this.panel.getBotonVenezuela().addActionListener(this.c);
        this.cartaSoldados.addActionListener(this.c);
    }
    // Permitir al modelo acceder al panel
    public Panel getPanel(){
        return panel;
    }
}
