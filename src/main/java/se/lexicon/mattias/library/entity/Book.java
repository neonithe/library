package se.lexicon.mattias.library.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    private String title;
    private boolean available;
    private boolean reserved;
    private int maxLoanDays;
    private BigDecimal finePerDay;
    private String description;

    /** Constructors **/

    public Book() {
    }

    public Book(String title, boolean available, boolean reserved, int maxLoanDays, BigDecimal finePerDay, String description) {
        this.title = title;
        this.available = available;
        this.reserved = reserved;
        this.maxLoanDays = maxLoanDays;
        this.finePerDay = finePerDay;
        this.description = description;
    }

    public Book(Integer bookId, String title, boolean available, boolean reserved, int maxLoanDays, BigDecimal finePerDay, String description) {
        this.bookId = bookId;
        this.title = title;
        this.available = available;
        this.reserved = reserved;
        this.maxLoanDays = maxLoanDays;
        this.finePerDay = finePerDay;
        this.description = description;
    }

    /** Getters and setters **/

    public Integer getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public int getMaxLoanDays() {
        return maxLoanDays;
    }

    public void setMaxLoanDays(int maxLoanDays) {
        this.maxLoanDays = maxLoanDays;
    }

    public BigDecimal getFinePerDay() {
        return finePerDay;
    }

    public void setFinePerDay(BigDecimal finePerDay) {
        this.finePerDay = finePerDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /** Hash & Equals **/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return available == book.available &&
                reserved == book.reserved &&
                maxLoanDays == book.maxLoanDays &&
                Objects.equals(bookId, book.bookId) &&
                Objects.equals(title, book.title) &&
                Objects.equals(finePerDay, book.finePerDay) &&
                Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, available, reserved, maxLoanDays, finePerDay, description);
    }

    /** To String **/

    @Override
    public String toString() {
        return "ID: "+bookId+" | Title: "+title+" | Available: "+available+" | Reserved: "+reserved+
                " | Loan days: "+maxLoanDays+" | Fine: "+finePerDay+" | Desc:"+description;
    }

}
