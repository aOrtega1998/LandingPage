package com.example.springjpa.service;

import com.example.springjpa.entities.Usuarios;
import com.example.springjpa.exception.ErroresEnum;
import com.example.springjpa.exception.LandingPageException;
import com.example.springjpa.exception.LandingPageInvalidParamException;
import com.example.springjpa.repository.UsuariosRepository;
import com.example.springjpa.utils.logging.FsLogManager;
import com.example.springjpa.utils.logging.Utilities;
import com.example.springjpa.utils.mail.MailBean;
import com.example.springjpa.utils.mail.MailTemplateLoader;
import com.example.springjpa.utils.mail.TagEmail1;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static com.example.springjpa.utils.Constants.*;


@Service
public class UsuariosServiceImpl implements UsuariosService{


    private static final Logger log = FsLogManager.getLogger(UsuariosServiceImpl.class);
    private static final Logger errorLog = FsLogManager.getErrorLog();

    private static final String URL_EMAIL1 = "https://youtu.be/groxUNoT9VA";


    private final UsuariosRepository usuariosRepository;
    private final  JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Autowired
    public UsuariosServiceImpl(UsuariosRepository usuariosRepository,  JavaMailSender javaMailSender){
        this.usuariosRepository = usuariosRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public Usuarios createUsuarios(Usuarios usuarios) throws Exception {
        try {
            log.debug(LOG_DEBUG_IN, usuarios);
            validateUsuario(usuarios);
            TagEmail1 tagEmail1 = new TagEmail1(URL_EMAIL1);
            MailBean mailBean = MailTemplateLoader.generateBody(tagEmail1);
            sendEmail(usuarios.getEmail(),mailBean.getSubject(),mailBean.getBody());
            usuarios.setNumEmail(2);
            usuarios = usuariosRepository.save(usuarios);

            log.debug(" Usuario = {}", usuarios);

            return usuarios;
        } catch (Exception e){
            log.error(LOG_ERROR_RESPONSE,  e.getClass().getSimpleName(), e.getMessage());
            errorLog.error(ERROR_LOG_RESPONSE, Utilities.printStackTrace(e));
            throw e;
        }
    }
    private void validateUsuario(Usuarios usuario) throws LandingPageException {
        Usuarios emailUsuario= usuariosRepository.findUsuariosByEmail(usuario.getEmail());
        if(usuario.getNombre() == null || usuario.getNombre().isEmpty() ){
            log.error("Name is null. Throwing {}", LandingPageInvalidParamException.class.getSimpleName());
            throw new LandingPageException(ErroresEnum.NAME_NULL);
        }
        if(usuario.getApellido() == null || usuario.getApellido().isEmpty()){
            log.error("Surname is null. Throwing {}", LandingPageInvalidParamException.class.getSimpleName());
            throw new LandingPageException(ErroresEnum.SURNAME_NULL);
        }

        if(usuario.getEmail() == null || usuario.getEmail().isEmpty()){
            log.error("Email is null. Throwing {}", LandingPageInvalidParamException.class.getSimpleName());
            throw new LandingPageException(ErroresEnum.EMAIL_NULL);
        }else if(emailUsuario != null){
            log.error("Email already exists. Throwing {}", LandingPageInvalidParamException.class.getSimpleName());
            throw new LandingPageException(ErroresEnum.EMAIL_ALREADY_EXISTS);
        }


    }
    public void sendEmail(String to, String subject, String body) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(env.getProperty("spring.mail.username"));
        helper.setTo(to);
        helper.setSubject (subject);
        helper.setText(body, true);
        javaMailSender.send(mimeMessage);
    }




}
