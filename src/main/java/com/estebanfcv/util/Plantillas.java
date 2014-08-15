package com.estebanfcv.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author esteb_000
 */
public class Plantillas {
    
    
    public static List<String> obtenerPlantillaConfig(){
        List<String> archivoConfiguacion= new ArrayList<>();
        archivoConfiguacion.add("EmailPrincipal=");
        archivoConfiguacion.add("Password=");
        archivoConfiguacion.add("Servidor=");
        archivoConfiguacion.add("Puerto=");
        archivoConfiguacion.add("TLS=");
        archivoConfiguacion.add("TiempoEsperaHilo=");
        archivoConfiguacion.add("TiempoHistorico=");
        return archivoConfiguacion;
    }
    
    
}
