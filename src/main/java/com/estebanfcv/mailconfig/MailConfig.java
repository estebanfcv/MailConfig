package com.estebanfcv.mailconfig;

import com.estebanfcv.util.Constantes;
import javax.swing.JOptionPane;

public class MailConfig {

    public static void main(String[] args) {
        int contador = 0;
        while (contador <= 2) {
            String pass = JOptionPane.showInputDialog(null,"Escriba la contraseña","MailConfig",JOptionPane.INFORMATION_MESSAGE);
            if (pass == null || pass.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Escriba la contraseña","MailConfig",JOptionPane.WARNING_MESSAGE);
                contador++;
                if (contador > 2) {
                    JOptionPane.showMessageDialog(null, "Bye","MailConfig",JOptionPane.INFORMATION_MESSAGE);
                }
                continue;
            }
            if (pass.equals(Constantes.PASSWORD)) {
                contador = 3;
                JOptionPane.showMessageDialog(null, "Bienvenido","MailConfig",JOptionPane.INFORMATION_MESSAGE);
                if (new Archivos().validarArchivos()) {
                    new MenuPrincipal();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta","MailConfig",JOptionPane.ERROR_MESSAGE);
            }
            contador++;
            if (contador > 2) {
                JOptionPane.showMessageDialog(null, "Bye","MailConfig",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
