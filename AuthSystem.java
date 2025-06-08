
// Main authentication logic - manages registration, login, and user persistence

    
import java.io.*;
import java.util.*;


public class AuthSystem {

    private static final String FILE_PATH = "users.txt";            // File where user data is stored
    private static final int MAX_ATTEMPTS = 4;                      // Max login attempts before user is blocked

   
    private Map<String, User> users = new HashMap<>();              // Stores all users in memory (key = username)

    public AuthSystem() {loadUsers();}                              // Constructor: loads existing users from file

    
    private void loadUsers() {
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
    public void registerUser(String username, String password) {}   // Register a new user if username doesn't exist

    
    public void loginUser(String username, String password) {}      // Login attempt: checks password and handles failed attempts
}


