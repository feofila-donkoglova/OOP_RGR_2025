package library.model;

public class Book {
    private String title;
    private String author;
    private int year;
    private int pages;
    private boolean available;


    public Book(String title, String author, int year, int pages) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.available = true;
    }
// geuuers and setters

    public boolean isAvailable() {
        return this.available;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return title.equals(book.title) && author.equals(book.author);
    }
    public int hashCode() {
        return (title + author).hashCode();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public void borrowBook() {
        if (available) {
            available = false;
            System.out.println("Книгу позичено.");
        } else {
            System.out.println("Книга вже позичена. ");
        }
    }
    public void returnBook() {
        if (!available) {
            available = true;
            System.out.println("Книгу " + title + " успішно повернуто.");
        } else {
            System.out.println("Помилка: Книга " + title + " вже є в бібліотеці. ");
        }

    }
    public void displayInfo() {
        System.out.println(title + " — " + author + " (" + year + ")");
    }

    public void setAvailable(boolean b) {
    }

}
