package com.estebanfcv.mailconfig;

import com.estebanfcv.util.Constantes;
import javax.swing.JOptionPane;

public class MailConfig {

    public static void main(String[] args) {
        if (JOptionPane.showInputDialog("Escriba el password").equals(Constantes.PASSWORD)) {
            JOptionPane.showMessageDialog(null, "Bienvenido");
            if(new Archivos().validarArchivos()){
                new MenuPrincipal();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
}
