package library.model;

import library.state.BookState;
import library.state.AvailableState;

public class Book implements BookInterface{

    private boolean rare;
    private String title;
    private String author;
    private String genre;
    private int pageCount;
    private int year;
    private String bookId;
    private boolean available; // доступність книги
    private BookState state;   // поточний стан книги (State pattern)

    //КОНСТРУКТОР
    protected Book(BookBuilder builder) {
        this.title = builder.title;
        this.author = builder.author;
        this.genre = builder.genre;
        this.pageCount = builder.pageCount;
        this.year = builder.year;
        this.bookId = builder.bookId;
        this.available = true;
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
    public boolean isAvailable() { return available; }
    public BookState getState() { return state; }
    @Override
    public boolean isAvailableForBorrow() {
        return available && !rare;
    }
    public boolean isRare() { return rare; }

    //  SETTERS
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setPageCount(int pageCount) { this.pageCount = pageCount; }
    public void setYear(int year) { this.year = year; }
    public void setBookId(String bookId) { this.bookId = bookId; }
    public void setState(BookState state) { this.state = state; }
    public void setRare(boolean rare) { this.rare = rare; }

    //  МЕТОДИ ДЛЯ ПОЗИКИ
    public void borrow() {
        available = false;
        state.borrow(this);
    }
    public void returnBook() {
        available = true;
        state.returnBook(this);
    }
    public void reserve() {
        state.reserve(this);
    }


    public void displayInfo() {
        System.out.println("=== Книга ===");
        System.out.println("Назва: " + title);
        System.out.println("Автор: " + author);
        System.out.println("Жанр: " + genre);
        System.out.println("Сторінки: " + pageCount);
        System.out.println("Рік: " + year);
        System.out.println("Доступність: " + (available ? "Так" : "Ні"));
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

        public T setTitle(String title) { this.title = title; return (T) this; }
        public T setAuthor(String author) { this.author = author; return (T) this; }
        public T setGenre(String genre) { this.genre = genre; return (T) this; }
        public T setPageCount(int pageCount) { this.pageCount = pageCount; return (T) this; }
        public T setYear(int year) { this.year = year; return (T) this; }
        public T setBookId(String bookId) { this.bookId = bookId; return (T) this; }

        public Book build() {
            return new Book(this);
        }
    }
}
