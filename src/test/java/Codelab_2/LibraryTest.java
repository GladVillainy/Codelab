package Codelab_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    Library instance = new Library();

    //All books are AI generated
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

        int expected = 2;
        int actual = instance.getAllBooks().size();

        assertEquals(expected, actual);
    }

    @Test
    void findByTitleShouldBeCaseInsensitiveAndUseEquals() {
        // TODO
        instance.addBook(book1);
        instance.addBook(book2);
        instance.addBook(book3);
        instance.addBook(book4);
        instance.addBook(book5);

        String title = "refactoring";

        Book expected = book4;
        Book actual = instance.findByTitle(title);

        assertEquals(expected, actual);

    }

    @Test
    void loanAndReturnHappyPath() {
        // TODO: lån bog til bruger med plads; returnér; tjek availableBooks
        User user1 = new User("Lucas");

        instance.addBook(book1); //User 1 loans book 1
        instance.addBook(book3);

        //Checking available
        int actualAvailable = instance.availableBooks().size();
        int expectedAvailable = 2;

        assertEquals(expectedAvailable, actualAvailable);

        //Checking if book gets loaned
        instance.loanBook("9780201616224", user1);

        int actualAvailableLoan = instance.availableBooks().size();
        int expectedAvailableLoan = 1;
        assertEquals(expectedAvailableLoan, actualAvailableLoan);

        //Checking if loaned
        boolean isLoaned = user1.getBorrowedBooks().contains(book1);
        boolean loaned = true;

        assertEquals(isLoaned, loaned);

        //Checking return
        user1.returnBook(book1);
        boolean isAvailable = instance.availableBooks().contains(book1);
        boolean available = true;

        assertEquals(isAvailable, available);
        
    }

    @Test
    void loanShouldNotChangeBookStateIfUserCannotBorrow() {
        // TODO: fyld bruger op med 3 bøger; forsøg at låne en 4.; bogen må ikke blive markeret som udlånt
       User user1 = new User("Lucas");

       instance.addBook(book1);
       instance.addBook(book2);
       instance.addBook(book3);
       instance.addBook(book4);

       instance.loanBook("9780201616224", user1); //Book1
       instance.loanBook("9780132350884", user1);//Book2
       instance.loanBook("9780134685991", user1);//Book3

       instance.loanBook("9780201485677", user1);//Book4


       boolean expected = user1.getBorrowedBooks().contains(book4);
       boolean actual = instance.availableBooks().contains(book4);

        assertEquals(expected,actual );

    }
}
