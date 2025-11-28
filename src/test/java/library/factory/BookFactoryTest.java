package library.factory;

import library.model.Book;
import library.model.EBook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookFactoryTest {

    @Test
    void testCreateBook() {
        String title = "Test Book";
        String author = "Factory Author";
        int year = 2023;

        Book book = BookFactory.createBook(title, author, "Fiction", 300, year);

        assertNotNull(book);
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(2023, book.getYear());
        // Перевірка, що BookId був згенерований
        assertNotNull(book.getBookId());
        assertTrue(book.getBookId().length() > 0);
        // Перевірка, що це саме Book, а не EBook
        assertFalse(book instanceof EBook);
    }

    @Test
    void testCreateEBook() {
        String title = "Test EBook";
        String author = "EBook Author";
        String link = "http://test.link/pdf";
        String format = "PDF";

        EBook eBook = BookFactory.createEBook(title, author, "Technical", 500, 2024, link, format);

        assertNotNull(eBook);
        assertEquals(title, eBook.getTitle());
        assertEquals(link, eBook.getDownloadLink());
        assertEquals(format, eBook.getFormat());
        // Перевірка, що це саме EBook
        assertTrue(eBook instanceof Book);
        assertTrue(eBook instanceof EBook);
    }

    @Test
    void testEBookAvailability() {
        EBook eBook = BookFactory.createEBook("E", "A", "G", 100, 2020, "L", "F");
        // EBook завжди доступна для позики згідно з моделлю
        assertTrue(eBook.isAvailableForBorrow());
    }
}