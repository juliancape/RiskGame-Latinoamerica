/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vistaR.Ventana;

/**
 *
 * @author Julian Cardenas - Daniel Velasquez
 */
public class Operacion {
    private Ventana ventana = null;
    public Operacion(Ventana v){
        this.ventana=v;
    }
    /**
     * Metodo para generar dos paises que van a ser usados por la IA para su movimiento
     * @return array con dos paises, pais[0] es el pais inicial, pais[1] es el pais destino
     */
    public String[] paisesIA(){
        //Arreglo donde se guardaran los paises inicio[0] y destino [1]
        String[] paisesMov = new String[2];
        Random r = new Random();
        boolean can = false;
        int i = 0;
        aumentoTropas("Enemigos");
        //Loop hasta que se encuentre un pais de las lineas enemigas cuyo pais vecino sea parte de los aliados, para atacar
        //Loop hasta que se determine si no hay ningun pais en las cercanias para atacar
        while(!can){
            //Si el index es menor que el tamaño de las listas enemigas
            //Es decir va a seguir buscando un pais vecino para atacar
            if(i < ventana.getPanel().getPaisesEnemigos().size()){
                //Se obtiene el listado de vecinos de cada pais perteneciente a las lienas enemigas
                //ArrayList<String> vecinosPais = ventana.getPanel().getPaisesEnemigos().get(i).getVecinos();
                
                if(ventana.getPanel().getPaisesEnemigos().get(i).getSoldados()>1){
                    //Se obtiene el listado de vecinos de cada pais perteneciente a las lienas enemigas
                    ArrayList<String> vecinosPais = ventana.getPanel().getPaisesEnemigos().get(i).getVecinos();
                    //Se recorre el listado de vecinos
                    for (int j = 0; j < vecinosPais.size(); j++) {
                        String pais = vecinosPais.get(j);
                        //Se obtiene el pais vecino
                        PuntoPais destino = ventana.getPanel().getPaises().stream().filter(p->p.getPais().equals(pais)).findAny().get();
                        //Si el pais vecino es parte de los aliados
                        if(destino.getPropiedad().equals("Aliados")){
                            //Como pais inicio se toma el pais perteneciente a los enemigos
                            paisesMov[0] = ventana.getPanel().getPaisesEnemigos().get(i).getPais();
                            //Como pais destino se toma el pais perteneciente a los aliados
                            paisesMov[1] = pais;
                            can = true;
                        }
                    }
                }
            }
            //Si el index es igual que el tamaño de las listas enemigas
            //Es decir que en las cercanias de las lineas enemigas no hay ningun pais por atacar
            else{
                //System.out.println("pais--");
                int v = r.nextInt(ventana.getPanel().getPaisesEnemigos().size());
                //Se obtiene un pais random de las lineas enemigas
                PuntoPais paisI = ventana.getPanel().getPaisesEnemigos().get(v);
                if(paisI.getSoldados()>1){
                    int v2 = r.nextInt(paisI.getVecinos().size());
                    //Se obtiene un pais random de los vecinos del pais inicio
                    String destino = paisI.getVecinos().get(v2);
                    //Como pais inicio
                    paisesMov[0] = paisI.getPais();
                    //Como pais destino; vecino de pais inicio
                    paisesMov[1] = destino;
                    can = true;
                }
            }
            i++;
        }
          return paisesMov;
    }
    
