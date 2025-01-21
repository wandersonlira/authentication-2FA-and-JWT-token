package br.com.studioSalon.apiAuthentication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class TextForTest {

    @GetMapping("/hello")
    public ResponseEntity<String> textForTest() {
        return ResponseEntity.ok("Olá, Mundo!!");
    }
}
