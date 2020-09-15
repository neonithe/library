package se.lexicon.mattias.library.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@Transactional
public class LibraryUserTest {

    LibraryUser userTest;

    @BeforeEach
    void setUp() {

        userTest =
                new LibraryUser(1, LocalDate.parse("2020-01-01"), "Name1", "test@test1.com");
    }

    @AfterEach
    public void dismantle() {
        
    }

    @Test
    public void successCreated() {

        LocalDate testDate = userTest.getRegDate();
        BigDecimal setFine = new BigDecimal(100);
        userTest.setDept(new BigDecimal(100));

        assertNotNull(userTest);
        assertTrue(userTest.getUserId() == 1);
        assertEquals("Name1", userTest.getName());
        assertEquals(testDate, userTest.getRegDate());
        assertEquals("test@test1.com", userTest.getEmail());
        assertEquals(setFine, userTest.getDept());

    }

    @Test
    void testEquals() {

        LibraryUser copy = new LibraryUser(1, LocalDate.parse("2020-01-01"), "Name1", "test@test1.com");

        assertTrue(userTest.equals(copy));

    }

    @Test
    void testHashCode() {

        LibraryUser copy = new LibraryUser(1, LocalDate.parse("2020-01-01"), "Name1", "test@test1.com");

        assertEquals(copy.hashCode(), userTest.hashCode());

    }

    @Test
    void testToString() {

        String toString = userTest.toString();

        assertTrue(toString.contains(Integer.toString(userTest.getUserId())));
        assertTrue(toString.contains(userTest.getName()));
        assertTrue(toString.contains(userTest.getEmail()));
        assertTrue(toString.contains(userTest.getRegDate().toString()));

    }
}
