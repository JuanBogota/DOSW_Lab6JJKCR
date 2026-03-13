package edu.eci.dosw.tdd.library;

import org.junit.jupiter.api.Test;

import edu.eci.dosw.tdd.library.book.Book;
import edu.eci.dosw.tdd.library.loan.Loan;
import edu.eci.dosw.tdd.library.loan.LoanStatus;
import edu.eci.dosw.tdd.library.user.User;

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
     * la cantidad de ese libro en el sistema aumente a 2, y que el tamaño del mapa
     * de libros siga siendo 1, ya que es el mismo libro.
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
     * la cantidad de ese libro en el sistema disminuya en 1.
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
     * Esta prueba se encarga de verificar que al devolver un préstamo,
     * la cantidad de ese libro en el sistema aumente en 1.
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
     * Verifica que un libro nuevo pueda ser agregado correctamente a la biblioteca.
     */
    public void shouldAddBookToLibrary() {
        Library library = new Library();
        Book book = new Book("Clean Code", "Robert Martin", "111");
        library.addBook(book);
        assertTrue(library.getBooks().containsKey(book));
    }

    @Test
    /**
     * Method by Cristian Gonzalez Rodriguez.
     * Verifica que la biblioteca pueda crear un préstamo cuando el usuario
     * está registrado en el sistema y el libro se encuentra disponible.
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
     * Method by Cristian Gonzalez Rodriguez.
     * Verifica que returnLoan retorne null cuando el préstamo
     * no existe en la biblioteca.
     */
    public void shouldNotReturnLoanIfLoanDoesNotExist() {
        Library library = new Library();
        Loan fakeLoan = new Loan();
        Loan result = library.returnLoan(fakeLoan);
        assertNull(result);
    }

    @Test
    /**
     * Verifica que addBook retorne true y agregue el libro al mapa
     * cuando se agrega un libro válido por primera vez.
     * DEBE FALLAR: addBook siempre retorna false (sin implementar).
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

    @Test
    /**
     * Verifica que loanABook retorne un préstamo con estado ACTIVE
     * cuando el usuario y libro existen y hay copias disponibles.
     * DEBE FALLAR: loanABook siempre retorna null (sin implementar).
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

    @Test
    /**
     * Verifica que al retornar un préstamo válido, la returnDate
     * quede establecida con una fecha (no sea null).
     * DEBE FALLAR: returnLoan siempre retorna null (sin implementar).
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
