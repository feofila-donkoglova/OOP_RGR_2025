package library.model;

import library.notification.*;
import java.util.HashSet;
import java.util.Set;

public class User implements Subscriber {
    private String username;
    private String password;
    private Set<EventType> subscribedEvents;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.subscribedEvents = new HashSet<>();
    }

    @Override
    public void update(EventType eventType, String message) {
        System.out.println("[" + username + "] отримано повідомлення (" + eventType + "): " + message);
    }

    @Override
    public Set<EventType> getSubscribedEvents() {
        return subscribedEvents;
    }

    public void subscribe(EventType eventType) {
        subscribedEvents.add(eventType);
    }

    public void unsubscribe(EventType eventType) {
        subscribedEvents.remove(eventType);
    }

    public String getUsername() {
        return username;
    }
}
