package library.service;

import library.model.Reader;
import library.dao.BorrowRecordDao;
import library.model.Book;
import library.model.BorrowRecord;

import java.util.List;

public class ReaderService {
    private BorrowRecordDao borrowRecordDao;

    public ReaderService() {
        this.borrowRecordDao = new BorrowRecordDao();
    }
    public ReaderService(BorrowRecordDao borrowRecordDao) {
        this.borrowRecordDao = borrowRecordDao;
    }
    // Повертає всі невірно повернені книги
    public List<BorrowRecord> getNonReturnedRecords() {
        return borrowRecordDao.getNonReturnedRecords();
    }
    // Повертає прострочені записи
    List<BorrowRecord> checkDueDate () {
        return getNonReturnedRecords().stream()
                .filter(BorrowRecord::isOverdue)
                .toList();
    }
    // Позика книги
    public void borrowBook(Reader reader, Book book, int daysNumber) {
        if (borrowRecordDao.isAvailable(book)) {
            borrowRecordDao.createBorrowRecord (reader, book, daysNumber );
            book.setAvailable(false);
            System.out.println(reader.getUsername() + " позичив(ла) книгу: " + book.getTitle());
        } else {
            System.out.println("Книга вже позичена!");
        }
    }
    // Повернення книги
    public void returnBook(Reader reader, Book book) {
        BorrowRecord record = getNonReturnedRecords().stream()
                .filter(r -> r.getBook().equals(book) && r.getReader().equals(reader))
                .findFirst()
                .orElse(null);

        if (record != null) {
            record.returnBook();
            book.setAvailable(true);
            System.out.println(reader.getUsername() + " повернув(ла) книгу: " + book.getTitle());
        } else {
            System.out.println("Помилка! Активний запис про позику не знайдено.");
        }
    }


    // Показує історію всіх позик
    public void showBorrowHistory() {
        borrowRecordDao.getAllRecords().forEach(BorrowRecord::showRecord);
    }
    // Повертає кількість книг, які зараз позичив читач
    public int getBooksBorrowed(Reader reader) {
        return borrowRecordDao.countBorrowedBooksByReader(reader);
    }
}

