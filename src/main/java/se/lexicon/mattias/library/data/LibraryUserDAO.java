package se.lexicon.mattias.library.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.mattias.library.entity.LibraryUser;

import java.util.Optional;

public interface LibraryUserDAO extends JpaRepository<LibraryUser, Integer> {

    Optional<LibraryUser> findByEmail(String email);

}
