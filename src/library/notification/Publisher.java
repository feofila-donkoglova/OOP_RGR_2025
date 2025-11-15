package library.notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Publisher {
    private List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers(EventType eventType, String message) {
        for (Subscriber subscriber : subscribers) {
            Set<EventType> subs = subscriber.getSubscribedEvents();
            if (subs != null && subs.contains(eventType)) {
                subscriber.update(eventType, message);
            }
        }
    }
}
