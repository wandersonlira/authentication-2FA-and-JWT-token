package br.com.studioSalon.apiAuthentication.services;

import br.com.studioSalon.apiAuthentication.model.UserCustomer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final String DEFAULT_SUBJECT_MAIL = "Lira 2FA: Aqui está o código de 6-dígitos solicitado";
    private final JavaMailSender javaMailSender;


    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(UserCustomer userCustomer) {
        String messageText = "Seu código para ativação é: " + userCustomer.getUserConfirmationCode().getConfirmationCode();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userCustomer.getEmail());
        message.setSubject(DEFAULT_SUBJECT_MAIL);
        message.setText(messageText);
        javaMailSender.send(message);
    }
}
