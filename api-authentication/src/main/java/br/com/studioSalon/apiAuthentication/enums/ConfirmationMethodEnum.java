package br.com.studioSalon.apiAuthentication.enums;

import br.com.studioSalon.apiAuthentication.components.message.EmailConfirmationSender;
import br.com.studioSalon.apiAuthentication.components.message.SmsConfirmationSender;
import br.com.studioSalon.apiAuthentication.components.message.WhatsappConfirmationSender;
import br.com.studioSalon.apiAuthentication.model.UserCustomer;
import br.com.studioSalon.apiAuthentication.services.EmailService;
import br.com.studioSalon.apiAuthentication.services.GtiService;
import br.com.studioSalon.apiAuthentication.services.TelesignService;
import org.springframework.context.ApplicationContext;

public enum ConfirmationMethodEnum {
    SMS,
    EMAIL,
    WA;


    public void sendConfirmation(UserCustomer userCustomer, ApplicationContext ctx) {
        switch (this) {
            case SMS -> {
                SmsConfirmationSender sender = new SmsConfirmationSender(ctx.getBean(TelesignService.class));
                sender.sendConfirmation(userCustomer);
            }
            case EMAIL -> {
                EmailConfirmationSender sender = new EmailConfirmationSender(ctx.getBean(EmailService.class));
                sender.sendConfirmation(userCustomer);
            }
            case WA -> {
                WhatsappConfirmationSender sender = new WhatsappConfirmationSender(ctx.getBean(GtiService.class));
                sender.sendConfirmation(userCustomer);
            }
        }
    }
}
