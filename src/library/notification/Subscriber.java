package library.notification;

import java.util.Set;

public interface Subscriber {
    void update(EventType eventType, String message);
    Set<EventType> getSubscribedEvents();
}

