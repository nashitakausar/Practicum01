import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ProductReader {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // user chooses file
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select Product file");
        int result = chooser.showOpenDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file chosen. Exiting.");
            return;
        }

        File file = chooser.getSelectedFile();
        Path path = file.toPath();

        if (!SafeInput.getYNConfirm(in, "Read file " + path.getFileName() + "?")) {
            System.out.println("Exiting without reading.");
            return;
        }

        // print header
        System.out.println("ID#\tName\tDescription\tCost");


        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String name = parts[1];
                String desc = parts[2];
                String cost = parts[3];

                System.out.println(id + "\t" + name + "\t" + desc + "\t" + cost);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
