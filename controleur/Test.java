package controleur;
import vue.ClientRegister;
import vue.PageRecherche;
import vue.ProprioRegister;

import java.awt.*;
import java.io.IOException;

/**
 *
 * @author palasi
 */
public class Test {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PageRecherche();
                } catch (IOException | FontFormatException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
