import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    public static void saveToFile(String data) {
        try {
            FileWriter writer = new FileWriter("billing_data.txt", true);
            writer.write(data + "\n----------------------\n");
            writer.close();
            System.out.println("Data saved to billing_data.txt");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
