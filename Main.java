import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Electric Billing System");

        System.out.print("Enter Customer ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Address: ");
        String address = sc.nextLine();
        System.out.print("Enter Previous Reading: ");
        int prevReading = sc.nextInt();
        System.out.print("Enter Current Reading: ");
        int currReading = sc.nextInt();

        if (!Validator.isFieldNotEmpty(name) || !Validator.isFieldNotEmpty(id) || !Validator.isReadingValid(prevReading, currReading)) {
            System.out.println("Invalid input. Please check all fields.");
            return;
        }

        Customer customer = new Customer(name, id, address, prevReading, currReading);
        int units = customer.getUnitsUsed();
        double bill = BillCalculator.calculateBill(units);

        String billDetails = customer.getCustomerInfo()
                + "\nPrevious Reading: " + prevReading
                + "\nCurrent Reading: " + currReading
                + "\nUnits Used: " + units
                + "\nTotal Bill: ₹" + bill;

        System.out.println("\n----- Bill Summary -----");
        System.out.println(billDetails);

        FileHandler.saveToFile(billDetails);
    }
}
