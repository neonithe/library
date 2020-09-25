package se.lexicon.mattias.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.mattias.library.data.BookDAO;
import se.lexicon.mattias.library.data.LibraryUserDAO;
import se.lexicon.mattias.library.data.LoanDAO;
import se.lexicon.mattias.library.dto.BookDTO;
import se.lexicon.mattias.library.dto.LibraryUserDTO;
import se.lexicon.mattias.library.dto.LoanDTO;
import se.lexicon.mattias.library.entity.Book;
import se.lexicon.mattias.library.entity.LibraryUser;
import se.lexicon.mattias.library.entity.Loan;
import se.lexicon.mattias.library.exceptions.ResourceNotFoundException;

import static se.lexicon.mattias.library.exceptions.ErrorMessage.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyConversionService {

    private BookDAO bookDAO;
    private LibraryUserDAO userDAO;
    private LoanDAO loanDAO;

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

    public LibraryUser opToObjUserId(Integer id) {

        LibraryUser user = userDAO.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(ERROR_NOT_FOUND_ID + id +" ]"));

       Optional<LibraryUser> userList = userDAO.findById(id);

        if ( userList.isPresent() ) {
                user = userList.get();
        }

        return user;
    }

    /** Optional to object LibraryUser EMAIL **/

    public LibraryUser opToObjEmail(String email) {

        LibraryUser user = userDAO.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException(ERROR_NOT_FOUND_ID + email +" ]"));

        Optional<LibraryUser> userList = userDAO.findByEmail(email);

        if ( userList.isPresent() ) {
            user = userList.get();
        }

        return user;
    }

    /** Convert USER to DTO list **/

    public List<LibraryUserDTO> convertUserList(List<LibraryUser> userList) {

        if ( userList.isEmpty() ) {
            throw new ResourceNotFoundException(LIST_EMPTY);
        }

        List<LibraryUserDTO> dtoList = new ArrayList<>();

        for ( LibraryUser user : userList ){
            dtoList.add(convertUserToDto(user));
        }

        return dtoList;
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

        Book book = bookDAO.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(ERROR_NOT_FOUND_ID + id +" ]"));

        Optional<Book> bookList = bookDAO.findById(id);

        if ( bookList.isPresent() ) {
            book = bookList.get();
        }

        return book;
    }

    /** Convert BOOK to DTO list **/

    public List<BookDTO> convertBookList(List<Book> bookList) {

        if ( bookList.isEmpty() ) {
            throw new ResourceNotFoundException(LIST_EMPTY);
        }

        List<BookDTO> dtoList = new ArrayList<>();

        for ( Book book : bookList ){
            dtoList.add(convertBookToDto(book));
        }

        return dtoList;
    }

    /** Conversions for Loan | ID | LOANTAKER | BOOK | LOANDATE | AVSLUTAD ********************************************/

    public Loan convertDtoToLoan(LoanDTO loan) {

        Loan newLoan =
                new Loan(loan.getLoanId(),opToObjUserId(loan.getUserId()),opToObjBookId(loan.getBookId()),loan.getLoanDate(),loan.isAvslutad());

        return newLoan;
    }

    public LoanDTO convertLoanToDto(Loan loan) {

        LoanDTO dto = new LoanDTO(loan.getLoanId(),loan.getLoanTaker().getUserId(),loan.getBook().getBookId(),loan.getLoanDate(),loan.isAvslutad());

        return dto;
    }

    /** Optional to object Loan ID **/

    public Loan opToObjLoanId(Long id) {

        Loan loan = loanDAO.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(ERROR_NOT_FOUND_ID + id +" ]"));

        Optional<Loan> loanList = loanDAO.findById(id);

        if ( loanList.isPresent() ) {
            loan = loanList.get();
        }

        return loan;
    }

    /** Convert LOAN to DTO list **/

    public List<LoanDTO> convertLoanList(List<Loan> loanList) {

        if ( loanList.isEmpty() ) {
            throw new ResourceNotFoundException(LIST_EMPTY);
        }

        List<LoanDTO> dtoList = new ArrayList<>();

        for ( Loan loan : loanList ){
            dtoList.add(convertLoanToDto(loan));
        }

        return dtoList;
    }


}
