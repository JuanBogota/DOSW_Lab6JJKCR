package edu.eci.dosw.tdd.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.eci.dosw.tdd.library.book.Book;
import edu.eci.dosw.tdd.library.loan.Loan;
import edu.eci.dosw.tdd.library.loan.LoanStatus;
import edu.eci.dosw.tdd.library.user.User;

public class LibraryTest {


// ---- ADDBOOK TESTS ----
    @Test
    /**
     * Method by Juan pablo Vélez
     * This test only creates the book. It does not fulfill any other characteristic.
     * In this case, the book is not in the system.
     */
    public void shouldAddBook() {
        //Having
        Book newBook = new Book("El Conde De Monte Cristo", "Alexandre Dumas", "978-84-92493-70-8");
        Library library = new Library();
        //When
        library.addBook(newBook);
        //Then
        assertTrue(library.getBooks().size() == 1);
    }

    @Test
    /**
     * Method by Juan Daniel Bogotá
     * This test verifies that when the same book is added twice,
     * the quantity of that book in the system increases to 2, and that the size of the book map
     * remains 1, since it is the same book.
     */
    public void shouldIncreaseQuantityWhenAddingSameBookTwice() {
        // Given
        Library library = new Library();
        Book book = new Book("Clean Code", "Robert C. Martin", "978-0132350884");

        // When
        library.addBook(book);
        library.addBook(book);

        // Then
        assertEquals(1, library.getBooks().size());
        assertEquals(2, library.getBooks().get(book));
    }

    @Test
    /**
     * Method by Cristian Gonzalez Rodriguez.
     * Verifies that a new book can be added correctly to the library.
     */
    public void shouldAddBookToLibrary() {
        Library library = new Library();
        Book book = new Book("Clean Code", "Robert Martin", "111");
        library.addBook(book);
        assertTrue(library.getBooks().containsKey(book));
    }

    @Test
    /**
     * Method by Kevin Segura
     * Verifies that addBook returns true and adds the book to the map
     * when a valid book is added for the first time.
     * SHOULD FAIL: addBook always returns false (not implemented).
     */
    public void shouldReturnTrueWhenAddingValidBook() {
        // Given
        Library library = new Library();
        Book book = new Book(
                "The Pragmatic Programmer",
                "David Thomas",
                "978-0135957059"
        );

        // When
        boolean result = library.addBook(book);

        // Then
        assertTrue(result);
        assertEquals(1, library.getBooks().get(book));
    }

    /**
     * Method by Rafael Moreno
     * Verifies that a null book cannot be added to the system.
     * The test validates that the addBook method returns false when
     * trying to add a null book.
     */
    @Test
    public void shouldNotAddNullBook() {
        // Given
        Library library = new Library();

        // When
        boolean result = library.addBook(null);

        // Then
        assertEquals(false, result);
        assertEquals(0, library.getBooks().size());
    }

// ---- LOANABOOK TESTS ----

    @Test
    /**
     * Method by Juan Pablo Vélez
     * This test creates the loan and does not fulfill any other characteristic.
     */
    public void shouldLoanBook() {
        //Having
        Library library = new Library();
        User user = new User();
        user.setName("Juan");
        user.setId("777666777");
        library.addUser(user);
        Book book = new Book("El Conde De Monte Cristo", "Alexandre Dumas", "978-84-92493-70-8");
        library.addBook(book);
        //When
        Loan newLoan = library.loanABook("777666777", "978-84-92493-70-8");
        //Then
        assertNotNull(newLoan);
    }

    @Test
    /**
     * Method by Juan Daniel Bogotá
     * This test verifies that when a loan is created for a book,
     * the quantity of that book in the system decreases by 1.
     */
    public void shouldDecreaseAvailableBooksWhenLoanIsCreated() {
        // Given
        Library library = new Library();
        User user = new User();
        user.setId("123");
        user.setName("Juan");
        library.addUser(user);

        Book book = new Book("Clean Code", "Robert C. Martin", "978-0132350884");
        library.addBook(book);
        library.addBook(book);

        // When
        Loan loan = library.loanABook("123", "978-0132350884");

        // Then
        assertNotNull(loan);
        assertEquals(1, library.getBooks().get(book));
    }

    @Test
    /**
     * Method by Cristian Gonzalez Rodriguez.
     * Verifies that the library can create a loan when the user
     * is registered in the system and the book is available.
     */
    public void shouldCreateLoanWhenBookAndUserExist() {
        Library library = new Library();

        User user = new User();
        user.setId("2");
        user.setName("Cristian");
        library.addUser(user);

        Book book = new Book("Java", "James Gosling", "222");
        library.addBook(book);

        Loan loan = library.loanABook("2", "222");
        assertNotNull(loan);
    }

