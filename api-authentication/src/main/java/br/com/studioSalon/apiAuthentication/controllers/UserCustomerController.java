package br.com.studioSalon.apiAuthentication.controllers;

import br.com.studioSalon.apiAuthentication.dto.twoFactor.RegistrationRequestDTO;
import br.com.studioSalon.apiAuthentication.dto.twoFactor.SendRequestDTO;
import br.com.studioSalon.apiAuthentication.services.UserCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
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
        userCustomerService.sendCodeUser(request.getEmail());
    }

}
