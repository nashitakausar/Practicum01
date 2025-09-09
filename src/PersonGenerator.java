import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();
        SafeInputObj in = new SafeInputObj();

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\personData.txt");

        boolean done = false;


        do {
            String ID = in.getNonZeroLenString("Enter the ID of the person you want to create [6 digits]");
            String firstName = in.getNonZeroLenString("Enter the first name of the person you want to create");
            String lastName = in.getNonZeroLenString("Enter the last name of the person you want to create");
            String title = in.getNonZeroLenString("Enter the title of the person you want to create");
            int YOB = in.getRangedInt("Enter the year of birth", 1000, 9999);

            people.add(new Person(firstName, lastName, ID, title, YOB));
            done = in.getYNConfirm("Stop entering people?");
        } while (!done);

        // Echo to console
        for (Person p : people) {
            System.out.println(p.toString());
        }

        // Write CSV lines using toCSV()
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new BufferedOutputStream(Files.newOutputStream(file, CREATE))))) {
            for (Person p : people) {
                String rec = p.toCSV();
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            System.out.println("Data file written: " + file.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}