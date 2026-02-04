package Codelab_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Library {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public Book findByTitle(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }

    public boolean loanBook(String isbn, User user) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn) && !b.isLoaned()) {
                b.setLoaned(true);
                boolean ok = user.borrowBook(b);
                return true;
            }
        }
        return false;
    }

    public void returnBook(Book book, User user) {
        if (user.getBorrowedBooks().contains(book)) {
            book.setLoaned(false);
            user.getBorrowedBooks().remove(book);
        }
    }

    public List<Book> availableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.isLoaned()) {
                result.add(b);
            }
        }
        return result;
    }

    // Bonus: simpel søgning - med bugs
    public List<Book> search(String query) {
        return books.stream()
                .filter(b -> b.getTitle().contains(query))
                .collect(Collectors.toList());
    }

    public List<Book> getAllBooks() {
        return Collections.unmodifiableList(books);
    }
}
