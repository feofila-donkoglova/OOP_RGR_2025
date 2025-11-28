package library.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EBookTest {

    @Test
    void testEBookCreation() {
        EBook ebook = new EBook.EBookBuilder()
                .setTitle("Digital Book")
                .setAuthor("Author")
                .setGenre("Fantasy")
                .setPageCount(200)
                .setYear(2023)
                .setBookId("EB1")
                .setDownloadLink("http://example.com")
                .setFormat("PDF")
                .build();

        assertEquals("Digital Book", ebook.getTitle());
        assertEquals("PDF", ebook.getFormat());
        assertEquals("http://example.com", ebook.getDownloadLink());
    }

    @Test
    void testEBookAlwaysAvailable() {
        EBook ebook = new EBook.EBookBuilder()
                .setTitle("Digital Book")
                .setAuthor("Author")
                .setGenre("Fantasy")
                .setPageCount(200)
                .setYear(2023)
                .setBookId("EB1")
                .setDownloadLink("http://example.com")
                .setFormat("PDF")
                .build();

        assertTrue(ebook.isAvailableForBorrow());
    }
}
