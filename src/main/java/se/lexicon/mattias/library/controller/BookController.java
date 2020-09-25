package se.lexicon.mattias.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.mattias.library.dto.BookDTO;
import se.lexicon.mattias.library.service.BookService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {

    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/findid/{bookId}")
    public ResponseEntity<BookDTO> findById(@PathVariable Integer bookId) {
        return ResponseEntity.ok(bookService.findById(bookId));
    }

    @GetMapping("/find")
    public ResponseEntity<Object> find(
            @RequestParam(value = "type", defaultValue = "all") String type,
            @RequestParam(value = "value", defaultValue = "all") String value,
            @RequestParam(value = "status", defaultValue = "all") String status)
    {

        switch ( type ) {
            case "bookid":
                return ResponseEntity.ok(bookService.findById(Integer.parseInt(value)));

            case "reserved":
                return ResponseEntity.ok(bookService.findByReserved(Boolean.parseBoolean(status)));

            case "available":
                return ResponseEntity.ok(bookService.findByAvailable(Boolean.parseBoolean(status)));

            case "title":
                return ResponseEntity.ok(bookService.findByTitle(value));

            case "all":
                return ResponseEntity.ok(bookService.findAll());

            default: throw new IllegalArgumentException("Not a Valid type: [ " + type +" ]" );
        }

    }

    @PostMapping("/add")
    public ResponseEntity<BookDTO> create(@Valid @RequestBody BookDTO bookDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(bookDTO));

    }

    @PutMapping("/update")
    public ResponseEntity<BookDTO> update(@Valid @RequestBody BookDTO bookDTO) {

        return ResponseEntity.ok(bookService.update(bookDTO));
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer bookId) {

        return ResponseEntity.ok(bookService.delete(bookId));
    }


}
