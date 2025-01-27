package br.com.studioSalon.apiAuthentication.dto.twoFactor;

import br.com.studioSalon.apiAuthentication.enums.ConfirmationMethodEnum;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class SendRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String email;
//    private ConfirmationMethodEnum confirmationMethodEnum;


    public SendRequestDTO(String email/*, ConfirmationMethodEnum confirmationMethodEnum*/) {
        this.email = email;
//        this.confirmationMethodEnum = confirmationMethodEnum;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public ConfirmationMethodEnum getConfirmationMethodEnum() {
//        return confirmationMethodEnum;
//    }
//
//    public void setConfirmationMethodEnum(ConfirmationMethodEnum confirmationMethodEnum) {
//        this.confirmationMethodEnum = confirmationMethodEnum;
//    }


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        SendRequestDTO that = (SendRequestDTO) o;
//        return Objects.equals(email, that.email) && confirmationMethodEnum == that.confirmationMethodEnum*/;
//    }

//    @Override
//    public int hashCode() {
//        int result = Objects.hashCode(email);
//        result = 31 * result + Objects.hashCode(confirmationMethodEnum);
//        return result;
//    }
}
