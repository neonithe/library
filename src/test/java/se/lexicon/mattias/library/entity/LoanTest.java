package se.lexicon.mattias.library.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
class LoanTest {

    Book testBook;
    LibraryUser testUser;
    Loan testLoan;


    @BeforeEach
    void setUp() {

        // ID | REGDATE | NAME | EMAIL | DEPT
        testUser =
                new LibraryUser(1, LocalDate.parse("2020-01-01"),"Name1","mail@mail.com");
        // ID | TITLE | AVALIBLE | RESERVED | MAXLOANDAYS | FINEPERDAY | DESCRIPTION
        testBook =
                new Book(1,"Title",true,false,20,
                        new BigDecimal(10),"Desc");

        // ID | LOANTAKER | BOOK | LOANDATE | AVSLUTAD
        testLoan =
                new Loan(1L, testUser,testBook,LocalDate.parse("2020-01-01"),false);

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
        assertEquals(1,testLoan.getBook().getBookId());
        assertEquals(testDate, testLoan.getLoanDate());
        assertEquals(false, testLoan.isAvslutad());

    }

    @Test
    void testDays() {
        //Setup
        LocalDate todaysDate = LocalDate.now();
        testLoan.setLoanDate(todaysDate.plusDays(1)); //2020-09-14 -> 2020-09-15

        //Test
        int testDays = testLoan.days(); // -1
        assertEquals(-1, testDays);

    }

    @Test
    void testGetFine() { // Test so the value is 0
        //Setup
        LocalDate todaysDate = LocalDate.now();
        testLoan.setLoanDate(todaysDate.minusDays(10)); // 2020-09-14 -> 2020-09-04
        testBook.setMaxLoanDays(5); // 2020-09-04 End Date 2020-09-09

        // Get fine and set result fine
        BigDecimal testFine = testLoan.getFine();
        BigDecimal testFineResult = new BigDecimal(-50);

        assertEquals(testFineResult, testFine);

    }

    @Test
    void testOverDue() { // Test so the overdue is false
        // Setup
        LocalDate todaysDate = LocalDate.now();
        testLoan.setLoanDate(todaysDate.minusDays(10)); // 2020-09-14 -> 2020-09-04
        testBook.setMaxLoanDays(5); // 2020-09-04 End Date 2020-09-09

        // The book is overdue
        assertEquals(true, testLoan.isOverdue());

    }

    @Test
    void testExtendLoan() { // Test so the extend is not avalible
        // Setup
        LocalDate toDay = LocalDate.now();
        int days = testBook.getMaxLoanDays();
        // Set loandate so the overdue is not conflict
        testLoan.setLoanDate(toDay.minusDays(10));

        // Result should be todays date + 10 2020-09-24
        assertEquals(true, testLoan.extendLoan(days));


    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}