package com.estebanfcv.mailconfig;

import com.estebanfcv.util.AESCrypt;
import com.estebanfcv.util.Constantes;
import com.estebanfcv.util.Plantillas;
import com.estebanfcv.util.Util;
import java.io.File;
import javax.swing.JOptionPane;
import static com.estebanfcv.util.Util.obtenerRutaJar;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author estebanfcv
 */
public class Archivos {

    private File jarDir;
    private AESCrypt aes;

    public Archivos() {
    }

    public boolean validarArchivos() {
        try {
            aes = new AESCrypt(true, "123");
            jarDir = obtenerRutaJar();
            crearArchivoDebug();
            crearCarpetaLogs();
            if (!encontrarArchivoProperties()) {
                crearArchivoProperties();
            }
            if (!encontrarArchivoCorreos()) {
                crearArchivoCorreos();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Util.agregarDebug(e);
        }
        return false;
    }

    private void crearCarpetaLogs() {
        try {
            File carpetaLogs = new File(jarDir, Constantes.NOMBRE_CARPETA_LOGS);
            if (!carpetaLogs.exists()) {
                JOptionPane.showMessageDialog(null, "La carpeta logs no existe, se creará en: " + carpetaLogs.getAbsolutePath(),
                        "MailConfig", JOptionPane.INFORMATION_MESSAGE);
                carpetaLogs.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Util.agregarDebug(e);
        }

    }

    private boolean encontrarArchivoProperties() {
        try {
            if (jarDir != null && jarDir.isDirectory()) {
                return new File(jarDir, Constantes.NOMBRE_ARCHIVO_CONF).exists();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Util.agregarDebug(e);
            return false;
        }
        return false;
    }

    private boolean encontrarArchivoCorreos() {
        try {
            if (jarDir != null && jarDir.isDirectory()) {
                return new File(jarDir, Constantes.NOMBRE_ARCHIVO_CORREO).exists();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Util.agregarDebug(e);
            return false;
        }
        return false;
    }

    private void crearArchivoProperties() {
        String texto = "";
        for (String s : Plantillas.obtenerPlantillaConfig()) {
            texto += (s + "\n");
        }
        try {
            File file = new File(jarDir, Constantes.NOMBRE_ARCHIVO_CONF);
            JOptionPane.showMessageDialog(null, "El archivo de configuración no existe, se creará en: " + file.getAbsolutePath(),
                    "MailConfig", JOptionPane.INFORMATION_MESSAGE);
            aes.encriptar(2, texto, file);
        } catch (Exception e) {
            e.printStackTrace();
            Util.agregarDebug(e);
        }
    }

    private void crearArchivoCorreos() {
        try {
            File file = new File(jarDir, Constantes.NOMBRE_ARCHIVO_CORREO);
            JOptionPane.showMessageDialog(null, "El archivo de correos no existe, se creará en: " + file.getAbsolutePath(),
                    "MailConfig", JOptionPane.INFORMATION_MESSAGE);
            aes.encriptar(2, "", file);
        } catch (Exception e) {
            e.printStackTrace();
            Util.agregarDebug(e);
        }
    }

    private void crearArchivoDebug() {
        FileWriter fw = null;
        PrintWriter pw = null;
        File archivoDebug;
        try {
            archivoDebug = new File(obtenerRutaJar(), Constantes.NOMBRE_DEBUG);
            if (!archivoDebug.exists()) {
                JOptionPane.showMessageDialog(null, "El archivo debug no existe, se creará en: " + archivoDebug.getAbsolutePath(),
                        "MailConfig", JOptionPane.INFORMATION_MESSAGE);
                fw = new FileWriter(archivoDebug);
                pw = new PrintWriter(fw);
                pw.append("====================================================DEBUG=====================================================================\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.cerrarLecturaEscritura(pw, fw);
        }
    }
}
