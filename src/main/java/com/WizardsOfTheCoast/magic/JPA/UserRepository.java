package com.WizardsOfTheCoast.magic.JPA;

import com.WizardsOfTheCoast.magic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
    @Query("SELECT r FROM User r WHERE r.username = ?1 OR r.email = ?2")
    List<User> findByUsernameOrEmail(String username, String email);
}
