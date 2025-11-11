package library.state;

import library.model.Book;

public class BorrowedState implements BookState {
    public void borrow(Book book) {
        System.out.println("Книга '" + book.getTitle() + "' вже позичена.");
    }

    @Override
    public void returnBook(Book book) {
        System.out.println("Книга '" + book.getTitle() + "' повернена.");
        book.setState(new AvailableState());
    }

    @Override
    public void reserve(Book book) {
        System.out.println("Книга '" + book.getTitle() + "' позичена, але можна зарезервувати.");
        book.setState(new ReservedState());
    }

    @Override
    public String getStateName() {
        return "Borrowed";
    }

}
