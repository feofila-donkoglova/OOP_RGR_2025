package library.service;

import library.exceptions.BookUnavailableException;
import library.exceptions.MaxLoanLimitExceededException;
import library.model.Reader;
import library.model.Book;
import library.model.BorrowRecord;
import library.dao.BorrowRecordDao;

import java.time.LocalDate;

public class BorrowService {

    private final BorrowRecordDao borrowRecordDao;

    public BorrowService(BorrowRecordDao borrowRecordDao) {
        this.borrowRecordDao = borrowRecordDao;
    }

    public BorrowService() {
        this.borrowRecordDao = BorrowRecordDao.getInstance();
    }

    private static final int MAX_LOANS = 5;

    public void borrowBook(Book book, Reader reader)
            throws BookUnavailableException, MaxLoanLimitExceededException {
        borrowBook(book, reader, 14);
    }

    public void borrowBook(Book book, Reader reader, int daysNumber)
            throws BookUnavailableException, MaxLoanLimitExceededException {

        if (borrowRecordDao.countBorrowedBooksByReader(reader) >= MAX_LOANS) {
            throw new MaxLoanLimitExceededException(reader.getUsername(), MAX_LOANS);
        }

        if (!borrowRecordDao.isAvailable(book)) {
            throw new BookUnavailableException(book.getTitle());
        }

        borrowRecordDao.createBorrowRecord(reader, book, daysNumber);
        reader.incrementBooksBorrowed();
        System.out.println(reader.getUsername() + " позичив книгу: " + book.getTitle());
    }


    public void returnBook(Book book, Reader reader) {
        BorrowRecord record = borrowRecordDao.findNonReturnedRecord(reader, book);
        if (record != null) {
            record.returnBook();
            reader.decrementBooksBorrowed();
            System.out.println(reader.getUsername() + " успішно повернув книгу: " + book.getTitle());
        } else {
            System.out.println("Помилка: Активний запис про позику не знайдено.");
        }
    }


    public void showBorrowHistory() {
        System.out.println("\n=== ІСТОРІЯ ПОЗИЧЕНЬ ===");
        borrowRecordDao.getAllRecords().forEach(BorrowRecord::showRecord);
    }
}