    /**
     * Metodo que se ejecuta cuando el usuario y la maquina elijen un pais de inicio
     * @param paisI pais de inicio
     * @param bando Aliados o Enemigos
     */
    public void accionInicial(String paisI, String bando){
        Random r= new Random();
        if(ventana.getPanel().getPaises().stream().filter(p->p.getPais().equals(paisI)).findAny().isPresent()){
            PuntoPais pais = ventana.getPanel().getPaises().stream().filter(p->p.getPais().equals(paisI)).findAny().get();
            //Si el pais de inicio es para los aliados
            if(bando.equals("Aliados")){
                //Se declara el pais inicio como propiedad de los aliados
                pais.setPropiedad("Aliados");
                //Se declara una cantidad inicial de 10 soldados
                pais.setSoldados(10);
                 for (int i = 0; i < ventana.getPanel().getPaises().size(); i++) {
                    if (paisI.equals(ventana.getPanel().getPaises().get(i).getPais())) {
                        ventana.getPanel().getPaises().get(i).setPropiedad("Aliados");
                        ventana.getPanel().getPaises().get(i).setSoldados(10);
                    }
                 }
                //Se añade el pais inicio a las lineas de los aliados
                ventana.getPanel().getPaisesAliados().add(pais);
                //System.out.println(ventana.getPanel().getPaisesAliados().get(0));
                do {
                    int b=0;
                    boolean boleano=false;
                    while (!boleano) {
                        b = r.nextInt(13);
                        if (ventana.getPanel().getPaises().get(b).getSoldados() == 0) {
                            boleano = true;
                        }
                    }
                    if (ventana.getPanel().getPaisesAliados().size()<6) {
                        PuntoPais paisesAliados= ventana.getPanel().getPaises().get(b);
                        paisesAliados.setPropiedad("Aliados");
                        //ventana.getPanel().getPaises().get(b).setPropiedad("Aliados");
                        //ventana.getPanel().getPaises().get(b).setSoldados(1);
                        paisesAliados.setSoldados(1);
                        //System.out.println(paisesAliados);
                        ventana.getPanel().getPaisesAliados().add(paisesAliados);
                    }
                } while (ventana.getPanel().getPaisesAliados().size()<6 );
                ventana.getPanel().repaint();

            }
            //Si el pais de inicio es para los enemigos
            else{
                PuntoPais pi = ventana.getPanel().getPaises().stream().filter(p->p.getPais().equals(paisI)).findAny().get();
                //Se declara el pais inicio como propiedad de los enemigos
                pi.setPropiedad("Enemigos");
                //Se declara una cantidad inicial de 10 soldados
                pi.setSoldados(10);
                
                for (int i = 0; i < ventana.getPanel().getPaises().size(); i++) {
                    if (paisI.equals(ventana.getPanel().getPaises().get(i).getPais())) {
                        ventana.getPanel().getPaises().get(i).setPropiedad("Enemigos");
                        ventana.getPanel().getPaises().get(i).setSoldados(10);
                    }
                }
                //Se añade el pais inicio a las lineas de los enemigos
                ventana.getPanel().getPaisesEnemigos().add(pi);
                JOptionPane.showMessageDialog(null,"El enemigo comenzo su movimiento en " + pi.getPais());
                do {
                    int b=0;
                    boolean boleano=false;
                    while (!boleano) {
                        b=r.nextInt(13);
                        if (ventana.getPanel().getPaises().get(b).getSoldados() == 0) {
                            //System.out.println("pais vacio : " + ventana.getPanel().getPaises().get(b).getPais() + " soldados: " 
                              //      + ventana.getPanel().getPaises().get(b).getSoldados());
                            boleano=true;
                        }
                    }
                    if (ventana.getPanel().getPaisesEnemigos().size()<6) {
                        PuntoPais pa= ventana.getPanel().getPaises().get(b);
                        //System.out.println(pa.getPais());
                        pa.setPropiedad("Enemigos");
                        //ventana.getPanel().getPaises().get(b).setPropiedad("Enemigos");
                        //ventana.getPanel().getPaises().get(b).setSoldados(1);
                        pa.setSoldados(1);
                        ventana.getPanel().getPaisesEnemigos().add(pa);
                    }
                } while (ventana.getPanel().getPaisesEnemigos().size()<6);
            }
            ventana.getPanel().repaint();
            //System.out.println(ventana.getPanel().getPaisesAliados().size());
            //System.out.println(ventana.getPanel().getPaisesEnemigos().size());
        }
    }
    
    private boolean cambioTurno=false;
    public boolean isCambioTurno() {
        return cambioTurno;
    }
    
