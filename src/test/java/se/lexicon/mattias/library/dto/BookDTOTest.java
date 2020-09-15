package se.lexicon.mattias.library.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.mattias.library.entity.Book;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BookDTOTest {

    Book bookTest;

    @BeforeEach
    void setUp() {
        //Fine Per day
        BigDecimal fine = new BigDecimal(10);
        // ID | TITLE | AVALIBLE | RESERVED | MAXLOANDAYS | FINEPERDAY | DESCRIPTION
        bookTest = new Book(1, "Title", true, false, 20, new BigDecimal(10), "Test");

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void successCreated() {

        BigDecimal testFine = new BigDecimal(10);

        assertNotNull(bookTest);
        assertTrue(bookTest.getBookId() == 1);
        assertEquals("Title", bookTest.getTitle());
        assertEquals(true, bookTest.isAvailable());
        assertEquals(false, bookTest.isReserved());
        assertEquals(20, bookTest.getMaxLoanDays());
        assertEquals(testFine, bookTest.getFinePerDay());
        assertEquals("Test", bookTest.getDescription());

    }

}