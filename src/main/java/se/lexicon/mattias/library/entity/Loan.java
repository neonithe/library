package se.lexicon.mattias.library.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LibraryUser loanTaker;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Book book;

    private LocalDate loanDate;

    private boolean avslutad;

    /** Local other variables **/
    LocalDate today = LocalDate.now();

    /** Constructors **/

    public Loan() {
    }

  public Loan(LibraryUser loanTaker, Book book, LocalDate loanDate, boolean terminated) {
    this.loanTaker = loanTaker;
    this.book = book;
    this.loanDate = loanDate;
    this.avslutad = terminated;
  }

  public Loan(Long loanId, LibraryUser loanTaker, Book book, LocalDate loanDate, boolean terminated) {
    this.loanId = loanId;
    this.loanTaker = loanTaker;
    this.book = book;
    this.loanDate = loanDate;
    this.avslutad = terminated;
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

  /** Equals and hashcode **/

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Loan loan = (Loan) o;
    return avslutad == loan.avslutad &&
            Objects.equals(loanId, loan.loanId) &&
            Objects.equals(loanTaker, loan.loanTaker) &&
            Objects.equals(book, loan.book) &&
            Objects.equals(loanDate, loan.loanDate) &&
            Objects.equals(today, loan.today);
  }

  @Override
  public int hashCode() {
    return Objects.hash(loanId, loanTaker, book, loanDate, avslutad, today);
  }

  /** To String **/

  @Override
  public String toString() {
    return "Loan ID: "+loanId+" | LoneDate: "+loanDate+" | Terminated: "+avslutad+
            "\n | Lonetaker: "+loanTaker+
            "\n | Book: "+book;
  }

  /** Methods **/

  public int days() {
    LocalDate loanDate = getLoanDate();
    return today.compareTo(loanDate);
  }

  public BigDecimal getFine() {
    BigDecimal amount = new BigDecimal(0);

    BigDecimal fine = book.getFinePerDay();
    int daysOverDue = book.getMaxLoanDays() - days();

        if ( daysOverDue <= 0 ) {
            amount = fine.multiply(new BigDecimal(daysOverDue));
              return amount;
        } else {
              return amount;
      }
  }

  public boolean isOverdue() {

    if ( days() > book.getMaxLoanDays() ) {
      return true;
    } else {
      return false;
    }
  }

  public boolean extendLoan(int days) {

        if (isOverdue() == false) {

            setLoanDate(today);
            today.plusDays(days);

          return true;

       } else {

          return false;
    }
  }

}
