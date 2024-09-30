package com.michael.test.repository;

import com.michael.test.model.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Books, String> {

    Page<Books> findByDeletedAtIsNull(Pageable pageable);

    Optional<Books> findByIdAndDeletedAtIsNull(String id);

    @Query(value = "SELECT * FROM books " +
            "WHERE id = ?1 " +
            "AND stock > 0 " +
            "AND deleted_at IS NULL" ,nativeQuery = true)
    Optional<Books> findBookCanBorrow(String id);
}
