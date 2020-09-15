package se.lexicon.mattias.library.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.mattias.library.entity.LibraryUser;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class LibraryUserDAOTest {

    @Autowired
    LibraryUserDAO testDAO;


    @BeforeEach
    void setUp() {

       testDAO.save(new LibraryUser(1, LocalDate.parse("2021-01-01"), "TestName1", "supertest@test1.com"));
       testDAO.save(new LibraryUser(2, LocalDate.parse("2021-01-01"), "TestName2", "supertest@test2.com"));
       testDAO.save(new LibraryUser(3, LocalDate.parse("2021-01-01"), "TestName3", "supertest@test3.com"));
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void findByEmail() {

        String expected = "supertest@test1.com";
        Optional<LibraryUser> testList = testDAO.findByEmail("supertest@test1.com");
        LibraryUser testUser = null;

        if(testList.isPresent()) {
            testUser = testList.get();
        }
        String result = testUser.getEmail();


        assertEquals(expected,result);

    }

    @Test
    void findByEmailNotFound() {

        Optional<LibraryUser> testList = testDAO.findByEmail("supertest@test4.com");

    }
}