package br.com.studioSalon.apiAuthentication.model;


import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "users_customer")
public class UserCustomer implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id = null;
    @Column(name = "name") private String name;
    @Column(name = "email") private String email;
    @Column(name = "phone_number") private String phoneNumber;
    @Column(name = "active") private String active = "N";

    @OneToOne(mappedBy = "userCustomer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserCustomerConfirmationCode userConfirmationCode = null;



    public UserCustomer() {}

    public UserCustomer(String name, String email, String phoneNumber, String active, UserCustomerConfirmationCode userConfirmationCode) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.active = active;
        this.userConfirmationCode = userConfirmationCode;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public UserCustomerConfirmationCode getUserConfirmationCode() {
        return userConfirmationCode;
    }

    public void setUserConfirmationCode(UserCustomerConfirmationCode userConfirmationCode) {
        this.userConfirmationCode = userConfirmationCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCustomer that = (UserCustomer) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(active, that.active) && Objects.equals(userConfirmationCode, that.userConfirmationCode);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(email);
        result = 31 * result + Objects.hashCode(phoneNumber);
        result = 31 * result + Objects.hashCode(active);
        result = 31 * result + Objects.hashCode(userConfirmationCode);
        return result;
    }

}
