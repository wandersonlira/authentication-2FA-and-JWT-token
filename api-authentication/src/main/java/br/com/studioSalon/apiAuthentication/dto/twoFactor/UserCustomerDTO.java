package br.com.studioSalon.apiAuthentication.dto.twoFactor;

import br.com.studioSalon.apiAuthentication.model.UserCustomer;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class UserCustomerDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String userCustomerName;
    private String email;
    private String phoneNumber;



    public UserCustomerDTO(String userCustomerName, String phoneNumber, String email) {
        this.userCustomerName = userCustomerName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }



    public UserCustomer toUserCustomer() {
        UserCustomer userCustomer = new UserCustomer();
        userCustomer.setName(this.userCustomerName);
        userCustomer.setEmail(this.email);
        userCustomer.setPhoneNumber(this.phoneNumber);
        return userCustomer;
    }



    public String getUserCustomerName() {
        return userCustomerName;
    }

    public void setUserCustomerName(String userCustomerName) {
        this.userCustomerName = userCustomerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCustomerDTO that = (UserCustomerDTO) o;
        return Objects.equals(userCustomerName, that.userCustomerName) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(userCustomerName);
        result = 31 * result + Objects.hashCode(email);
        result = 31 * result + Objects.hashCode(phoneNumber);
        return result;
    }
}
