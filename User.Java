
// User structure


// Private data saved by the user 
public class User {                 
    private String username;
    private String passwordHash;
    private int failedAttempts;
    private boolean blocked;


// Constructor - how to create a user
    public User(String username, String passwordHash, int failedAttempts, boolean blocked){
        this.username = username;
        this.passwordHash = passwordHash;
        this.failedAttempts = failedAttempts;
        this.blocked = blocked;
    }

// Getters and Setters
    public String getUsername () {return username;}
    public String getPasswordHash () {return passwordHash;}
    public int getFailedAttempts() {return failedAttempts;}
    public boolean isBlocked() {return blocked;}

    public void setFailedAttempts (int failedAttempts) {this.failedAttempts = failedAttempts;}
    public void setBlocked (boolean blocked) {this.blocked = blocked;}


// Saving the file 
    @Override
    public String toString() {return username + ";" + passwordHash + ";" + failedAttempts + ";" + blocked;}


// Reading the file 
    public static User fromString(String data) {
        String[] parts = data.split(";");
        return new User(
            parts[0],
            parts[1],
            Integer.parseInt(parts[2]),
            Boolean.parseBoolean(parts[3])
        );
    }
}