import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args) {
        ArrayList<String> products = new ArrayList<>();
        Scanner in = new Scanner(System.in);


        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        boolean done = false;

        String record;
        String ID;
        String name;
        String description;
        double cost;

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the product ID [6 digits]");
            name = SafeInput.getNonZeroLenString(in, "Enter the product name");
            description = SafeInput.getNonZeroLenString(in, "Enter the product description");
            cost = SafeInput.getDouble(in, "Enter the product cost (double)");


            record = ID + ", " + name + ", " + description + ", " + cost;
            products.add(record);

            done = SafeInput.getYNConfirm(in, "Do you want to stop entering products?");
        } while (!done);


        for (String p : products)
            System.out.println(p);

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String rec : products) {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Product data file written to ProductTestData.txt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
