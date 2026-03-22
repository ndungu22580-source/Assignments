import java.util.Scanner;

public class SmallestNumber {
    public static double findSmallest(double a, double b, double c) {
        return Math.min(a, Math.min(b, c));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Input the first number: ");
        double num1 = input.nextDouble();

        System.out.print("Input the second number: ");
        double num2 = input.nextDouble();

        System.out.print("Input the third number: ");
        double num3 = input.nextDouble();

        double smallest = findSmallest(num1, num2, num3);
        System.out.println("The smallest value is " + smallest);
    }
}
