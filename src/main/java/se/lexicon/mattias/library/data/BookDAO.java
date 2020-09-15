package se.lexicon.mattias.library.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.mattias.library.entity.Book;

import java.util.List;

public interface BookDAO extends JpaRepository<Book, Integer> {

    List<Book> findByReserved(boolean status);
    List<Book> findByAvailable(boolean status);
    List<Book> findByTitleIgnoreCase(String title);

}
