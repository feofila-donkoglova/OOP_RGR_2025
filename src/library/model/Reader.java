package library.model;

public class Reader extends User {
    private int booksBorrowed;

    public Reader(String password, String username) {
        super(password, username);
        this.booksBorrowed = 0;

    }
    public void borrowBook(Library library, Book book) {
        if (book.isAvailable()) {
            library.borrowBook(book, this);
            booksBorrowed++;
        } else {
            System.out.println("Книга вже позичена!");
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }


}
