
// Main authentication logic - manages registration, login, and user persistence

    
import java.io.*;
import java.util.*;


public class AuthSystem {

    private static final String FILE_PATH = "users.txt";            // File where user data is stored
    private static final int MAX_ATTEMPTS = 4;                      // Max login attempts before user is blocked

   
    private Map<String, User> users = new HashMap<>();              // Stores all users in memory (key = username)

    public AuthSystem() {loadUsers();}                              // Constructor: loads existing users from file

    
    private void loadUsers() {}                                     // Load users from the file into memory 

    private void saveUsers() {}                                     // Save users from memory into the file 

    public void registerUser(String username, String password) {}   // Register a new user if username doesn't exist

    
    public void loginUser(String username, String password) {}      // Login attempt: checks password and handles failed attempts
}


