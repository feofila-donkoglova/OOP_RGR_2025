package library.service;

import library.model.Book;
import library.model.Librarian;
import library.model.Library;
import library.model.User;

public class BookService  {
    private User currentUser;

    public BookService(String username, String password) {
        currentUser = new User(username, password);
    }

    public void addBook(Library library, Book book, User user) {
        if (user instanceof Librarian) {
            library.addBook(book);
            System.out.println(user.getUsername() + " додав(ла) книгу: " + book.getTitle());
        } else {
            System.out.println("Помилка: Недостатньо прав для додавання книг.");
        }

    }

    public void removeBook(Library library, Book book, User user) {
        if (user instanceof Librarian) {
            library.removeBook(book);
            System.out.println(user.getUsername() + " видалив(ла) книгу: " + book.getTitle());
        } else {
            System.out.println("Помилка: Недостатньо прав для видалення книг.");
        }
    }
}
