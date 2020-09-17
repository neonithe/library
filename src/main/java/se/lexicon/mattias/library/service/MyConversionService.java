package se.lexicon.mattias.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.mattias.library.data.BookDAO;
import se.lexicon.mattias.library.data.LibraryUserDAO;
import se.lexicon.mattias.library.data.LoanDAO;
import se.lexicon.mattias.library.dto.BookDTO;
import se.lexicon.mattias.library.dto.LibraryUserDTO;
import se.lexicon.mattias.library.entity.Book;
import se.lexicon.mattias.library.entity.LibraryUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyConversionService {

    BookDAO bookDAO;
    LibraryUserDAO userDAO;
    LoanDAO loanDAO;

    @Autowired
    public MyConversionService(BookDAO bookDAO, LibraryUserDAO userDAO, LoanDAO loanDAO) {
        this.bookDAO = bookDAO;
        this.userDAO = userDAO;
        this.loanDAO = loanDAO;
    }

    /** Conversions for LibraryUser | ID | REGDATE | NAME | EMAIL | DEPT **********************************************/

    public LibraryUser convertDtoToUser(LibraryUserDTO user) {

        LibraryUser libraryUser =
                new LibraryUser(user.getUserId(),user.getRegDate(),user.getName(),user.getEmail());

        return libraryUser;
    }

    public LibraryUserDTO convertUserToDto(LibraryUser user) {

        LibraryUserDTO dto =
                new LibraryUserDTO(user.getUserId(),user.getRegDate(),user.getName(),user.getEmail());

        return dto;
    }

    /** Optional to object LibraryUser ID **/

    public LibraryUser opToObjId(Integer id) {

       Optional<LibraryUser> userList = userDAO.findById(id);
       LibraryUser user = null;

        if ( userList.isPresent() ) {
                user = userList.get();
        }

        return user;
    }

    /** Optional to object LibraryUser EMAIL **/

    public LibraryUser opToObjEmail(String email) {

        Optional<LibraryUser> userList = userDAO.findByEmail(email);
        LibraryUser user = null;

        if ( userList.isPresent() ) {
            user = userList.get();
        }

        return user;
    }

    /** Conversions for Book | ID | TITLE | AVALIBLE | RESERVED | MAXLOANDAYS | FINEPERDAY | DESCRIPTION **************/

    public Book convertDtoToBook(BookDTO book) {

        Book newBook =
                new Book(book.getBookId(), book.getTitle(),book.isAvailable(),
                        book.isReserved(), book.getMaxLoanDays(), book.getFinePerDay(), book.getDescription());

        return newBook;
    }

    public BookDTO convertBookToDto(Book book) {

        BookDTO dto = new BookDTO(book.getBookId(), book.getTitle(),book.isAvailable(),
                book.isReserved(), book.getMaxLoanDays(), book.getFinePerDay(), book.getDescription());

        return dto;
    }

    /** Optional to object Book ID **/

    public Book opToObjBookId(Integer id) {

        Optional<Book> bookList = bookDAO.findById(id);
        Book book = null;

        if ( bookList.isPresent() ) {
            book = bookList.get();
        }

        return book;
    }

    /** Convert BOOK to DTO list **/

    public List<BookDTO> convertBookList(List<Book> bookList) {

        List<BookDTO> dtoList = new ArrayList<>();

        for ( Book book : bookList ){
            dtoList.add(convertBookToDto(book));
        }

        return dtoList;
    }



}