// BorrowServiceTest.java
package library.service;

import library.dao.BorrowRecordDao;
import library.exceptions.BookUnavailableException;
import library.exceptions.MaxLoanLimitExceededException;
import library.model.Book;
import library.model.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BorrowServiceTest {

    @Mock
    private BorrowRecordDao mockDao;

    @InjectMocks
    private BorrowService borrowService;

    private Reader testReader;
    private Book testBook;
    private final int MAX_LOANS = 5;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testReader = new Reader("testUser", "pass");
        testBook = new Book("Test Book", "Author", 2023, 100);
    }

    @Test
    void testBorrowBook_Success() throws Exception {
        when(mockDao.countBorrowedBooksByReader(testReader)).thenReturn(0);
        when(mockDao.isAvailable(testBook)).thenReturn(true);

        borrowService.borrowBook(testBook, testReader);

        // Перевіряємо, чи викликався DAO для створення запису
        verify(mockDao, times(1)).createBorrowRecord(eq(testReader), eq(testBook), anyInt());
        // Перевіряємо, чи оновився лічильник у читача
        assertEquals(1, testReader.getBooksBorrowed());
    }

    @Test
    void testBorrowBook_LimitExceeded() {
        when(mockDao.countBorrowedBooksByReader(testReader)).thenReturn(MAX_LOANS);

        assertThrows(MaxLoanLimitExceededException.class, () -> {
            borrowService.borrowBook(testBook, testReader);
        });

        verify(mockDao, never()).createBorrowRecord(any(), any(), anyInt());
    }
}