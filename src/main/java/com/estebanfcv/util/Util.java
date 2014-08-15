package com.estebanfcv.util;

import com.estebanfcv.mailconfig.MailConfig;
import java.io.File;

/**
 *
 * @author esteb_000
 */
public class Util {
    
    public static File obtenerRutaJar(){
        File f = null;
        try {
            f = new File(MailConfig.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
    
}
