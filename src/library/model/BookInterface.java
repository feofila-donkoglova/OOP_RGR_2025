package library.model;

public interface BookInterface {
    String getTitle();
    String getAuthor();
    void displayInfo();
    boolean isAvailableForBorrow();
}
