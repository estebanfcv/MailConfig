package com.estebanfcv.mailconfig;

import com.estebanfcv.util.Constantes;
import javax.swing.JOptionPane;

/**
 * Hello world!
 *
 */
public class MailConfig {

    public static void main(String[] args) {
        if (JOptionPane.showInputDialog("Escriba el password").equals(Constantes.PASSWORD)) {
            JOptionPane.showMessageDialog(null, "Bienvenido");
        } else {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
}
