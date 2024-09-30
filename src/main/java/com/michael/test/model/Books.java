package com.michael.test.model;

import com.fasterxml.jackson.annotation.*;
import com.michael.test.controller.dto.request.CreateBookRequest;
import com.michael.test.controller.dto.request.UpdateBookRequest;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Books extends AuditTrail{

    @Id
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private String id;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "isbn", nullable = false)
    private String isbn;
    @Column(name = "published_date")
    private Date publishedDate;
    @Column(name = "stock")
    private int stock;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Transactions> transactions;

    public Books(CreateBookRequest request) {
        this.id = UUID.randomUUID().toString();
        this.title = request.getTitle();
        this.author = request.getAuthor();
        this.isbn = request.getIsbn();
        this.publishedDate = request.getPublishedDate();
        this.stock = request.getStock();
    }

    public void applyUpdate(UpdateBookRequest request) {
        this.title = request.getTitle();
        this.author = request.getAuthor();
        this.isbn = request.getIsbn();
        this.publishedDate = request.getPublishedDate();
        this.stock = request.getStock();
    }

    public void applyDelete() {
        this.deletedAt = new Timestamp(System.currentTimeMillis());
    }
}
