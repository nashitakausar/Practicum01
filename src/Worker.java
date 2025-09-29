import java.text.NumberFormat;
import java.util.Locale;

public class Worker extends Person {
    private double hourlyPayRate;

    public Worker(String firstName, String lastName, String ID, String title, int YOB, double hourlyPayRate) {
        super(firstName, lastName, ID, title, YOB);
        setHourlyPayRate(hourlyPayRate);
    }

    public Worker(String ID, double hourlyPayRate) {
        super(ID);
        setHourlyPayRate(hourlyPayRate);
    }

    public double getHourlyPayRate() {
        return hourlyPayRate;
    }

    public void setHourlyPayRate(double hourlyPayRate) {
        if (hourlyPayRate < 0) {
            throw new IllegalArgumentException("Hourly pay rate cannot be negative");
        }
        this.hourlyPayRate = hourlyPayRate;
    }

    /**
     * returns total weekly pay using time&half for hours > 40.
     */
    public double calculateWeeklyPay(double hoursWorked) {
        if (hoursWorked < 0) throw new IllegalArgumentException("Hours cannot be negative");
        double regularHours = Math.min(40.0, hoursWorked);
        double overtimeHours = Math.max(0.0, hoursWorked - 40.0);
        return regularHours * hourlyPayRate + overtimeHours * hourlyPayRate * 1.5;
    }

    /**
     * prints clear breakdown of regular vs overtime pay.
     */
    public void displayWeeklyPay(double hoursWorked) {
        NumberFormat money = NumberFormat.getCurrencyInstance(Locale.US);
        double regularHours = Math.min(40.0, Math.max(0.0, hoursWorked));
        double overtimeHours = Math.max(0.0, hoursWorked - 40.0);

        double regularPay = regularHours * hourlyPayRate;
        double overtimePay = overtimeHours * hourlyPayRate * 1.5;
        double total = regularPay + overtimePay;

        System.out.println("Worker: " + formalName() + " [ID " + getID() + "]");
        System.out.println("  Regular: " + regularHours + "h @ " + money.format(hourlyPayRate) +
                " = " + money.format(regularPay));
        System.out.println("  Overtime: " + overtimeHours + "h @ " + money.format(hourlyPayRate * 1.5) +
                " = " + money.format(overtimePay));
        System.out.println("  Total: " + money.format(total));
    }

    //serialization including the new field

    @Override
    public String toCSV() {
        // person order: ID, firstName, lastName, title, YOB
        // add hourlyPayRate
        return String.join(",",
                getID(),
                csv(getFirstName()),
                csv(getLastName()),
                csv(getTitle()),
                String.valueOf(getYOB()),
                String.valueOf(hourlyPayRate)
        );
    }

    @Override
    public String toJSON() {
        return "{"
                + "\"type\":\"Worker\","
                + "\"ID\":\"" + json(getID()) + "\","
                + "\"firstName\":\"" + json(getFirstName()) + "\","
                + "\"lastName\":\"" + json(getLastName()) + "\","
                + "\"title\":\"" + json(getTitle()) + "\","
                + "\"YOB\":" + getYOB() + ","
                + "\"hourlyPayRate\":" + hourlyPayRate
                + "}";
    }

    @Override
    public String toXML() {
        return "<Worker>"
                + "<ID>" + xml(getID()) + "</ID>"
                + "<FirstName>" + xml(getFirstName()) + "</FirstName>"
                + "<LastName>" + xml(getLastName()) + "</LastName>"
                + "<Title>" + xml(getTitle()) + "</Title>"
                + "<YOB>" + getYOB() + "</YOB>"
                + "<HourlyPayRate>" + hourlyPayRate + "</HourlyPayRate>"
                + "</Worker>";
    }

    // small local helpers to align with Person’s escaping behavior
    private String csv(String s) {
        if (s == null) return "";
        String v = s;
        if (v.contains(",") || v.contains("\"") || v.contains("\n")) {
            v = "\"" + v.replace("\"", "\"\"") + "\"";
        }
        return v;
    }
    private String json(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
    private String xml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&apos;");
    }
}
