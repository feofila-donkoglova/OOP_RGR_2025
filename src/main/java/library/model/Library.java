package library.model;

import library.exceptions.UserNotFoundException;
import library.notification.EventType;
import library.notification.Publisher;
import library.repository.BookRepository;
import library.service.BorrowService;
import library.exceptions.BookUnavailableException;
import library.exceptions.MaxLoanLimitExceededException;

import java.util.ArrayList;
import java.util.List;

public class Library extends Publisher {
    private static Library instance;
    private BookRepository bookRepository;
    private List<User> users;
    private BorrowService borrowService; // сервіс для позик

    private Library() {
        this.bookRepository = new BookRepository();
        this.users = new ArrayList<>();
        this.borrowService = new BorrowService(); // створюємо сервіс
    }

    public static Library getInstance() {
        if (instance == null) instance = new Library();
        return instance;
    }

    public void addBook(Book book) {
        bookRepository.addBook(book);
        System.out.println("Додано книгу: " + book.getTitle());
        notifySubscribers(EventType.NEW_BOOK, "Додано нову книгу: " + book.getTitle());
    }

    // Делегуємо логіку позики до BorrowService
    public void borrowBook(Book book, Reader reader)
            throws BookUnavailableException, MaxLoanLimitExceededException{
        borrowService.borrowBook(book, reader);
        notifySubscribers(EventType.BOOK_BORROWED, "Книгу '" + book.getTitle() + "' позичено.");
    }

    public void returnBook(Book book, Reader reader) {
        borrowService.returnBook(book, reader);
        notifySubscribers(EventType.BOOK_RETURNED, "Книгу '" + book.getTitle() + "' повернуто.");
    }

    public void reserve(Book book) {
        System.out.println("Метод reserve ще не реалізовано. Резервування можна додати через BorrowService або BookState.");
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
        return bookRepository.getAllBooks();
    }

    public void displayAllBooks() {
        getBookRepository().getAllBooks().forEach(Book::displayInfo);
    }

    public void removeBook(Book book) {
        bookRepository.removeBook(book);
        System.out.println("Видалено книгу: " + book.getTitle());
    }

    public User findUser(String username) throws UserNotFoundException {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(username));
    }
}
