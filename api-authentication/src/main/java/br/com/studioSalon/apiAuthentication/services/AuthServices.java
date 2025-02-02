package br.com.studioSalon.apiAuthentication.services;


import br.com.studioSalon.apiAuthentication.dto.security.AccountCredentialsDTO;
import br.com.studioSalon.apiAuthentication.dto.security.TokenDTO;
import br.com.studioSalon.apiAuthentication.model.User;
import br.com.studioSalon.apiAuthentication.model.UserCustomer;
import br.com.studioSalon.apiAuthentication.repositories.UserRepository;
import br.com.studioSalon.apiAuthentication.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@EnableAsync
public class AuthServices {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository repository;
    private final UserCustomerService userCustomerService;


    @Autowired
    public AuthServices(AuthenticationManager authenticationManager, UserCustomerService userCustomerService,
                        UserRepository repository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userCustomerService = userCustomerService;
        this.repository = repository;
        this.jwtTokenProvider = jwtTokenProvider;
    }



    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken) {
        var user = repository.findByUserName(username);

        var tokenResponse = new TokenDTO();
        if (user != null) {
            tokenResponse = jwtTokenProvider.refreshToken(refreshToken);
            tokenResponse.setFullName(user.getFullName()); // --------  N E W   I M P L E M E N T A T I O N -----------
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
        return ResponseEntity.ok(tokenResponse);
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity signin(AccountCredentialsDTO data) {
        try {
            var username = data.getUsername();
            var password = data.getPassword();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            var user = repository.findByUserName(username);
            if (user != null) {
                userCustomerService.sendCodeUser(user.getUserCustomer().getEmail());
            } else {
                throw new UsernameNotFoundException("Username " + username + "not found!");
            }
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    public TokenDTO validateConfirmationCode(String username, String code) {
        User user = getEmailOfUsername(username);
        String email = user.getUserCustomer().getEmail();
//        UserCustomer userCustomer = userCustomerService.findUserByEmailAndCode(email, code);
        UserCustomer userCustomer = userCustomerService.findUserByEmailAndCode(email, code);
        var tokenResponse = new TokenDTO();
        userCustomerService.codeIsValid(userCustomer.getUserConfirmationCode());
        tokenResponse = this.generateToken(userCustomer.getUser().getUsername());
        userCustomerService.deleteCode(email, code);
        return tokenResponse;
    }

    private TokenDTO generateToken(String username) {
        try {
            var user = repository.findByUserName(username);
            var tokenResponse = new TokenDTO();
            if (user != null) {
                tokenResponse = jwtTokenProvider.createAccessToken(username, user.getRoles());
                tokenResponse.setFullName(user.getFullName()); // --------  N E W   I M P L E M E N T A T I O N -----------
            } else {
                throw new UsernameNotFoundException("Username " + username + "not found!");
            }
            return tokenResponse;
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    private User getEmailOfUsername(String username) {
        return this.repository.findByUserName(username);
    }
}
