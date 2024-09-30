package com.michael.test.services;

import com.michael.test.controller.dto.request.BorrowBookRequest;
import com.michael.test.controller.dto.request.FilterParam;
import com.michael.test.controller.dto.response.TransactionUserResponse;
import com.michael.test.model.Books;
import com.michael.test.model.Transactions;
import com.michael.test.model.Users;
import com.michael.test.repository.BookRepository;
import com.michael.test.repository.TransactionRepository;
import com.michael.test.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final TransactionRepository transactionRepository;
    private final BookService bookService;

    public Transactions borrowBook(BorrowBookRequest request) {
        try {
            Users user = userRepository.findById(request.getUserId()).orElse(null);
            Books book = bookRepository.findBookCanBorrow(request.getBookId()).orElse(null);

            if (user != null && book != null) {
                bookService.updateStockBook(book, book.getStock() - 1);
                Transactions newTransaction = new Transactions(request, user, book);

                System.out.println(newTransaction.getBook().getStock());

                return transactionRepository.save(newTransaction);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Transactions returnBook(String transactionID) {
        Transactions transactions = transactionRepository.findByIdAndDeletedAtIsNull(transactionID).orElse(null);

        if (transactions != null) {
            transactions.applyReturnBook();

            bookService.updateStockBook(transactions.getBook(), transactions.getBook().getStock() + 1);

            return transactionRepository.save(transactions);
        } else {
            return null;
        }
    }

    public Page<TransactionUserResponse> getAllTransactionUser(FilterParam param, String userId) {
        try {
            Sort.Direction direction = Sort.Direction.fromString(param.getSort());
            Sort sort = Sort.by(direction, param.getSortBy());
            Pageable pageable = PageRequest.of(param.getPage(), param.getSize(), sort);

            Page<Transactions> transactionsPage = transactionRepository.findByUserIdAndDeletedAtIsNull(userId, pageable);

            return transactionsPage.map(x -> new TransactionUserResponse(x));
        } catch (Exception e) {
            return Page.empty();
        }
    }
}
