package se.lexicon.mattias.library.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.mattias.library.entity.Book;
import se.lexicon.mattias.library.entity.LibraryUser;
import se.lexicon.mattias.library.entity.Loan;

import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class LoanDAOTest {

    @Autowired
    LoanDAO testDAO;

    @BeforeEach
    void setUp() {

        // FINE
        BigDecimal fine = new BigDecimal(10);

        // USER
        LibraryUser user1 = new LibraryUser(1, LocalDate.parse("2020-01-01"), "Name1", "name@name1.com");
        LibraryUser user2 = new LibraryUser(2, LocalDate.parse("2020-01-01"), "Name2", "name@name2.com");
        // BOOKS
        Book book1 = new Book(1,"Title1", true, 		false, 	20, fine, "Good book 1");
        Book book2 = new Book(2,"Title2", false, 	false, 	20, fine, "Good book 2");
        Book book3 = new Book(3,"Title3", false, 	true, 	20, fine, "Good book 3");
        // CREATE LOANS
        testDAO.save(new Loan(1L, user1, book1, LocalDate.parse("2020-09-01"), false));
        testDAO.save(new Loan(2L, user1, book2, LocalDate.parse("2020-09-01"), false));
        testDAO.save(new Loan(3L, user2, book3, LocalDate.parse("2020-09-01"), true));

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByLoanTaker() {
        LibraryUser user1 = new LibraryUser(1, LocalDate.parse("2020-01-01"), "Name1", "name@name1.com");

        List<Loan> testList = testDAO.findByLoanTaker(user1);

        Integer expectedID = 1;
        Integer resultID = null;

        for ( Loan res : testList ) {
            if ( res.getLoanTaker().getUserId().equals(expectedID) ) {
                resultID = res.getLoanTaker().getUserId();
            }

        }
        assertEquals(expectedID, resultID);

    }

    @Test
    void findByBook() {
        BigDecimal fine = new BigDecimal(10);
        Book book1 = new Book(1,"Title1", true, 		false, 	20, fine, "Good book 1");

        List<Loan> testList = testDAO.findByBook(book1);

        Integer expectedID = 1;
        Integer resultID = null;

        for ( Loan res : testList ) {
            if ( res.getBook().getBookId().equals(expectedID) ) {
                resultID = res.getBook().getBookId();
            }

        }
        assertEquals(expectedID, resultID);

    }

    @Test
    void findByAvslutadNumbersOf() {

        List<Loan> testList = testDAO.findByAvslutad(false);

        Integer expectedID = 2;
        Integer resultSize = testList.size();

        assertEquals(expectedID, resultSize);

    }

    @Test
    void findByAvslutadLoanId() {

        List<Loan> testList = testDAO.findByAvslutad(true);

        Long expectedID = 3L;
        Long resultID = null;

        for ( Loan res : testList ) {
            if ( res.getLoanId().equals(expectedID) ) {
                resultID = res.getLoanId();
            }

        }
        assertEquals(expectedID, resultID);

    }
}