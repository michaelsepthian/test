package com.michael.test.services;

import com.michael.test.controller.dto.request.CreateBookRequest;
import com.michael.test.controller.dto.request.FilterParam;
import com.michael.test.controller.dto.request.UpdateBookRequest;
import com.michael.test.model.Books;
import com.michael.test.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {
    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    public Books addNewBook(CreateBookRequest request) {
        try {
            Books book = new Books(request);

            return bookRepository.save(book);
        } catch (Exception e) {
            log.error("Error When Save Book : {}", e.getMessage());
            return null;
        }
    }

    public Books updateBook(UpdateBookRequest updateBookRequest, String bookId) {
        try {
            Books book = bookRepository.findByIdAndDeletedAtIsNull(bookId).orElse(null);
            if (book != null) {
                book.applyUpdate(updateBookRequest);

                return bookRepository.save(book);
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Error When Update Book : {}", e.getMessage());
            return null;
        }
    }

    public void deleteBook(String bookId) {
        try {
            Books book = bookRepository.findByIdAndDeletedAtIsNull(bookId).orElse(null);
            if (book != null) {
//                bookRepository.delete(book);

                book.applyDelete();

                bookRepository.save(book);
                log.info("Book {} success update flag deleted_at", bookId);
            }
        } catch (Exception e) {
            log.error("Error When Delete Book : {}", e.getMessage());
        }
    }

    public Page<Books> getAllBooksByPaging(FilterParam param) {
        try {
            Sort.Direction direction = Sort.Direction.fromString(param.getSort());
            Sort sort = Sort.by(direction, param.getSortBy());
            Pageable pageable = PageRequest.of(param.getPage(), param.getSize(), sort);
            return bookRepository.findByDeletedAtIsNull(pageable);
        } catch (Exception e) {
            log.error("Error When Get All Books : {}", e.getMessage());
            return Page.empty();
        }
    }

    public Books updateStockBook(Books books, int stock) {
        try {
            Books book = bookRepository.findByIdAndDeletedAtIsNull(books.getId()).orElse(null);
            if (book != null) {
                book.setStock(stock);
                return bookRepository.save(book);
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Error When Update Stock Book : {}", e.getMessage());

            return null;
        }
    }

    public Books getOneBook(String bookId) {
        try {
            return bookRepository.findByIdAndDeletedAtIsNull(bookId).orElse(null);
        } catch (Exception e) {
            log.error("Error When Get Book : {}", e.getMessage());
            return null;
        }
    }
}
