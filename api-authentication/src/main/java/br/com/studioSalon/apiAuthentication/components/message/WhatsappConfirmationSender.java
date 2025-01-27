package br.com.studioSalon.apiAuthentication.components.message;

import br.com.studioSalon.apiAuthentication.interfaces.IConfirmationSender;
import br.com.studioSalon.apiAuthentication.model.UserCustomer;
import br.com.studioSalon.apiAuthentication.services.GtiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class WhatsappConfirmationSender implements IConfirmationSender {
    private static final Logger log = LoggerFactory.getLogger(WhatsappConfirmationSender.class);
    private final GtiService gtiService;


    public WhatsappConfirmationSender(GtiService gtiService) {
        this.gtiService = gtiService;
    }


    @Override
    public void sendConfirmation(UserCustomer userCustomer) {
        this.gtiService.sendMessage(userCustomer);
        log.info("Seu código WhatsApp para ativação é: {}", userCustomer.getUserConfirmationCode().getConfirmationCode());
    }
    
}
