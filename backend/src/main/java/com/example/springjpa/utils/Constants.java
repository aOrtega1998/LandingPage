package com.example.springjpa.utils;

/**
 * Application constants.
 */
public final class Constants {

    // Spring profile for development and production, see http://jhipster.github.io/profiles/
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";

    //////////////////// Constantes para el formato de los logs \\\\\\\\\\\\\\\\\\\\\\\
    // Formato log.info para los log
    public static final String LOG_INFO = "[{}] {} -";
    // Formato log.info para los finally
    public static final String FINALLY_RESPONSE = "[{}] Response {}";
    // Formato log.error para los log error response
    public static final String LOG_ERROR_RESPONSE = "[{}] {} : {}";
    // Formato error.log para los error logs
    public static final String ERROR_LOG_RESPONSE = "[{}] - {}";
    // Formato error.log para los errores de conexión con la raspberry
    public static final String LOG_ERROR_CONNECTION = "[{}] Failed connection to Raspberry access control from {}. Error {}";
    // Formato error.log para los errores de emails
    public static final String ERROR_SENDING_EMAIL = "[{}] Unable to send error mail to {}";

    // Formato log.debug para los log debug
    public static final String LOG_DEBUG_IN = "[{}] {}";

    // Formato log.debug para registrar las peticiones del usuario a Spring
    public static final String TRACE_LOG_IDROL = "[{}] idRol {}";
    public static final String TRACE_LOG_IDCIA = "[{}] idCia {}";

    public static final String SPRING_PROFILE_PREPRODUCTION = "pre";
    public static final String SPRING_PROFILE_PRODUCTION = "pro";
    // Spring profile used when deploying with Spring Cloud (used when deploying to CloudFoundry)
    public static final String SPRING_PROFILE_CLOUD = "cloud";
    // Spring profile used when deploying to Heroku
    public static final String SPRING_PROFILE_HEROKU = "heroku";
    // Spring profile used to disable swagger
    public static final String SPRING_PROFILE_NO_SWAGGER = "no-swagger";
    // Spring profile used to disable running liquibase
    public static final String SPRING_PROFILE_NO_LIQUIBASE = "no-liquibase";

    public static final String SYSTEM_ACCOUNT = "system";

    //Tipos de fichaje online
    public static final String CONNECT_CHECK_OUT = "fichajeConexion";
    public static final String PAUSE_CHECK_OUT = "fichajePausa";
    public static final String DISCONNECT_CHECK_OUT = "fichajeDesconexion";

    //Representación del formato para obtener un string de un ZonedDateTime
    public static final String DATE_TIME_FORMAT_EMAIL_FICHAJES = "dd/MM/uu HH:mm";

    //Formatos para los log.info que se utilizan para registrar que se estan enviando notificaciones a los empleados por
    //tener un fichaje de conexión/entrada durante mucho tiempo (FichajesCron.java)
    public static final String CUT_OF_NOTIFICATION_OFFICE_SIGN = "[{}] - Employee with code {} has an office sign in " +
            "with date and time [{}]. As it has a duration longer than the sign in end threshold, a new register of " +
            "type disconnect is inserted an a notification is send to him and his manager";
    public static final String CUT_OF_NOTIFICATION_ONLINE_SIGN = "[{}] - Employee with code {} has an online sign in " +
            "with date and time [{}]. As it has a duration longer than the sign in end threshold, a new register of " +
            "type disconnect is inserted an a notification is send to him and his manager";
    public static final String OVERTIME_NOTIFICATION_OFFICE_SIGN = "[{}] - Employee with code {} has an office " +
            "sign in with date and time [{}]. As it has a duration longer than the notification threshold, a " +
            "notification is send to him";
    public static final String OVERTIME_NOTIFICATION_ONLINE_SIGN = "[{}] - Employee with code {} has an online " +
            "sign in with date and time [{}]. As it has a duration longer than the notification threshold, a " +
            "notification is send to him";
    public static final String OVERTIME_NOTIFICATION_OFFICE_SIGN_RESEND = "[{}] - Employee with code {} has an office" +
            " sign in with date and time [{}]. As it has a duration longer than the notification threshold and the " +
            "notification period has passed, another notification is sent to him";
    public static final String OVERTIME_NOTIFICATION_ONLINE_SIGN_RESEND = "[{}] - Employee with code {} has an online" +
            " sign in with date and time [{}]. As it has a duration longer than the notification threshold and the " +
            "notification period has passed, another notification is sent to him";

    private Constants() {
    }
}
