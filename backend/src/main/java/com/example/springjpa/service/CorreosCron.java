package com.example.springjpa.service;

import com.example.springjpa.entities.Usuarios;
import com.example.springjpa.repository.UsuariosRepository;
import com.example.springjpa.utils.logging.FsLogManager;
import com.example.springjpa.utils.logging.Utilities;
import com.example.springjpa.utils.mail.*;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@EnableScheduling
class CorreosCron {

    private static final Logger log = FsLogManager.getLogger(CorreosCron.class);
    private static final Logger errorLog = FsLogManager.getErrorLog();
    private static final String LOG_ERROR_RESPONSE = "[{}] {} : {}";
    private static final String ERROR_LOG_RESPONSE = "[{}] - {}";

    private static final String USER_LOGGED = "TALKING RIGHT NOW";
    private static final Integer NUM_EMAIL_2 = 2;
    private static final Integer NUM_EMAIL_3 = 3;
    private static final Integer NUM_EMAIL_4 = 4;

    private static final String URL_EMAIL2 = "https://youtu.be/CuzkjtrwT2A";
    private static final String URL_EMAIL3 = "https://youtu.be/beLBO05amBs";
    private static final String URL_EMAIL4 = "https://go.hotmart.com/I66830114C?dp=1";

    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    UsuariosServiceImpl usuariosService;


    @Scheduled(cron = "${correos.envio.2}")
    private void sendSegundoCorreo(){
        log.info("[{}] CRON SendSegundoCorreo", USER_LOGGED);
        try{
            List<Usuarios> usuariosList = usuariosRepository.findUsuariosByNumEmail(NUM_EMAIL_2);
            if(!usuariosList.isEmpty()){
                log.info("[{}] Sending emails to users",  USER_LOGGED);
                for (Usuarios usuarios : usuariosList) {
                    TagEmail2 tagEmail2 = new TagEmail2(URL_EMAIL2);
                    MailBean mailBean = MailTemplateLoader.generateBody(tagEmail2);
                    usuariosService.sendEmail(usuarios.getEmail(),mailBean.getSubject(),mailBean.getBody());
                    usuarios.setNumEmail(3);
                    usuariosRepository.save(usuarios);
                    log.info("[{}] Correos enviados", USER_LOGGED);
                }
            }

        } catch (Exception e) {
            log.error(LOG_ERROR_RESPONSE, CorreosCron.class.getSimpleName(), e.getClass().getSimpleName(), e.getMessage());
            errorLog.error(ERROR_LOG_RESPONSE, CorreosCron.class.getSimpleName(), Utilities.printStackTrace(e));
        }
    }

    @Scheduled(cron = "${correos.envio.3}")
    private void sendTerceroCorreo(){
        log.info("[{}] CRON SendTerceroCorreo", USER_LOGGED);
        try{
            List<Usuarios> usuariosList = usuariosRepository.findUsuariosByNumEmail(NUM_EMAIL_3);
            if(!usuariosList.isEmpty()){
                log.info("[{}] Sending emails to users",  USER_LOGGED);
                for (Usuarios usuarios : usuariosList) {
                    TagEmail3 tagEmail3 = new TagEmail3(URL_EMAIL3);
                    MailBean mailBean = MailTemplateLoader.generateBody(tagEmail3);
                    usuariosService.sendEmail(usuarios.getEmail(),mailBean.getSubject(),mailBean.getBody());
                    usuarios.setNumEmail(4);
                    usuariosRepository.save(usuarios);
                    log.info("[{}] Correos enviados", USER_LOGGED);
                }
            }

        } catch (Exception e) {
            log.error(LOG_ERROR_RESPONSE, CorreosCron.class.getSimpleName(), e.getClass().getSimpleName(), e.getMessage());
            errorLog.error(ERROR_LOG_RESPONSE, CorreosCron.class.getSimpleName(), Utilities.printStackTrace(e));
        }
    }

    @Scheduled(cron = "${correos.envio.4}")
    private void sendCuartoCorreo(){
        log.info("[{}] CRON SendCuartoCorreo", USER_LOGGED);
        try{
            List<Usuarios> usuariosList = usuariosRepository.findUsuariosByNumEmail(NUM_EMAIL_4);
            if(!usuariosList.isEmpty()){
                log.info("[{}] Sending emails to users",  USER_LOGGED);
                for (Usuarios usuarios : usuariosList) {
                    TagEmail4 tagEmail4 = new TagEmail4(URL_EMAIL4);
                    MailBean mailBean = MailTemplateLoader.generateBody(tagEmail4);
                    usuariosService.sendEmail(usuarios.getEmail(),mailBean.getSubject(),mailBean.getBody());
                    log.info("[{}] Correos enviados", USER_LOGGED);
                }
            }

        } catch (Exception e) {
            log.error(LOG_ERROR_RESPONSE, CorreosCron.class.getSimpleName(), e.getClass().getSimpleName(), e.getMessage());
            errorLog.error(ERROR_LOG_RESPONSE, CorreosCron.class.getSimpleName(), Utilities.printStackTrace(e));
        }
    }

}
