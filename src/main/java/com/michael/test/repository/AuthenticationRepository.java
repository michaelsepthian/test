package com.michael.test.repository;

import com.michael.test.model.Authentications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentications, String> {

    Optional<Authentications> findByUsername(String username);
}
