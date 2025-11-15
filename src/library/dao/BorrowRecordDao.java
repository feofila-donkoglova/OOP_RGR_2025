package library.dao;

import library.model.Reader;
import library.model.Book;
import library.model.BorrowRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BorrowRecordDao {

    private static BorrowRecordDao instance;
    private final List<BorrowRecord> records = new ArrayList<>();

    private BorrowRecordDao() {}

    public static BorrowRecordDao getInstance() {
        if (instance == null) {
            instance = new BorrowRecordDao();
        }
        return instance;
    }

    public List<BorrowRecord> getAllRecords() {
        return records;
    }

    // створюємо новий запис про позику
    public void createBorrowRecord(Reader reader, Book book, int daysNumber) {
        BorrowRecord record = new BorrowRecord(reader, book, LocalDate.now(), null, daysNumber);
        records.add(record);
        System.out.println("Створено запис про позику: " + book.getTitle() + " для " + reader.getUsername());
    }

    // перевіряємо чи доступна книга (тобто немає активного запису)
    public boolean isAvailable(Book book) {
        for (BorrowRecord record : records) {
            if (record.getBook().equals(book) && !record.isReturned()) {
                return false;
            }
        }
        return true;
    }

    // отримати записи, де книга ще не повернена
    public List<BorrowRecord> getNonReturnedRecords() {
        return records.stream()
                .filter(r -> !r.isReturned())
                .collect(Collectors.toList());
    }

    // Повертає запис про конкретну позику книги користувачем (активний)
    public BorrowRecord findNonReturnedRecord(Reader reader, Book book) {
        return getNonReturnedRecords().stream()
                .filter(r -> r.getBook().equals(book) && r.getReader().equals(reader))
                .findFirst()
                .orElse(null);
    }

    //скільки книг користувач зараз не повернув
    public int countBorrowedBooksByReader(Reader reader) {
        int count = 0;
        for (BorrowRecord record : records) {
            if (record.getReader().equals(reader) && !record.isReturned()) {
                count++;
            }
        }
        return count;
    }

}
