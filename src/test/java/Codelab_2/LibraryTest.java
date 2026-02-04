package Codelab_2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class LibraryTest {

    Library instance = new Library();

    Book book1 = new Book(
            "Clean Code",
            "Robert C. Martin",
            "9780132350884"
    );

    Book book2 = new Book(
            "Clean Code (2nd Copy)",
            "Robert C. Martin",
            "9780132350884" // Samme ISBN som book1
    );

    Book book3 = new Book(
            "Effective Java",
            "Joshua Bloch",
            "9780134685991"
    );

    Book book4 = new Book(
            "Refactoring",
            "Martin Fowler",
            "9780201485677"
    );

    Book book5 = new Book(
            "The Pragmatic Programmer",
            "Andrew Hunt & David Thomas",
            "9780201616224"
    );

    @Test
    void addBookShouldBeIdempotentOnIsbn() {
        // TODO: tilføj samme ISBN to gange, forvent kun én bog
        instance.addBook(book1);
        instance.addBook(book2);
        instance.addBook(book3);
    }

    @Test
    void findByTitleShouldBeCaseInsensitiveAndUseEquals() {
        // TODO
        fail("Skriv testen først (TDD) og fjern denne fail.");
    }

    @Test
    void loanAndReturnHappyPath() {
        // TODO: lån bog til bruger med plads; returnér; tjek availableBooks
        fail("Skriv testen først (TDD) og fjern denne fail.");
    }

    @Test
    void loanShouldNotChangeBookStateIfUserCannotBorrow() {
        // TODO: fyld bruger op med 3 bøger; forsøg at låne en 4.; bogen må ikke blive markeret som udlånt
        fail("Skriv testen først (TDD) og fjern denne fail.");
    }
}
