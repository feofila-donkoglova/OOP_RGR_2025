package library.state;

import library.model.Book;

public class AvailableState implements BookState {
    public void borrow(Book book) {
        System.out.println("Книгу '" + book.getTitle() + "' взято.");
        book.setState(new BorrowedState());
    }

    public void returnBook(Book book) {
        System.out.println("Книга '" + book.getTitle() + "' вже на місці.");
    }

    public void reserve(Book book) {
        System.out.println("Книгу '" + book.getTitle() + "' зарезервовано.");
        book.setState(new ReservedState());
    }

    public String getStateName() {
        return "Available State";
    }
}

