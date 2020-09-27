package se.lexicon.mattias.library.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.mattias.library.data.BookDAO;
import se.lexicon.mattias.library.dto.BookDTO;
import se.lexicon.mattias.library.entity.Book;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class BookServiceTest {

    @Autowired
    BookService bookService;

    @Autowired
    BookDAO dao;

    @Autowired
    MyConversionService conversionService;

    BigDecimal fine = new BigDecimal(10);

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByReserved() {

       List<BookDTO> result = bookService.findByReserved(true);

       assertEquals(2, result.size());

    }

    @Test
    void findByAvailable() {

        List<BookDTO> result = bookService.findByAvailable(true);

        assertEquals(2, result.size());

    }

    @Test
    void findByTitle() {

        String res ="";

        List<BookDTO> result = bookService.findByTitle("Title1");

        for ( BookDTO p : result ) {
            res = p.getTitle();
        }

        assertEquals(1, result.size());
        assertEquals("Title1", res);
    }

    @Test
    void findAll() {

        List<BookDTO> result = bookService.findAll();
        assertEquals(4, result.size());

    }

    @Test
    void findById() {

        BookDTO result = bookService.findById(1);

        assertEquals("Title1", result.getTitle());

    }

    @Test
    void create() {

        BookDTO bookDTO = conversionService.convertBookToDto( new Book("Title5", false, 	true, 	20, fine, "Good book 5"));

        bookService.create(bookDTO);

       assertEquals(5, bookService.findById(5).getBookId() );

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}