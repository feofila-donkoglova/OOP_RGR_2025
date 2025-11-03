package library.model;

public class User {
    private final String username;
    private final String password;

    public User(String username, String password) {
        this.password = password;
        this.username = username;
    }


    public String getUsername() {
        return username;
    }
}



