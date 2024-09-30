package com.michael.test.model;

import com.fasterxml.jackson.annotation.*;
import com.michael.test.controller.dto.request.BorrowBookRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transactions extends AuditTrail {

    @Id
    @Column(nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @JsonBackReference
    private Books book;

    private Date borrowDate;
    private Date returnDate;

    public Transactions(BorrowBookRequest request, Users idUser, Books idBook) {
        this.id = UUID.randomUUID().toString();
        this.user = idUser;
        this.book = idBook;
        this.borrowDate = new Date(System.currentTimeMillis());
    }

    public void applyReturnBook() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
        this.returnDate = new Date(System.currentTimeMillis());
    }

}
