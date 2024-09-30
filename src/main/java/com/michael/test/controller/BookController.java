package com.michael.test.controller;

import com.michael.test.controller.dto.request.CreateBookRequest;
import com.michael.test.controller.dto.request.FilterParam;
import com.michael.test.controller.dto.request.UpdateBookRequest;
import com.michael.test.controller.dto.request.UpdateStokBookRequest;
import com.michael.test.controller.dto.response.BaseResponse;
import com.michael.test.model.Books;
import com.michael.test.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/add")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<BaseResponse> addBook (@RequestBody CreateBookRequest request) {
        try {
            Books bookSaved = bookService.addNewBook(request);

            if (bookSaved != null) {
                return ResponseEntity.ok().body(new BaseResponse(true, "Success Add New Book", bookSaved));
            } else {
                return ResponseEntity.badRequest().body(new BaseResponse(false, "Failed Add New Book", null));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage(), null));
        }
    }

    @GetMapping("/get-books")
    public ResponseEntity<BaseResponse> getBooks (FilterParam filterParam) {
        try {
            Page<Books> booksPage = bookService.getAllBooksByPaging(filterParam);

            return ResponseEntity.ok().body(new BaseResponse(true, "Success Get All Books", booksPage));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage(), null));
        }
    }

    @GetMapping("/get-book/{idBook}")
    public ResponseEntity<BaseResponse> getBook (@PathVariable String idBook) {
        try {
            Books book = bookService.getOneBook(idBook);

            return ResponseEntity.ok().body(new BaseResponse(true, "Success Get Book", book));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage(), null));
        }
    }

    @PutMapping("/update-book/{idBook}")
    public ResponseEntity<BaseResponse> updateBook (@PathVariable("idBook") String idBook, @RequestBody UpdateBookRequest updateBookRequest) {
        Books updatedBook = bookService.updateBook(updateBookRequest, idBook);

        return ResponseEntity.ok().body(new BaseResponse(true, "Success Update Book", updatedBook));
    }

    @DeleteMapping("/delete-book/{idBook}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<BaseResponse> deleteBook (@PathVariable("idBook") String idBook) {
        try {
            bookService.deleteBook(idBook);

            return ResponseEntity.ok().body(new BaseResponse(true, "Success Delete Book", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage(), null));
        }
    }

    @PutMapping("/update-stock-book/{idBook}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<BaseResponse> updateStockBook(@PathVariable("idBook") Books books, @RequestBody UpdateStokBookRequest updateStokBookRequest) {
        try {
            Books updatedBook = bookService.updateStockBook(books, updateStokBookRequest.getStock());

            return ResponseEntity.ok().body(new BaseResponse(true, "Success Update Book", updatedBook));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage(), null));
        }
    }

}
