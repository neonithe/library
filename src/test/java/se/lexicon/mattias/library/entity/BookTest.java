package se.lexicon.mattias.library.entity;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
class BookTest {

    Book bookTest;

    @BeforeEach
    void setUp() {

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

    @Test
    void testEquals() {

        Book copy = new Book(1, "Title", true, false, 20, new BigDecimal(10), "Test");

        assertTrue(bookTest.equals(copy));

    }

    @Test
    void testHashCode() {

        Book copy = new Book(1, "Title", true, false, 20, new BigDecimal(10), "Test");

        assertEquals(copy.hashCode(), bookTest.hashCode());

    }

    @Test
    void testToString() {

        String toString = bookTest.toString();

        assertTrue(toString.contains(Integer.toString(bookTest.getBookId())));
        assertTrue(toString.contains(bookTest.getTitle()));
   //     assertTrue(toString.contains(bookTest.isAvailable()));
     //   assertTrue(toString.contains(bookTest.isReserved()));
       // assertTrue(toString.contains(bookTest.getMaxLoanDays()));


    }
}