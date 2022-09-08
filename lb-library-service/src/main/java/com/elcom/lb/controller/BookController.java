package com.elcom.lb.controller;

import com.elcom.lb.model.Author;
import com.elcom.lb.model.Book;
import javax.validation.Valid;
import com.elcom.lb.repository.Book.BookCustomizeRepository;
import com.elcom.lb.repository.Book.BookRepository;
import com.elcom.lb.repository.author.AuthorRepository;
import com.elcom.lb.service.book.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
public class BookController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private BookCustomizeRepository bookCustomizeRepository;
    @Autowired
    private BookService bookService;


    @Autowired
    private BookRepository bookRepository;
    @RequestMapping(value = "/book", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Lay danh sach Book",response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thành công, có dữ liệu"),
            @ApiResponse(code = 204, message = "Thành công, không có dữ liệu"),
            @ApiResponse(code = 401, message = "Chưa xác thực"),
            @ApiResponse(code = 403, message = "Truy cập bị cấm"),
            @ApiResponse(code = 404, message = "Không tìm thấy"),
            @ApiResponse(code = 500, message = "Lỗi BackEnd")
    })
    public ResponseEntity<List<Book>>findAll(@RequestParam(defaultValue = "1") Integer currentPage,
                                                         @RequestParam(defaultValue = "10") Integer rowsPerPage,
                                                         @RequestParam(defaultValue = "authorid") String sort){
        List<Book> bookList = bookCustomizeRepository.findAll();
        if(bookList.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookList,HttpStatus.OK);
    }

    @GetMapping("/book/{bookID}")
    public ResponseEntity<Optional<Book>>findById(@PathVariable(value = "bookID") UUID bookID)
    {
        Optional<Book> book = bookRepository.findById(bookID);
        return ResponseEntity.ok().body(book);
    }
    // xóa author theo ID OK
    @DeleteMapping("/book/id")
    public ResponseEntity<HttpStatus> deleteBook(@RequestParam("bookID")UUID bookID)
    {
        try {
            bookRepository.deleteById(bookID);
            return new ResponseEntity<>(HttpStatus.OK, HttpStatus.valueOf("Successfully deleted"));
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // xóa book OK
    @DeleteMapping("/book")
    public ResponseEntity<HttpStatus> deleteAllBook()
    {
        try
        {
            bookRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //them sach
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        try {
            Book _book = bookRepository.save(
                    new Book(book.getBookID(),
                            book.getBookName(),
                            book.getFirstLetter(),
                            book.getAuthorID(),
                            book.getCategoryID()));
            return new ResponseEntity<>(_book, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // them book OK




    // update author
    @RequestMapping(value = "/book", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateBook(@RequestBody Book book,@PathVariable UUID bookID)
    {
        Optional<Book> bookOptional = bookRepository.findById(bookID);

        if(bookOptional.isEmpty())
            return ResponseEntity.notFound().build();


        book.setBookID(bookID);
        bookRepository.save(book);

        return ResponseEntity.noContent().build();
    }
}
