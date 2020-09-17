package se.lexicon.mattias.library.service;

import se.lexicon.mattias.library.dto.LoanDTO;

import java.util.List;

public interface LoanService {

    // FIND
    List<LoanDTO> findByBookId(Integer bookId);
    List<LoanDTO> findByUserId(Integer userId);
    List<LoanDTO> findByTerminated(boolean terminated);
    List<LoanDTO> findAll();
    LoanDTO findById(Long loanId);

    // CRUD
    LoanDTO create(LoanDTO dto);
    LoanDTO update(LoanDTO dto);
    boolean delete(Long loanId);

}
