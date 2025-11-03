package library.model;

import library.Users.Reader;
import java.time.LocalDate;

public class BorrowRecord {
    private Reader reader;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private LocalDate dueDate;
    private int daysNumber;

    public BorrowRecord(Reader reader, Book book, LocalDate borrowDate, LocalDate returnDate, int daysNumber) {
        this.reader = reader;
        this.book = book;
        this.borrowDate = LocalDate.now();
        this.returnDate = returnDate;
        this.daysNumber = daysNumber;
    }
    public void returnBook() {
        this.returnDate = LocalDate.now();
    }
    public void showRecord() {
        System.out.println(reader.getUsername() + " позичив(ла) \"" + book.getTitle() +
                "\" (" + borrowDate + (returnDate != null ? " — повернув(ла): " + returnDate : "") + ")");
    }

    public boolean isReturned() {
        return this.returnDate != null;
    }

    public Reader getReader() {
        return this.reader;
    }
    public boolean isOverdue() {
        LocalDate dueDate = this.borrowDate.plusDays(this.daysNumber);
        return !this.isReturned() && LocalDate.now().isAfter(dueDate);
    }
}
