package com.estebanfcv.mailconfig;

import com.estebanfcv.util.Constantes;
import com.estebanfcv.util.Util;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;
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
                        + "[2] Editar Archivo de Correos  \n"
                        + "[3] Salir"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Valor incorrecto");
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
                    System.exit(0);
                default:
                    JOptionPane.showMessageDialog(null, "Valor incorrecto");
            }

        } while (opcion != 3);
    }

    private void menuConfig() {
        Integer opcion = null;
        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione una opcion:\n"
                        + "[1] Email Principal  \n"
                        + "[2] PASSWORD  \n"
                        + "[3] SERVIDOR  \n"
                        + "[4] PUERTO  \n"
                        + "[5] TLS  \n"
                        + "[6] TIEMPO_ESPERA_HILO  \n"
                        + "[7] TIEMPO_HISTORICO  \n"
                        + "[8] MOSTRA ARCHIVO  \n"
                        + "[9] Regresar al menu anterior"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Valor incorrecto");
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
        String parametro = JOptionPane.showInputDialog("Escriba el parametro para: " + nombreParametro).trim();
        if (parametro == null || parametro.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Escriba el valor del parametro");
            return;
        }
        File config;
        FileReader fr = null;
        BufferedReader br = null;
        OutputStream out = null;
        InputStream is = null;
        String lineaActual;
        String texto = "";
        int contador = 1;
        try {
            config = new File(Util.obtenerRutaJar(), Constantes.NOMBRE_ARCHIVO_CONF);
            fr = new FileReader(config);
            br = new BufferedReader(fr);
            while ((lineaActual = br.readLine()) != null) {
                if (renglon == contador) {
                    for (StringTokenizer tk = new StringTokenizer(lineaActual, "="); tk.hasMoreTokens();) {
                        texto += tk.nextToken() + "=" + parametro + "\n";
                        break;
                    }
                } else {
                    texto += lineaActual + "\n";
                }
                contador++;
            }
            is = new ByteArrayInputStream(texto.getBytes());
            out = new FileOutputStream(config);
            byte buf[] = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            JOptionPane.showMessageDialog(null, "El archivo se modifico con exito");
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
                if (fr != null) {
                    fr.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void mostrarArchivoConfiguracion() {
        File config;
        FileReader fr = null;
        BufferedReader br = null;
        String texto = "";
        String lineaActual;
        try {
            config = new File(Util.obtenerRutaJar(), Constantes.NOMBRE_ARCHIVO_CONF);
            fr = new FileReader(config);
            br = new BufferedReader(fr);
            while ((lineaActual = br.readLine()) != null) {
                texto += lineaActual + "\n";
            }
            JOptionPane.showMessageDialog(null, texto);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // =====================================================================
    private void menuCorreo() {
        Integer opcion = null;
        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione una opcion:\n"
                        + "[1] Agregar correo  \n"
                        + "[2] Editar correo  \n"
                        + "[3] Eliminar correo  \n"
                        + "[4] Regresar al menu principal  \n"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Valor incorrecto");
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
                    JOptionPane.showMessageDialog(null, "Valor incorrecto");
            }

        } while (opcion != 4);
    }

    private void agregarCorreo() {
        String email = JOptionPane.showInputDialog("Escriba el correo").trim();
        if (email == null || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Escriba una cuenta de correo");
            return;
        }
        String permisoEliminar = "0";
        File correo;
        FileReader fr = null;
        BufferedReader br = null;
        OutputStream out = null;
        InputStream is = null;
        String lineaActual;
        String texto = "";
        try {
            correo = new File(Util.obtenerRutaJar(), Constantes.NOMBRE_ARCHIVO_CORREO);
            fr = new FileReader(correo);
            br = new BufferedReader(fr);
            while ((lineaActual = br.readLine()) != null) {
                texto += lineaActual + "\n";
            }
            if (texto.contains(email)) {
                JOptionPane.showMessageDialog(null, "El correo ya existe");
                return;
            }
            texto += email + ":" + permisoEliminar + "\n";
            is = new ByteArrayInputStream(texto.getBytes());
            out = new FileOutputStream(correo);
            byte buf[] = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            JOptionPane.showMessageDialog(null, "El correo se agrego con exito");
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
                if (fr != null) {
                    fr.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void modificarCorreo() {
        String contenido = contenidoCorreo().trim();
        if (contenido.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay correos");
            return;
        }
        String correo = JOptionPane.showInputDialog("Escriba un correo para modificarlo \n" + contenido);
        String permisoEliminar = "1";
        if (correo == null || correo.isEmpty() || !contenidoCorreo().contains(correo)) {
            JOptionPane.showMessageDialog(null, "Correo no encontrado");
            return;
        }
        File email;
        FileReader fr = null;
        BufferedReader br = null;
        OutputStream out = null;
        InputStream is = null;
        String lineaActual;
        String texto = "";
        try {
            email = new File(Util.obtenerRutaJar(), Constantes.NOMBRE_ARCHIVO_CORREO);
            fr = new FileReader(email);
            br = new BufferedReader(fr);
            while ((lineaActual = br.readLine()) != null) {
                if (lineaActual.contains(correo)) {
                    texto += lineaActual.replace(lineaActual.charAt(lineaActual.length() - 1), permisoEliminar.charAt(0)) + "\n";
                } else {
                    texto += lineaActual + "\n";
                }
            }
            is = new ByteArrayInputStream(texto.getBytes());
            out = new FileOutputStream(email);
            byte buf[] = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            JOptionPane.showMessageDialog(null, "El correo se modifico con exito");

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
                if (fr != null) {
                    fr.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void eliminarCorreo() {
        String contenido = contenidoCorreo().trim();
        if (contenido.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay correos");
            return;
        }
        String correo = JOptionPane.showInputDialog("Escriba un correo para eliminarlo \n" + contenido);
        JOptionPane.showMessageDialog(null, "Correo vale "+correo);
        if (correo == null || correo.isEmpty() || !contenidoCorreo().contains(correo)) {
            JOptionPane.showMessageDialog(null, "Correo no encontrado");
            return;
        }
        File email;
        FileReader fr = null;
        BufferedReader br = null;
        OutputStream out = null;
        InputStream is = null;
        String lineaActual;
        String texto = "";
        try {
            email = new File(Util.obtenerRutaJar(), Constantes.NOMBRE_ARCHIVO_CORREO);
            fr = new FileReader(email);
            br = new BufferedReader(fr);
            while ((lineaActual = br.readLine()) != null) {
                if (lineaActual.contains(correo)) {
                    continue;
                }
                texto += lineaActual + "\n";
            }
            is = new ByteArrayInputStream(texto.getBytes());
            out = new FileOutputStream(email);
            byte buf[] = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            JOptionPane.showMessageDialog(null, "El correo se elimino con exito");
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
                if (fr != null) {
                    fr.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String contenidoCorreo() {
        File correos;
        FileReader fr = null;
        BufferedReader br = null;
        String texto = "";
        String lineaActual;
        try {
            correos = new File(Util.obtenerRutaJar(), Constantes.NOMBRE_ARCHIVO_CORREO);
            fr = new FileReader(correos);
            br = new BufferedReader(fr);
            while ((lineaActual = br.readLine()) != null) {
                texto += lineaActual + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return texto;
    }
}
