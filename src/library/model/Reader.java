package library.model;

import library.notification.Subscriber;

public class Reader extends User implements Subscriber {
    private int booksBorrowed;

    public Reader(String username, String password) {
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
