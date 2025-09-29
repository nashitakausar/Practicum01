import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InheritanceDemo {
    public static void main(String[] args) {
        List<Worker> staff = new ArrayList<>();

        // 3 hourly workers
        staff.add(new Worker("Aisha", "Rahman", "100101", "Ms.", 2002, 22.50));
        staff.add(new Worker("Dev", "Patel", "100102", "Mr.", 2001, 28.00));
        staff.add(new Worker("Lena", "Park", "100103", "Ms.", 2003, 30.75));

        // 3 salary workers
        staff.add(new SalaryWorker("Marco", "Silva", "200201", "Mr.", 1999, 78000)); // ~1500/wk
        staff.add(new SalaryWorker("Noor", "Hassan", "200202", "Ms.", 2000, 91000));
        staff.add(new SalaryWorker("Yuki", "Tanaka", "200203", "Dr.", 1998, 120000));

        double[] weekHours = {40.0, 50.0, 40.0};
        String[] weekLabels = {"Week 1 (40h)", "Week 2 (50h crunch)", "Week 3 (40h)"};
        NumberFormat money = NumberFormat.getCurrencyInstance(Locale.US);

        for (int w = 0; w < weekHours.length; w++) {
            double hrs = weekHours[w];
            System.out.println("\n========================================");
            System.out.println(weekLabels[w]);
            System.out.println("========================================");

            // Simple table header
            System.out.printf("%-22s  %-10s%n", "Name (ID)", "Weekly Pay");
            System.out.println("----------------------------------------");

            for (Worker worker : staff) {
                double pay = worker.calculateWeeklyPay(hrs);
                System.out.printf("%-22s  %-10s%n",
                        worker.formalName() + " (" + worker.getID() + ")",
                        money.format(pay));
            }

            // Detailed breakdown per worker (meets the “displayWeeklyPay” requirement)
            System.out.println("\n-- Detailed breakdowns --");
            for (Worker worker : staff) {
                worker.displayWeeklyPay(hrs);
            }
        }
    }
}
