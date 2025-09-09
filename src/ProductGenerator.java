import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductGenerator {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        SafeInputObj in = new SafeInputObj();


        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        boolean done = false;


        do {
            String ID = in.getNonZeroLenString("Enter the product ID [6 digits]");
            String name = in.getNonZeroLenString("Enter the product name");
           String description = in.getNonZeroLenString("Enter the product description");
           double cost = in.getDouble("Enter the product cost (double)");


            products.add(new Product(name, description, ID, cost));
            done = in.getYNConfirm("Stop entering products?");
        } while (!done);


        for (Product p : products)
            System.out.println(p);

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (Product p: products) {
                String rec = p.toCSV();
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
