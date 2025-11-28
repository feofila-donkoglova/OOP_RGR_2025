package library.model;

import library.notification.EventType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testSubscribe() {
        User user = new User("testUser", "123");

        assertTrue(user.getSubscribedEvents().isEmpty());

        user.subscribe(EventType.NEW_BOOK);
        assertTrue(user.getSubscribedEvents().contains(EventType.NEW_BOOK));

        user.unsubscribe(EventType.NEW_BOOK);
        assertFalse(user.getSubscribedEvents().contains(EventType.NEW_BOOK));
    }

    @Test
    void testUpdate() {
        User user = new User("user", "pass");

        // Просто перевіряємо, що метод не видає помилку
        assertDoesNotThrow(() ->
                user.update(EventType.BOOK_BORROWED, "Книгу взято")
        );
    }
}
