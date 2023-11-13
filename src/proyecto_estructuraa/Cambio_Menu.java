/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_estructuraa;

import java.awt.Label;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author bryan
 */

public class Cambio_Menu extends Thread {
    JPanel panel;
    JPanel panel4;
    JPanel panel5;
    JPanel panel6;
    JPanel panel7;
    JPanel panel8;
    JTabbedPane tabpane1;
    boolean pass;
    boolean pass2=false;
    boolean pass3=false;
    public Cambio_Menu(JPanel pane,JPanel pane4,JPanel pane5,JPanel pane6,JPanel pane7,JPanel pane8,JTabbedPane tabpane,boolean pas){
        panel=pane;
        panel4=pane4;
        panel5=pane5;
        panel6=pane6;
        panel7=pane7;
        tabpane1=tabpane;
        panel8=pane8;
        pass=pas;
        
    }

    public Cambio_Menu(JPanel panel, JPanel panel4, JTabbedPane tabpane1, boolean pass, boolean pass2) {
        this.panel = panel;
        this.panel4 = panel4;
        this.tabpane1 = tabpane1;
        this.pass = pass;
        this.pass2 = pass2;
    }
    public Cambio_Menu(JPanel panel, JPanel panel4,JPanel panel3,JPanel panel5,boolean pass3, boolean pass, boolean pass2) {
        this.panel = panel;
        this.panel4 = panel4;
        this.panel6 = panel4;
        this.panel5 = panel5;
        this.pass = pass;
        this.pass2 = pass2;
        this.pass3 = pass3;
    }
    
    public void run () {
            if (pass3) {
                int w=1230;
                    panel.setLocation(w, 0);
                    panel4.setLocation(w, 0);
                    panel6.setLocation(w, 0);
                    panel5.setLocation(w, 0);
                    while (w>80){
                    try {
                            Thread.sleep(1);
                            panel.setLocation(w,0);
                            w=w-3;
                        } catch (InterruptedException ex) {}    
                    }
            }else{
                if (pass2) {
                    if (pass) {
                    int w=1230;
                    tabpane1.setLocation(w, 0);
                    panel.setLocation(w, 0);
                    panel4.setLocation(w, 0);
                    while (w>80){
                    try {
                            Thread.sleep(1);
                            panel.setLocation(w,0);
                            w=w-3;
                        } catch (InterruptedException ex) {}    
                    }
                    }else{
                        int w=1230;
                        panel.setLocation(w,0);
                        panel4.setLocation(w, 0);
                        while (w>80){
                        try {
                                Thread.sleep(1);
                                tabpane1.setLocation(w,0);
                                w=w-3;
                            } catch (InterruptedException ex) {}

                        }
                    }
            }else{
                if (pass) {
                int w=1230;
                tabpane1.setLocation(w, 0);
                panel4.setLocation(w, 0);
                panel5.setLocation(w, 0);
                panel6.setLocation(w, 0);
                panel7.setLocation(w, 0);
                panel8.setLocation(w, 0);
                while (w>80){
                try {
                        Thread.sleep(1);
                        panel.setLocation(w,0);
                        w=w-3;
                    } catch (InterruptedException ex) {}    
                }
                }else{
                    int w=1230;
                    panel.setLocation(w,0);
                    panel4.setLocation(w, 0);
                    panel5.setLocation(w, 0);
                    panel6.setLocation(w, 0);
                    panel7.setLocation(w, 0);
                    panel8.setLocation(w, 0);
                    while (w>80){
                    try {
                            Thread.sleep(1);
                            tabpane1.setLocation(w,0);
                            w=w-3;
                        } catch (InterruptedException ex) {}

                    }
                }
            }
        }
        
        
        
            
    }
    
}


