package com.michael.test.controller.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class BorrowBookRequest {
    private String userId;
    private String bookId;
}
