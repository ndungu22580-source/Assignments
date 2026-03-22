import java.io.*;

// i. Define a structure to hold student information
class Student implements Serializable {
    String name;
    String regNumber;
    double marks;

    public Student(String name, String regNumber, double marks) {
        this.name = name;
        this.regNumber = regNumber;
        this.marks = marks;
    }
}

public class ReadStudentResults {
    public static void main(String[] args) {
        String fileName = "results.dat";

        // ii. Read all student records from the binary file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            
            System.out.println("--- Student Examination Results ---");
            while (true) {
                try {
                    Student s = (Student) ois.readObject();
                    // iii. Display name and marks
                    System.out.println("Name: " + s.name + " | Marks: " + s.marks);
                } catch (EOFException e) {
                    // End of file reached
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found. Please create 'results.dat' first.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
