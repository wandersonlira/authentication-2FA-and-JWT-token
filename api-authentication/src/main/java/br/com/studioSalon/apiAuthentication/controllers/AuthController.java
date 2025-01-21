package br.com.studioSalon.apiAuthentication.controllers;


import br.com.studioSalon.apiAuthentication.dto.security.AccountCredentialsDTO;
import br.com.studioSalon.apiAuthentication.security.jwt.JwtTokenProvider;
import br.com.studioSalon.apiAuthentication.services.AuthServices;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthServices authServices;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;  // DEIXEI FINAL


    @SuppressWarnings("rawtypes")
    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsDTO data) {
        if (checkIfParamsIsNotNull(data))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");

        var token = authServices.signin(data);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }


    @SuppressWarnings("rawtypes")
//    @Operation(summary = "Refresh token for authenticated user and returns a token")
    @PutMapping(value = "/refresh/{username}")
    public ResponseEntity refreshToken(@PathVariable("username") String username,
                                       @RequestHeader("Authorization") String refreshToken) {
        if (checkIfParamsIsNotNull(username, refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        var token = authServices.refreshToken(username, refreshToken);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }


//    @GetMapping(value = "/verify/")
//    public ResponseEntity<UserDetails> refreshToken(@RequestHeader("Authorization") String token) {
//        String tokenResponse = jwtTokenProvider.resolveToken2( token);
//        if (tokenResponse != null && jwtTokenProvider.validateToken(tokenResponse)) {
//            UserDetails userDetails = jwtTokenProvider.getAUserDetails(tokenResponse);
//            if (userDetails != null) {
//                return ResponseEntity.ok().body(userDetails);
//            }
//        }
//        return null;
//    }



    private boolean checkIfParamsIsNotNull(String username, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() ||
                username == null || username.isBlank();
    }

    private boolean checkIfParamsIsNotNull(AccountCredentialsDTO data) {
        return data == null || data.getUsername() == null || data.getUsername().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank();
    }
}
