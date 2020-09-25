package se.lexicon.mattias.library.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LibraryUserDTO {

    private Integer userId;

    @NotNull(message = "You need to provide a date when user is registered")
    private LocalDate regDate;

    @NotBlank(message = "You need to input name between 2 and 45 characters")
    @Size(min = 2, max = 45)
    private String name;

    @NotBlank
    @Email(message = "Email should be a valid email")
    private String email;

    private BigDecimal dept;

    /** Constructors **/

    public LibraryUserDTO() {
    }

    public LibraryUserDTO(LocalDate regDate, String name, String email, BigDecimal dept) {
        this.regDate = regDate;
        this.name = name;
        this.email = email;
        this.dept = dept;
    }

    public LibraryUserDTO(Integer userId, LocalDate regDate, String name, String email) {
        this.userId = userId;
        this.regDate = regDate;
        this.name = name;
        this.email = email;
        this.dept = new BigDecimal(0);
    }

    /** Getters and setters **/

    public Integer getUserId() {
        return userId;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getDept() {
        return dept;
    }

    public void setDept(BigDecimal dept) {
        this.dept = dept;
    }
}
