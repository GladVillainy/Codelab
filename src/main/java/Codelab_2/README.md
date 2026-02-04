# Codelab med Debugging + TDD i par: Mini-bibliotek

**Formål:** I arbejder 2 og 2. Skriv tests først (TDD), brug derefter IntelliJ-debuggeren til at lokalisere og rette fejl i koden.  
Projektet indeholder bevidst *subtile* bugs – ikke syntaksfejl.

## Læringsmål
- Træn JUnit 5 og TDD (red-green-refactor).
- Brug breakpoints, step into/over, **inline watches** og **reset frame** i IntelliJ.
- Refaktorér, når alle tests passerer.

## Sådan kommer I i gang
1. Importér som **Maven-projekt** i IntelliJ.
2. Kør `Main` for at se den nuværende (forkerte) adfærd.
3. Opret tests (start med `LibraryTest`, `BookTest`, `UserTest`).
4. Skriv tests ud fra **kravene** nedenfor. Kør tests → de fejler.
5. Brug debuggeren til at finde og rette fejl **én ad gangen**.
6. Når alle tests er grønne, **refaktorér** (f.eks. equals/hashCode, indkapsling).

## Krav (adfærd, som systemet skal have, når I er færdige)
1. **Book**
   - Felter: `title`, `author`, `isbn`, `loaned` (boolean).
   - To bøger er **lige** hvis og kun hvis de har **samme ISBN**.
   - `equals` og `hashCode` skal være konsistente og baseret på ISBN.
2. **User**
   - Kan maksimalt have **3 bøger** lånt samtidig.
   - `borrowBook` skal **afvise** lån nummer 4 og returnere `false`.
   - `getBorrowedBooks()` må **ikke** eksponere en modificerbar intern liste.
3. **Library**
   - `addBook` må ikke skabe dubletter af samme ISBN (idempotent).
   - `findByTitle(String)` er **case-insensitive** og bruger **equals**, ikke `==`.
   - `loanBook(isbn, user)` låner kun, hvis bogen findes, ikke er udlånt, og brugeren har plads. Hvis brugeren **ikke** kan låne, må bogens `loaned`-flag ikke ændres.
   - `returnBook(book, user)` returnerer kun, hvis brugeren har bogen; derefter bliver bogen ledig.
   - `availableBooks()` returnerer **alle ikke-udlånte** bøger.
   - Bonus: `search(String query)` kan finde på delstrenge i titel/forfatter (case-insensitive).

> **Vigtigt:** Koden i `src/main/java` er fyldt med fejl, som strider imod ovenstående krav. Brug TDD + debugging for at bringe implementeringen i overensstemmelse med kravene.

## Foreslået tidsplan (4 timer)
- 0:00–0:20 – Læs krav, planlæg tests (arranger i par-programmering: driver/navigator).
- 0:20–1:20 – Skriv første bølge af tests (red-green).
- 1:20–2:40 – Debug & fix runde (brug **inline watch** og **reset frame** målrettet).
- 2:40–3:30 – Dækkende tests, refaktorering.
- 3:30–4:00 – Kort rapport + oprydning.

## Tip til første tests
- `BookTest`: to instanser med samme ISBN skal være lige; sæt dem i en HashSet og bevis, at dublet ikke tilføjes.
- `UserTest`: lån tre bøger → `borrowBook` returnerer `true`; forsøg at låne en fjerde → `false` og ingen effekt på de andre.
- `LibraryTest`: tilføj bog to gange → mængden af bøger ændrer sig ikke; lån og returnér; kontroller `availableBooks`.

## IntelliJ-debugging checkliste
- Slå **Show values inline** til (Settings → Debugger → Data Views).
- Højreklik i editor → **Add Inline Watch** for udtryk (fx `arr[i]`, `user.getBorrowedBooks().size()`).
- Brug **Reset Frame** på metoder, I vil genspille.
- Brug **Evaluate Expression** (Alt+F8) til at teste edge cases.

## Leverancer
- En JUnit test suite, som dokumenterer kravene.
- Rettet og refaktoreret kode, hvor alle tests er grønne.
- ½ sides refleksion: Hvilke bugs krævede debuggeren? Hvordan hjalp TDD jer?

# repo auto created
