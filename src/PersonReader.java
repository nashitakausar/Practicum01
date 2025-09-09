import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonReader {
    public static void main(String[] args) {
        SafeInputObj in = new SafeInputObj();

        //user chooses file
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select Person file");
        int result = chooser.showOpenDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file chosen. Exiting.");
            return;
        }

        File file = chooser.getSelectedFile();
        Path path = file.toPath();


        if (!in.getYNConfirm("Read file " + path.getFileName() + "?")) {
            System.out.println("Exiting without reading.");
            return;
        }

        ArrayList<Person> people = new ArrayList<>();
        // print header
        System.out.println("ID#\tFirstname\tLastname\tTitle\tYOB");


        // Read and display records
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String first = parts[1];
                String last = parts[2];
                String title = parts[3];
                String yob = parts[4];

                System.out.println(id + "\t" + first + "\t" + last + "\t" + title + "\t" + yob);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}


