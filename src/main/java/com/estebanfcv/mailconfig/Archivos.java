package com.estebanfcv.mailconfig;

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

/**
 *
 * @author esteb_000
 */
public class Archivos {


    private Properties propConfig;
    File jarDir;

    public Archivos() {
    }

    public boolean validarArchivos() {
        try {
            jarDir = new File(MailConfig.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile();
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
            JOptionPane.showMessageDialog(null, "La carpeta logs no existe, se creará en: "+carpeta.getAbsolutePath());
            carpeta.mkdir();
        }
    }

    private boolean encontrarArchivoProperties() {
        try {
            if (jarDir != null && jarDir.isDirectory()) {
                File propFile = new File(jarDir, Constantes.NOMBRE_ARCHIVO_CONF);
                propConfig = new Properties();
                propConfig.load(new BufferedReader(new FileReader(propFile.getAbsoluteFile())));
                return true;
            }
        } catch (IOException e) {
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

    private void crearArchivoCorreos() {
        InputStream is = null;
        OutputStream out = null;
        String texto = "";
        try {
            is = new ByteArrayInputStream(texto.getBytes());
            File file = new File(jarDir, Constantes.NOMBRE_ARCHIVO_CORREO);
            out = new FileOutputStream(file);
            byte buf[] = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            JOptionPane.showMessageDialog(null, "El archivo de correos no existe, se creará en: "+file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void crearArchivoProperties() {
        List<String> listaPropiedades = Plantillas.obtenerPlantillaConfig();
        InputStream is = null;
        OutputStream out = null;
        String texto = "";
        for (String s : listaPropiedades) {
            texto += (s + "\n");
        }
        try {
            is = new ByteArrayInputStream(texto.getBytes());
            File file = new File(jarDir, Constantes.NOMBRE_ARCHIVO_CONF);
            out = new FileOutputStream(file);
            byte buf[] = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            JOptionPane.showMessageDialog(null, "El archivo de configuración no existe, se creará en: "+file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
