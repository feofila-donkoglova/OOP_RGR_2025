package library.state;

import library.model.Book;

public class ReservedState implements BookState {
    public void borrow(Book book) {
        System.out.println("Книга '" + book.getTitle() + "' зарезервована, не можна взяти.");
    }
    public void returnBook(Book book) {
        System.out.println("Книга '" + book.getTitle() + "' повернена та стала доступною.");
        book.setState(new AvailableState());
    }
    public void reserve(Book book) {
        System.out.println("Книга '" + book.getTitle() + "' вже зарезервована.");
    }
    public String getStateName() {
        return "Reserved";
    }


}
