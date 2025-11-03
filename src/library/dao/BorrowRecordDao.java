package library.dao;

import library.model.Reader;
import library.model.Book;
import library.model.BorrowRecord;

import java.util.ArrayList;
import java.util.List;

public class BorrowRecordDao {

    private List<BorrowRecord> records = new ArrayList<>();

    public List<BorrowRecord> getAllRecords() {
        return records;
    }

    // створюємо новий запис про позику
    public void createBorrowRecord(Reader reader, Book book, int daysNumber) {
        BorrowRecord record = new BorrowRecord(reader, book);
        // тут можна додати логіку по daysNumber, якщо треба
        records.add(record);
        System.out.println("Створено запис про позику: " + book.getTitle() + " для " + reader.getUsername());
    }

    // перевіряємо чи доступна книга
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
        List<BorrowRecord> nonReturned = new ArrayList<>();
        for (BorrowRecord record : records) {
            if (record.isReturned()) {
                nonReturned.add(record);
            }
        }
        return nonReturned;
    }

    //Повертає запис про конкретну позику книги користувачем
    public BorrowRecord findNonReturnedRecord(Reader reader, Book book) {
        return getNonReturnedRecords().stream()
                .filter(r -> r.getBook().equals(book) && r.getReader().equals(reader))
                .findFirst()
                .orElse(null);
    }


    //скільки книг користувач не повернув
    public int countBorrowedRecordsByReader(Reader reader) {
        int count = 0;
        for (BorrowRecord record : records) {
            if (record.getReader().equals(reader) && !record.isReturned()) {
                count++;
            }
        }
        return count;
    }

    public Book getBook() {
        return null;
    }

    public int countBorrowedBooksByReader(Reader reader) {
        int count = 0;
        for (BorrowRecord record : records) {
            if (record.getBook().equals(reader) && !record.isReturned()) {
                count++;
            }
        }
        return count;
    }
}
