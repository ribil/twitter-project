package com.gmail.ribil39.repos;

import com.gmail.ribil39.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);


    //User findByIdAndActive(Long id);

    User findById(Integer id);

    User findByActivationCode(String code);
}
