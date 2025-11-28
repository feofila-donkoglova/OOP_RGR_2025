package library.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {

    @Test
    void testBorrowCounter() {
        Reader reader = new Reader("user", "pass");

        assertEquals(0, reader.getBooksBorrowed());

        reader.incrementBooksBorrowed();
        assertEquals(1, reader.getBooksBorrowed());

        reader.decrementBooksBorrowed();
        assertEquals(0, reader.getBooksBorrowed());
    }
}
