package library.notification;

import java.util.*;

public class UserSubscriber {
    private String name;
    private Set<EventType> subscribedEvents = new HashSet<>();
    private List<String> notifications = new ArrayList<>();

    public UserSubscriber(String name) {
        this.name = name;
    }
    public void update(EventType eventType, String message) {
        String notification = "[" + eventType + "] " + message;
        notifications.add(notification);
        System.out.println(name + " отримав повідомлення: " + notification);
    }
    public Set<EventType> getSubscribedEvents() {
        return subscribedEvents;
    }

    public void subscribe(EventType eventType) {
        subscribedEvents.add(eventType);
    }
    public void unsubscribe(EventType eventType) {
        subscribedEvents.remove(eventType);
    }
    public void showNotifications() {
        System.out.println("\nПовідомлення для " + name + ":");
        if (notifications.isEmpty()) {
            System.out.println(" (немає нових повідомлень)");
        } else {
            for (String note: notifications) {
                System.out.println(" - " + note);
            }
        }
    }

    public String getName() {
        return name;
    }
}
