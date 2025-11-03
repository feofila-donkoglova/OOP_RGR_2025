package library.service;

import library.Users.Reader;
import library.dao.BorrowRecordDao;
import library.model.Book;
import library.model.BorrowRecord;
import library.model.Library;

import java.util.ArrayList;
import java.util.List;

public class ReaderService {
    private BorrowRecordDao borrowRecordDao;

    public ReaderService() {
        borrowRecordDao = new BorrowRecordDao();
    }

    public ReaderService(BorrowRecordDao borrowRecordDao) {
        this.borrowRecordDao = borrowRecordDao;
    }


    List<BorrowRecord> checkDueDate () {
        List<BorrowRecord> nonReturnedRecords = borrowRecordDao.getNonReturnedRecords();
        List<BorrowRecord> overdueRecords = new ArrayList<>();
        for (BorrowRecord borrowRecord : nonReturnedRecords) {
            if (borrowRecord.isOverdue()) {
                overdueRecords.add(borrowRecord);
            }
        }

        return overdueRecords;
    }

    public void borrowBook(Reader reader, Book book, int daysNumber) {
        if (borrowRecordDao.isAvailable(book)) {
            borrowRecordDao.createBorrowRecord (reader, book, daysNumber );

        } else {
            System.out.println("Книга вже позичена!");
        }
    }
   public int countBorrowedBooksByReader(Reader reader) {
        int count = 0;
        for  (BorrowRecord record : borrowRecordDao.getAllRecords()) {
            if (record.getReader().equals(reader) && !record.isReturned()) {
                count++;
            }
        }
        return count;
   }
    public int getBooksBorrowed(Reader reader) {
        return borrowRecordDao.countBorrowedBooksByReader(reader);
    }

}

