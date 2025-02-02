package br.com.studioSalon.apiAuthentication.services;


import br.com.studioSalon.apiAuthentication.enums.ConfirmationMethodEnum;
import br.com.studioSalon.apiAuthentication.model.UserCustomer;
import br.com.studioSalon.apiAuthentication.model.UserCustomerConfirmationCode;
import br.com.studioSalon.apiAuthentication.repositories.UserCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
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
                               ApplicationContext ctx) {
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

    @Async
    public void sendCodeUser(String email) {
        UserCustomer userCustomer = repository.findUserCustomerByEmail(email);
        if (userCustomer == null) {
            throw new UsernameNotFoundException("Email not found in registry!");
        }
        this.checksAndDeleteExistingCode(userCustomer);
        userConfirmationCodeService.createConfirmationCode(userCustomer);
        ConfirmationMethodEnum confirmationMethodEnum2 = ConfirmationMethodEnum.EMAIL;
        confirmationMethodEnum2.sendConfirmation(userCustomer, ctx);
    }

    public void codeIsValid(UserCustomerConfirmationCode userConfirmationCode) {
        if (!userConfirmationCode.isValid()) {
            throw new NoSuchElementException("The code has expired, please request another one.");
        }
    }

    public UserCustomer findUserByEmailAndCode(String email, String code) {
        UserCustomerConfirmationCode userConfirmationCode = this.findByEmailAndCode(email, code);
        return Optional.ofNullable(userConfirmationCode.getUserCustomer())
                .orElseThrow(() -> new NoSuchElementException("User not found for the E-mail and Code provided!"));
    }

    public void deleteCode(String email, String code) {
        userConfirmationCodeService.deleteCode(email, code);
    }

    private UserCustomerConfirmationCode findByEmailAndCode(String email, String code) {
        return Optional.ofNullable(userConfirmationCodeService.findByEmailAndCode(email, code))
                .orElseThrow(() -> new NoSuchElementException("E-mail or Code not found!"));
    }

    private void checksAndDeleteExistingCode(UserCustomer userCustomer) {
        if (userCustomer.getEmail() != null && userCustomer.getUserConfirmationCode() != null) {
            String email = userCustomer.getEmail();
            String confirmationCode = userCustomer.getUserConfirmationCode().getConfirmationCode();
            try {
                this.findByEmailAndCode(email, confirmationCode);
                deleteCode(email, confirmationCode);
            } catch (NoSuchElementException ignored) {

            } catch (Exception exception) {
                throw new RuntimeException("Error occurred while deleting confirmation code.", exception);
            }
        }
    }

}
