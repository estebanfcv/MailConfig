package com.estebanfcv.mailconfig;

import javax.swing.JOptionPane;

/**
 *
 * @author esteb_000
 */
public class MenuPrincipal {

    public MenuPrincipal() {
        abrirMenuPrincipal();
    }
    

    private void abrirMenuPrincipal() {

        Integer opcion = null;
        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione una opcion:\n"
                        + "[1] Editar Archivo Configuracion  \n"
                        + "[2] Salir"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Valor incorrecto");
                continue;
            }
            switch (opcion) {
                case 1:
                    menuConfig();
                    break;
                case 2:
                    System.exit(0);
                default:
                    JOptionPane.showMessageDialog(null, "Valor incorrecto");
            }

        } while (opcion != 2);
    }

    private void menuConfig() {
        Integer opcion = null;
        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione una opcion:\n"
                        + "[1] EmailPrincipal  \n"
                        + "[2] PASSWORD  \n"
                        + "[3] SERVIDOR  \n"
                        + "[4] PUERTO  \n"
                        + "[5] TLS  \n"
                        + "[6] TIEMPO_ESPERA_HILO  \n"
                        + "[7] TIEMPO_HISTORICO  \n"
                        + "[8] MOSTRA ARCHIVO  \n"
                        + "[9] Salir"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Valor incorrecto");
                continue;
            }
            switch (opcion) {
                case 1:
                    JOptionPane.showMessageDialog(null, "1");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "2");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "3");
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "4");
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "5");
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "6");
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "7");
                    break;
                case 8:
                    JOptionPane.showMessageDialog(null, "8");
                    break;
                case 9:
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Valor incorrecto");
            }
        } while (opcion != 9);

    }
}
