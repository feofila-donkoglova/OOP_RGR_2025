package library.state;

import library.dao.BorrowRecordDao;
import library.model.Book;
import library.model.Reader;

public interface BookState {
    void borrow(Book book, Reader reader, BorrowRecordDao dao);
    void returnBook(Book book, Reader reader, BorrowRecordDao dao);
    void reserve(Book book, Reader reader, BorrowRecordDao dao);
    String getStateName();
}
