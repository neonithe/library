package se.lexicon.mattias.library.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.mattias.library.entity.Book;
import se.lexicon.mattias.library.entity.LibraryUser;

import java.util.List;

public interface BookDAO extends JpaRepository<Book, Integer> {

    List<Book> findByReserved(boolean reserved);
    List<Book> findByAvailable(boolean available);
    List<Book> findByTitleIgnoreCase(String title);

}
