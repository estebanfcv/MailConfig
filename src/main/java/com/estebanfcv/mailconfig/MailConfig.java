package com.estebanfcv.mailconfig;

import com.estebanfcv.util.Constantes;
import javax.swing.JOptionPane;

public class MailConfig {

    public static void main(String[] args) {
        int contador = 0;
        while (contador <= 2) {
            String pass = JOptionPane.showInputDialog("Escriba el password");
            if (pass == null || pass.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Escriba la contraseña");
                contador++;
                if (contador > 2) {
                    JOptionPane.showMessageDialog(null, "Adios");
                }
                continue;
            }
            if (pass.equals(Constantes.PASSWORD)) {
                contador = 3;
                JOptionPane.showMessageDialog(null, "Bienvenido");
                if (new Archivos().validarArchivos()) {
                    new MenuPrincipal();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Password incorrecto");
            }
            contador++;
            if (contador > 2) {
                JOptionPane.showMessageDialog(null, "Adios");
            }
        }
    }
}
