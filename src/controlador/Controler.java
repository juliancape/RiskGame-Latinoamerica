/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Operacion;
import modelo.PuntoPais;
import vistaR.Ventana;

/**
 *
 * @author Julian Cardenas - Daniel Velasquez
 */
public class Controler implements ActionListener{
    private Operacion modelo;
    private Ventana vista;
    public Controler(Operacion modelo, Ventana v){
        this.modelo = modelo;
        this.vista = v;
        this.vista.setContoller(this);
    }
    
    String paisI=null;
    String pais1 = null;
    String pais2 = null;
    //Se declara como 2 ya que
    //Cuando vale 2, se toma el pais inicial de los aliados
    //Cuando vale 1, se genera el pais inicial de los enemigos
    //Cuando vale 0, se realizan las acciones posteriores
    int actBegin=2;
    //True si es turno de la maquina
    //False si es turno del usuario
    boolean cambioTurno = false;
     int cont= 1;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Si actBegin vale 0, se realizan las acciones posteriores al inicio
        if(actBegin==0){
            //Se ejecutan los botones solo si los dos bandos siguen de pie (presentes)
            if(!dtWin()){
                
                //Si el boton oprimido es Cambio Turno
                if(e.getActionCommand().equals("Cambio Turno") && cambioTurno){
                    //Se generan los paises inicio y destino del movimiento enemigo
                    String[] pIA = modelo.paisesIA();
                    modelo.accion(pIA[0], pIA[1], "Enemigos");
                    cambioTurno = false;
                }
                if(!cambioTurno){
                     if (cont==1) {
                        if (e.getActionCommand().equals("Carta: Soldados")) {
          
                    modelo.aumentoTropas("Aliados");
                    cont--;
                     }
                    }
                             
                    //Se llama el metodo para leer los botones de los paises
                    botonesPaises(e);
                }
            
                //Si el boton oprimido es Accion
                if(e.getActionCommand().equals("Accion") && !cambioTurno){
                    cont=1;
                    //Si pais1 (inicio) y pais2 (destino), ya tienen un pais asignado a cada uno
                    if(pais1!=null && pais2!=null){
                        vista.updateDe("");
                        vista.updateHasta("");
                        System.out.println(pais1 + "-" + pais2);
                        modelo.accion(pais1, pais2, "Aliados");
                        if(modelo.isCambioTurno()){
                            //System.out.println("Si");
                            cambioTurno = true;
                        }
                        //cambioTurno = true;
                    }
                    pais1=null;
                    pais2=null;
                }
            }
        }
        else{
            //Si actBegin vale 2, se toma el pais inicial de los aliados
            if(actBegin==2){
                paisInicial(e);
                modelo.accionInicial(paisI, "Aliados");
            }
            //Si actBegin vale 1, se genera el pais inicial de los enemigos
            else{
                //Si el boton oprimido es Cambio Turno
                if(e.getActionCommand().equals("Cambio Turno")){
                    //Obtiene el listado de paises
                    ArrayList<PuntoPais> paisesT = vista.getPanel().getPaises();
                    ArrayList<Integer> ix = new ArrayList<>();
                    for (int i = 0; i < vista.getPanel().getPaisesAliados().size(); i++) {
                        ix.add(paisesT.indexOf(vista.getPanel().getPaisesAliados().get(i)));
                    }
                    //ix.forEach(System.out::println);
                    //Obtiene el index del pais que a sido ya elegido como pais de inicio de los aliados
                    //int index = paisesT.indexOf(vista.getPanel().getPaisesAliados().get(0));
                    
                    
                    Random r = new Random();
                    //Se obtiene un numero random desde 0 hasta el tamaño de la lista de paises
                    int v=r.nextInt(paisesT.size());
                    //Si el numero random esta en el listado de indices de los paisesAliados
                    for (int i = 0; i < ix.size(); i++) {
                        if(ix.get(i).equals(v)){
                            while(ix.get(i).equals(v)){
                                v = r.nextInt(paisesT.size());
                            }
                        }
                    }
                    //Si el numero random es igual al index
                    /*if(v==index){
                        //Loop hasta que se obtenga un numero random diferente a index
                        while(v==index){
                            v = r.nextInt(paisesT.size());
                        }
                    }*/
                    
                    
                    //Obtiene el pais de inicio de los enemigos
                    PuntoPais pi = paisesT.get(v);
                    modelo.accionInicial(pi.getPais(), "Enemigos");
                    
                    //vista.getPanel().getPaises().forEach(System.out::println);
                    actBegin--;
                    
                }
            }
        }
       
        
    }
    
    /**
     * Metodo para leer el pais de inicio de los aliados
     * @param e ActionEvent
     */
    public void paisInicial(ActionEvent e){
        //Recorre el listado de paises
        for (int i = 0; i < vista.getPanel().getPaises().size(); i++) {
            //Se obtiene el nombre del pais
            String pais = vista.getPanel().getPaises().get(i).getPais();
            //Si el nombre del pais es igual al botonPais oprimido
            if(e.getActionCommand().equals(pais)){
                paisI=pais;
                actBegin--;
            }
        }
    }
    
    /**
     * Metodo para leer los botones de los paises
     * @param e ActionEvent
     */
    public void botonesPaises(ActionEvent e){
        //Recorre el listado de paises
        for (int i = 0; i < vista.getPanel().getPaises().size(); i++) {
            //Se obtiene el nombre del pais
            String pais = vista.getPanel().getPaises().get(i).getPais();
            //Si el nombre del pais es igual al botonPais oprimido
            if(e.getActionCommand().equals(pais)){
                if(pais1 == null){
                    if(vista.getPanel().getPaisesAliados().stream().filter(p->p.getPais().equals(pais)).findAny().isPresent()){
                        if(vista.getPanel().getPaisesAliados().stream().filter(p->p.getPais().equals(pais)).findAny().get().getSoldados()>1){
                            vista.updateDe("Desde: " + pais);
                            pais1 = pais;
                        }else{
                            JOptionPane.showMessageDialog(null, pais + " tiene un solo soldado \nNo es posible realizar alguna accion");
                        }
                    }else if(vista.getPanel().getPaisesEnemigos().stream().filter(p->p.getPais().equals(pais)).findAny().isPresent()){
                        JOptionPane.showMessageDialog(null, pais + " es de propiedad enemiga");
                    }else{
                        JOptionPane.showMessageDialog(null, pais + " no ha sido conquistado");
                    }
                    //vista.updateDe("Desde: " + pais);
                    //pais1 = pais;
                }else{
                    vista.updateHasta("Hasta: " + pais);
                    pais2 = pais;
                }
            }
        }
    }
    
    public boolean dtWin(){
        boolean win = false;
        if(vista.getPanel().getPaisesAliados().isEmpty()){
            vista.remove(vista.getBoton());
            vista.remove(vista.getAccion());
            vista.repaint();
            win = true;
            JOptionPane.showMessageDialog(null, "BAD ENDING \nLOS ENEMIGOS HAN GANADO!!! \nLos aliados han caido");
        }else if(vista.getPanel().getPaisesEnemigos().isEmpty()){
            vista.remove(vista.getBoton());
            vista.remove(vista.getAccion());
            vista.repaint();
            win = true;
            JOptionPane.showMessageDialog(null, "GOOD ENDING \nLOS ALIADOS HAN GANADO!!! \nLos enemigos han caido");
        }
        return win;
    }
}

