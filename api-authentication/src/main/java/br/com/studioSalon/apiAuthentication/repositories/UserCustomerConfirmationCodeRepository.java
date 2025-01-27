package br.com.studioSalon.apiAuthentication.repositories;

import br.com.studioSalon.apiAuthentication.model.UserCustomerConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCustomerConfirmationCodeRepository extends JpaRepository<UserCustomerConfirmationCode, Long> {

    @Query("SELECT ucc FROM UserCustomerConfirmationCode ucc JOIN ucc.userCustomer userCustomer WHERE userCustomer.email = :email AND ucc.confirmationCode = :code")
    UserCustomerConfirmationCode findByEmailAndCode(@Param("email") String email, @Param("code") String code);

    @Modifying
    @Query("DELETE FROM UserCustomerConfirmationCode u WHERE u.id = :id")
    void deleteCode(@Param("id") Long id);

}
