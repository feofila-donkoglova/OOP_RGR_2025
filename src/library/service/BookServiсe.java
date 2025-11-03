package library.service;

import library.model.Book;
import library.model.Library;
import library.model.User;

public class BookServiсe {
    private User corerentUser;

    public BookServiсe(String username, String password) {
        corerentUser = new User(username, password);
    }

    public void addBook(Library library, Book book, User user) {
        library.addBook(book);
        System.out.println(user.getUsername() + " додав(ла) книгу: " + book.getTitle());
    }

    public void removeBook(Library library, Book book, User user) {
        library.removeBook(book);
        System.out.println(user.getUsername() + " видалив(ла) книгу: " + book.getTitle());
    }
}
