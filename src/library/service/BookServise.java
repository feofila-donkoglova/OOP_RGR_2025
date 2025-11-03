package library.service;

import library.model.Book;
import library.model.Library;
import library.model.User;

public class BookServise {

    public void addBook(Library library, Book book) {
        library.addBook(book);
        System.out.println(getUsername() + " додав(ла) книгу: " + book.getTitle());
    }

    public void removeBook(Library library, Book book) {
        library.removeBook(book);
        System.out.println(getUsername() + " видалив(ла) книгу: " + book.getTitle());
    }
}
