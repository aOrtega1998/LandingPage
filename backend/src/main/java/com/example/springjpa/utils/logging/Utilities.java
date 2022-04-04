package com.example.springjpa.utils.logging;

import org.apache.logging.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

public class Utilities {

    private static final Logger log = FsLogManager.getLogger(Utilities.class);

    /**
     * Devuelve una cadena con la pila de ejecucion
     * USAR CON CUIDADO: PUEDE CARGAR EL SISTEMA
     */
    public static String printStackTrace(Throwable t)
    {
        StringWriter s=new StringWriter();
        PrintWriter p=new PrintWriter(s);
        t.printStackTrace(p);
        return s.toString();
    }

    /**
     * Genera un Id de transaccion, recortado a 8 caracteres
     */
    public static String generateTrid(String username) {
        UUID randomUUID = UUID.randomUUID();
        String trid = randomUUID.toString().replace("-", "").substring(0, 8);
        log.debug("[{}] trid=[{}] generado para username=[{}]", trid, trid, username);
        return trid;
    }
}
