package com.example.springjpa.controller;


import com.example.springjpa.entities.Usuarios;
import com.example.springjpa.service.UsuariosService;
import com.example.springjpa.utils.HeaderUtil;
import com.example.springjpa.utils.logging.FsLogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.example.springjpa.utils.Constants.FINALLY_RESPONSE;
import static com.example.springjpa.utils.Constants.LOG_INFO;

/**
 * Contoller para gestionar Usuarios
 */
@RestController
@RequestMapping("/api")
public class UsuariosController {


    private static final Logger log = FsLogManager.getLogger(UsuariosController.class);

    private final UsuariosService usuariosService;

    @Autowired
    public UsuariosController(UsuariosService usuariosService){
        this.usuariosService = usuariosService;
    }


    @PostMapping(value = "/usuarios",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuarios> createUsuarios(@RequestBody Usuarios usuarios){

        HttpStatus httpStatus = HttpStatus.OK;
        log.info(" create usuario: "+ LOG_INFO , usuarios,RequestMethod.POST);
        try {
            Usuarios retrievedUsuarios = usuariosService.createUsuarios(usuarios);

            return ResponseEntity.created(new URI("/api/puestos/" + retrievedUsuarios.getIdUsuario()))
                    .headers(HeaderUtil.createEntityCreationAlert(Usuarios.class.getName(), retrievedUsuarios.getIdUsuario().toString()))
                    .body(retrievedUsuarios);

        } catch (Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(httpStatus);
        } finally{
            log.info(FINALLY_RESPONSE,  httpStatus);
        }
    }
}
