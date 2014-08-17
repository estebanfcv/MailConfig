package com.estebanfcv.mailconfig;

import com.estebanfcv.util.AESCrypt;
import com.estebanfcv.util.Constantes;
import com.estebanfcv.util.Plantillas;
import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;
import static com.estebanfcv.util.Util.obtenerRutaJar;

/**
 *
 * @author esteb_000
 */
public class Archivos {

    File jarDir;
    AESCrypt aes;

    public Archivos() {
    }

    public boolean validarArchivos() {
        try {
            aes = new AESCrypt(true, "123");
            jarDir = obtenerRutaJar();
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
        }
        return false;
    }

    private void crearCarpetaLogs() {
        File carpeta = new File(jarDir, Constantes.NOMBRE_CARPETA_LOGS);
        if (!carpeta.exists()) {
            JOptionPane.showMessageDialog(null, "La carpeta logs no existe, se creará en: " + carpeta.getAbsolutePath(),
                    "MailConfig", JOptionPane.INFORMATION_MESSAGE);
            carpeta.mkdir();
        }
    }

    private boolean encontrarArchivoProperties() {
        try {
            if (jarDir != null && jarDir.isDirectory()) {
                return new File(jarDir, Constantes.NOMBRE_ARCHIVO_CONF).exists();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            return false;
        }
        return false;
    }

    private void crearArchivoProperties() {
        List<String> listaPropiedades = Plantillas.obtenerPlantillaConfig();
        String texto = "";
        for (String s : listaPropiedades) {
            texto += (s + "\n");
        }
        try {
            File file = new File(jarDir, Constantes.NOMBRE_ARCHIVO_CONF);
            JOptionPane.showMessageDialog(null, "El archivo de configuración no existe, se creará en: " + file.getAbsolutePath(),
                    "MailConfig", JOptionPane.INFORMATION_MESSAGE);
            aes.encriptar(2, texto, file);
        } catch (Exception e) {
            e.printStackTrace();
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
        }
    }
}
