package br.com.studioSalon.apiAuthentication.services;


import br.com.studioSalon.apiAuthentication.feign.client.TelesignMessagingClient;
import br.com.studioSalon.apiAuthentication.model.UserCustomer;
import org.springframework.stereotype.Service;

@Service
public class TelesignService {
    private final TelesignMessagingClient messagingClient;

    public TelesignService(TelesignMessagingClient messagingClient) {
        this.messagingClient = messagingClient;
    }

    public void sendSMS(UserCustomer userCustomer) {
        String phoneNumber = userCustomer.getPhoneNumber();
        String message = "Olá" + userCustomer.getName() + "seu código de confirmação é: "
                + userCustomer.getUserConfirmationCode().getConfirmationCode();
        String requestBody = "phone_number=" + phoneNumber + "&message=" + message + "&message_type=ARN";
        this.messagingClient.sendSMS(requestBody);
    }
}
