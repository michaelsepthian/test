package com.michael.test.repository;

import com.michael.test.model.Transactions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transactions, String> {

    Optional<Transactions> findByIdAndDeletedAtIsNull(String id);

    Page<Transactions> findByUserIdAndDeletedAtIsNull(String userId, Pageable pageable);
}
