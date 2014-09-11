package com.estebanfcv.mailconfig;

import com.estebanfcv.util.AESCrypt;
import com.estebanfcv.util.Constantes;
import com.estebanfcv.util.Util;
import java.io.File;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author esteb_000
 */
public class MenuPrincipal {

    AESCrypt aes;

    public MenuPrincipal() {
        abrirMenuPrincipal();
    }

    private void abrirMenuPrincipal() {

        Integer opcion = null;
        do {
            try {
                aes = new AESCrypt(true, "123");
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione una opción:\n"
                        + "[1] Editar Archivo Configuración  \n"
                        + "[2] Editar Archivo de Correos  \n"
                        + "[3] Salir", "MailConfig", JOptionPane.INFORMATION_MESSAGE));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Valor incorrecto", "MailConfig", JOptionPane.WARNING_MESSAGE);
                continue;
            }
            switch (opcion) {
                case 1:
                    menuConfig();
                    break;
                case 2:
                    menuCorreo();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Bye", "MailConfig", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                default:
                    JOptionPane.showMessageDialog(null, "Valor incorrecto", "MailConfig", JOptionPane.WARNING_MESSAGE);
            }

        } while (opcion != 3);
    }

    private void menuConfig() {
        Integer opcion = null;
        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione una opción:\n"
                        + "[1] CORREO PRINCIPAL  \n"
                        + "[2] CONTRASEÑA  \n"
                        + "[3] SERVIDOR  \n"
                        + "[4] PUERTO  \n"
                        + "[5] TLS  \n"
                        + "[6] TIEMPO_ESPERA_HILO  \n"
                        + "[7] TIEMPO_HISTORICO  \n"
                        + "[8] MOSTRA ARCHIVO  \n"
                        + "[9] Regresar al menú anterior", "MailConfig", JOptionPane.INFORMATION_MESSAGE));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Valor incorrecto", "MailConfig", JOptionPane.WARNING_MESSAGE);
                continue;
            }
            switch (opcion) {
                case 1:
                    editarArchivoConfiguracion(opcion, "Email Principal");
                    break;
                case 2:
                    editarArchivoConfiguracion(opcion, "Password");
                    break;
                case 3:
                    editarArchivoConfiguracion(opcion, "Servidor");
                    break;
                case 4:
                    editarArchivoConfiguracion(opcion, "Puerto");
                    break;
                case 5:
                    editarArchivoConfiguracion(opcion, "TLS");
                    break;
                case 6:
                    editarArchivoConfiguracion(opcion, "Tiempo Espera Proceso");
                    break;
                case 7:
                    editarArchivoConfiguracion(opcion, "Tiempo Historico");
                    break;
                case 8:
                    mostrarArchivoConfiguracion();
                    break;
                case 9:
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Valor incorrecto");
            }
        } while (opcion != 9);

    }

    private void editarArchivoConfiguracion(Integer renglon, String nombreParametro) {
        String parametro = JOptionPane.showInputDialog(null, "Escriba el parametro para: " + nombreParametro,
                "MailConfig", JOptionPane.INFORMATION_MESSAGE);
        if (parametro == null || parametro.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Escriba el valor del parametro", "MailConfig", JOptionPane.WARNING_MESSAGE);
            return;
        }
        File config;
        String texto = "";
        int contador = 1;
        try {
            config = new File(Util.obtenerRutaJar(), Constantes.NOMBRE_ARCHIVO_CONF);
            for (StringTokenizer tok = new StringTokenizer(aes.desencriptar(config), "\n"); tok.hasMoreTokens();) {
                if (renglon == contador) {
                    for (StringTokenizer tk = new StringTokenizer(tok.nextToken(), "="); tk.hasMoreTokens();) {
                        texto += tk.nextToken() + "=" + parametro + "\n";
                        break;
                    }
                } else {
                    texto += tok.nextToken() + "\n";
                }
                contador++;
            }
            aes.encriptar(2, texto, config);
            JOptionPane.showMessageDialog(null, "El archivo se modificó con éxito", "MailConfig", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            Util.agregarDebug(e);
        }
    }

    private void mostrarArchivoConfiguracion() {
        File config;
        try {
            config = new File(Util.obtenerRutaJar(), Constantes.NOMBRE_ARCHIVO_CONF);
            JOptionPane.showMessageDialog(null, aes.desencriptar(config), "MailConfig", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            Util.agregarDebug(e);
        }
    }

    // =====================================================================
    private void menuCorreo() {
        Integer opcion = null;
        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione una opción:\n"
                        + "[1] Agregar correo  \n"
                        + "[2] Editar correo  \n"
                        + "[3] Eliminar correo  \n"
                        + "[4] Regresar al menú principal  \n", "MailConfig", JOptionPane.INFORMATION_MESSAGE));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Valor incorrecto", "MailConfig", JOptionPane.WARNING_MESSAGE);
                continue;
            }
            switch (opcion) {
                case 1:
                    agregarCorreo();
                    break;
                case 2:
                    modificarCorreo();
                    break;
                case 3:
                    eliminarCorreo();
                    break;
                case 4:
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Valor incorrecto", "MailConfig", JOptionPane.WARNING_MESSAGE);
            }
        } while (opcion != 4);
    }

    private void agregarCorreo() {
        String emailOrigen = JOptionPane.showInputDialog(null, "Escriba el correo", "MailConfig", JOptionPane.INFORMATION_MESSAGE);
        if (emailOrigen == null || emailOrigen.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Escriba la cuenta de correo de origen", "MailConfig", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            if (!Util.validarEmail(emailOrigen)) {
                JOptionPane.showMessageDialog(null, "No es una cuenta de correo Valida", "MailConfig", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        String emailDestino = JOptionPane.showInputDialog(null, "Escriba el correo", "MailConfig", JOptionPane.INFORMATION_MESSAGE);
        if (emailDestino == null || emailDestino.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Escriba la cuenta de correo de destino", "MailConfig", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            if (!Util.validarEmail(emailDestino)) {
                JOptionPane.showMessageDialog(null, "No es una cuenta de correo Valida", "MailConfig", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        Integer permisoEliminar = JOptionPane.showConfirmDialog(null, "¿Tendrá permiso de eliminar?", "MailConfig",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (permisoEliminar == -1) {
            return;
        }
        permisoEliminar = permisoEliminar == 0 ? 1 : 0;
        File correo;
        String texto;
        try {
            correo = new File(Util.obtenerRutaJar(), Constantes.NOMBRE_ARCHIVO_CORREO);
            System.out.println("el file existe " + correo.exists());
            texto = aes.desencriptar(correo);
            if (texto.contains(emailOrigen)) {
                JOptionPane.showMessageDialog(null, "El correo ya existe", "MailConfig", JOptionPane.WARNING_MESSAGE);
                return;
            }
            texto += emailOrigen + ":" + emailDestino + ":" + permisoEliminar + "\n";
            aes.encriptar(2, texto, correo);
            JOptionPane.showMessageDialog(null, "El correo se agregó con éxito", "MailConfig", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            Util.agregarDebug(e);
            e.printStackTrace();
        }
    }

    private void modificarCorreo() {
        String contenido = contenidoCorreo().trim();
        if (contenido.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay correos", "MailConfig", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String correo = JOptionPane.showInputDialog(null, "Escriba un correo para modificarlo \n" + contenido,
                "MailConfig", JOptionPane.INFORMATION_MESSAGE);
        if (correo == null || correo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Escriba una cuenta de correo", "MailConfig", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            if (!Util.validarEmail(correo)) {
                JOptionPane.showMessageDialog(null, "No es una cuenta de correo Valida", "MailConfig", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                if (!contenidoCorreo().contains(correo)) {
                    JOptionPane.showMessageDialog(null, "No se encontró el correo", "MailConfig", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        }
        String permisoEliminar = String.valueOf(JOptionPane.showConfirmDialog(null, "¿Tendrá permiso de eliminar?", "MailConfig",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE));
        if (permisoEliminar.equals("-1")) {
            return;
        }
        permisoEliminar = permisoEliminar.equals("0") ? "1" : "0";
        File email;
        String texto = "";
        try {
            email = new File(Util.obtenerRutaJar(), Constantes.NOMBRE_ARCHIVO_CORREO);
            for (StringTokenizer t = new StringTokenizer(aes.desencriptar(email), "\n"); t.hasMoreTokens();) {
                String token = t.nextToken();
                if (token.contains(correo)) {
                    texto += token.replace(token.charAt(token.length() - 1), permisoEliminar.charAt(0)) + "\n";
                } else {
                    texto += token + "\n";
                }
            }
            aes.encriptar(2, texto, email);
            JOptionPane.showMessageDialog(null, "El correo se modificó con éxito", "MailConfig", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            Util.agregarDebug(e);
            e.printStackTrace();
        }
    }

    private void eliminarCorreo() {
        String contenido = contenidoCorreo().trim();
        if (contenido.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay correos", "MailConfig", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String correo = JOptionPane.showInputDialog(null, "Escriba un correo para eliminarlo \n" + contenido,
                "MailConfig", JOptionPane.INFORMATION_MESSAGE);
        if (correo == null || correo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Escriba una cuenta de correo", "MailConfig", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            if (!Util.validarEmail(correo)) {
                JOptionPane.showMessageDialog(null, "No es una cuenta de correo válida", "MailConfig", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                if (!contenidoCorreo().contains(correo)) {
                    JOptionPane.showMessageDialog(null, "No se encontró el correo", "MailConfig", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        }
        File email;
        String texto = "";
        try {
            email = new File(Util.obtenerRutaJar(), Constantes.NOMBRE_ARCHIVO_CORREO);
            for (StringTokenizer tk = new StringTokenizer(aes.desencriptar(email)); tk.hasMoreTokens();) {
                String token = tk.nextToken();
                texto += token.contains(correo) ? "" : token + "\n";
            }
            aes.encriptar(2, texto, email);
            JOptionPane.showMessageDialog(null, "El correo se eliminó con éxito", "MailConfig", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            Util.agregarDebug(e);
            e.printStackTrace();
        }
    }

    private String contenidoCorreo() {
        File correos;
        String texto = "";
        try {
            correos = new File(Util.obtenerRutaJar(), Constantes.NOMBRE_ARCHIVO_CORREO);
            texto = aes.desencriptar(correos);
        } catch (Exception e) {
            e.printStackTrace();
            Util.agregarDebug(e);
        }
        return texto;
    }
}
