import java.util.Scanner;

// Class Product representing individual items
class Product {
    private String productId;
    private String name;
    private double price;
    private int quantityInStock;

    // Constructor
    public Product(String productId, String name, double price, int quantityInStock) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    // Accessors (Getters)
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantityInStock() { return quantityInStock; }

    // Mutators (Setters) and Business Logic
    public void updatePrice(double newPrice) {
        if (newPrice > 0) {
            this.price = newPrice;
            System.out.println("Price updated to: $" + newPrice);
        }
    }

    public void sell(int quantity) {
        if (quantity > 0 && quantity <= quantityInStock) {
            this.quantityInStock -= quantity;
            System.out.println("Successfully sold " + quantity + " units of " + name);
        } else {
            System.out.println("Error: Insufficient stock or invalid quantity.");
        }
    }

    public void restock(int quantity) {
        if (quantity > 0) {
            this.quantityInStock += quantity;
            System.out.println("Restocked " + quantity + " units. New total: " + quantityInStock);
        }
    }

    public void displayProduct() {
        System.out.println("ID: " + productId + " | Name: " + name + 
                           " | Price: $" + price + " | Stock: " + quantityInStock);
    }
}

// Store Class to manage the collection of products
class Store {
    private Product[] inventory;
    private int count;

    public Store(int capacity) {
        this.inventory = new Product[capacity];
        this.count = 0;
    }

    public void addProduct(Product p) {
        if (count < inventory.length) {
            inventory[count] = p;
            count++;
            System.out.println("Product '" + p.getName() + "' added to inventory.");
        } else {
            System.out.println("Inventory full!");
        }
    }

    public void sellProduct(String productId, int quantity) {
        for (int i = 0; i < count; i++) {
            if (inventory[i].getProductId().equalsIgnoreCase(productId)) {
                inventory[i].sell(quantity);
                return;
            }
        }
        System.out.println("Product ID " + productId + " not found.");
    }

    public void listLowStock() {
        System.out.println("\n--- LOW STOCK REPORT (Quantity < 10) ---");
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (inventory[i].getQuantityInStock() < 10) {
                inventory[i].displayProduct();
                found = true;
            }
        }
        if (!found) System.out.println("All products are well-stocked.");
    }
    
    public Product getProduct(String id) {
        for (int i = 0; i < count; i++) {
            if (inventory[i].getProductId().equalsIgnoreCase(id)) return inventory[i];
        }
        return null;
    }
}

// Main Class
public class EcommerceApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Store myStore = new Store(10);

        // 1. Create 4 Product objects (Static initialization as per task)
        Product p1 = new Product("P01", "Laptop", 1200.0, 15);
        Product p2 = new Product("P02", "Mouse", 25.0, 5);
        Product p3 = new Product("P03", "Keyboard", 45.0, 12);
        Product p4 = new Product("P04", "Monitor", 150.0, 8);

        // 2. Add them to the Store
        myStore.addProduct(p1);
        myStore.addProduct(p2);
        myStore.addProduct(p3);
        myStore.addProduct(p4);

        // 3. Sell/Restock Task (Demonstrating Scanner usage)
        System.out.print("\nEnter Product ID to sell: ");
        String idToSell = sc.nextLine();
        System.out.print("Enter quantity to sell: ");
        int qtyToSell = sc.nextInt();
        myStore.sellProduct(idToSell, qtyToSell);

        sc.nextLine(); // Consume leftover newline
        
        System.out.print("\nEnter Product ID to restock: ");
        String idToRestock = sc.nextLine();
        Product productToRestock = myStore.getProduct(idToRestock);
        if (productToRestock != null) {
            System.out.print("Enter amount to add to stock: ");
            int qtyRestock = sc.nextInt();
            productToRestock.restock(qtyRestock);
        } else {
            System.out.println("Product not found.");
        }

        // 4. List low stock products
        myStore.listLowStock();

        sc.close();
        System.out.println("\nSystem closed.");
    }
}
