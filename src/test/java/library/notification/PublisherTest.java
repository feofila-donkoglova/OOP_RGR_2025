package library.notification;

import library.model.Reader;
import library.model.User;
import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PublisherTest {

    @Test
    void testSubscribeAndNotify() {
        Publisher publisher = new Publisher();
        // Використовуємо Reader як приклад Subscriber
        Reader reader = new Reader("TestReader", "pass");

        // Підписка
        publisher.subscribe(EventType.NEW_BOOK, reader);

        // Оскільки update() виводить повідомлення в консоль,
        // ми не можемо прямо перевірити його виконання, але можемо перевірити логіку підписки

        // Створюємо макет підписника для перевірки
        class MockSubscriber implements Subscriber {
            private String lastMessage = null;
            @Override
            public void update(EventType eventType, String message) {
                lastMessage = message;
            }
            @Override
            public Set<EventType> getSubscribedEvents() {
                return Set.of(EventType.NEW_BOOK);
            }
        }

        MockSubscriber mock = new MockSubscriber();
        publisher.subscribe(EventType.NEW_BOOK, mock);

        String message = "New book is out!";
        publisher.notifySubscribers(EventType.NEW_BOOK, message);

        assertEquals(message, mock.lastMessage, "Повідомлення має бути доставлено підписнику");
    }

    @Test
    void testUnsubscribe() {
        Publisher publisher = new Publisher();
        class MockSubscriber implements Subscriber {
            private int updateCount = 0;
            @Override
            public void update(EventType eventType, String message) {
                updateCount++;
            }
            @Override
            public Set<EventType> getSubscribedEvents() {
                return Set.of(EventType.NEW_BOOK);
            }
        }

        MockSubscriber mock = new MockSubscriber();

        publisher.subscribe(EventType.NEW_BOOK, mock);
        publisher.notifySubscribers(EventType.NEW_BOOK, "Before unsubscribe");
        assertEquals(1, mock.updateCount, "Має бути 1 оновлення до відписки");

        publisher.unsubscribe(EventType.NEW_BOOK, mock);
        publisher.notifySubscribers(EventType.NEW_BOOK, "After unsubscribe");

        assertEquals(1, mock.updateCount, "Кількість оновлень не має збільшитися після відписки");
    }
}