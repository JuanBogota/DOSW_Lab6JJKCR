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
     * 
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
        Loan newLoan = library.loanABook("777666777", "978-84-92493-70-8");
        //When
        library.returnLoan(newLoan);
        //Then
        assertEquals(newLoan.getStatus(), LoanStatus.RETURNED);
    }


    @Test
    /**
     * Method by Juan Daniel Bogotá
     * Esta prueba se encarga de verificar que al agregar el mismo libro dos veces, 
     * la cantidad de ese libro en el sistema aumente a 2, y que el tamaño del mapa de libros siga siendo 1, 
     * ya que es el mismo libro.
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
     * Method by Juan Daniel Bogotá
     * Esta prueba se encarga de verificar que al crear un préstamo para un libro, 
     * la cantidad de ese libro en el sistema disminuya en 1, 
     * lo que indica que el libro ha sido prestado y ya no está disponible para otros usuarios.
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
     * Method by Juan Daniel Bogotá
     * Esta prueba se encarga de verificar que al devolver un préstamo, la cantidad de ese libro en el sistema aumente en 1,
     * lo que indica que el libro ha sido devuelto y está disponible nuevamente para otros usuarios.
     * Además, se verifica que el estado del préstamo se actualice a RETURNED.
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
        Loan fakeLoan = new Loan();
        Loan result = library.returnLoan(fakeLoan);
        assertNull(result);
    }

    /**
     * Method by Rafael Moreno
     * Verifica que no se pueda agregar un libro null al sistema.
     * La prueba valida que el método addBook retorne false cuando
     * se intenta agregar un libro null.
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

    /**
     * Method by Rafael Moreno
     * Verifica que no se pueda crear un préstamo cuando el usuario no existe.
     * La prueba valida que el método loanABook retorne null cuando
     * se intenta crear un préstamo con un userId que no está registrado.
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

    /**
     * Method by Rafael Moreno
     * Verifica que al devolver un préstamo, la fecha de devolución se establezca.
     * La prueba valida que el método returnLoan actualice la fecha de retorno
     * del préstamo cuando se devuelve correctamente.
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
