import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ShortLister {
    public static void main(String[] args) {
        // pick text file
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose a text file");
        chooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt", "text", "md", "csv"));
        int result = chooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file selected. Exiting.");
            return;
        }

        File file = chooser.getSelectedFile();
        Filter filter = new ShortWordFilter();

        List<String> shortWords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            // split on any non-letter/digit/apostrophe
            while ((line = br.readLine()) != null) {
                for (String token : line.split("[^A-Za-z0-9']+")) {
                    if (token.isEmpty()) continue;
                    if (filter.accept(token)) {
                        shortWords.add(token);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading file:\n" + e.getMessage(),
                    "Read Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // display results in console
        System.out.println("Short words (length < 5) from: " + file.getName());
        for (String w : shortWords) {
            System.out.println(w);
        }

        // quick summary dialog
        JOptionPane.showMessageDialog(null,
                "Found " + shortWords.size() + " short words (length < 5).\n" +
                        "See console for the full list.",
                "ShortLister", JOptionPane.INFORMATION_MESSAGE);
    }
}
