package se.lexicon.mattias.library.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.mattias.library.entity.LibraryUser;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LibraryUserDTOTest {

    LibraryUser userTest;

    @BeforeEach
    void setUp() {

        userTest = new LibraryUser(1, LocalDate.parse("2020-01-01"), "Name1", "test@test1.com");

    }

    @AfterEach
    void tearDown() {
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
}