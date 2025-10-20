package library.service ;

import library.Users.Reader;
import library.model.Book;
import library.model.BorrowRecord;
import java.util.ArrayList;
import java.util.List;


public class BorrowService {

    private List<BorrowRecord> records = new ArrayList<>();

    public void borrowBook(Book book, Reader reader) {
        book.setAvailable(false);
        records.add(new BorrowRecord(reader, book));
        System.out.println(reader.getUsername() + " позичив книгу: " + book.getTitle());
    }

    public void showBorrowHistory() {
        System.out.println("\n=== ІСТОРІЯ ПОЗИЧЕНЬ ===");
        for (BorrowRecord record : records) {
            record.showRecord();
        }

    }
}
