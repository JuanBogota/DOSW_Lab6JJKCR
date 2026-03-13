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


    /**
     * Method by Cristian Gonzalez Rodriguez.
     * Verifica que un libro nuevo pueda ser agregado correctamente a la biblioteca.
     * La prueba valida que cuando se agrega un libro que no existe previamente
     * en el sistema, este quede almacenado dentro de la colección de libros
     * administrada por la biblioteca.
     */
    @Test
    public void shouldAddBookToLibrary() {

        Library library = new Library();
        Book book = new Book("Clean Code", "Robert Martin", "111");
        library.addBook(book);
        assertTrue(library.getBooks().containsKey(book));
    }

    /**
     * Method by Cristian Gonzalez Rodriguez.
     * Verifica que la biblioteca pueda crear un préstamo cuando el usuario
     * está registrado en el sistema y el libro se encuentra disponible.
     * La prueba valida que el método loanABook retorne un objeto Loan válido.
     */
    @Test
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

    /**
     * Method by Cristian Gonzalez Rodriguez.
     * Verifica el comportamiento del sistema cuando se intenta devolver
     * un préstamo que no está registrado en la biblioteca.
     * La prueba valida que el método returnLoan no procese la devolución
     * y retorne null cuando el préstamo no existe en la lista de préstamos.
     */
    @Test
    void shouldNotReturnLoanIfLoanDoesNotExist() {

        Library library = new Library();
        Loan fakeLoan = new Loan(); // préstamo que nunca fue creado por la biblioteca
        Loan result = library.returnLoan(fakeLoan);
        assertNull(result);
    }
}