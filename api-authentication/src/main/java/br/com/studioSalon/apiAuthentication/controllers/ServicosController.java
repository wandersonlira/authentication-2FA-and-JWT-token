package br.com.studioSalon.apiAuthentication.controllers;


import br.com.studioSalon.apiAuthentication.model.ServicosModel;
import br.com.studioSalon.apiAuthentication.repositories.ServicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/servicos",
        consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ServicosController {
    @Autowired
    ServicosRepository repository;

    @GetMapping(value = "/v1/")
    public ResponseEntity<List<ServicosModel>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }
}