    /**
     * Metodo para acciones a realizar (movilizar tropas, conquistar territorios, atacar territorios)
     * @param pais1 pais inicio
     * @param pais2 pais destino (al que se quiere movilizar)
     * @param bando Aliados o Enemigos 
     */
    public void accion(String pais1, String pais2, String bando){
        //System.out.println(ventana.getPanel().getPaisesAliados().size());
        //System.out.println(ventana.getPanel().getPaisesEnemigos().size());
        //System.out.println(ventana.getPanel().getPaisesAliados().toString());
        JFrame jFrame = new JFrame();
        //Si la accion es de los aliados
        if(bando.equals("Aliados")){
            //Si el pais inicio esta presente en la lista de paisesAliados
            if(ventana.getPanel().getPaisesAliados().stream().filter(p->p.getPais().equals(pais1)).findAny().isPresent()){
                PuntoPais pais = ventana.getPanel().getPaisesAliados().stream().filter(p->p.getPais().equals(pais1)).findAny().get();
                //Si el destino es un vecino del pais inicio
                if(pais.getVecinos().stream().filter(p->p.equals(pais2)).findAny().isPresent()){
                    PuntoPais destino = ventana.getPanel().getPaises().stream().filter(p->p.getPais().equals(pais2)).findAny().get();
                    //Si el destino ya esta en la lista de paisesAliados
                    //Se pregunta cuantos soldados se desean mover al destino
                    if(ventana.getPanel().getPaisesAliados().stream().filter(p->p.getPais().equals(destino.getPais())).findAny().isPresent()){
                        //Obtiene el numero de soldados en el pais inicio
                        int sI = pais.getSoldados();
                        boolean can = false;
                        String s = null;
                        //Loop hasta que se ingrese un valor valido
                        while(!can){
                            //Mensaje emergente preguntando cuantos soldados se desean movilizar
                            s = JOptionPane.showInputDialog(jFrame, "¿Cuantos solados desea movilizar? \n Disponibles: " + sI);
                            //Si se cancela el movimiento
                            if(s == null){
                                JOptionPane.showMessageDialog(null, "Movimiento a " + destino.getPais() + " cancelado");
                                can = true;
                            }
                            //Si se realiza el movimiento
                            else{
                                //Si el numero ingresado es mayor que los soldados disponibles
                                if(Integer.parseInt(s) > sI){
                                    can = false;
                                    JOptionPane.showMessageDialog(null, "Soldados insuficientes para el movimiento");
                                }
                                //Si el numero ingresado es menor o igual a 0
                                else if(Integer.parseInt(s) <= 0){
                                    can = false;
                                    JOptionPane.showMessageDialog(null, "Al menos 1 soldado para realizar el movimiento");
                                }
                                else if(Integer.parseInt(s) == sI){
                                    can = false;
                                    JOptionPane.showMessageDialog(null, "No se pueden movilizar todos los soldados de " + pais.getPais() );
                                }
                                //Si el numero ingresado es valido
                                else{
                                    //System.out.println("jaja");
                                    int sN = Integer.parseInt(s);
                                    //Se actualiza la cantidad de soldados del destino
                                    destino.setSoldados(destino.getSoldados()+sN);
                                    //Se actualiza la cantidad de soldados del pais inicio
                                    pais.setSoldados(sI-sN);
                                    can = true;
                                    //indica el cambio de turno
                                    cambioTurno= true;
                                }
                            }
                        }
                        
                    //Si el destino no esta en la lista de paisesAliados
                    //Se pregunta cuantos soldados se desean mover al destino
                    //Se añade el destino a la lista de paisesAliados
                    }else{
                        //Si el destino no tiene propiedad
                        if(destino.getPropiedad().equals("")){
                            //Obtiene el numero de soldados en el pais inicio
                            int sI = pais.getSoldados();
                            boolean can = false;
                            String s = null;
                            //Loop hasta que se ingrese un valor valido
                            while(!can){
                                //Mensaje emergente preguntando cuantos soldados se desean movilizar
                                s = JOptionPane.showInputDialog(jFrame, "¿Cuantos solados desea movilizar? \n Disponibles: " + sI);
                                //Si se cancela el movimiento
                                if(s == null){
                                    JOptionPane.showMessageDialog(null, "Movimiento a " + destino.getPais() + " cancelado");
                                    can = true;
                                }
                                //Si se realiza el movimiento
                                else{
                                    //Si el numero ingresado es mayor que los soldados disponibles
                                    if(Integer.parseInt(s) > sI){
                                        can = false;
                                        JOptionPane.showMessageDialog(null, "Soldados insuficientes para el movimiento");
                                    }
                                    //Si el numero ingresado es menor o igual a 0
                                    else if(Integer.parseInt(s) <= 0){
                                        can = false;
                                        JOptionPane.showMessageDialog(null, "Al menos 1 soldado para realizar el movimiento");
                                    }
                                    else if(Integer.parseInt(s) == sI){
                                        can = false;
                                        JOptionPane.showMessageDialog(null, "No se pueden movilizar todos los soldados de " + pais.getPais() );
                                    }
                                    //Si el numero ingresado es valido
                                    else{
                                        int sN = Integer.parseInt(s);
                                        //Se actualiza la cantidad de soldados del destino
                                        destino.setSoldados(sN);
                                        //Se declara el destino como propiedad de los aliados
                                        destino.setPropiedad("Aliados");
                                        //Se actualiza la cantidad de soldados del pais inicio
                                        pais.setSoldados(sI-sN);
                                        //Se añade el destino a las lineas de los aliados
                                        ventana.getPanel().getPaisesAliados().add(destino);
                                        can = true;
                                        //indica el cambio de turno
                                        cambioTurno= true;
                                    }
                                }
                            }
                        }
                        //Si el destino es propiedad enemiga
                        else{
                            //Mensaje emergente indicando que el ataque es de los aliados
                            JOptionPane.showMessageDialog(null, "ATAQUE DE LOS ALIADOS \n" + pais.getPais() + " vs " + destino.getPais());
                            ataque(pais.getPais(), destino.getPais(), "Aliados");
                            //indica el cambio de turno
                            cambioTurno= true;
                        }
                    }
                    //Si el pais inicio (despues del movimiento) se queda sin soldados, la propiedad del pais queda sin propietario
                    if(pais.getSoldados() == 0){
                        JOptionPane.showMessageDialog(null, "No quedan soldados en " + pais.getPais());
                        pais.setPropiedad("");
                        ventana.getPanel().getPaisesAliados().remove(pais);
                    }
                }
                //Si el destino no es un vecino del pais inicio
                else{
                    JOptionPane.showMessageDialog(null,"Pais no disponible para movilizar");
                }
            }
            
            //Si el pais inicio no es parte de los paisesAliados
            else{
                JOptionPane.showMessageDialog(null,"Pais no aliado");
            }
            ventana.getPanel().repaint();
            //aumentoTropas(bando);
            ventana.getPanel().repaint();
        }
        
        //Si la accion es de los enemigos
        else if(bando.equals("Enemigos")){
            Random r = new Random();
            PuntoPais pais = ventana.getPanel().getPaisesEnemigos().stream().filter(p->p.getPais().equals(pais1)).findAny().get();
            //Obtiene un numero random de soldados entre 1 y el disponible
            //System.out.println(pais.getPais() + " enemigo -  soldados " + pais.getSoldados());
            //int s = r.nextInt((pais.getSoldados()-1)-1)+1;
            int s=0;
            if(pais.getSoldados()>2){
                s = r.nextInt((pais.getSoldados()-1)-1)+1;
            }else{
                s = 1;
            }
            
            //System.out.println("\n");
            //for (int i = 0; i < pais.getSoldados(); i++) {
            //    System.out.println(r.nextInt((pais.getSoldados()-1)-1)+1);
            //}
            //Si el destino es parte de los paisesEnemigos
            if(ventana.getPanel().getPaisesEnemigos().stream().filter(p->p.getPais().equals(pais2)).findAny().isPresent()){
                PuntoPais destino = ventana.getPanel().getPaisesEnemigos().stream().filter(p->p.getPais().equals(pais2)).findAny().get();
                //Se actualizan la cantidad de soldados enemigos del destino
                destino.setSoldados(destino.getSoldados()+s);
                //Se actualizan la cantidad de soldados enemigos del pais inicio
                pais.setSoldados(pais.getSoldados()-s);
                //Mensaje emergente que menciona el movimiento enemigo
                JOptionPane.showMessageDialog(null,"El enemigo movilizo tropas de " + pais.getPais() + " a " + destino.getPais());
            }
            //Si el destino no es parte de los paisesEnemigos
            else{
                PuntoPais destino = ventana.getPanel().getPaises().stream().filter(p->p.getPais().equals(pais2)).findAny().get();
                //Si el destino no tiene propiedad
                if(destino.getPropiedad().equals("")){
                    //Se actualiza la cantidad de soldados del destino
                    destino.setSoldados(s);
                    //Se actualiza la cantidad de soldados del pais inicio
                    pais.setSoldados(pais.getSoldados()-s);
                    //Se declara el destino como propiedad de los enemigos
                    destino.setPropiedad("Enemigos");
                    //Se añade el destino a las lineas de los enemigos
                    ventana.getPanel().getPaisesEnemigos().add(destino);
                    JOptionPane.showMessageDialog(null,"El enemigo se movilizo de " + pais.getPais() + " a " + destino.getPais());
                }
                //Si el destino es de propiedad de los aliados
                else{
                    JOptionPane.showMessageDialog(null, "ATAQUE DE LOS ENEMIGOS \n" + pais.getPais() + " vs " + destino.getPais());
                    ataque(pais.getPais(), destino.getPais(), "Enemigos");
                }
            }
            //Si el pais inicio (despues del movimiento) se queda sin soldados, la propiedad del pais queda sin propietario
            if(pais.getSoldados() == 0){
                pais.setPropiedad("");
                ventana.getPanel().getPaisesEnemigos().remove(pais);
            }
            ventana.getPanel().repaint();
            
        }
    }
    
    
    /**
     * Metodo para que por cada turno se agrege determinada cantidad de soldados al inicio de cada 
     * accion, sea de el jugador o CPU
     * @param bando para determinar si es Aliados o Enemigos
     */
    public void aumentoTropas(String bando){
        //Operacion para Aliados (persona)
        if (bando.equals("Aliados")) {
            JFrame jFrame = new JFrame();
            int nuevosSoldados= 2+ventana.getPanel().getPaisesAliados().size();
            boolean encontrado=false;
            //Bucle hasta que la agregacion de soldados sea exitosa por parte del jugador
            do {                
                String s=null;
            s = JOptionPane.showInputDialog(jFrame, "CARTA NUEVOS SOLDADOS \nA Que pais va a agregar los " +nuevosSoldados + " soldados. \n Ingrese el nombre:" );
            
            for (int i = 0; i < ventana.getPanel().getPaisesAliados().size(); i++) {
                if (s.equals(ventana.getPanel().getPaisesAliados().get(i).getPais())) {
                    if (ventana.getPanel().getPaisesAliados().get(i).getPropiedad().equals("Aliados")) {
                        int soldadosdelPais= ventana.getPanel().getPaisesAliados().get(i).getSoldados();
                      ventana.getPanel().getPaisesAliados().get(i).setSoldados(nuevosSoldados+soldadosdelPais);
                      encontrado=true;  
                    }
                }
            }  if (encontrado==false) {
                JOptionPane.showMessageDialog(null, "Pais ingresado no encontrado");   
            }
            } while (encontrado!=true);
            
             //JOptionPane.showMessageDialog(null, "A Que pais va a agregar los soldados");
            
         //Operacion para Enemigos (CPU)    
        }if (bando.equals("Enemigos")) {
            Random r = new Random();
            boolean existe=false;
            ArrayList<String> paisesEnemigosEnPeligro= new ArrayList<>();
             int nuevosSoldados= 3+ventana.getPanel().getPaisesEnemigos().size();
             //Por cada pais enemigo se conseguira sus vecinos para determinar si el pais enemigo esta en
             //riesgo al estar cerca de un pais Aliado, para asi agregar los soldados nuevos estrategicamente
            ArrayList<String> listaPaises= new ArrayList<>();
            //Variables para encontrar el primer y segungo pais aliado con mayor  cantidad de soldados
            int paisAliadoMayorSoldados=0;
            String nombreAliadoMayorSoldados="";
            //Operacion para encontrar el pais aliado con mayor cantidad de soldados
            for (int i = 0; i < ventana.getPanel().getPaisesAliados().size(); i++) {
                if (ventana.getPanel().getPaisesAliados().get(i).getSoldados()>paisAliadoMayorSoldados) {
                    paisAliadoMayorSoldados=ventana.getPanel().getPaisesAliados().get(i).getSoldados();
                    nombreAliadoMayorSoldados=ventana.getPanel().getPaisesAliados().get(i).getPais();
                }
                
                
        }
    
            
           // System.out.println("1"+nombreAliadioMayorSoldados +"2"+nombreSegundoPaisAliadoMayorSoldados);
                
             for (int i = 0; i < ventana.getPanel().getPaisesEnemigos().size(); i++) {
            listaPaises.addAll(ventana.getPanel().getPaisesEnemigos().get(i).getVecinos());
           
                //Agregar  los paises enemigos que tengan de vecino a algun pais aliado a el arrayList paisesEnemigosEnPeligro
                for (int j = 0; j < listaPaises.size(); j++) {
                    for (int k = 0; k < ventana.getPanel().getPaises().size(); k++) {
                        if (listaPaises.get(j).getBytes().equals(ventana.getPanel().getPaises().get(k).getPais())) {
                            if (ventana.getPanel().getPaises().get(k).getPropiedad().equals("Aliados")) {
                                paisesEnemigosEnPeligro.add(ventana.getPanel().getPaises().get(k).getPais());
                            }
                    }
                    }
                }
 
            }
             //Agregar los vecinos del pais  aliado con mayor tropas
             ArrayList<String> veciniosPaisMayorSoldados= new ArrayList<>();
              for (int i = 0; i < ventana.getPanel().getPaises().size();i++) {
                       
                          if (ventana.getPanel().getPaises().get(i).getPropiedad().equals("Aliados") &&
                                  ventana.getPanel().getPaises().get(i).getPais().equals(nombreAliadoMayorSoldados)) {
                            veciniosPaisMayorSoldados.addAll(ventana.getPanel().getPaises().get(i).getVecinos());
                          }
                          
              }
              //Remover de la lista de los vecinos los paises que sean Aliados
              for (int i = 0; i < ventana.getPanel().getPaisesEnemigos().size(); i++) {
                          if (!veciniosPaisMayorSoldados.isEmpty()) {
                              for (int j = 0; j < veciniosPaisMayorSoldados.size(); j++) {
                                //  if (veciniosPaisMayorSoldados.get(j).getBytes().equals(ventana.getPanel().getPaisesEnemigos().get(i).getPais() )) {
                                      for (int k = 0; k < ventana.getPanel().getPaises().size(); k++) {
                                          if (ventana.getPanel().getPaises().get(k).getPais().equals(veciniosPaisMayorSoldados.get(j).getBytes()) &&
                                                  ventana.getPanel().getPaises().get(k).getPropiedad().equals("Aliados")) {
                                              veciniosPaisMayorSoldados.remove(j);
                                          }
                                      }
                               //   
                              }
                      }
                  
                  
          /*          if (!paisesEnemigosEnPeligro.isEmpty() &&ventana.getPanel().getPaisesEnemigos().get(i).equals(paisesEnemigosEnPeligro.get(0).getBytes())) {
                        int soldadosdelPais= ventana.getPanel().getPaisesEnemigos().get(i).getSoldados();
                        ventana.getPanel().getPaisesEnemigos().get(i).setSoldados(nuevosSoldados+soldadosdelPais);
                        existe=true;
                    }*/
                }
              
              for (int i = 0; i < veciniosPaisMayorSoldados.size(); i++) {
                   for (int j = 0; j < ventana.getPanel().getPaisesEnemigos().size(); j++) {
                       if (ventana.getPanel().getPaisesEnemigos().get(j).getPais().equals(veciniosPaisMayorSoldados.get(i).getBytes())&&
                               !paisesEnemigosEnPeligro.isEmpty()) {
                            int soldadosdelPais= ventana.getPanel().getPaisesEnemigos().get(j).getSoldados();
                            existe=true;
                       }
                  }
        }
              
            //Si dentro de la lista de paises enemigos no esta peligrando ningun pais, se agrega aleatoriamente a alguno de estos
            if (existe==false) {
            int x = r.nextInt(ventana.getPanel().getPaisesEnemigos().size());
            int soldadosdelPais= ventana.getPanel().getPaisesEnemigos().get(x).getSoldados();
            ventana.getPanel().getPaisesEnemigos().get(x).setSoldados(nuevosSoldados+soldadosdelPais);
        } 
        }ventana.getPanel().repaint();
        }
        
    

    
    /**
     * Metodo que indica el ataque entre un pais aliado y un pais enemigo
     * @param pais1 nombre del pais aliado
     * @param pais2 nombre del pais enemigo
     * @param bando bando al que se le atribuye el ataque
     */
    public void ataque(String pais1, String pais2, String bando){
        JFrame jFrame = new JFrame();
        //Si el ataque lo hacen los aliados
        if(bando.equals("Aliados")){
            PuntoPais pAliado = ventana.getPanel().getPaisesAliados().stream().filter(p->p.getPais().equals(pais1)).findAny().get();
            PuntoPais pEnemigo = ventana.getPanel().getPaisesEnemigos().stream().filter(p->p.getPais().equals(pais2)).findAny().get();
            lineaAtaque(pAliado, pEnemigo, jFrame);
            ventana.getPanel().repaint();
        }
        //Si el ataque lo hacen los enemigos
        else{
            PuntoPais pEnemigo = ventana.getPanel().getPaisesEnemigos().stream().filter(p->p.getPais().equals(pais1)).findAny().get();
            PuntoPais pAliado = ventana.getPanel().getPaisesAliados().stream().filter(p->p.getPais().equals(pais2)).findAny().get();
            lineaAtaque(pAliado, pEnemigo, jFrame);
            ventana.getPanel().repaint();
        }
    }
    
