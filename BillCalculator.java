public class BillCalculator {
    public static double calculateBill(int units) {
        double amount;
        if (units <= 100) {
            amount = units * 3.0;
        } else if (units <= 200) {
            amount = 100 * 3.0 + (units - 100) * 4.0;
        } else {
            amount = 100 * 3.0 + 100 * 4.0 + (units - 200) * 5.0;
        }
        return amount;
    }
}
