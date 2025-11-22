package library.model;

import library.dao.BorrowRecordDao;
import library.state.BookState;
import library.state.AvailableState;

public class Book implements BookInterface{

    private final boolean rare;
    private final String title;
    private final String author;
    private final String genre;
    private final int pageCount;
    private final int year;
    private final String bookId;
    private BookState state;

    //КОНСТРУКТОР
    protected Book(BookBuilder builder) {
        this.title = builder.title;
        this.author = builder.author;
        this.genre = builder.genre;
        this.pageCount = builder.pageCount;
        this.year = builder.year;
        this.bookId = builder.bookId;
        this.rare = builder.rare;
        this.state = new AvailableState();
    }

    //  GETTERS
    @Override
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public int getPageCount() { return pageCount; }
    public int getYear() { return year; }
    public String getBookId() { return bookId; }
    public boolean isRare() { return rare; }
    public BookState getState() { return state; }

    @Override
    public boolean isAvailableForBorrow() {
        return (state instanceof AvailableState) && !rare;
    }

    //  SETTERS
    public void setState(BookState state) { this.state = state; }


    //  МЕТОДИ ДЛЯ ПОЗИКИ
    public void borrow(Reader reader, BorrowRecordDao dao) {
        state.borrow(this, reader, dao);
    }
    public void returnBook(Reader reader, BorrowRecordDao dao) {
        state.returnBook(this, reader, dao);
    }
    public void reserve(Reader reader, BorrowRecordDao dao) {
        state.reserve(this,  reader, dao);
    }

    public boolean isAvailable() {
        return state instanceof AvailableState;
    }

    public void displayInfo() {
        System.out.println("=== Книга ===");
        System.out.println("Назва: " + title);
        System.out.println("Автор: " + author);
        System.out.println("Жанр: " + genre);
        System.out.println("Сторінки: " + pageCount);
        System.out.println("Рік: " + year);
        System.out.println("Доступність: " + (isAvailableForBorrow() ? "Так" : "Ні"));
        System.out.println("Стан: " + state.getStateName());
        System.out.println("================");
    }

    //  BUILDER
    public static class BookBuilder<T extends BookBuilder<T>> {
        private String title;
        private String author;
        private String genre;
        private int pageCount;
        private int year;
        private String bookId;
        private boolean rare = false;

        public T setTitle(String title) { this.title = title; return (T) this; }
        public T setAuthor(String author) { this.author = author; return (T) this; }
        public T setGenre(String genre) { this.genre = genre; return (T) this; }
        public T setPageCount(int pageCount) { this.pageCount = pageCount; return (T) this; }
        public T setYear(int year) { this.year = year; return (T) this; }
        public T setBookId(String bookId) { this.bookId = bookId; return (T) this; }
        public T setRare(boolean rare) { this.rare = rare; return (T) this; }

        public Book build() {
            return new Book(this);
        }
    }
}
