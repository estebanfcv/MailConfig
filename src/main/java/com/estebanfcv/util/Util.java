package com.estebanfcv.util;

import com.estebanfcv.mailconfig.MailConfig;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author esteb_000
 */
public class Util {

    public static File obtenerRutaJar() {
        File f = null;
        try {
            f = new File(MailConfig.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public static void cerrarLecturaEscritura(OutputStream out, InputStream is) {
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
    public static void cerrarLecturaEscritura(PrintWriter pw, FileWriter fw) {
        try {
            if (pw != null) {
                pw.close();
            }
            if (fw != null) {
                fw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean validarEmail(String email) {
        Pattern pattern;
        pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void agregarDebug(Exception e) {
        Writer error = new StringWriter();
        e.printStackTrace(new PrintWriter(error));
        StringBuilder sb = new StringBuilder();
        sb.append("\n========================");
        sb.append(new SimpleDateFormat("EEEEE dd/MMMMM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
        sb.append("=========================\n").append(error.toString());
        sb.append("============================================================================================\n");
        agregarDebug(sb.toString());

    }

    private static void agregarDebug(String debug) {
        File archivoDebug;
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            archivoDebug = new File(obtenerRutaJar(), "debug.txt");
            fw = new FileWriter(archivoDebug, true);
            pw = new PrintWriter(fw);
            pw.append(debug);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarLecturaEscritura(pw, fw);
        }
    }
}