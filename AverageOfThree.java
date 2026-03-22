import java.util.Scanner;

public class AverageOfThree {
    public static double computeAverage(double a, double b, double c) {
        return (a + b + c) / 3;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Input the first number: ");
        double num1 = input.nextDouble();

        System.out.print("Input the second number: ");
        double num2 = input.nextDouble();

        System.out.print("Input the third number: ");
        double num3 = input.nextDouble();

        double average = computeAverage(num1, num2, num3);
        System.out.println("The average value is " + average);
    }
}
