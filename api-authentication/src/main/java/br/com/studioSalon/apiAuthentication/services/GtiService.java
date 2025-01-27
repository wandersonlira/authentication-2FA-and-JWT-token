package br.com.studioSalon.apiAuthentication.services;

import br.com.studioSalon.apiAuthentication.feign.client.GtiWhatsAppClient;
import br.com.studioSalon.apiAuthentication.model.UserCustomer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class GtiService {
    private final GtiWhatsAppClient gtiWhatsAppClient;

    @Value("${message.gti.url}")
    private String gtiUrl;

    @Value("${message.gti.token}")
    private String gtiToken;

    @Value("${message.gti.user}")
    private String gtiUser;



    public GtiService(GtiWhatsAppClient gtiWhatsAppClient) {
        this.gtiWhatsAppClient = gtiWhatsAppClient;
    }


    public void sendMessage(UserCustomer userCustomer) {
        String msg = "Olá" + userCustomer.getName() + ",seu código de confirmação é: ";
        String code = userCustomer.getUserConfirmationCode() != null ?
                userCustomer.getUserConfirmationCode().getConfirmationCode() : null;
        String id = code != null ? code + "1" : "1";

        this.gtiWhatsAppClient.sendMessage(gtiUser, gtiToken, id, msg, userCustomer.getPhoneNumber());

    }
}
