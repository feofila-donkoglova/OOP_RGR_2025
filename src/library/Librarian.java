package library;

public class Librarian extends User {
    public Librarian(String username, String password) {
        super(username, password);
    }
    public void addBook(Library library, Book book) {
        library.addBook(book);
        System.out.println(getUsername() + " додав(ла) книгу: " + book.getTitle());
    }

    public void removeBook(Library library, Book book) {
        library.removeBook(book);
        System.out.println(getUsername() + " видалив(ла) книгу: " + book.getTitle());
    }
}
