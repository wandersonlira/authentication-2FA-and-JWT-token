package br.com.studioSalon.apiAuthentication.services;


import br.com.studioSalon.apiAuthentication.enums.ConfirmationMethodEnum;
import br.com.studioSalon.apiAuthentication.model.UserCustomer;
import br.com.studioSalon.apiAuthentication.model.UserCustomerConfirmationCode;
import br.com.studioSalon.apiAuthentication.repositories.UserCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserCustomerService {
    private final UserCustomerRepository repository;
    private final UserCustomerConfirmationCodeService userConfirmationCodeService;
    private final ApplicationContext ctx;



    @Autowired
    public UserCustomerService(UserCustomerRepository repository,
                               UserCustomerConfirmationCodeService userConfirmationCodeService,
                               ApplicationContext ctx
    ) {
        this.repository = repository;
        this.userConfirmationCodeService = userConfirmationCodeService;
        this.ctx = ctx;
    }



    public void registerUserCustomer(UserCustomer userCustomer, ConfirmationMethodEnum confirmationMethodEnum) {
        UserCustomer userCustomerSaved = repository.findByUserCustomerName(userCustomer.getName());
        if (userCustomerSaved == null) {
            userCustomerSaved = repository.save(userCustomer);
        }
        userConfirmationCodeService.createConfirmationCode(userCustomerSaved);
        confirmationMethodEnum.sendConfirmation(userCustomerSaved, ctx);
    }

    public void sendCodeUser(String email/*, ConfirmationMethodEnum confirmationMethodEnum*/) {
        UserCustomer userCustomer = repository.findUserCustomerByEmail(email);
        if (userCustomer == null) {
            throw new UsernameNotFoundException("Email not found in registry!");
        }
        userConfirmationCodeService.createConfirmationCode(userCustomer);
        ConfirmationMethodEnum confirmationMethodEnum2 = ConfirmationMethodEnum.EMAIL;
        confirmationMethodEnum2.sendConfirmation(userCustomer, ctx);
    }

    public boolean validateConfirmationCode(String email, String code) {
        UserCustomer userCustomer = findByEmailAndCode(email, code);
        if (userCustomer == null || userCustomer.getUserConfirmationCode() == null) {
            return false;
        }
        return userCustomer.getUserConfirmationCode().isValid();
    }

    private UserCustomer findByEmailAndCode(String email, String code) {
        UserCustomerConfirmationCode userConfirmationCode = userConfirmationCodeService.findByEmailAndCode(email, code);
        if (userConfirmationCode == null) {
            throw new IllegalArgumentException("Usuário ou código não encontrado!");
        }
        return Optional.ofNullable(userConfirmationCode.getUserCustomer())
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
    }

    public void deleteCode(String email, String code) {
        userConfirmationCodeService.deleteCode(email, code);
    }
}
