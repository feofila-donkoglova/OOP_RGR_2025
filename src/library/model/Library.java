package library.model;

import java.util.ArrayList;
import java.util.List;

// GRASP Information Expert

public class Library {
    private List <Book> books;
    private List <User> users;
    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }
    public void addBook(Book book) {
        // GRASP creator
        books.add(book);
        System.out.println("Додано книгу: " + book.getTitle());

    }

    public void removeBook(Book book) {
        if (books.remove(book)) {
            System.out.println("Книгу"+ book.getTitle() +" видалено" );
        }
        else {
            System.out.println("Книгу \"" + book.getTitle() + "\" не знайдено у бібліотеці.");
        }


    }

    public void registerUser( User user) {
        users.add(user);
        System.out.println("Зареєстровано користувачку: " + user.getUsername());
    }

    public Book findBookByTitle(String title) {
        // GRY  (findBookByTitle логіка не дуюблюється у borrowBook
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        System.out.printf("\"Книгу '\" + title + \"' не знайдено.\"");
        return null;
    }
    public void displayAllBooks() {
        System.out.println("Усі книги бібліотеки:");
        for (Book book : books) {
            book.displayInfo();
            System.out.println("   Доступна: " + book.isAvailable());
        }
    }
    public List<Book> getBooks() {
        return books;
    }
}

