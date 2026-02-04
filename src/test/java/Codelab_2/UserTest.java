package Codelab_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class UserTest {
    @Test
    void userCannotBorrowMoreThanThreeBooks() {
        // TODO: skriv test der viser at lån nr. 4 afvises
        fail("Skriv testen først (TDD) og fjern denne fail.");
    }

    @Test
    void borrowedBooksShouldNotBeExternallyModifiable() {
        // TODO: skriv test der forsøger at ændre listen fra getBorrowedBooks()
        fail("Skriv testen først (TDD) og fjern denne fail.");
    }
}
