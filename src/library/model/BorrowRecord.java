package library.model;

import library.Users.Reader;
import java.time.LocalDate;

public class BorrowRecord {
    private Reader reader;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowRecord(Reader reader, Book book) {
        this.reader = reader;
        this.book = book;
        this.borrowDate = LocalDate.now();
    }
    public void returnBook() {
        this.returnDate = LocalDate.now();
    }
    public void showRecord() {
        System.out.println(reader.getUsername() + " позичив(ла) \"" + book.getTitle() +
                "\" (" + borrowDate + (returnDate != null ? " — повернув(ла): " + returnDate : "") + ")");
    }

}
