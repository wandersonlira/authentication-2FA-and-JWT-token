package br.com.studioSalon.apiAuthentication.dto.twoFactor;

import br.com.studioSalon.apiAuthentication.enums.ConfirmationMethodEnum;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class RegistrationRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonAlias("userCustomer")
    private UserCustomerDTO userCustomerDTO;
    private ConfirmationMethodEnum confirmationMethodEnum;


    public RegistrationRequestDTO(UserCustomerDTO userCustomerDTO, ConfirmationMethodEnum confirmationMethodEnum) {
        this.userCustomerDTO = userCustomerDTO;
        this.confirmationMethodEnum = confirmationMethodEnum;
    }



    public UserCustomerDTO getUserCustomerDTO() {
        return userCustomerDTO;
    }

    public void setUserCustomerDTO(UserCustomerDTO userCustomerDTO) {
        this.userCustomerDTO = userCustomerDTO;
    }

    public ConfirmationMethodEnum getConfirmationMethodEnum() {
        return confirmationMethodEnum;
    }

    public void setConfirmationMethodEnum(ConfirmationMethodEnum confirmationMethodEnum) {
        this.confirmationMethodEnum = confirmationMethodEnum;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistrationRequestDTO that = (RegistrationRequestDTO) o;
        return Objects.equals(userCustomerDTO, that.userCustomerDTO) && confirmationMethodEnum == that.confirmationMethodEnum;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(userCustomerDTO);
        result = 31 * result + Objects.hashCode(confirmationMethodEnum);
        return result;
    }
}
