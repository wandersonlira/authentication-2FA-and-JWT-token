package br.com.studioSalon.apiAuthentication.components.message;

import br.com.studioSalon.apiAuthentication.interfaces.IConfirmationSender;
import br.com.studioSalon.apiAuthentication.model.UserCustomer;
import br.com.studioSalon.apiAuthentication.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailConfirmationSender implements IConfirmationSender {
    private static final Logger log = LoggerFactory.getLogger(EmailConfirmationSender.class);
    private final EmailService emailService;


    public EmailConfirmationSender(EmailService emailService) {
        this.emailService = emailService;
    }



    @Override
    public void sendConfirmation(UserCustomer userCustomer) {
        emailService.sendEmail(userCustomer);
        log.info("Seu código Email para ativação é: {}", userCustomer.getUserConfirmationCode().getConfirmationCode());
    }
    
}
