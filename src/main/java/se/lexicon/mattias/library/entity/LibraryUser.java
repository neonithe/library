package se.lexicon.mattias.library.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class LibraryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private LocalDate regDate;
    private String name;

    @Column(unique = true,name = "email")
    private String email;
    private BigDecimal dept;

    /** Constructors **/
    public LibraryUser() {
    }

    public LibraryUser(LocalDate regDate, String name, String email) {
        this.regDate = regDate;
        this.name = name;
        this.email = email;
        this.dept = new BigDecimal(0);
    }

    public LibraryUser(Integer userId, LocalDate regDate, String name, String email) {
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

    /** Hash & Equals **/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryUser that = (LibraryUser) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(regDate, that.regDate) &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, regDate, name, email);
    }

    /** To String **/

    @Override
    public String toString() {
        return "User: "+userId+" | Name: "+name+" | Email: "+email+" | Reg: "+regDate+" | Dept: "+dept;
    }
}
