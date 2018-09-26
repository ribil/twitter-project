package com.gmail.ribil39.repository;

import com.gmail.ribil39.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findById(Integer id);

    User findByActivationCode(String code);
}
