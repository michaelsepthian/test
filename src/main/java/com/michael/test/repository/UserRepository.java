package com.michael.test.repository;

import com.michael.test.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    Optional<Users> findById(String id);

    Optional<Users> findByEmail(String email);
}
