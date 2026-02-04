# Hints til opgaven: Kig kun her hvis I sidder fast.

## Book
- `equals` skal sammenligne ISBN med `Objects.equals(this.isbn, other.isbn)`.
- `hashCode` skal baseres på ISBN.
- Overvej at gøre felter `final` (immutability) undtagen `loaned`.

## User
- `borrowBook`: ændr betingelsen til `if (borrowedBooks.size() < 3)`.
- `returnBook`: fjern den midlertidige nyinstans; brug direkte `remove(b)`.
- `getBorrowedBooks`: returnér `Collections.unmodifiableList(new ArrayList<>(borrowedBooks))`.
- Overvej at gøre metoder `package-private` alt efter behov.

## Library
- `addBook`: tjek om ISBN allerede findes før tilføjelse.
- `findByTitle`: brug `equalsIgnoreCase`.
- `loanBook`: tjek om `user.borrowBook(b)` **først**; sæt kun `loaned=true` hvis det lykkes. Ellers ingen sideeffekt.
- `returnBook`: brug `user.hasBook(book)` og `user.returnBook(book)` og sæt derefter `loaned=false`.
- `availableBooks`: returnér bøger hvor `!b.isLoaned()`.
- `search`: lav case-insensitive søgning i både titel og forfatter og på delstrenge.

## Ekstra idéer til debugging-demo
- Brug **inline watch** på `books.size()` og `user.getBorrowedBooks().size()` i loops.
- Brug **Reset Frame** på `loanBook` for at rejse igennem metoden igen efter at have ændret conditions.