    @Test
    /**
     * Method by Kevin Segura
     * Verifies that loanABook returns a loan with ACTIVE status
     * when the user and book exist and there are available copies.
     * SHOULD FAIL: loanABook always returns null (not implemented).
     */
    public void shouldCreateLoanWithActiveStatus() {
        // Given
        Library library = new Library();

        User user = new User();
        user.setId("001");
        user.setName("Ana");
        library.addUser(user);

        Book book = new Book(
                "The Pragmatic Programmer",
                "David Thomas",
                "978-0135957059"
        );
        library.addBook(book);

        // When
        Loan loan = library.loanABook("001", "978-0135957059");

        // Then
        assertNotNull(loan);
        assertEquals(LoanStatus.ACTIVE, loan.getStatus());
    }

    /**
     * Method by Rafael Moreno
     * Verifies that a loan cannot be created when the user does not exist.
     * The test validates that the loanABook method returns null when
     * trying to create a loan with a userId that is not registered.
     */
    @Test
    public void shouldNotLoanBookWhenUserDoesNotExist() {
        // Given
        Library library = new Library();
        Book book = new Book("The Pragmatic Programmer", "Andrew Hunt", "978-0201616224");
        library.addBook(book);

        // When
        Loan loan = library.loanABook("999", "978-0201616224");

        // Then
        assertNull(loan);
    }

// ---- RETURNLOAN TESTS ----

    @Test
    /**
     * Method by Juan Pablo Vélez
     * This method only changes the loan status to #RETURNED; the other characteristics
     * are not implemented.
     */
    public void shouldReturnLoan() {
        //Having
        Library library = new Library();
        User user = new User();
        user.setName("Juan");
        user.setId("777666777");
        library.addUser(user);
        Book book = new Book("El Conde De Monte Cristo", "Alexandre Dumas", "978-84-92493-70-8");
        library.addBook(book);
        Loan newLoan = library.loanABook("777666777", "978-84-92493-70-8");
        //When
        library.returnLoan(newLoan);
        //Then
        assertEquals(newLoan.getStatus(), LoanStatus.RETURNED);
    }

    @Test
    /**
     * Method by Juan Daniel Bogotá
     * This test verifies that when a loan is returned,
     * the quantity of that book in the system increases by 1.
     */
    public void shouldIncreaseAvailableBooksWhenReturningLoan() {
        // Given
        Library library = new Library();
        User user = new User();
        user.setId("123");
        user.setName("Juan");
        library.addUser(user);

        Book book = new Book("Clean Code", "Robert C. Martin", "978-0132350884");
        library.addBook(book);
        library.addBook(book);

        Loan loan = library.loanABook("123", "978-0132350884");

        // When
        library.returnLoan(loan);

        // Then
        assertEquals(2, library.getBooks().get(book));
    }

    @Test
    /**
     * Method by Cristian Gonzalez Rodriguez.
     * Verifies that returnLoan returns null when the loan
     * does not exist in the library.
     */
    public void shouldNotReturnLoanIfLoanDoesNotExist() {
        Library library = new Library();
        Loan fakeLoan = new Loan();
        Loan result = library.returnLoan(fakeLoan);
        assertNull(result);
    }

    @Test
    /**
     * Method by Kevin Segura
     * Verifies that when returning a valid loan, the returnDate
     * is set with a date value (is not null).
     * SHOULD FAIL: returnLoan always returns null (not implemented).
     */
    public void shouldSetReturnDateWhenLoanIsReturned() {
        // Given
        Library library = new Library();

        User user = new User();
        user.setId("555");
        user.setName("Maria");
        library.addUser(user);

        Book book = new Book(
                "The Pragmatic Programmer",
                "David Thomas",
                "978-0135957059"
        );
        library.addBook(book);

        Loan loan = library.loanABook("555", "978-0135957059");

        // When
        Loan returnedLoan = library.returnLoan(loan);

        // Then
        assertNotNull(returnedLoan);
        assertNotNull(returnedLoan.getReturnDate());
    }

    /**
     * Method by Rafael Moreno
     * Verifies that when returning a loan, the return date is set.
     * The test validates that the returnLoan method updates the loan return date
     * when it is returned correctly.
     */
    @Test
    public void shouldSetReturnDateWhenReturningLoan() {
        // Given
        Library library = new Library();
        User user = new User();
        user.setId("456");
        user.setName("Rafael");
        library.addUser(user);

        Book book = new Book("Design Patterns", "Gang of Four", "978-0201633612");
        library.addBook(book);

        Loan loan = library.loanABook("456", "978-0201633612");

        // When
        Loan returnedLoan = library.returnLoan(loan);

        // Then
        assertNotNull(returnedLoan);
        assertNotNull(returnedLoan.getReturnDate());
    }
}