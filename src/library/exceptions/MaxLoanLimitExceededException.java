package library.exceptions;

public class MaxLoanLimitExceededException extends Exception {
    public MaxLoanLimitExceededException(String username, int limit) {
        super("Користувач " + username + " перевищив ліміт позичених книг (" + limit + ").");
    }
}
