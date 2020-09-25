package se.lexicon.mattias.library.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class BookDTO {

    private Integer bookId;

    @NotBlank(message = "You need to provide a title")
    private String title;

    @NotNull(message = "You need to provide a status if the book is available or not [ true or false ]")
    private boolean available;

    @NotNull(message = "You need to provide a status if the book is reserved or not [ true or false ]")
    private boolean reserved;

    @PositiveOrZero
    @NotNull(message = "You need to provide a zero or positive value")
    private int maxLoanDays;

    @PositiveOrZero
    @NotNull(message = "You need to provide a zero or positive value")
    private BigDecimal finePerDay;

    @NotNull(message = "You need to provide a description between 5 - 100 characters")
    @Size(min = 5, max = 100)
    private String description;

    /** Constructors **/

    public BookDTO() {
    }

    public BookDTO(String title, boolean available, boolean reserved, int maxLoanDays, BigDecimal finePerDay, String description) {
        this.title = title;
        this.available = available;
        this.reserved = reserved;
        this.maxLoanDays = maxLoanDays;
        this.finePerDay = finePerDay;
        this.description = description;
    }

    public BookDTO(Integer bookId, String title, boolean available, boolean reserved, int maxLoanDays, BigDecimal finePerDay, String description) {
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
}
