package library.notification;

import java.util.ArrayList;
import java.util.List;

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
            if (subscriber.getSubscribedEvents().contains(eventType)) {
                subscriber.update(eventType, message);
            }
        }
    }
}
