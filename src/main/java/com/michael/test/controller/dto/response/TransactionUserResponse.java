package com.michael.test.controller.dto.response;

import com.michael.test.model.Transactions;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class TransactionUserResponse {
    private UserResponse user;
    private BookResponse book;
    private Date borrowDate;
    private Date returnDate;

    public TransactionUserResponse(Transactions transactions) {
        this.book = new BookResponse(transactions.getBook());
        this.user = new UserResponse(transactions.getUser());
        this.borrowDate = transactions.getBorrowDate();
        this.returnDate = transactions.getReturnDate();
    }
}
