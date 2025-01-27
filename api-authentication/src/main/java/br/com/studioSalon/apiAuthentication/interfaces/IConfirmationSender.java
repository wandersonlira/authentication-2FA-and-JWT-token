package br.com.studioSalon.apiAuthentication.interfaces;

import br.com.studioSalon.apiAuthentication.model.UserCustomer;

public interface IConfirmationSender {

    void sendConfirmation(UserCustomer userCustomer);
}
