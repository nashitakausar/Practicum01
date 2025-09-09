import java.util.Calendar;
import java.util.Objects;

public class Person {
    private String firstName;
    private String lastName;
    private String ID;      // 6 digits, never changes
    private String title;   // e.g., Mr., Ms., Dr., etc.
    private int YOB;

    public Person(String firstName, String lastName, String ID, String title, int YOB) {
        setFirstName(firstName);
        setLastName(lastName);
        setID(ID);
        setTitle(title);
        setYOB(YOB);
    }


    public Person(String ID) {
        setID(ID);
        this.firstName = "";
        this.lastName = "";
        this.title = "";
        this.YOB = 2000;
    }

    // getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getID() { return ID; }
    public String getTitle() { return title; }
    public int getYOB() { return YOB; }

    // setters
    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? "" : firstName.trim();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? "" : lastName.trim();
    }

    public void setID(String ID) {
        if (ID == null || !ID.trim().matches("\\d{6}")) {
            throw new IllegalArgumentException("ID must be 6 digits");
        }
        this.ID = ID.trim();
    }

    public void setTitle(String title) {
        this.title = title == null ? "" : title.trim();
    }

    public void setYOB(int YOB) {
        if (YOB < 1940 || YOB > 2010) {
            throw new IllegalArgumentException("YOB must be in [1940, 2010]");
        }
        this.YOB = YOB;
    }

    // helpers required by lab
    public String fullName() { return (firstName + " " + lastName).trim(); }
    public String formalName() { return (title + " " + fullName()).trim(); }

    public String getAge() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return String.valueOf(year - YOB);
    }
    public String getAge(int year) {
        return String.valueOf(year - YOB);
    }

    // CSV/JSON/XML
    public String toCSV() {
        return String.join(",",
                ID,
                escapeCsv(firstName),
                escapeCsv(lastName),
                escapeCsv(title),
                String.valueOf(YOB)
        );
    }

    public String toJSON() {
        return "{"
                + "\"ID\":\"" + jsonEscape(ID) + "\","
                + "\"firstName\":\"" + jsonEscape(firstName) + "\","
                + "\"lastName\":\"" + jsonEscape(lastName) + "\","
                + "\"title\":\"" + jsonEscape(title) + "\","
                + "\"YOB\":" + YOB
                + "}";
    }

    public String toXML() {
        return "<Person>"
                + "<ID>" + xmlEscape(ID) + "</ID>"
                + "<FirstName>" + xmlEscape(firstName) + "</FirstName>"
                + "<LastName>" + xmlEscape(lastName) + "</LastName>"
                + "<Title>" + xmlEscape(title) + "</Title>"
                + "<YOB>" + YOB + "</YOB>"
                + "</Person>";
    }

    @Override
    public String toString() {
        return formalName() + " (ID " + ID + "), YOB " + YOB;
    }

    // equals on immutable identity (ID). Also provide hashCode.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(ID, person.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    // Small helpers
    private String escapeCsv(String s) {
        if (s == null) return "";
        String val = s;
        if (val.contains(",") || val.contains("\"") || val.contains("\n")) {
            val = "\"" + val.replace("\"", "\"\"") + "\"";
        }
        return val;
    }

    private String jsonEscape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private String xmlEscape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&apos;");
    }
}

