package library.exceptions;

public class BookUnavailableException extends  Exception {
    public BookUnavailableException(String title) {
        super("Книга '" + title + "' наразі недоступна для позичання.");
    }
}

