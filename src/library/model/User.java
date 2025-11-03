package library.model;

public class User {
    private final String username;
    private final String password;

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }


    public String getUsername() {
        return username;
    }
}



