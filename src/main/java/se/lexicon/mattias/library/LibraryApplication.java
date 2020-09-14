package se.lexicon.mattias.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.lexicon.mattias.library.entity.Book;
import se.lexicon.mattias.library.entity.LibraryUser;
import se.lexicon.mattias.library.entity.Loan;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

	Book newBook;
	LibraryUser user;
	Loan loan;


	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {

		//Fine Per day
		BigDecimal fine = new BigDecimal(10);

		// ID | REGDATE | NAME | EMAIL | DEPT
		LibraryUser user1 = new LibraryUser(1, LocalDate.parse("2020-01-01"), "Name1", "name@name.com");
		LibraryUser user2 = new LibraryUser(2, LocalDate.parse("2020-01-01"), "Name2", "name@name.com");

		// ID | TITLE | AVALIBLE | RESERVED | MAXLOANDAYS | FINEPERDAY | DESCRIPTION
		Book book1 = new Book(1,"Title1", true, 		false, 	20, fine, "Good book 1");
		Book book2 = new Book(2,"Title2", false, 	false, 	20, fine, "Good book 2");
		Book book3 = new Book(3,"Title3", false, 	true, 	20, fine, "Good book 3");

		// ID | LOANTAKER | BOOK | LOANDATE | AVSLUTAD
		Loan loan1 = new Loan(1L, user1, book1, LocalDate.parse("2020-01-01"), false);

		BigDecimal fine2 = new BigDecimal(10);
		int daysOverDue = -7;
		BigDecimal amount = new BigDecimal(0); //fine2.multiply(new BigDecimal(daysOverDue));

		System.out.println(loan().getFine());
		System.out.println(loan().isOverdue());
		System.out.println(loan().extendLoan(book().getMaxLoanDays()));

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
