/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher;

import controlador.Controler;
import modelo.Operacion;
import vistaR.Ventana;

/**
 *
 * @author User
 */
public class Launcher {
    public static void main(String[] args) {
        // el new llama un metodo de la clase Ventana
        Ventana v = new Ventana();
        Operacion a = new Operacion(v);
        if(v.getPanel().getPaises().isEmpty()){
            a.generarPaisesYVecinos();
        }
        Controler c = new Controler(a,v);
    }
}
