package Codelab_2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library lib = new Library();
        lib.addBook(new Book("Clean Code", "Robert C. Martin", "9780132350884"));
        lib.addBook(new Book("Effective Java", "Joshua Bloch", "9780134685991"));
        lib.addBook(new Book("clean code", "Robert C. Martin", "9780132350884")); // dublet ISBN - bør ignoreres ifølge krav

        User alice = new User("Alice");
        boolean loaned = lib.loanBook("9780132350884", alice);
        System.out.println("Loaned Clean Code to Alice? " + loaned);

        List<Book> available = lib.availableBooks();
        System.out.println("Available books now: " + available);

        // Prøv at returnere og lån igen
        if (!available.isEmpty()) {
            Book b = available.get(0);
            lib.returnBook(b, alice); // kan fejle pga equals/contains bugs
        }
        System.out.println("After return attempt, available: " + lib.availableBooks());
    }
}
