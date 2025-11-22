package library.model;

import java.time.LocalDate;

public class BorrowRecord {
    private Reader reader;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private int daysNumber;

    public BorrowRecord(Reader reader, Book book, LocalDate borrowDate, LocalDate returnDate, int daysNumber) {
        this.reader = reader;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.daysNumber = daysNumber;
    }
    //спрощений конструктор
    public BorrowRecord(Reader reader, Book book) {
        this.reader = reader;
        this.book = book;
        this.borrowDate = LocalDate.now();
        this.returnDate = null;
        this.daysNumber = 14; // за замовчуванням
    }

    public Book getBook(){
        return book;
    }
    public Reader getReader() {
        return this.reader;
    }
    public void returnBook() {
        this.returnDate = LocalDate.now();
    }
    public boolean isReturned() {
        return this.returnDate != null;
    }
    public boolean isOverdue() {
        LocalDate dueDate = this.borrowDate.plusDays(this.daysNumber);
        return !this.isReturned() && LocalDate.now().isAfter(dueDate);
    }
    public void showRecord() {
        System.out.println(reader.getUsername() + " позичив(ла) \"" + book.getTitle() +
                "\" (" + borrowDate + (returnDate != null ? " — повернув(ла): " + returnDate : "") + ")");
    }
}
