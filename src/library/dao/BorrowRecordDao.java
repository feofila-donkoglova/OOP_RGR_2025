package library.dao;

import library.Users.Reader;
import library.model.Book;
import library.model.BorrowRecord;

import java.util.List;

public class BorrowRecordDao {
    private Book book;

    public List<BorrowRecord> getAllRecords() {
        //TODO дістати всі рекорди з дати
        throw  new UnsupportedOperationException("Not supported yet.") ;
    }
    public List<BorrowRecord> getNonReturnedRecords() {
        //TODO дістати всі рекорди з дати
        throw  new UnsupportedOperationException("Not supported yet.") ;
    }

    public void createBorrowRecord(Reader reader, Book book, int daysNumber) {
    }

    public boolean isAvailable(Book book) {
    }
    public Book getBook() {
        return this.book;
    }

    public int countBorrowedBooksByReader(Reader reader) {
        

    }
}
