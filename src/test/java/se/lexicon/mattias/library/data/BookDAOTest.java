package se.lexicon.mattias.library.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.mattias.library.entity.Book;
import se.lexicon.mattias.library.entity.LibraryUser;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class BookDAOTest {

    @Autowired
    BookDAO testDAO;

    @BeforeEach
    void setUp() {

        BigDecimal fine = new BigDecimal(10);

        testDAO.save(new Book(1,"Title1", true, 		false, 	20, fine, "Good book 1"));
        testDAO.save(new Book(2,"Title2", false, 	true, 	20, fine, "Good book 2"));
     //   testDAO.save(new Book(3,"Title2", true, 	true, 	20, fine, "Good book 3"));

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByReserved() {

        List<Book> testList = testDAO.findByReserved(true);

        Integer expectedID = 2;
        Integer resultID = null;

        for ( Book res : testList ) {
            if ( res.isReserved() ) {
                resultID = res.getBookId();
            }

        }
            assertEquals(expectedID, resultID);

    }

    @Test
    void findByAvailable() {

        List<Book> testList = testDAO.findByAvailable(true);

        Integer expectedID = 1;
        Integer resultID = null;

        for ( Book res : testList ) {
            if ( res.isAvailable() ) {
                resultID = res.getBookId();
            }

        }
        assertEquals(expectedID, resultID);
    }

    @Test
    void findByTitle() {

        String expectedTitle = "title1";
        Integer expectedID = 1;
        Integer resultID = null;

        List<Book> testList = testDAO.findByTitleIgnoreCase(expectedTitle);

        for ( Book res : testList ) {
            if ( res.getTitle().equalsIgnoreCase(expectedTitle) ) {
                resultID = res.getBookId();

            }

            assertEquals(expectedID, resultID);

        }

    }
}