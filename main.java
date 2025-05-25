import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Customer class to store customer details.
 */
class Customer {
    private String customerId;
    private String name;
    private String address;
    private List<ElectricityBill> bills;

    public Customer(String name, String address) {
        this.customerId = UUID.randomUUID().toString().substring(0, 8); // Generate a unique ID
        this.name = name;
        this.address = address;
        this.bills = new ArrayList<>();
    }

    // Getters
    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<ElectricityBill> getBills() {
        return bills;
    }

    // Method to add a bill to this customer
    public void addBill(ElectricityBill bill) {
        this.bills.add(bill);
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + "\nName: " + name + "\nAddress: " + address;
    }
}

/**
 * ElectricityBill class to store bill details.
 */
class ElectricityBill {
    private String billId;
    private Customer customer;
    private double unitsConsumed;
    private double billAmount;
    private LocalDate billDate;
    private LocalDate dueDate;
    private boolean isPaid;
    private static final double RATE_PER_UNIT = 7.5; // Example rate: 7.5 currency units per kWh

    public ElectricityBill(Customer customer, double unitsConsumed) {
        this.billId = UUID.randomUUID().toString().substring(0, 10);
        this.customer = customer;
        this.unitsConsumed = unitsConsumed;
        this.billDate = LocalDate.now();
        this.dueDate = billDate.plusDays(15); // Due date is 15 days from bill date
        this.billAmount = calculateBillAmount();
        this.isPaid = false;
    }

    // Calculate bill amount
    private double calculateBillAmount() {
        // Simple calculation: unitsConsumed * ratePerUnit
        // This can be expanded with tiered rates, taxes, etc.
        return unitsConsumed * RATE_PER_UNIT;
    }

    // Getters
    public String getBillId() {
        return billId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getUnitsConsumed() {
        return unitsConsumed;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    // Setter for payment status
    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "\n--- Bill Details ---" +
               "\nBill ID: " + billId +
               "\nCustomer Name: " + customer.getName() +
               "\nUnits Consumed: " + String.format("%.2f", unitsConsumed) + " kWh" +
               "\nRate per Unit: $" + String.format("%.2f", RATE_PER_UNIT) +
               "\nBill Amount: $" + String.format("%.2f", billAmount) +
               "\nBill Date: " + billDate.format(formatter) +
               "\nDue Date: " + dueDate.format(formatter) +
               "\nPayment Status: " + (isPaid ? "Paid" : "Pending");
    }
}

/**
 * Main class for the Electric Billing System.
 * Handles user interaction and system operations.
 */
public class BillingSystem {
    private List<Customer> customers;
    private Scanner scanner;

