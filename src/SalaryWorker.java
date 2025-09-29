import java.text.NumberFormat;
import java.util.Locale;

public class SalaryWorker extends Worker {
    private double annualSalary;

    /**
     * For SalaryWorker we still call super(...) to keep the inheritance chain intact.
     * hourlyPayRate isn’t used for salary calculations, so pass 0.0.
     */
    public SalaryWorker(String firstName, String lastName, String ID, String title, int YOB, double annualSalary) {
        super(firstName, lastName, ID, title, YOB, 0.0);
        setAnnualSalary(annualSalary);
    }

    public SalaryWorker(String ID, double annualSalary) {
        super(ID, 0.0);
        setAnnualSalary(annualSalary);
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        if (annualSalary < 0) throw new IllegalArgumentException("Annual salary cannot be negative");
        this.annualSalary = annualSalary;
    }

    /**
     * Weekly pay for salaried employees is annualSalary / 52.
     * The hoursWorked parameter is ignored (kept for polymorphism).
     */
    @Override
    public double calculateWeeklyPay(double hoursWorked) {
        return annualSalary / 52.0;
    }

    @Override
    public void displayWeeklyPay(double hoursWorked) {
        NumberFormat money = NumberFormat.getCurrencyInstance(Locale.US);
        double weekly = calculateWeeklyPay(hoursWorked);
        System.out.println("SalaryWorker: " + formalName() + " [ID " + getID() + "]");
        System.out.println("  Weekly salary (annual/52): " + money.format(weekly)
                + "  (Annual: " + money.format(annualSalary) + ")");
    }

    // ----- Serialization including the new field -----

    @Override
    public String toCSV() {
        // Person order: ID, firstName, lastName, title, YOB
        // Add annualSalary at the end
        return String.join(",",
                getID(),
                csv(getFirstName()),
                csv(getLastName()),
                csv(getTitle()),
                String.valueOf(getYOB()),
                String.valueOf(getHourlyPayRate()), // will be 0.0
                String.valueOf(annualSalary)
        );
    }

    @Override
    public String toJSON() {
        return "{"
                + "\"type\":\"SalaryWorker\","
                + "\"ID\":\"" + json(getID()) + "\","
                + "\"firstName\":\"" + json(getFirstName()) + "\","
                + "\"lastName\":\"" + json(getLastName()) + "\","
                + "\"title\":\"" + json(getTitle()) + "\","
                + "\"YOB\":" + getYOB() + ","
                + "\"hourlyPayRate\":" + getHourlyPayRate() + ","
                + "\"annualSalary\":" + annualSalary
                + "}";
    }

    @Override
    public String toXML() {
        return "<SalaryWorker>"
                + "<ID>" + xml(getID()) + "</ID>"
                + "<FirstName>" + xml(getFirstName()) + "</FirstName>"
                + "<LastName>" + xml(getLastName()) + "</LastName>"
                + "<Title>" + xml(getTitle()) + "</Title>"
                + "<YOB>" + getYOB() + "</YOB>"
                + "<HourlyPayRate>" + getHourlyPayRate() + "</HourlyPayRate>"
                + "<AnnualSalary>" + annualSalary + "</AnnualSalary>"
                + "</SalaryWorker>";
    }

    // local helpers
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

