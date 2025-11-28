package library.model;

import library.dao.BorrowRecordDao;
import library.state.AvailableState;
import library.state.BorrowedState;
import library.state.ReservedState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Використовуємо MockitoExtension для ініціалізації моків
@ExtendWith(MockitoExtension.class)
public class BookTest {

    // Створюємо мок для DAO, оскільки Book взаємодіє з ним через State
    @Mock
    private BorrowRecordDao mockDao;

    // Створюємо фіктивного читача
    private Reader testReader;

    @BeforeEach
    void setUp() {
        testReader = new Reader("TestReader", "password");
    }

    @Test
    void testBookCreationWithBuilder() {
        String testTitle = "The Great Book";
        String testAuthor = "A. Author";
        int testYear = 2024;

        Book book = new Book.BookBuilder()
                .setTitle(testTitle)
                .setAuthor(testAuthor)
                .setGenre("Fiction")
                .setPageCount(400)
                .setYear(testYear)
                .setBookId("B-12345")
                .setRare(true)
                .build();

        // Перевірка, чи коректно Builder встановив усі поля
        assertEquals(testTitle, book.getTitle());
        assertEquals(testAuthor, book.getAuthor());
        assertEquals(testYear, book.getYear());
        assertTrue(book.isRare());

        // Перевірка початкового стану (має бути AvailableState)
        assertTrue(book.getState() instanceof AvailableState);
    }

    @Test
    void testAvailableForBorrow_RegularBook() {
        // Звичайна книга, стан Available
        Book book = new Book.BookBuilder()
                .setTitle("Normal Book")
                .setRare(false) // Не рідкісна
                .build();

        assertTrue(book.isAvailableForBorrow(), "Звичайна книга у стані Available має бути доступна.");
    }

    @Test
    void testAvailableForBorrow_RareBook() {
        // Рідкісна книга, стан Available
        Book rareBook = new Book.BookBuilder()
                .setTitle("Rare Edition")
                .setRare(true) // Рідкісна
                .build();

        assertFalse(rareBook.isAvailableForBorrow(), "Рідкісна книга не має бути доступна для позики.");
    }

    @Test
    void testAvailableForBorrow_BorrowedBook() {
        // Книга, щойно позичена (стан Borrowed)
        Book borrowedBook = new Book.BookBuilder()
                .setTitle("Borrowed")
                .setRare(false)
                .build();

        borrowedBook.setState(new BorrowedState());

        assertFalse(borrowedBook.isAvailableForBorrow(), "Позичена книга не має бути доступна.");
    }

    @Test
    void testStateChange_BorrowToBorrowed() {
        Book book = new Book.BookBuilder().setTitle("Test Borrow").build();
        assertTrue(book.getState() instanceof AvailableState);

        // Викликаємо метод, який делегує роботу поточному стану
        book.borrow(testReader, mockDao);

        // Після borrow() у AvailableState, стан має змінитися на BorrowedState
        assertTrue(book.getState() instanceof BorrowedState, "Стан має змінитися на BorrowedState після успішної позики.");

        // Оскільки метод borrow() у AvailableState викликає dao.createBorrowRecord(), ми перевіряємо цей виклик
        // Оскільки ми не можемо перевірити виклик усередині самого AvailableState без імітації (mocking)
        // або шпигування (spying) стану, ми покладаємося на те, що зміна стану Book є доказом успішної дії.
        // Для більш глибокої перевірки див. тести BorrowService.
    }

    @Test
    void testStateChange_ReturnToAvailable() {
        Book book = new Book.BookBuilder().setTitle("Test Return").build();

        // Встановлюємо початковий стан Borrowed (якщо книга вже позичена)
        book.setState(new BorrowedState());
        assertTrue(book.getState() instanceof BorrowedState);

        // Імітуємо, що DAO знаходить активний запис для повернення у BorrowedState.returnBook
        when(mockDao.findNonReturnedRecord(any(Reader.class), eq(book))).thenReturn(mock(BorrowRecord.class));

        // Викликаємо метод повернення
        book.returnBook(testReader, mockDao);

        // Після returnBook() у BorrowedState, стан має змінитися на AvailableState
        assertTrue(book.getState() instanceof AvailableState, "Стан має змінитися на AvailableState після повернення.");
    }

    @Test
    void testStateChange_Reserve() {
        Book book = new Book.BookBuilder().setTitle("Test Reserve").build();
        assertTrue(book.getState() instanceof AvailableState);

        // Резервуємо книгу
        book.reserve(testReader, mockDao);

        // Після reserve(), стан має змінитися на ReservedState
        assertTrue(book.getState() instanceof ReservedState, "Стан має змінитися на ReservedState після резервування.");
    }

    @Test
    void testGetStateName() {
        Book book = new Book.BookBuilder().setTitle("State Name Test").build();

        // Початковий стан
        assertEquals("Available State", book.getState().getStateName());

        // Змінюємо стан
        book.setState(new BorrowedState());
        assertEquals("Borrowed", book.getState().getStateName());
    }
}