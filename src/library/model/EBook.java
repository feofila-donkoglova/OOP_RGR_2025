package library.model;

public class EBook extends Book {
    private String downloadLink;
    private String format;

    public EBook(String title, String author, int year, int pages, String downloadLink, String format) {
        super(title, author, year, pages);
        this.downloadLink = downloadLink;
        this.format = format;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public String getFormat() {
        return format;
    }
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Формат: " + format + ", посилання: " + downloadLink);
    }
}
