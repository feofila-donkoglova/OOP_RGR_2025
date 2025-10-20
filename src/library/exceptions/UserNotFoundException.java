package library.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String identifier) {
        super("Користувача з ідентифікатором '" + identifier + "' не знайдено.");
    }
}
