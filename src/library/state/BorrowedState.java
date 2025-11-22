package library.state;

import library.dao.BorrowRecordDao;
import library.model.Book;
import library.model.BorrowRecord;
import library.model.Reader;

public class BorrowedState implements BookState {
    @Override
    public void borrow(Book book, Reader  reader, BorrowRecordDao dao) {
        System.out.println("Книга '" + book.getTitle() + "' вже позичена.");
    }

    @Override
    public void returnBook(Book book, Reader  reader, BorrowRecordDao dao) {
        BorrowRecord record = dao.findNonReturnedRecord(reader, book);
        if (record != null) {
            record.returnBook();
            reader.decrementBooksBorrowed();
            System.out.println("Книга '" + book.getTitle() + "' повернена.");
            book.setState(new AvailableState());
        } else {
            System.out.println("Помилка повернення: Активний запис не знайдено.");
        }
    }

    @Override
    public void reserve(Book book, Reader  reader, BorrowRecordDao dao) {
        System.out.println("Книга '" + book.getTitle() + "' позичена, але можна зарезервувати.");
        book.setState(new ReservedState());
    }

    @Override
    public String getStateName() {
        return "Borrowed";
    }

}
