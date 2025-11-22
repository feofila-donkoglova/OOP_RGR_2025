package library.state;

import library.dao.BorrowRecordDao;
import library.model.Book;
import library.model.Reader;

public class AvailableState implements BookState {
    @Override
    public void borrow(Book book, Reader reader, BorrowRecordDao dao) {
        System.out.println("Книгу '" + book.getTitle() + "' взято.");
        dao.createBorrowRecord(reader, book, 14);
        book.setState(new BorrowedState());
        reader.incrementBooksBorrowed();
    }

    public void returnBook(Book book, Reader reader, BorrowRecordDao dao) {
        System.out.println("Книга '" + book.getTitle() + "' вже на місці.");
    }

    public void reserve(Book book, Reader reader, BorrowRecordDao dao) {
        System.out.println("Книгу '" + book.getTitle() + "' зарезервовано.");
        book.setState(new ReservedState());
    }

    public String getStateName() {
        return "Available State";
    }
}

