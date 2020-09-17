package se.lexicon.mattias.library.service;

import se.lexicon.mattias.library.dto.BookDTO;

import java.util.List;

public interface BookService {

    // FIND
    List<BookDTO> findByReserved(boolean reserved);
    List<BookDTO> findByAvailable(boolean available);
    List<BookDTO> findByTitle(String title);
    List<BookDTO> findAll();
    BookDTO findById(Integer id);

    // CRUD
    BookDTO create(BookDTO book);
    BookDTO update(BookDTO book);
    boolean delete(Integer bookId);

}
