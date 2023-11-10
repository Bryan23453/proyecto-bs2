/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_estructuraa;

import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Cambio_Usuario extends Thread {
    JLabel fondo2;
    JLabel fondo3;
    JLabel fondo4;
    JPanel panel;
    public Cambio_Usuario(JLabel fondo_2,JLabel fondo_3,JLabel fondo_4,JPanel panel) {
        fondo2=fondo_2;
        this.panel=panel;
        this.fondo3=fondo_3;
        this.fondo4=fondo_4;
    }

	public void run () {
        try {
            Thread.sleep(6500);
            //6500
            fondo2.setVisible(false);
            panel.setVisible(true);
            fondo3.setVisible(true);
            fondo4.setVisible(false);
        } catch (InterruptedException ex) {
           
        }
    }
}
