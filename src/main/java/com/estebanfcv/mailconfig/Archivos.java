package com.estebanfcv.mailconfig;

import com.estebanfcv.util.AESCrypt;
import com.estebanfcv.util.Constantes;
import com.estebanfcv.util.Plantillas;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;
import static com.estebanfcv.util.Util.cerrarLecturaEscritura;
import static com.estebanfcv.util.Util.obtenerRutaJar;

/**
 *
 * @author esteb_000
 */
public class Archivos {

    private Properties propConfig;
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
            JOptionPane.showMessageDialog(null, "La carpeta logs no existe, se creará en: " + carpeta.getAbsolutePath());
            carpeta.mkdir();
        }
    }

    private boolean encontrarArchivoProperties() {
        try {
            if (jarDir != null && jarDir.isDirectory()) {
                File propFile = new File(jarDir, Constantes.NOMBRE_ARCHIVO_CONF);
                return propFile.exists();
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
                File emailFile = new File(jarDir, Constantes.NOMBRE_ARCHIVO_CORREO);
                return emailFile.exists();
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
            JOptionPane.showMessageDialog(null, "El archivo de configuración no existe, se creará en: " + file.getAbsolutePath());
            aes.encrypt(2, texto, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void crearArchivoCorreos() {
        InputStream is = null;
        OutputStream out = null;
        String texto = "";
        try {
            is = new ByteArrayInputStream(texto.getBytes());
            File file = new File(jarDir, Constantes.NOMBRE_ARCHIVO_CORREO);
            JOptionPane.showMessageDialog(null, "El archivo de correos no existe, se creará en: " + file.getAbsolutePath());
            out = new FileOutputStream(file);
            byte buf[] = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cerrarLecturaEscritura(null, null, out, is);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
