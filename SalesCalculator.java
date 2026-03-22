import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SalesCalculator {
    public static void main(String[] args) {
        File file = new File("sales.txt");
        double totalSales = 0.0;

        // iii. Using try-with-resources ensures the file is properly closed
        try (Scanner fileReader = new Scanner(file)) {
            
            // i. Read all transactions
            while (fileReader.hasNextDouble()) {
                double transaction = fileReader.nextDouble();
                totalSales += transaction;
            }

            // ii. Display total sales
            System.out.printf("Total sales for the day: $%.2f%n", totalSales);

        } catch (FileNotFoundException e) {
            System.out.println("Error: sales.txt not found. Please ensure the file exists.");
        }
    }
}
