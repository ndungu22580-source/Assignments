import java.util.Scanner; // Import the Scanner class

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isIssued;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isIssued = false; 
    }

    public String getIsbn() { return isbn; }
    public boolean isIssued() { return isIssued; }

    public void issueBook() {
        if (!isIssued) {
            isIssued = true;
            System.out.println(">> Success: " + title + " has been issued.");
        } else {
            System.out.println(">> Error: " + title + " is already out.");
        }
    }

    public void returnBook() {
        isIssued = false;
        System.out.println(">> Success: " + title + " has been returned.");
    }

    public void displayBookInfo() {
        String status = isIssued ? "Issued" : "Available";
        System.out.println("[" + isbn + "] Title: " + title + " | Author: " + author + " | Status: " + status);
    }
}

class Library {
    private Book[] books = new Book[10];
    private int bookCount = 0;

    public void addBook(Book b) {
        if (bookCount < 10) {
            books[bookCount] = b;
            bookCount++;
        } else {
            System.out.println("Library is full!");
        }
    }

    public void issueBook(String isbn) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getIsbn().equalsIgnoreCase(isbn)) {
                books[i].issueBook();
                return;
            }
        }
        System.out.println("Book with ISBN " + isbn + " not found.");
    }

    public void returnBook(String isbn) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getIsbn().equalsIgnoreCase(isbn)) {
                books[i].returnBook();
                return;
            }
        }
        System.out.println("Book with ISBN " + isbn + " not found.");
    }

    public void listAvailableBooks() {
        System.out.println("\n--- Available Books ---");
        boolean any = false;
        for (int i = 0; i < bookCount; i++) {
            if (!books[i].isIssued()) {
                books[i].displayBookInfo();
                any = true;
            }
        }
        if (!any) System.out.println("No books currently available.");
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library myLibrary = new Library();

        // 1. Adding Books via User Input
        System.out.println("Enter details for 3 books:");
        for (int i = 1; i <= 3; i++) {
            System.out.println("\nBook " + i + ":");
            System.out.print("Title: ");
            String title = scanner.nextLine();
            System.out.print("Author: ");
            String author = scanner.nextLine();
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();
            
            myLibrary.addBook(new Book(title, author, isbn));
        }

        // 2. Issuing a book
        System.out.print("\nEnter ISBN of the book to ISSUE: ");
        String issueIsbn = scanner.nextLine();
        myLibrary.issueBook(issueIsbn);

        // 3. Returning a book
        System.out.print("Enter ISBN of the book to RETURN: ");
        String returnIsbn = scanner.nextLine();
        myLibrary.returnBook(returnIsbn);

        // 4. List available books
        myLibrary.listAvailableBooks();

        scanner.close(); // Good practice to close the scanner
    }
}
