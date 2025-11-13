package library.model;

import library.notification.*;
import library.repository.BookRepository;
import library.state.*;

import java.util.ArrayList;
import java.util.List;

public class Library extends Publisher {
    private static Library instance;
    private BookRepository bookRepository;
    private List<User> users;

    private Library() {
        this.bookRepository = new BookRepository();
        this.users = new ArrayList<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addBook(Book book) {
        bookRepository.addBook(book);
        System.out.println("Додано книгу: " + book.getTitle());
        notifySubscribers(EventType.NEW_BOOK, "Додано нову книгу: " + book.getTitle());
    }

    public void borrowBook(Book book) {
        if (book != null && book.isAvailable()) {
            book.returnBook();
            notifySubscribers(EventType.BOOK_RETURNED, "Книгу '" + book.getTitle() + "' повернуто.");
        }
    }

    public void reserve(Book book) {
        System.out.println("Книга '" + book.getTitle() + "' вже зарезервована.");
    }

    public void announceEvent(String event, EventType eventType) {
        notifySubscribers(eventType, "Подія: " + event);
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void registerUser(User user) {
        users.add(user);
        System.out.println("Зареєстровано користувачку: " + user.getUsername());
    }

    public List<User> getUsers() {
        return users;
    }
    public List<Book> getBooks() {
        return books();
    }

    private List<Book> books() {
        return bookRepository.getAllBooks();
    }

    public void displayAllBooks() {
        getBookRepository().getAllBooks().forEach(Book::displayInfo);
    }

    public void removeBook(Book book) {
    }


}
