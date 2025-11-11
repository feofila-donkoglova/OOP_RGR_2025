package library.model;

import library.notification.*;

import java.util.Set;

public class User implements Subscriber {
    private  String username;
    private  String password;
    private Set<EventType> subscribedEvents;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void update(EventType eventType, String message) {
        System.out.println("[" + username + "] отримано повідомлення (" + eventType + "): " + message);
    }

    public Set<EventType> getSubscribedEvents() {
        return subscribedEvents;
    }

    public String getUsername() {
        return username;
    }
}



