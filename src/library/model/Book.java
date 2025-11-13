package library.model;

import library.state.*;

public class Book {
    private String title;
    private String author;
    private int year;
    private int pages;
    private BookState state;
    private boolean available;

    public Book(String title, String author, int year, int pages) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.state = new AvailableState();
    }

    public void borrowBook() { state.borrow(this); }
    public void returnBook() { state.returnBook(this); }
    public void reserveBook() { state.reserve(this); }
    public boolean isAvailable() { return state  instanceof AvailableState; }

    public void setState(BookState state) { this.state = state; }
    public String getStateName() { return state.getStateName(); }

    public void displayInfo() {
        System.out.println(title + " — " + author + " (" + year + ") — " + getStateName());
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public int getPages() { return pages; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return title.equals(book.title) && author.equals(book.author);
    }

    @Override
    public int hashCode() { return (title + author).hashCode(); }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void reserve(Book book) {
        System.out.println("Не можна зарезервувати книгу '" + book.getTitle() + "', вона вже позичена.");
    }

}
