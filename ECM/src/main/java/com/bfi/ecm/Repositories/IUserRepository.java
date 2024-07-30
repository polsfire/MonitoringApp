package com.bfi.ecm.Repositories;

import com.bfi.ecm.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
Optional<User> findByEmail(String Email);
}
