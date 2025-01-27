package br.com.studioSalon.apiAuthentication.controllers;

import br.com.studioSalon.apiAuthentication.dto.twoFactor.RegistrationRequestDTO;
import br.com.studioSalon.apiAuthentication.dto.twoFactor.SendRequestDTO;
import br.com.studioSalon.apiAuthentication.services.UserCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth/2fa/userCustomers")
public class UserCustomerController {
    @Autowired
    private UserCustomerService userCustomerService;


    @PostMapping(value = "/register")
    public void registerUser(@RequestBody RegistrationRequestDTO request) {
        userCustomerService.registerUserCustomer(request.getUserCustomerDTO().toUserCustomer(),
                request.getConfirmationMethodEnum());
    }

    @PostMapping(value = "/send")
    public void sendUser(@RequestBody SendRequestDTO request) {
        userCustomerService.sendCodeUser(request.getEmail()/*, request.getConfirmationMethodEnum()*/);
    }

    @GetMapping(value = "/validate")
    public ResponseEntity<?> validateConfirmationCode(
            @RequestParam String email,
            @RequestParam String code
    ) {
        var isValid = this.userCustomerService.validateConfirmationCode(email, code);
        if (isValid) {
            userCustomerService.deleteCode(email, code);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The code has expired, please request another one.");
        }
    }
}
