package se.lexicon.mattias.library.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.mattias.library.entity.Book;
import se.lexicon.mattias.library.entity.LibraryUser;
import se.lexicon.mattias.library.entity.Loan;

import java.util.List;

public interface LoanDAO extends JpaRepository<Loan, Long> {

    List<Loan> findByLoanTaker(LibraryUser user);
    List<Loan> findByBook(Book book);
    List<Loan> findByAvslutad(boolean status);

}
