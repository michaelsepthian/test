package com.michael.test.controller;

import com.michael.test.controller.dto.request.BorrowBookRequest;
import com.michael.test.controller.dto.request.FilterParam;
import com.michael.test.controller.dto.response.BaseResponse;
import com.michael.test.controller.dto.response.TransactionUserResponse;
import com.michael.test.model.Transactions;
import com.michael.test.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/borrow-book")
    @Secured({"ROLE_USER"})
    public ResponseEntity<BaseResponse> borrowBook(@RequestBody BorrowBookRequest borrowBookRequest) {
        try {
            Transactions transactions = transactionService.borrowBook(borrowBookRequest);

            return ResponseEntity.ok().body(new BaseResponse(true, "Success Borrow Book", transactions));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage(), null));
        }
    }

    @PostMapping("/return-book/{transactionId}")
    @Secured({"ROLE_USER"})
    public ResponseEntity<BaseResponse> returnBook(@PathVariable String transactionId) {
        try {
            Transactions transactions = transactionService.returnBook(transactionId);

            return ResponseEntity.ok().body(new BaseResponse(true, "Success Return Book", transactions));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage(), null));
        }
    }

    @GetMapping("/get-transaction/{idUser}")
    public ResponseEntity<BaseResponse> getTransaction(@PathVariable String idUser, FilterParam filterParam) {
        try {
            Page<TransactionUserResponse> transactionUserResponses = transactionService.getAllTransactionUser(filterParam, idUser);

            return ResponseEntity.ok().body(new BaseResponse(true, "Success Get Transaction", transactionUserResponses));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage(), null));
        }
    }
}
