public class Customer {
    private String name;
    private String id;
    private String address;
    private int prevReading;
    private int currReading;

    public Customer(String name, String id, String address, int prevReading, int currReading) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.prevReading = prevReading;
        this.currReading = currReading;
    }

    public int getUnitsUsed() {
        return currReading - prevReading;
    }

    public String getCustomerInfo() {
        return "Customer ID: " + id + "\nName: " + name + "\nAddress: " + address;
    }
}
