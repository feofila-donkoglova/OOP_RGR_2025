package library.service;

import library.model.Reader;
import library.dao.BorrowRecordDao;
import library.model.BorrowRecord;

import java.util.List;

public class ReaderService {
    private final BorrowRecordDao borrowRecordDao;

    public ReaderService(BorrowRecordDao borrowRecordDao) {
        this.borrowRecordDao = borrowRecordDao;
    }

    public ReaderService() {
        this(BorrowRecordDao.getInstance());
    }

    public List<BorrowRecord> getNonReturnedRecords() {
        return borrowRecordDao.getNonReturnedRecords();
    }

    // Повертає прострочені записи
    public List<BorrowRecord> checkDueDate() {
        return getNonReturnedRecords().stream()
                .filter(BorrowRecord::isOverdue)
                .toList();
    }

    // Повертає кількість книг, які зараз позичив читач
    public int getBooksBorrowed(Reader reader) {
        return borrowRecordDao.countBorrowedBooksByReader(reader);
    }
}
