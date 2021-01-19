/**
 * A class to create Cashier object
 * @author Siva Aditya
 */

import java.util.Objects;

public class Cashier {

    private String username;
    private String password;
    private String fullName;

    // Default constructor
    public Cashier() { this("user", "user", "user"); }

    // Constructor with parameters
    public Cashier(String user, String pass, String name) {
        setUsername(user);
        setPassword(pass);
        setFullName(name);
    }

    // Getter setter
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cashier cashier = (Cashier) o;

        // Username is unique
        return Objects.equals(username, cashier.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "username ["+username+"]; " +
                "password ["+password+"]; " +
                "full name ["+fullName+"];";
    }
}
