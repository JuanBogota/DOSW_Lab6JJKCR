package edu.eci.dosw.tdd.library;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.eci.dosw.tdd.library.book.Book;
import edu.eci.dosw.tdd.library.loan.Loan;
import edu.eci.dosw.tdd.library.loan.LoanStatus;
import edu.eci.dosw.tdd.library.user.User;

/**
 * Library responsible for manage the loans and the users.
 */
public class Library {

    private final List<User> users;
    private final Map<Book, Integer> books;
    private final List<Loan> loans;

    public Library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    /**
     * Adds a new Book into the system. If the book already exists,
     * increases its quantity by 1. If it's new, sets quantity to 1.
     *
     * @param book The book to store in the map.
     * @return true if the book was stored, false otherwise.
     */
    public boolean addBook(Book book) {
        if (book == null) {
            return false;
        }
        if (books.containsKey(book)) {
            books.put(book, books.get(book) + 1);
        } else {
            books.put(book, 1);
        }
        return true;
    }

    /**
     * Creates a new loan for the User identified by userId and
     * the book identified by isbn.
     * Validations:
     * - The book must exist and have available copies
     * - The user must exist
     * - The same user cannot have an ACTIVE loan for the same book
     *
     * @param userId id of the user.
     * @param isbn   book identification.
     * @return The new created loan, or null if validations fail.
     */
    public Loan loanABook(String userId, String isbn) {
        User foundUser = null;
        for (User user : users) {
            if (user.getId().equals(userId)) {
                foundUser = user;
                break;
            }
        }
        if (foundUser == null) {
            return null;
        }
        Book foundBook = null;
        for (Book book : books.keySet()) {
            if (book.getIsbn().equals(isbn)) {
                foundBook = book;
                break;
            }
        }
        if (foundBook == null || books.get(foundBook) <= 0) {
            return null;
        }

        for (Loan loan : loans) {
            if (loan.getUser().getId().equals(userId)
                    && loan.getBook().getIsbn().equals(isbn)
                    && loan.getStatus() == LoanStatus.ACTIVE) {
                return null;
            }
        }

        Loan newLoan = new Loan();
        newLoan.setBook(foundBook);
        newLoan.setUser(foundUser);
        newLoan.setStatus(LoanStatus.ACTIVE);
        newLoan.setLoanDate(LocalDateTime.now());

        books.put(foundBook, books.get(foundBook) - 1);
        loans.add(newLoan);

        return newLoan;
    }

    /**
     * Returns a loan. Increases book quantity by 1,
     * sets loan status to RETURNED and sets the return date.
     * Validates that the loan exists in the system.
     *
     * @param loan loan to return.
     * @return the loan with RETURNED status, or null if it doesn't exist.
     */
    public Loan returnLoan(Loan loan) {
        if (!loans.contains(loan)) {
            return null;
        }
        Book book = loan.getBook();
        books.put(book, books.get(book) + 1);
        loan.setStatus(LoanStatus.RETURNED);
        loan.setReturnDate(LocalDateTime.now());

        return loan;
    }

    public boolean addUser(User user) {
        return users.add(user);
    }

    public Map<Book, Integer> getBooks() {
        return books;
    }
}