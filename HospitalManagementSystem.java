import java.util.Scanner;

// Class Patient representing individual patients
class Patient {
    private String patientId;
    private String name;
    private int age;
    private String diagnosis;
    private boolean admitted;

    // Constructor
    public Patient(String patientId, String name, int age, String diagnosis) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
        this.admitted = false; // Default state
    }

    // Accessors (Getters)
    public String getPatientId() { return patientId; }
    public String getName() { return name; }
    public boolean isAdmitted() { return admitted; }

    // Mutators (Methods to modify state)
    public void admit() { this.admitted = true; }
    public void discharge() { this.admitted = false; }
    public void updateDiagnosis(String newDiagnosis) { this.diagnosis = newDiagnosis; }

    public void displayPatientInfo() {
        String status = admitted ? "Admitted" : "Discharged";
        System.out.println("[" + status + "] ID: " + patientId + " | Name: " + name + 
                           " | Age: " + age + " | Diagnosis: " + diagnosis);
    }
}

// Class HospitalWard to manage an array of Patients
class HospitalWard {
    private Patient[] patients;
    private int count;

    public HospitalWard() {
        this.patients = new Patient[20]; // Maximum 20 patients as per requirements
        this.count = 0;
    }

    public void admitPatient(Patient p) {
        if (count < 20) {
            p.admit();
            patients[count] = p;
            count++;
            System.out.println("Patient " + p.getName() + " admitted successfully.");
        } else {
            System.out.println("Ward is full! Cannot admit more patients.");
        }
    }

    public void dischargePatient(String patientId) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (patients[i].getPatientId().equalsIgnoreCase(patientId)) {
                patients[i].discharge();
                System.out.println("Patient " + patients[i].getName() + " has been discharged.");
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Patient ID not found.");
    }

    public void listAdmittedPatients() {
        System.out.println("\n--- Currently Admitted Patients ---");
        boolean anyAdmitted = false;
        for (int i = 0; i < count; i++) {
            if (patients[i].isAdmitted()) {
                patients[i].displayPatientInfo();
                anyAdmitted = true;
            }
        }
        if (!anyAdmitted) System.out.println("No patients are currently admitted.");
    }

    public Patient findPatient(String patientId) {
        for (int i = 0; i < count; i++) {
            if (patients[i].getPatientId().equalsIgnoreCase(patientId)) {
                return patients[i];
            }
        }
        return null;
    }
}

// Main Class
public class HospitalManagementSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        HospitalWard ward = new HospitalWard();

        // Task 1: Create 4 Patient objects
        Patient p1 = new Patient("P001", "John Doe", 45, "Hypertension");
        Patient p2 = new Patient("P002", "Jane Smith", 30, "Appendicitis");
        Patient p3 = new Patient("P003", "Mark Evans", 12, "Fracture");
        Patient p4 = new Patient("P004", "Lucy Hale", 65, "Pneumonia");

        // Task 2: Admit 3 patients to the ward
        ward.admitPatient(p1);
        ward.admitPatient(p2);
        ward.admitPatient(p3);

        // Task 3: Discharge one and update another's diagnosis
        System.out.println("\n--- Updating Patient Records ---");
        ward.dischargePatient("P001"); // Discharge John Doe
        
        Patient toUpdate = ward.findPatient("P002");
        if (toUpdate != null) {
            toUpdate.updateDiagnosis("Post-Surgery Recovery");
            System.out.println("Updated diagnosis for Jane Smith.");
        }

        // Task 4: Display all currently admitted patients
        ward.listAdmittedPatients();

        // Optional: Interactive part using the Scanner
        System.out.println("\nWould you like to admit the 4th patient (Lucy Hale)? (yes/no)");
        String choice = input.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            ward.admitPatient(p4);
            ward.listAdmittedPatients();
        }

        input.close();
        System.out.println("System closed.");
    }
}
