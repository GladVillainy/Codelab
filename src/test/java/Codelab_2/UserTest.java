package Codelab_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
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
            "9780132350881"
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
    void userCannotBorrowMoreThanThreeBooks() {
        // TODO: skriv test der viser at lån nr. 4 afvises
        User user1 = new User("Lucas");
          user1.borrowBook(book1); //Book1
         user1.borrowBook(book2);//Book2
        user1.borrowBook(book3); //Book3

        boolean actual = user1.borrowBook(book4); //Book4
        boolean expected = false;

        assertEquals(expected, actual);
    }

    @Test
    void borrowedBooksShouldNotBeExternallyModifiable() {
        // TODO: skriv test der forsøger at ændre listen fra getBorrowedBooks()
        User user1 = new User("Lucas");

        assertThrows(UnsupportedOperationException.class,
                () -> user1.getBorrowedBooks().add(book1));

    }
}
