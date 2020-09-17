package se.lexicon.mattias.library.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import se.lexicon.mattias.library.data.BookDAO;
import se.lexicon.mattias.library.data.LibraryUserDAO;
import se.lexicon.mattias.library.data.LoanDAO;
import se.lexicon.mattias.library.dto.LoanDTO;
import se.lexicon.mattias.library.entity.Book;
import se.lexicon.mattias.library.entity.LibraryUser;
import se.lexicon.mattias.library.entity.Loan;
import se.lexicon.mattias.library.service.LoanService;
import se.lexicon.mattias.library.service.MyConversionService;

import java.util.List;

public class LoanServiceImpl implements LoanService {

    LibraryUserDAO userDAO;
    BookDAO bookDAO;
    LoanDAO loanDAO;
    MyConversionService myConversionService;

    @Autowired
    public LoanServiceImpl(LibraryUserDAO userDAO, BookDAO bookDAO, LoanDAO loanDAO, MyConversionService myConversionService) {
        this.userDAO = userDAO;
        this.bookDAO = bookDAO;
        this.loanDAO = loanDAO;
        this.myConversionService = myConversionService;
    }

    @Override
    public List<LoanDTO> findByBookId(Integer bookId) {
        Book book = myConversionService.opToObjBookId(bookId);

        List<LoanDTO> resultList = myConversionService.convertLoanList(loanDAO.findByBook(book));

        return resultList;
    }

    @Override
    public List<LoanDTO> findByUserId(Integer userId) {
        LibraryUser user = myConversionService.opToObjId(userId);

        List<LoanDTO> resultList = myConversionService.convertLoanList(loanDAO.findByLoanTaker(user));

        return resultList;
    }

    @Override
    public List<LoanDTO> findByTerminated(boolean terminated) {

        return myConversionService.convertLoanList(loanDAO.findByAvslutad(terminated));
    }

    @Override
    public List<LoanDTO> findAll() {

        return myConversionService.convertLoanList(loanDAO.findAll());
    }

    @Override
    public LoanDTO findById(Long loanId) {
        return myConversionService.convertLoanToDto( myConversionService.opToObjLoanId(loanId) );
    }

    @Override
    public LoanDTO create(LoanDTO dto) {
        Loan newLoan =
                new Loan(dto.getLoanId(),dto.getLoanTaker(),dto.getBook(),dto.getLoanDate(),dto.isAvslutad());

        loanDAO.save(newLoan);

        return myConversionService.convertLoanToDto(newLoan);
    }

    @Override
    public LoanDTO update(LoanDTO dto) {

        LoanDTO updateLoan = findById(dto.getLoanId());

        updateLoan.setLoanTaker(dto.getLoanTaker());
        updateLoan.setBook(dto.getBook());
        updateLoan.setLoanDate(dto.getLoanDate());
        updateLoan.setAvslutad(dto.isAvslutad());

        loanDAO.save(myConversionService.convertDtoToLoan(updateLoan));

        return updateLoan;
    }

    @Override
    public boolean delete(Long loanId) {

        if ( loanDAO.existsById(loanId) ) {
            Loan loan = myConversionService.opToObjLoanId(loanId);
            loanDAO.delete(loan);
            return true;
        } else {
            return false;
        }
    }
}
