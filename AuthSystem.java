
// Main authentication logic - manages registration, login, and user persistence

    
import java.io.*;
import java.util.*;


public class AuthSystem {

    private static final String FILE_PATH = "users.txt";            // File where user data is stored
    private static final int MAX_ATTEMPTS = 4;                      // Max login attempts before user is blocked

   
    private Map<String, User> users = new HashMap<>();              // Stores all users in memory (key = username)

    public AuthSystem() {loadUsers();}                              // Constructor: loads existing users from file

    
    private void loadUsers() {                                      //Loading the info from users
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {           // Reading every line of the file
            User user = User.fromString(line);                     // Converting in object User
            users.put(user.getUsername(), user);}                  // Adding to the map of users
        } 
        
        catch (FileNotFoundException e) {                          // If not data = News data 
        System.out.println(
            "No user file found. Starting with empty user list.");
        } 
        
        catch (IOException e) {                                    // Other possible errors
        System.out.println(
        "Error reading user file: " + e.getMessage());
        }
}

    private void saveUsers() {                                      // Save users from memory into the file 
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (User user : users.values()) {writer.println(user.toString());}
        } 
        
        catch (IOException e) {
        System.out.println("Error saving users: " + e.getMessage());
    }
}                            

    public void registerUser(String username, String password) {     // Register a new user if username doesn't exist
        if (users.containsKey(username)) {
            System.out.println("Username already exists.");
            return;}
    
        String passwordHash = HashUtil.hash(password);              // Hash the password using HashUtil   

        User newUser = new User(username, passwordHash,             // Create a new user with 0 failed attempts and not blocked
        0, false); 

        users.put(username, newUser);                               // Add to memory

        saveUsers();

        System.out.println("User registered successfully.");
}

    public void loginUser(String username, String password) {        // Login attempt: checks password and handles failed attempts
        User user = users.get(username);

    
    if (user == null) {System.out.println("User not found.");return;}                   // Case 1: User does not exist
    
    if (user.isBlocked()) {
        System.out.println("User is blocked due to too many failed attempts.");         // Case 2: User is blocked 
        return;}

    
    String inputHash = HashUtil.hash(password);                                           // Case 3: 1 and 2 okay , Verify password hash

    if (user.getPasswordHash().equals(inputHash)) {
        System.out.println("Login successful.");
        user.setFailedAttempts(0);          // resetting the failed attemmps after sucessful login
    } 
    
    else {
        System.out.println("Incorrect password.");
        user.setFailedAttempts(user.getFailedAttempts() + 1);

        
    if (user.getFailedAttempts() >= MAX_ATTEMPTS) {       // Block after X attemps
        user.setBlocked(true);
        System.out.println("User has been blocked due to too many failed attempts.");}
    }
    saveUsers();
    }
}
