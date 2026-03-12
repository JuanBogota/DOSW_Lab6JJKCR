package edu.eci.dosw.tdd;

import edu.eci.dosw.tdd.library.Library;
import edu.eci.dosw.tdd.library.book.Book;
import edu.eci.dosw.tdd.library.loan.Loan;
import edu.eci.dosw.tdd.library.loan.LoanStatus;
import edu.eci.dosw.tdd.library.user.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    @Test
    /**
     * Method by Juan pablo Vélez
     * Esta prueba solo crea el libro. no cumple ninguna otra característica.
     * En este caso el libro no está en el sistema.
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
     * Method by Juan Pablo Vélez
     * Esta prueba crea la loan no cumple ninguna otra caracteristica
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
        library.addBook(book);
        library.addBook(book);
        //When
        Loan newLoan = library.loanABook("777666777", "978-84-92493-70-8");
        //Then
        assertNotNull(newLoan);
    }

    @Test
    /**
     * Method by Juan Pablo Vélez
     * este metodo solo cambia el estado de loan a #RETURNED, las otras características no
     * están hechas
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
        library.addBook(book);
        library.addBook(book);
        Loan newLoan = library.loanABook("777666777", "978-84-92493-70-8");
        //When
        library.returnLoan(newLoan);
        //Then
        assertEquals(newLoan.getStatus(), LoanStatus.RETURNED);
    }
}