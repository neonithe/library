package se.lexicon.mattias.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.lexicon.mattias.library.data.BookDAO;
import se.lexicon.mattias.library.data.LibraryUserDAO;
import se.lexicon.mattias.library.data.LoanDAO;
import se.lexicon.mattias.library.entity.Book;
import se.lexicon.mattias.library.entity.LibraryUser;
import se.lexicon.mattias.library.entity.Loan;
import se.lexicon.mattias.library.service.LibraryUserService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

	Book newBook;
	LibraryUser user;
	Loan loan;

	@Autowired
	LibraryUserDAO userDAO;

	@Autowired
	BookDAO bookDAO;

	@Autowired
	LoanDAO loanDAO;

	LibraryUserService userService;


	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {

		//Fine Per day
		BigDecimal fine = new BigDecimal(10);

		// ID | REGDATE | NAME | EMAIL | DEPT
		LibraryUser user1 = new LibraryUser(1, LocalDate.parse("2020-01-01"), "Name1", "name@name1.com");
		LibraryUser user2 = new LibraryUser(2, LocalDate.parse("2020-01-01"), "Name2", "name@name2.com");

		// ID | TITLE | AVALIBLE | RESERVED | MAXLOANDAYS | FINEPERDAY | DESCRIPTION
		Book book1 = new Book(1,"Title1", true, 		false, 	20, fine, "Good book 1");
		Book book2 = new Book(2,"Title2", false, 	false, 	20, fine, "Good book 2");
		Book book3 = new Book(3,"Title3", false, 	true, 	20, fine, "Good book 3");

		// ID | LOANTAKER | BOOK | LOANDATE | AVSLUTAD
		Loan loan1 = new Loan(1L, user1, book1, LocalDate.parse("2020-01-01"), false);

		userDAO.save(new LibraryUser(2, LocalDate.parse("2020-01-01"), "Name2", "name@name2.com"));

		bookDAO.save(new Book(1,"Title1", true, 		false, 	20, fine, "Good book 1"));
		bookDAO.save(new Book(2,"Title2", false, 	true, 	20, fine, "Good book 2"));

		loanDAO.save(new Loan(1L, user1, book1, LocalDate.parse("2020-09-01"), false));
		loanDAO.save(new Loan(2L, user1, book2, LocalDate.parse("2020-09-01"), false));
		loanDAO.save(new Loan(3L, user2, book3, LocalDate.parse("2020-09-01"), false));
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
	    List<Loan> loanList2 = loanDAO.findByAvslutad(false);

	//	loanList0.forEach(System.out::println);
	//	loanList1.forEach(System.out::println);
		loanList2.forEach(System.out::println);

		System.out.println(loanList2.size());


	}

	public LibraryUser user() {
		return new LibraryUser(1, LocalDate.parse("2020-01-01"), "Name1", "name@name.com");
	}

	public Book book() {
		BigDecimal fine = new BigDecimal(10);
		return new Book(1,"Title1", true, 		false, 	10, fine, "Good book 1");
	}

	public Loan loan(){
		return new Loan(1L, user(), book(), LocalDate.parse("2020-09-01"), false);
	}


}