    /**
     * Metodo que contiene las condiciones para un ataque entre dos paises
     * @param pAliado pais perteneciente a los aliados
     * @param pEnemigo pais perteneciente a los enemigos
     * @param jFrame objeto de la clase JFrame para generar ventanas emergentes
     */
    public void lineaAtaque(PuntoPais pAliado, PuntoPais pEnemigo, JFrame jFrame){
       Random r = new Random();
        //Ciclo while para generar los ataques entre los dos paises
        //System.out.println("Estado inicial" + pEnemigo.getSoldados());
        while(pAliado.getSoldados()!=0 && pEnemigo.getSoldados()!=0){
            int s1 = pAliado.getSoldados();
            int s2 = pEnemigo.getSoldados();
            int suma = s1+s2;
            int dado = r.nextInt(suma);
            //Si los soldados aliados son mayores que los soldados enemigos
            if (s1>s2) {
                //Ataque para pAliado
                if (dado >= s2) {
                    pEnemigo.setSoldados(pEnemigo.getSoldados()-1);
                }//Ataque para pEnemigo
                else {
                    pAliado.setSoldados(pAliado.getSoldados()-1);
                }
            }
            //Si los soldados enemigos son mayores que los soldados aliados
            else if (s2 > s1) {
                //Ataque para pEnemigo
                if (dado >= s1) {
                    pAliado.setSoldados(pAliado.getSoldados()-1);
                }//Ataque para pAliado
                else {
                    pEnemigo.setSoldados(pEnemigo.getSoldados()-1);
                }
            }
            //Si los soldados aliados y enemigos son de igual cantidad -> random
            else {
                //Ataque para pAliado -- Genera un true
                if (r.nextBoolean()) {
                    pEnemigo.setSoldados(pEnemigo.getSoldados()-1);
                }//Ataque para pEnemigo -- False
                else {
                    pAliado.setSoldados(pAliado.getSoldados()-1);		
                }	
            }
        }
        
        //Linea de codigo si los enemigos ganan y el pais aliado se queda sin soldados
        if(pAliado.getSoldados() == 0){
            
            //Se elimina el pAliado
            ventana.getPanel().getPaisesAliados().remove(pAliado);
            ventana.getPanel().repaint();
            int sI = pEnemigo.getSoldados();
            if(sI>1){
                //Obtiene un numero random de soldados entre 1 y el disponible
                //System.out.println("cant sol " + pEnemigo.getSoldados());
                int s=0;
                if(pEnemigo.getSoldados()>2){
                    s = r.nextInt((pEnemigo.getSoldados()-1)-1)+1;
                }else{
                    s = 1;
                }
                //int s = r.nextInt((pEnemigo.getSoldados()-1)-1)+1;
                //Se movilizan soldados enemigos al pais destino
                pAliado.setSoldados(s);
                //Se actualizan la cantidad de soldados enemigos del pais inicio
                pEnemigo.setSoldados(pEnemigo.getSoldados()-s);
                //Se declara el pais destino como propiedad de los enemigos
                pAliado.setPropiedad("Enemigos");
                //Se añade el pais destino a las lineas de los enemigos
                ventana.getPanel().getPaisesEnemigos().add(pAliado);
            }
            //Obtiene un numero random de soldados entre 1 y el disponible
            /*int s = r.nextInt((pEnemigo.getSoldados()-1)+1)+1;
            //Se movilizan soldados enemigos al pais destino
            pAliado.setSoldados(s);
            //Se actualizan la cantidad de soldados enemigos del pais inicio
            pEnemigo.setSoldados(pAliado.getSoldados()-s);
            //Se declara el pais destino como propiedad de los enemigos
            pAliado.setPropiedad("Enemigos");
            //Se añade el pais destino a las lineas de los enemigos
            ventana.getPanel().getPaisesEnemigos().add(pAliado);*/
            //Si el pais inicio (despues del movimiento) se queda sin soldados, la propiedad del pais queda sin propietario
            if(pEnemigo.getSoldados() == 0){
                pEnemigo.setPropiedad("");
                ventana.getPanel().getPaisesEnemigos().remove(pEnemigo);
            }
            //Mensaje emergente de la victoria enemiga
            JOptionPane.showMessageDialog(null,"VICTORIA ENEMIGA \nEl enemigo se movilizo de " + pEnemigo.getPais() + " a " + pAliado.getPais());
        }
        
        //Lineas de codigo si los aliados ganan y el pais enemigo se queda sin soldados
        else{
            //Se elimina el pEnemigo
            ventana.getPanel().getPaisesEnemigos().remove(pEnemigo);
            ventana.getPanel().repaint();
            //Obtiene el numero de soldados del pais inicio
            int sI = pAliado.getSoldados();
            boolean can = false;
            String s = null;
            if(sI>1){
                //Loop hasta que se ingrese un valor valido
                while(!can){
                    //Mensaje emergente de la victoria aliada y se recibe el numero de soldados disponimbles a movilizar
                    s = JOptionPane.showInputDialog(jFrame, "VICTORIA ALIADA \n¿Cuantos solados desea movilizar? \n Disponibles: " + sI);
                    //Si se cancela el movimiento
                    if(s == null){
                        JOptionPane.showMessageDialog(null, "Movimiento a " + pEnemigo.getPais() + " cancelado");
                        can = true;
                    }
                    //Si se realiza el movimiento
                    else{
                        //Si el numero ingresado es mayor que los soldados disponibles
                        if(Integer.parseInt(s) > sI){
                            can = false;
                            JOptionPane.showMessageDialog(null, "Soldados insuficientes para el movimiento");
                        }
                        //Si el numero ingresado es menor o igual a 0
                        else if(Integer.parseInt(s) <= 0){
                            can = false;
                            JOptionPane.showMessageDialog(null, "Al menos 1 soldado para realizar el movimiento");
                        }
                        else if(Integer.parseInt(s) == sI){
                            can = false;
                            JOptionPane.showMessageDialog(null, "No se pueden movilizar todos los soldados de " + pAliado.getPais() );
                        }
                        //Si el numero ingresado es valido
                        else{
                            int sN = Integer.parseInt(s);
                            //Se movilizan soldados aliados al pais destino
                            pEnemigo.setSoldados(sN);
                            //Se declara el pais destino como propiedad de los aliados
                            pEnemigo.setPropiedad("Aliados");
                            //Se actualizan la cantidad de soldados enemigos del pais inicio
                            pAliado.setSoldados(sI-sN);
                            //Se añade el pais destino a las lineas de los aliados
                            ventana.getPanel().getPaisesAliados().add(pEnemigo);
                            can = true;
                        }
                    }
                }
                //Si el pais inicio (despues del movimiento) se queda sin soldados, la propiedad del pais queda sin propietario
                if(pAliado.getSoldados() == 0){
                    JOptionPane.showMessageDialog(null, "No quedan soldados en " + pAliado.getPais());
                    pAliado.setPropiedad("");
                    ventana.getPanel().getPaisesAliados().remove(pAliado);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Solo queda un soldado en " + pAliado.getPais() +"\nNo es posible movilizar");
            }
        }
    }
    
    //Metodo en no uso
    /*public void generarImagenes(){
        Image imgTitle = new ImageIcon("src/main/java/Images/titulo.png").getImage();
        ventana.getPanel().getImgs().add(new Imagenes(imgTitle,0,0,200,400));
        Image imgPlaneta = new ImageIcon("src/main/java/Images/planeta.png").getImage();
        ventana.getPanel().getImgs().add(new Imagenes(imgPlaneta,100,100,200,400));
    }*/
    
    /**
     * Metodo para generar la lista de paises con su respectivos datos
     */
    public void generarPaisesYVecinos() {
        //Argentina
        String [] argentinaVecinos = {"Chile","Bolivia","Paraguay","Brasil","Uruguay"};
        ventana.getPanel().getPaises().add(new PuntoPais("Argentina", 260, 440, 0, "",new ArrayList<String>(Arrays.asList(argentinaVecinos))));
        //Bolivia
        String [] boliviaVecinos = {"Brasil","Paraguay","Argentina","Chile","Peru"};
        ventana.getPanel().getPaises().add(new PuntoPais("Bolivia", 240, 290, 0, "",new ArrayList<String>(Arrays.asList(boliviaVecinos))));
        //Brasil
        String [] brasilVecinos = {"Uruguay","Argentina","Paraguay","Bolivia","Peru","Colombia","Venezuela","Guyana","Suriname","GuyanaFrancesa"};
        ventana.getPanel().getPaises().add(new PuntoPais("Brasil", 370, 230, 0, "",new ArrayList<String>(Arrays.asList(brasilVecinos))));
        //Chile
        String [] chileVecinos = {"Peru","Bolivia","Argentina"};
        ventana.getPanel().getPaises().add(new PuntoPais("Chile", 195, 380, 0, "",new ArrayList<String>(Arrays.asList(chileVecinos))));
        //Colombia
        String [] colombiaVecinos = {"Venezuela", "Ecuador", "Peru","Brasil"};
        ventana.getPanel().getPaises().add(new PuntoPais("Colombia", 130, 96, 0, "",new ArrayList<String>(Arrays.asList(colombiaVecinos))));
        //Ecuador
        String [] ecuadorVecinos = {"Colombia","Peru"};
        ventana.getPanel().getPaises().add(new PuntoPais("Ecuador", 70, 135, 0, "",new ArrayList<String>(Arrays.asList(ecuadorVecinos))));
        //Guyana
        String [] guyanaVecinos = {"Venezuela","Brasil","Suriname"};
        ventana.getPanel().getPaises().add(new PuntoPais("Guyana", 316, 84, 0, "",new ArrayList<String>(Arrays.asList(guyanaVecinos))));
        //GuayanaFrancesa
        String [] guyanaFrancesaVecinos = {"Suriname","Brasil"};
        ventana.getPanel().getPaises().add(new PuntoPais("GuyanaFrancesa", 390, 74, 0, "",new ArrayList<String>(Arrays.asList(guyanaFrancesaVecinos))));
        //Paraguay
        String [] paraguayVecinos = {"Brasil","Argentina","Bolivia"};
        ventana.getPanel().getPaises().add(new PuntoPais("Paraguay", 320, 330, 0, "",new ArrayList<String>(Arrays.asList(paraguayVecinos))));
        //Peru
        String [] peruVecinos = {"Ecuador","Colombia","Chile","Bolivia","Brasil"};
        ventana.getPanel().getPaises().add(new PuntoPais("Peru", 115, 195, 0, "",new ArrayList<String>(Arrays.asList(peruVecinos))));
        //Suriname
        String [] surinameVecinos = {"Guyana", "Brasil", "GuyanaFrancesa"};
        ventana.getPanel().getPaises().add(new PuntoPais("Suriname", 352, 75, 0, "",new ArrayList<String>(Arrays.asList(surinameVecinos))));
        //Uruguay
        String [] uruguayVecinos = {"Argentina","Brasil"};
        ventana.getPanel().getPaises().add(new PuntoPais("Uruguay", 350, 430, 0, "",new ArrayList<String>(Arrays.asList(uruguayVecinos))));
        //Venezuela
        String [] venezuelaVecinos = {"Colombia","Brasil","Guyana"};
        ventana.getPanel().getPaises().add(new PuntoPais("Venezuela", 227, 62, 0, "",new ArrayList<String>(Arrays.asList(venezuelaVecinos))));
        
        ventana.getPanel().repaint();
    }
}
