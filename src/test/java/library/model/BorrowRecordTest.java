package library.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BorrowRecordTest {

    @Test
    void testCreateRecord() {
        Reader reader = new Reader("user", "123");
        Book book = new Book.BookBuilder()
                .setTitle("Test")
                .setAuthor("Auth")
                .setGenre("G")
                .setPageCount(100)
                .setYear(2020)
                .setBookId("ID1")
                .build();

        BorrowRecord record = new BorrowRecord(reader, book, LocalDate.now(), null, 14);

        assertEquals(reader, record.getReader());
        assertEquals(book, record.getBook());
        assertFalse(record.isReturned());
    }

    @Test
    void testReturnBook() {
        Reader reader = new Reader("user", "123");
        Book book = new Book.BookBuilder()
                .setTitle("Test")
                .setAuthor("Auth")
                .setGenre("G")
                .setPageCount(100)
                .setYear(2020)
                .setBookId("ID1")
                .build();

        BorrowRecord record = new BorrowRecord(reader, book);
        assertFalse(record.isReturned());

        record.returnBook();
        assertTrue(record.isReturned());
    }

    @Test
    void testIsOverdue() {
        Reader reader = new Reader("user", "123");
        Book book = new Book.BookBuilder()
                .setTitle("Test")
                .setAuthor("Auth")
                .setGenre("G")
                .setPageCount(100)
                .setYear(2020)
                .setBookId("ID1")
                .build();

        BorrowRecord record = new BorrowRecord(reader, book, LocalDate.now().minusDays(20), null, 14);

        assertTrue(record.isOverdue());
    }
}
