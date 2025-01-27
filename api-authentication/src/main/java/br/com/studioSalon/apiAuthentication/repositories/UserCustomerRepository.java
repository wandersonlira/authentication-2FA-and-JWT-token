package br.com.studioSalon.apiAuthentication.repositories;

import br.com.studioSalon.apiAuthentication.model.User;
import br.com.studioSalon.apiAuthentication.model.UserCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCustomerRepository extends JpaRepository<UserCustomer, Long> {

    @Query("SELECT uc FROM UserCustomer uc WHERE uc.name = :name")
    UserCustomer findByUserCustomerName(@Param("name") String name);

    @Query("SELECT uc FROM UserCustomer uc WHERE uc.email = :email")
    UserCustomer findUserCustomerByEmail(@Param("email") String email);
}
