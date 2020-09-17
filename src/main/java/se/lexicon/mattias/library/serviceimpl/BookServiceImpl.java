package se.lexicon.mattias.library.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.mattias.library.data.BookDAO;
import se.lexicon.mattias.library.dto.BookDTO;
import se.lexicon.mattias.library.entity.Book;
import se.lexicon.mattias.library.service.BookService;
import se.lexicon.mattias.library.service.MyConversionService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;
    private MyConversionService myConversionService;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO, MyConversionService myConversionService) {
        this.bookDAO = bookDAO;
        this.myConversionService = myConversionService;
    }

    @Override
    public List<BookDTO> findByReserved(boolean reserved) {

        return myConversionService.convertBookList( bookDAO.findByReserved(reserved) );
    }

    @Override
    public List<BookDTO> findByAvailable(boolean available) {

        return myConversionService.convertBookList( bookDAO.findByAvailable(available) );
    }

    @Override
    public List<BookDTO> findByTitle(String title) {

        return myConversionService.convertBookList( bookDAO.findByTitleIgnoreCase(title) );
    }

    @Override
    public List<BookDTO> findAll() {

        return myConversionService.convertBookList( bookDAO.findAll() );
    }

    @Override
    public BookDTO findById(Integer id) {

        return myConversionService.convertBookToDto( myConversionService.opToObjBookId(id) );
    }

    @Override
    public BookDTO create(BookDTO book) {

        Book newBook =
                new Book(book.getBookId(), book.getTitle(),book.isAvailable(),
                    book.isReserved(), book.getMaxLoanDays(), book.getFinePerDay(), book.getDescription());

        bookDAO.save(newBook);

        return myConversionService.convertBookToDto(newBook);
    }

    @Override
    public BookDTO update(BookDTO book) {

        BookDTO updateBook = findById(book.getBookId());

        updateBook.setTitle(book.getTitle());
        updateBook.setAvailable(book.isAvailable());
        updateBook.setReserved(book.isReserved());
        updateBook.setMaxLoanDays(book.getMaxLoanDays());
        updateBook.setFinePerDay(book.getFinePerDay());
        updateBook.setDescription(book.getDescription());

        bookDAO.save(myConversionService.convertDtoToBook(updateBook));

        return updateBook;
    }

    @Override
    public boolean delete(Integer bookId) {

        if ( bookDAO.existsById(bookId) ) {
            Book book = myConversionService.opToObjBookId(bookId);
            bookDAO.delete(book);
            return true;
        } else {
            return false;
        }
    }
}
