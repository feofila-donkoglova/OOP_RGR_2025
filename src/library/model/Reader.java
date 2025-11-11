package library.model;

import library.notification.EventType;
import library.notification.Subscriber;

import java.util.Set;

public class Reader extends User implements Subscriber {
    private int booksBorrowed;

    public Reader(String password, String username) {
        super(username, password);
        this.booksBorrowed = 0;

    }
    public int getBooksBorrowed(){
        return booksBorrowed;
    }

    public void incrementBooksBorrowed(){
        booksBorrowed++;
    }
    public void decrementBooksBorrowed(){
        if (booksBorrowed > 0) booksBorrowed--;
    }
    public void update(String message) {
        System.out.println(getUsername() + " отримав сповіщення: " + message);
    }
}
