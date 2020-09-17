package se.lexicon.mattias.library.dto;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LibraryUserDTO {

    private Integer userId;
    private LocalDate regDate;
    private String name;
    private String email;
    private BigDecimal dept;

    /** Constructors **/

    public LibraryUserDTO() {
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