    public BillingSystem() {
        this.customers = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Adds a new customer to the system.
     */
    public void addCustomer() {
        System.out.println("\n--- Add New Customer ---");
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Customer Address: ");
        String address = scanner.nextLine();

        Customer newCustomer = new Customer(name, address);
        customers.add(newCustomer);
        System.out.println("Customer added successfully! Customer ID: " + newCustomer.getCustomerId());
    }

    /**
     * Finds a customer by their ID.
     * @param customerId The ID of the customer to find.
     * @return The Customer object if found, otherwise null.
     */
    public Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Generates a new electricity bill for a customer.
     */
    public void generateBill() {
        System.out.println("\n--- Generate Electricity Bill ---");
        if (customers.isEmpty()) {
            System.out.println("No customers in the system. Please add a customer first.");
            return;
        }

        System.out.print("Enter Customer ID to generate bill for: ");
        String customerId = scanner.nextLine();
        Customer customer = findCustomerById(customerId);

        if (customer == null) {
            System.out.println("Customer with ID " + customerId + " not found.");
            return;
        }

        System.out.print("Enter Units Consumed (kWh): ");
        double unitsConsumed;
        try {
            unitsConsumed = Double.parseDouble(scanner.nextLine());
            if (unitsConsumed < 0) {
                System.out.println("Units consumed cannot be negative. Please try again.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for units consumed. Please enter a numeric value.");
            return;
        }

        ElectricityBill newBill = new ElectricityBill(customer, unitsConsumed);
        customer.addBill(newBill); // Add bill to customer's bill list
        System.out.println("Bill generated successfully for " + customer.getName() + "!");
        System.out.println(newBill);
    }

    /**
     * Displays all bills for a specific customer.
     */
    public void displayCustomerBills() {
        System.out.println("\n--- Display Customer Bills ---");
        if (customers.isEmpty()) {
            System.out.println("No customers in the system.");
            return;
        }
        System.out.print("Enter Customer ID to display bills: ");
        String customerId = scanner.nextLine();
        Customer customer = findCustomerById(customerId);

        if (customer == null) {
            System.out.println("Customer with ID " + customerId + " not found.");
            return;
        }

        List<ElectricityBill> bills = customer.getBills();
        if (bills.isEmpty()) {
            System.out.println("No bills found for customer " + customer.getName() + " (ID: " + customerId + ").");
            return;
        }

        System.out.println("\nBills for " + customer.getName() + " (ID: " + customerId + "):");
        for (ElectricityBill bill : bills) {
            System.out.println(bill);
        }
    }
    
    /**
     * Displays all customers in the system.
     */
    public void displayAllCustomers() {
        System.out.println("\n--- All Customers ---");
        if (customers.isEmpty()) {
            System.out.println("No customers registered in the system yet.");
            return;
        }
        for (Customer customer : customers) {
            System.out.println(customer);
            System.out.println("--------------------");
        }
    }
    
    /**
     * Marks a bill as paid.
     */
    public void markBillAsPaid() {
        System.out.println("\n--- Mark Bill as Paid ---");
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();
        Customer customer = findCustomerById(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        if (customer.getBills().isEmpty()) {
            System.out.println("No bills found for this customer.");
            return;
        }

        System.out.println("Bills for " + customer.getName() + ":");
        for (int i = 0; i < customer.getBills().size(); i++) {
            ElectricityBill bill = customer.getBills().get(i);
            System.out.println((i + 1) + ". Bill ID: " + bill.getBillId() + ", Amount: $" + String.format("%.2f", bill.getBillAmount()) + ", Status: " + (bill.isPaid() ? "Paid" : "Pending"));
        }
        
        System.out.print("Enter Bill ID to mark as paid: ");
        String billIdToPay = scanner.nextLine();
        
        ElectricityBill billToUpdate = null;
        for(ElectricityBill bill : customer.getBills()){
            if(bill.getBillId().equals(billIdToPay)){
                billToUpdate = bill;
                break;
            }
        }

        if (billToUpdate != null) {
            if (billToUpdate.isPaid()) {
                System.out.println("This bill is already marked as paid.");
            } else {
                billToUpdate.setPaid(true);
                System.out.println("Bill ID: " + billToUpdate.getBillId() + " marked as paid successfully.");
            }
        } else {
            System.out.println("Bill with ID " + billIdToPay + " not found for this customer.");
        }
    }


    /**
     * Displays the main menu and handles user choices.
     */
    public void showMenu() {
        int choice;
        do {
            System.out.println("\n====== Electric Billing System Menu ======");
            System.out.println("1. Add New Customer");
            System.out.println("2. Generate Electricity Bill");
            System.out.println("3. Display All Bills for a Customer");
            System.out.println("4. Display All Customers");
            System.out.println("5. Mark Bill as Paid");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = 0; // Reset choice to continue loop
            }

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    generateBill();
                    break;
                case 3:
                    displayCustomerBills();
                    break;
                case 4:
                    displayAllCustomers();
                    break;
                case 5:
                    markBillAsPaid();
                    break;
                case 6:
                    System.out.println("Exiting Electric Billing System. Goodbye!");
                    break;
                default:
                    if (choice != 0) { // Avoid double message if input was invalid
                         System.out.println("Invalid choice. Please try again.");
                    }
            }
        } while (choice != 6);
    }

    public static void main(String[] args) {
        BillingSystem system = new BillingSystem();
        // Welcome message
        System.out.println("***************");
        System.out.println("* Welcome to the Electric Billing System! *");
        System.out.println("***************");
        system.showMenu();
    }
}
