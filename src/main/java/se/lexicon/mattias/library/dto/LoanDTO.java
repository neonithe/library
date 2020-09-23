package se.lexicon.mattias.library.dto;

import se.lexicon.mattias.library.entity.Book;
import se.lexicon.mattias.library.entity.LibraryUser;

import java.time.LocalDate;

public class LoanDTO {

    private Long loanId;
    private LibraryUser loanTaker;
    private Book book;
    private LocalDate loanDate;
    private boolean avslutad;

    /** Constructors **/

    public LoanDTO() {
    }

    public LoanDTO(LibraryUser loanTaker, Book book, LocalDate loanDate, boolean avslutad) {
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDate = loanDate;
        this.avslutad = avslutad;
    }

    public LoanDTO(Long loanId, LibraryUser loanTaker, Book book, LocalDate loanDate, boolean avslutad) {
        this.loanId = loanId;
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDate = loanDate;
        this.avslutad = avslutad;
    }

    /** Getters and setters **/

    public Long getLoanId() {
        return loanId;
    }

    public LibraryUser getLoanTaker() {
        return loanTaker;
    }

    public void setLoanTaker(LibraryUser loanTaker) {
        this.loanTaker = loanTaker;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public boolean isAvslutad() {
        return avslutad;
    }

    public void setAvslutad(boolean avslutad) {
        this.avslutad = avslutad;
    }
}
