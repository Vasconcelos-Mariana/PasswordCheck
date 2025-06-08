import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthSystem auth = new AuthSystem();

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("[1] Register");
            System.out.println("[2] Login");
            System.out.println("[0] Exit");
            System.out.print("Option: ");
            String option = scanner.nextLine();

            if (option.equals("1")) {
                System.out.print("Username: ");
                String username = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();
                auth.registerUser(username, password);
            }

            else if (option.equals("2")) {
                System.out.print("Username: ");
                String username = scanner.nextLine();

                User user = auth.getUser(username);

                if (user == null) {
                    System.out.println("User not found.");
                    continue;
                }

                if (user.isBlocked()) {
                    System.out.println("User is blocked.");
                    continue;
                }

                while (!user.isBlocked()) {
                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    boolean success = auth.loginUserAndReturnStatus(username, password);

                    if (success) break;

                    int remaining = AuthSystem.MAX_ATTEMPTS - user.getFailedAttempts();
                    System.out.println("Attempts remaining: " + remaining);

                    if (user.isBlocked()) {
                        System.out.println("User has been blocked.");
                    }
                }
            }

            else if (option.equals("0")) {
                System.out.println("Goodbye!");
                break;
            }

            else {
                System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}
