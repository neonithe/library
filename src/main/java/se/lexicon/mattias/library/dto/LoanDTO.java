package se.lexicon.mattias.library.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

public class LoanDTO {

    private Long loanId;

    @NotBlank(message = "You need to provide a user to the loan")
    private Integer userId;

    @NotBlank(message = "You need to provide a book to the loan")
    private Integer bookId;

    @NotBlank(message = "You need to provide a loan date")
    private LocalDate loanDate;

    @NotNull
    @NotBlank(message = "You need to provide a status if loan is terminated or not [ true or false ]")
    private boolean avslutad;

    /** Constructors **/

    public LoanDTO() {
    }

    public LoanDTO(Integer userId, Integer bookId, LocalDate loanDate, boolean avslutad) {
        this.userId = userId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.avslutad = avslutad;
    }

    public LoanDTO(Long loanId, Integer userId, Integer bookId, LocalDate loanDate, boolean avslutad) {
        this.loanId = loanId;
        this.userId = userId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.avslutad = avslutad;
    }

    /** Getters and setters **/

    public Long getLoanId() {
        return loanId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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
