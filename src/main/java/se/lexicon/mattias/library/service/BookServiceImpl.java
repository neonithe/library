package se.lexicon.mattias.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.mattias.library.data.BookDAO;
import se.lexicon.mattias.library.dto.BookDTO;
import se.lexicon.mattias.library.entity.Book;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private BookDAO bookDAO;
    private MyConversionService myConversionService;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO, MyConversionService myConversionService) {
        this.bookDAO = bookDAO;
        this.myConversionService = myConversionService;
    }

    @Override
    public List<BookDTO> findByReserved(boolean reserved) {

        List<BookDTO> resultList = myConversionService.convertBookList( bookDAO.findByReserved(reserved) );

        return resultList;
    }

    @Override
    public List<BookDTO> findByAvailable(boolean available) {

        List<BookDTO> resultList = myConversionService.convertBookList( bookDAO.findByAvailable(available) );

        return resultList;
    }

    @Override
    public List<BookDTO> findByTitle(String title) {

        List<BookDTO> resultList = myConversionService.convertBookList( bookDAO.findByTitleIgnoreCase(title) );

        return resultList;
    }

    @Override
    public List<BookDTO> findAll() {

        List<BookDTO> resultList = myConversionService.convertBookList( bookDAO.findAll() );

        return resultList;
    }

    @Override
    public BookDTO findById(Integer id) {

        BookDTO dto = myConversionService.convertBookToDto(
                      myConversionService.opToObjBookId(id) );

        return dto;
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
