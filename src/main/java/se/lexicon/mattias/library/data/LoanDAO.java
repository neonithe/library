package se.lexicon.mattias.library.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.mattias.library.entity.Loan;

public interface LoanDAO extends JpaRepository<Loan, Long> {



}
