package com.elcom.lb.controller;

import com.elcom.lb.model.Author;
import com.elcom.lb.model.Category;
import com.elcom.lb.repository.author.AuthorCustomizeRepository;
import com.elcom.lb.repository.author.AuthorRepository;
import com.elcom.lb.service.author.AuthorService;
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

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepository authorRepository;


    @Autowired
    private AuthorCustomizeRepository authorCustomizeRepository;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping(value = "/author", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Lay danh sach Author", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thành công, có dữ liệu"),
            @ApiResponse(code = 204, message = "Thành công, không có dữ liệu"),
            @ApiResponse(code = 401, message = "Chưa xác thực"),
            @ApiResponse(code = 403, message = "Truy cập bị cấm"),
            @ApiResponse(code = 404, message = "Không tìm thấy"),
            @ApiResponse(code = 500, message = "Lỗi BackEnd")
    })
    public ResponseEntity<List<Author>> findAll(@RequestParam(defaultValue = "1") Integer currentPage,
                                                @RequestParam(defaultValue = "10") Integer rowsPerPage,
                                                @RequestParam(defaultValue = "authorid") String sort) {
        List<Author> authorList = authorCustomizeRepository.findAll();
        if (authorList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(authorList, HttpStatus.OK);
    }

    @RequestMapping(value = "/find-author", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Lay danh sach Author", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thành công, có dữ liệu"),
            @ApiResponse(code = 204, message = "Thành công, không có dữ liệu"),
            @ApiResponse(code = 401, message = "Chưa xác thực"),
            @ApiResponse(code = 403, message = "Truy cập bị cấm"),
            @ApiResponse(code = 404, message = "Không tìm thấy"),
            @ApiResponse(code = 500, message = "Lỗi BackEnd")
    })
    public ResponseEntity<List<Author>> findAllCustomize(@RequestParam(defaultValue = "1") Integer currentPage,
                                                         @RequestParam(defaultValue = "10") Integer rowsPerPage,
                                                         @RequestParam(defaultValue = "authorid") String sort) {
        List<Author> authorList = authorCustomizeRepository.findAll();
        if (authorList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(authorList, HttpStatus.OK);
    }

//    @RequestMapping(value = "/author/{id}", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<Author> getById(@PathVariable("authorID") String authorID) throws ValidationException {
//        LOGGER.info("authorID: " + authorID);
//        if(authorID == null || authorID.isEmpty())
//            throw new ValidationException("authorID must not be null");
//
//        Author author = authorService.findById(authorID);
//        if(author == null)
//            return new ResponseEntity<>(author,HttpStatus.NO_CONTENT);
//        return new ResponseEntity<>(author,HttpStatus.OK);
//    }

    @GetMapping("/author/{authorID}")
    public ResponseEntity<Optional<Author>> getById(@PathVariable(value = "authorID") String authorID) {
        Optional<Author> author = authorRepository.findById(authorID);
        return ResponseEntity.ok().body(author);
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Author> delete(@PathVariable("id") String authorID) throws ValidationException {
        LOGGER.info("delete() -->id[{}]", authorID);
        if (authorID.equals(0L)) throw new ValidationException("id khong được để trống");
        Author author = authorService.findById(authorID);

        if (author == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        authorService.remove(author);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//   @RequestMapping(value = "/add-author", method = RequestMethod.POST)
//   @ApiOperation(value = "Create a new author", response = List.class)
//   @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Thêm mới thành công"),
//            @ApiResponse(code = 401, message = "Chưa xác thực"),
//            @ApiResponse(code = 403, message = "Truy cập bị cấm"),
//            @ApiResponse(code = 404, message = "Không tìm thấy"),
//            @ApiResponse(code = 500, message = "Lỗi BackEnd")
//    })
//    public String create(@RequestBody Author author, BindingResult result, Model model){
//        author.setAuthorID(String.valueOf(SocketFlow.Status.IN_PROGRESS));
//
//       Author newAuthor = authorServiceImpl.save(author);
//    }

    // xóa author theo ID OK
    @DeleteMapping("/author/{authorID}")
    public ResponseEntity<HttpStatus> deleteAuthor(@RequestParam("authorID") String authorID) {
        try {
            authorRepository.deleteById(authorID);
            return new ResponseEntity<>(HttpStatus.OK, HttpStatus.valueOf("Successfully deleted"));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // xóa author OK
    @DeleteMapping("/author")
    public ResponseEntity<HttpStatus> deleteAllAuthor() {
        try {
            authorRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // them author OK
    @RequestMapping(value = "/author", method = RequestMethod.POST)
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        try {
            Author _author = authorRepository.save(
                    new Author(author.getAuthorID(),
                            author.getAuthorName()));
            return new ResponseEntity<>(_author, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update author
    @PutMapping("/author/{authorID}")
    public ResponseEntity<Author> updateAuthor(@PathVariable("authorID") String authorID, @RequestBody Author author) {
        Optional<Author> categoryData = authorRepository.findById(authorID);
        if (categoryData.isPresent()) {
            Author _author = categoryData.get();
            _author.setAuthorID(author.getAuthorID());
            _author.setAuthorName(author.getAuthorName());
            return new ResponseEntity<>(authorRepository.save(_author), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
