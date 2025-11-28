package library.dao;

import library.model.Book;
import library.model.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BorrowRecordDaoTest {

    private BorrowRecordDao dao;
    private Reader reader;
    private Book book;

    @BeforeEach
    void setUp() {
        dao = BorrowRecordDao.getInstance();
        dao.getAllRecords().clear(); // очищаємо, бо DAO — Singleton

        reader = new Reader("testUser", "1234");
        book = new Book.BookBuilder()
                .setTitle("TestBook")
                .setAuthor("Author")
                .setGenre("Genre")
                .setPageCount(100)
                .setYear(2020)
                .setBookId("ID1")
                .build();
    }

    @Test
    void testCreateBorrowRecord() {
        dao.createBorrowRecord(reader, book, 14);

        assertEquals(1, dao.getAllRecords().size());
        assertEquals(book, dao.getAllRecords().get(0).getBook());
        assertEquals(reader, dao.getAllRecords().get(0).getReader());
    }

    @Test
    void testGetNonReturnedRecords() {
        dao.createBorrowRecord(reader, book, 10);

        var nonReturned = dao.getNonReturnedRecords();

        assertEquals(1, nonReturned.size());
        assertFalse(nonReturned.get(0).isReturned());
    }

    @Test
    void testFindNonReturnedRecord() {
        dao.createBorrowRecord(reader, book, 10);

        var found = dao.findNonReturnedRecord(reader, book);

        assertNotNull(found);
        assertEquals(book, found.getBook());
    }

    @Test
    void testCountBorrowedBooksByReader() {
        dao.createBorrowRecord(reader, book, 10);
        dao.createBorrowRecord(reader, book, 10);

        int count = dao.countBorrowedBooksByReader(reader);

        assertEquals(2, count);
    }
}
