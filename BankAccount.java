public class BankAccount {
    // 2. All attributes declared as private
    private String accountNumber;
    private String accountHolder;
    private double balance;

    // A constructor to initialize all attributes
    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // 3 & iv. Accessor (Getter) and Mutator (Setter) methods
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accNum) { this.accountNumber = accNum; }

    public String getAccountHolder() { return accountHolder; }
    public void setAccountHolder(String holder) { this.accountHolder = holder; }

    public double getBalance() { return balance; }
    public void setBalance(double bal) { this.balance = bal; }

    // deposit(double amount) – adds amount to balance
    public void deposit(double amount) {
        balance += amount;
    }

    // withdraw(double amount) – subtracts if sufficient balance exists
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    // checkBalance() – returns current balance
    public double checkBalance() {
        return balance;
    }

    // displayAccountInfo() – displays all details
    public void displayAccountInfo() {
        System.out.println("ID: " + accountNumber + " | Name: " + accountHolder + " | Balance: $" + balance);
    }

    // --- MAIN METHOD (Task Execution) ---
    public static void main(String[] args) {
        // i. Create two BankAccount objects
        BankAccount acc1 = new BankAccount("BNK001", "Alice Smith", 1000.0);
        BankAccount acc2 = new BankAccount("BNK002", "Bob Jones", 500.0);

        // ii. Two deposits and one withdrawal for Account 1
        acc1.deposit(200.0);
        acc1.deposit(50.0);
        acc1.withdraw(100.0);

        // ii. Two deposits and one withdrawal for Account 2
        acc2.deposit(100.0);
        acc2.deposit(100.0);
        acc2.withdraw(300.0);

        // iii. Display final information for both
        System.out.println("Final Account Status:");
        acc1.displayAccountInfo();
        acc2.displayAccountInfo();
    }
}
