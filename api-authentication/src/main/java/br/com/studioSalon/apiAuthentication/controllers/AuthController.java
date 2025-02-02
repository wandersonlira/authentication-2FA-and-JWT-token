package br.com.studioSalon.apiAuthentication.controllers;


import br.com.studioSalon.apiAuthentication.dto.security.AccountCredentialsDTO;
import br.com.studioSalon.apiAuthentication.services.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthServices authServices;



    @SuppressWarnings("rawtypes")
    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsDTO data) {
        if (checkIfParamsIsNotNull(data))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");

        var statusCode = authServices.signin(data);
        if (statusCode.getStatusCode().value() != 202) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return statusCode;
    }


    @GetMapping("/edit")
    public ResponseEntity<?> edit() {
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/validate")
    public ResponseEntity<?> validateConfirmationCode(
            @RequestParam String username, @RequestParam String code) {
        var tokenResponse = authServices.validateConfirmationCode(username, code);
        if (tokenResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(tokenResponse);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The code has expired, please request another one.");
        }
    }


    @SuppressWarnings("rawtypes")
    @PutMapping(value = "/refresh/{username}")
    public ResponseEntity refreshToken(@PathVariable("username") String username,
                                       @RequestHeader("Authorization") String refreshToken) {
        if (checkIfParamsIsNotNull(username, refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        var token = authServices.refreshToken(username, refreshToken);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }


    private boolean checkIfParamsIsNotNull(String username, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() ||
                username == null || username.isBlank();
    }

    private boolean checkIfParamsIsNotNull(AccountCredentialsDTO data) {
        return data == null || data.getUsername() == null || data.getUsername().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank();
    }
}
