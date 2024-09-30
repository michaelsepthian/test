package com.michael.test.controller.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class CreateBookRequest {
    private String title;
    private String author;
    private String isbn;
    private Date publishedDate;
    private int stock;
}
