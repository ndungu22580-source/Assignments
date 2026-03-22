import java.util.Scanner;

public class PasswordValidator {

    public static boolean isValid(String password) {
        if (password.length() < 10) {
            return false;
        }

        int digitCount = 0;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (!Character.isLetterOrDigit(ch)) {
                return false;
            }

            if (Character.isDigit(ch)) {
                digitCount++;
            }
        }

        return digitCount >= 2;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("1. A password must have at least ten characters.");
        System.out.println("2. A password consists of only letters and digits.");
        System.out.println("3. A password must contain at least two digits.");

        System.out.print("Input a password: ");
        String password = input.nextLine();

        if (isValid(password)) {
            System.out.println("Password is valid: " + password);
        } else {
            System.out.println("Password is invalid.");
        }
    }
}
