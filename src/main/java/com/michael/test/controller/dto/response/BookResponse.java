package com.michael.test.controller.dto.response;

import com.michael.test.model.Books;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class BookResponse {
    private String title;
    private String author;
    private String isbn;
    private Date publishedDate;
    private int stock;

    public BookResponse(Books book) {
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
        this.publishedDate = book.getPublishedDate();
        this.stock = book.getStock();
    }
}
