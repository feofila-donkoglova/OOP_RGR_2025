package library.service;

import library.model.Book;
import library.model.BorrowRecord;
import library.model.Reader;

import java.util.ArrayList;
import java.util.List;

public class BorrowRecordService {
    private List<BorrowRecord> records = new ArrayList<>();

    public void createBorrowRecord(Reader reader, Book book) {
        BorrowRecord record = new BorrowRecord(reader, book);
        records.add(record);
        System.out.println("Додано новий запис про позику.");
    }

    public List<BorrowRecord> getAllRecords() {
        return records;
    }
}
