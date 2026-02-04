package Codelab_2;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean loaned;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.loaned = false;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }

    public void setLoaned(boolean loaned) {
        this.loaned = loaned;
    }

    public boolean isLoaned() {
        return loaned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book other = (Book) o;
        return this.title == other.title;
    }

    @Override
    public int hashCode() {
        return Objects.hash(author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", loaned=" + loaned +
                '}';
    }
}
