package Codelab_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    private final String name;
    private final List<Book> borrowedBooks = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean borrowBook(Book b) {
        if (borrowedBooks.size() < 3) {
            borrowedBooks.add(b);
            return true;
        }
        return false;
    }

    public boolean hasBook(Book b) {
        return borrowedBooks.contains(b);
    }

    public boolean returnBook(Book b) {
        Book tmp = new Book(b.getTitle(), b.getAuthor(), b.getIsbn());
        return borrowedBooks.remove(tmp);
    }

    public List<Book> getBorrowedBooks() {
        int maxBorrowed = 3;
        return borrowedBooks.stream().limit(maxBorrowed).toList();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + "'" +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}
