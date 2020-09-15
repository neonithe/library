package se.lexicon.mattias.library.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.mattias.library.entity.Book;
import se.lexicon.mattias.library.entity.LibraryUser;
import se.lexicon.mattias.library.entity.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanDTOTest {

    Book testBook;
    LibraryUser testUser;
    Loan testLoan;

    @BeforeEach
    void setUp() {

        // ID | REGDATE | NAME | EMAIL | DEPT
        testUser = new LibraryUser(1, LocalDate.parse("2020-01-01"),"Name1","mail@mail.com");
        // ID | TITLE | AVALIBLE | RESERVED | MAXLOANDAYS | FINEPERDAY | DESCRIPTION
        testBook = new Book(1,"Title",true,false,20, new BigDecimal(10),"Desc");
        // ID | LOANTAKER | BOOK | LOANDATE | AVSLUTAD
        testLoan = new Loan(1L, testUser,testBook,LocalDate.parse("2020-01-01"),false);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void successCreated() {

        LocalDate testDate = LocalDate.parse("2020-01-01");

        assertNotNull(testLoan);
        assertTrue(testLoan.getLoanId() == 1L);
        assertEquals(1, testLoan.getLoanTaker().getUserId());
        assertEquals(1, testLoan.getBook().getBookId());
        assertEquals(testDate, testLoan.getLoanDate());
        assertEquals(false, testLoan.isAvslutad());
    }
}