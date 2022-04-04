package com.example.springjpa.utils.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author afioravantti
 *
 * Esta clase sirve como interfaz para instanciar los distintos logs que utilice el servicio:
 *
 * Las constantes <br>ACCESSLOG</br> y <br>ERRORLOG</br> son un ejemplo de loggers que se utilizan en casi todos los proyectos.
 * <br>ACCESSLOG</br>: Se utiliza para registrar los accesos (ok y nok) a la aplicacion
 * <br>ERRORLOG</br>: Se utiliza para registrar el stacktrace de excepciones no controladas. Preferiblemente ira a un appender aparte, para mantener la limpieza
 * del log de servicio.
 *
 * Cualquier otro tipo de loggers que se quieran generar para registrar hacia uno u otro appender, se puede definir aqui como constante e implementar su metodo
 * get correspondiente.
 */
public class FsLogManager {

    public static final String ACCESSLOG = "access";
    public static final String ERRORLOG = "error";


    public static Logger getErrorLog() {
        return getLogger(ERRORLOG);
    }

    public static Logger getAccessLog() {
        return getLogger(ACCESSLOG);
    }

    public static Logger getLogger(String logName) {
        return LogManager.getLogger(logName);
    }

    public static Logger getLogger(Class clazz) {
        return LogManager.getLogger(clazz.getName());
    }

}
