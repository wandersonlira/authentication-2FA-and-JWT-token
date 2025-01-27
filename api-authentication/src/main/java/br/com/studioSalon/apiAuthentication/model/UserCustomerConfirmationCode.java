package br.com.studioSalon.apiAuthentication.model;


import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "users_customer_confirmation_code")
public class UserCustomerConfirmationCode implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "confirmation_code") private String confirmationCode;
    @Column(name = "creation_time") private LocalDateTime creationTime = LocalDateTime.now();
    @OneToOne @JoinColumn(name = "user_customer_id") private UserCustomer userCustomer;



    public UserCustomerConfirmationCode() {}

    public UserCustomerConfirmationCode(String confirmationCode, UserCustomer userCustomer) {
        this.confirmationCode = confirmationCode;
        this.userCustomer = userCustomer;
    }



    public boolean isValid(){
        var expirationLimit = creationTime.plusHours(1); // limite de validade de 1 hora
        var currentTime = LocalDateTime.now();
        return currentTime.isBefore(expirationLimit);

    }



    public Long getId() {
        return id;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public UserCustomer getUserCustomer() {
        return userCustomer;
    }

    public void setUserCustomer(UserCustomer userCustomer) {
        this.userCustomer = userCustomer;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCustomerConfirmationCode that = (UserCustomerConfirmationCode) o;
        return Objects.equals(id, that.id) && Objects.equals(confirmationCode, that.confirmationCode) && Objects.equals(creationTime, that.creationTime) && Objects.equals(userCustomer, that.userCustomer);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(confirmationCode);
        result = 31 * result + Objects.hashCode(creationTime);
        result = 31 * result + Objects.hashCode(userCustomer);
        return result;
    }
}
