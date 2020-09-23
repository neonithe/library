package se.lexicon.mattias.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.lexicon.mattias.library.data.BookDAO;
import se.lexicon.mattias.library.data.LibraryUserDAO;
import se.lexicon.mattias.library.data.LoanDAO;
import se.lexicon.mattias.library.dto.LoanDTO;
import se.lexicon.mattias.library.entity.Book;
import se.lexicon.mattias.library.entity.LibraryUser;
import se.lexicon.mattias.library.entity.Loan;
import se.lexicon.mattias.library.service.LibraryUserService;
import se.lexicon.mattias.library.service.MyConversionService;
import se.lexicon.mattias.library.serviceimpl.LoanServiceImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

	@Autowired
	LibraryUserDAO userDAO;

	@Autowired
	BookDAO bookDAO;

	@Autowired
	LoanDAO loanDAO;

	@Autowired
	MyConversionService conversionService;

	@Autowired
	LoanServiceImpl loanService;


	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Fine Per day
		BigDecimal fine = new BigDecimal(10);

		// USER ID | REGDATE | NAME | EMAIL | DEPT
		// BOOK ID | TITLE | AVALIBLE | RESERVED | MAXLOANDAYS | FINEPERDAY | DESCRIPTION
		// LOAN ID | LOANTAKER | BOOK | LOANDATE | AVSLUTAD

		userDAO.save(new LibraryUser(LocalDate.parse("2020-01-01"), "Martin", "martin@mail.com"));
		userDAO.save(new LibraryUser(LocalDate.parse("2020-01-01"), "Mattias", "mattias@mail.com"));
		userDAO.save(new LibraryUser(LocalDate.parse("2020-01-01"), "Sofia", "sofia@mail.com"));
		userDAO.save(new LibraryUser(LocalDate.parse("2020-01-01"), "Hanna", "hanna@mail.com"));

		bookDAO.save(new Book("Title1", true, 	false, 	20, fine, "Good book 1"));
		bookDAO.save(new Book("Title2", false, 	true, 	20, fine, "Good book 2"));
		bookDAO.save(new Book("Title3", true, 	false, 	20, fine, "Good book 3"));
		bookDAO.save(new Book("Title4", false, 	true, 	20, fine, "Good book 4"));

		// Create loan
		System.out.println("------------------------------------------------------------------------------------------");
		create(new Loan(conversionService.opToObjUserId(1), conversionService.opToObjBookId(1),LocalDate.parse("2020-01-10"), false ));
		create(new Loan(conversionService.opToObjUserId(1), conversionService.opToObjBookId(2),LocalDate.parse("2020-01-20"), true ));
		create(new Loan(conversionService.opToObjUserId(2), conversionService.opToObjBookId(3),LocalDate.parse("2020-01-30"), false ));
		System.out.println("------------------------------------------------------------------------------------------");

		LibraryUser user = conversionService.opToObjUserId(1);
		Book book = conversionService.opToObjBookId(1);

		//loanDAO.save(new Loan(user, book, LocalDate.parse("2020-01-01"), false));

	}

	public Loan create(Loan loan) {

		Book book = loan.getBook();
		LibraryUser user = loan.getLoanTaker();

		Loan newLoan =
				new Loan(null, null, loan.getLoanDate(),loan.isAvslutad());
		newLoan = loanDAO.save(newLoan);
		newLoan.setLoanTaker(user);
		newLoan.setBook(book);
		loanDAO.save(newLoan);

		return newLoan;
	}

	/**
	 List<Book> bookList0 = bookDAO.findByAvailable(false);
	 List<Book> bookList1 = bookDAO.findByReserved(false);
	 List<Book> bookList2 = bookDAO.findByTitleIgnoreCase("title1");

	 bookList0.forEach(System.out::println);
	 bookList1.forEach(System.out::println);
	 bookList2.forEach(System.out::println);

	 /*************************************/

	//	List<Loan> loanList0 = loanDAO.findByLoanTaker(user1);
	//	List<Loan> loanList1 = loanDAO.findByBook(book2);
	//    List<Loan> loanList2 = loanDAO.findByAvslutad(false);

	//	loanList0.forEach(System.out::println);
	//	loanList1.forEach(System.out::println);
	//	loanList2.forEach(System.out::println);

	//	System.out.println(loanList2.size());

}
