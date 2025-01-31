package br.com.studioSalon.apiAuthentication.services;

import br.com.studioSalon.apiAuthentication.model.UserCustomer;
import br.com.studioSalon.apiAuthentication.model.UserCustomerConfirmationCode;
import br.com.studioSalon.apiAuthentication.repositories.UserCustomerConfirmationCodeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserCustomerConfirmationCodeService {
    private final UserCustomerConfirmationCodeRepository repository;


    public UserCustomerConfirmationCodeService(UserCustomerConfirmationCodeRepository repository) {
        this.repository = repository;
    }


    public void createConfirmationCode(UserCustomer userCustomer) {
        String newConfirmationCode = generateConfirmationCode();
        UserCustomerConfirmationCode userCustomerConfirmationCode =
                new UserCustomerConfirmationCode(newConfirmationCode, userCustomer);
        repository.save(userCustomerConfirmationCode);
        userCustomer.setUserConfirmationCode(userCustomerConfirmationCode); // Seta o código gerado ao userCustomer
    }

    private String generateConfirmationCode() {
        int codeLength = 6;
        int minCodeValue = 100_000;
        int maxCodeValue = 999_999;

        Random random = new Random();
        int randomNumber = random.nextInt(maxCodeValue - minCodeValue + 1) + minCodeValue;
        return String.format("%0" + codeLength + "d", randomNumber);
    }

    public UserCustomerConfirmationCode findByEmailAndCode(String email, String code) {
        return repository.findByEmailAndCode(email, code);
    }

    @Transactional
    public void deleteCode(String email, String code) {
        UserCustomerConfirmationCode isCode = repository.findByEmailAndCode(email, code);
        if ( isCode != null) {
            repository.deleteCode(isCode.getId());
        } else {
            throw new IllegalArgumentException("Code does not exist for deletion!");
        }
    }

}
