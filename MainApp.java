import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 1. Accept user input
        System.out.print("Enter Student Name: ");
        String name = input.nextLine();

        System.out.print("Enter Student Marks: ");
        double marks = input.nextDouble();

        // 2. Create Student object
        Student student = new Student(name, marks);

        // 3. Use GradeCalculator to compute the grade
        GradeCalculator calculator = new GradeCalculator();
        String finalGrade = calculator.calculateGrade(student.marks);

        // 4. Display all details
        System.out.println("\n--- Student Report ---");
        student.displayDetails();
        System.out.println("Final Grade: " + finalGrade);

        input.close();
    }
}
