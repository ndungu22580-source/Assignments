import java.util.Scanner;

class Student {
    private String studentId;
    private String name;
    private int[] marks;

    public Student(String studentId, String name, int[] marks) {
        this.studentId = studentId;
        this.name = name;
        this.marks = marks;
    }

    // Accessors
    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public int[] getMarks() { return marks; }

    // Mutators
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public void setName(String name) { this.name = name; }
    public void setMarks(int[] marks) { this.marks = marks; }

    public int calculateTotal() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    public double calculateAverage() {
        return calculateTotal() / 5.0; // Size is fixed at 5
    }

    public char findGrade() {
        double avg = calculateAverage();
        if (avg >= 80) return 'A';
        else if (avg >= 60) return 'B';
        else if (avg >= 50) return 'C';
        else return 'F';
    }

    public void displayStudentReport() {
        System.out.println("\n--- Student Report ---");
        System.out.println("ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Total Marks: " + calculateTotal());
        System.out.println("Average: " + calculateAverage());
        System.out.println("Grade: " + findGrade());
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student[] students = new Student[3];

        // 1. Input data for three students
        for (int i = 0; i < 3; i++) {
            System.out.println("\nEnter details for Student " + (i + 1) + ":");
            System.out.print("Enter Student ID: ");
            String id = sc.nextLine();
            
            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            int[] tempMarks = new int[5];
            System.out.println("Enter 5 marks:");
            for (int j = 0; j < 5; j++) {
                System.out.print("Mark " + (j + 1) + ": ");
                tempMarks[j] = sc.nextInt();
            }
            sc.nextLine(); // Consume the leftover newline character

            students[i] = new Student(id, name, tempMarks);
        }

        // 2. Display reports
        for (Student s : students) {
            s.displayStudentReport();
        }

        // 3. Identify highest average
        Student topStudent = students[0];
        for (int i = 1; i < students.length; i++) {
            if (students[i].calculateAverage() > topStudent.calculateAverage()) {
                topStudent = students[i];
            }
        }

        System.out.println("\n***********************************");
        System.out.println("TOP PERFORMER: " + topStudent.getName());
        System.out.println("Highest Average: " + topStudent.calculateAverage());
        System.out.println("***********************************");
        
        sc.close();
    }
}
