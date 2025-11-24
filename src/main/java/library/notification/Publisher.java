package library.notification;

import java.util.*;

public class Publisher {
    private final Map<EventType, List<Subscriber>> subscribers = new HashMap<>();

    public Publisher() {
        for (EventType type : EventType.values()) {
            subscribers.put(type, new ArrayList<>());
        }
    }

    public void subscribe(EventType eventType, Subscriber subscriber) {
        List<Subscriber> eventSubscribers = subscribers.get(eventType);
        if (eventSubscribers != null && !eventSubscribers.contains(subscriber)) {
            eventSubscribers.add(subscriber);
            System.out.println(subscriber.getClass().getSimpleName() +
                    " підписано на " + eventType);
        }
    }

    public void unsubscribe(EventType eventType, Subscriber subscriber) {
        List<Subscriber> eventSubscribers = subscribers.get(eventType);
        if (eventSubscribers != null) {
            eventSubscribers.remove(subscriber);
            System.out.println(subscriber.getClass().getSimpleName() +
                    " відписано від " + eventType);
        }
    }
    public void notifySubscribers(EventType eventType, String message) {
        System.out.println("\nPUBLISH: Подія [" + eventType + "] з даними: " + message);
        List<Subscriber> eventSubscribers = subscribers.get(eventType);

        if (eventSubscribers != null) {
            for (Subscriber subscriber : eventSubscribers) {
                 subscriber.update(eventType, message);
            }
        }
    }
}
