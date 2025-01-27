package br.com.studioSalon.apiAuthentication.components.message;

import br.com.studioSalon.apiAuthentication.interfaces.IConfirmationSender;
import br.com.studioSalon.apiAuthentication.model.UserCustomer;
import br.com.studioSalon.apiAuthentication.services.TelesignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SmsConfirmationSender implements IConfirmationSender {
    private static final Logger log = LoggerFactory.getLogger(SmsConfirmationSender.class);
    private final TelesignService telesignService;

    public SmsConfirmationSender(TelesignService telesignService) {
        this.telesignService = telesignService;
    }

    @Override
    public void sendConfirmation(UserCustomer userCustomer) {
        this.telesignService.sendSMS(userCustomer);
        log.info("Seu código SMS para ativação é: {}", userCustomer.getUserConfirmationCode().getConfirmationCode());
    }

}
