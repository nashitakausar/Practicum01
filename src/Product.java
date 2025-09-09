import java.util.Objects;

public class Product {
    private String name;
    private String description;
    private String ID;   // immutable identity
    private double cost;

    public Product(String name, String description, String ID, double cost) {
        setName(name);
        setDescription(description);
        setID(ID);
        setCost(cost);
    }

    public Product(String ID) {
        setID(ID);
        this.name = "";
        this.description = "";
        this.cost = 0.0;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getID() { return ID; }
    public double getCost() { return cost; }

    public void setName(String name) { this.name = name == null ? "" : name.trim(); }
    public void setDescription(String description) { this.description = description == null ? "" : description.trim(); }

    public void setID(String ID) {
        if (ID == null || !ID.trim().matches("\\d{6}")) {
            throw new IllegalArgumentException("ID must be 6 digits");
        }
        this.ID = ID.trim();
    }

    public void setCost(double cost) {
        if (cost < 0) throw new IllegalArgumentException("Cost cannot be negative");
        this.cost = cost;
    }

    public String toCSV() {
        return String.join(",",
                ID,
                escapeCsv(name),
                escapeCsv(description),
                String.valueOf(cost)
        );
    }

    public String toJSON() {
        return "{"
                + "\"ID\":\"" + jsonEscape(ID) + "\","
                + "\"name\":\"" + jsonEscape(name) + "\","
                + "\"description\":\"" + jsonEscape(description) + "\","
                + "\"cost\":" + cost
                + "}";
    }

    public String toXML() {
        return "<Product>"
                + "<ID>" + xmlEscape(ID) + "</ID>"
                + "<Name>" + xmlEscape(name) + "</Name>"
                + "<Description>" + xmlEscape(description) + "</Description>"
                + "<Cost>" + cost + "</Cost>"
                + "</Product>";
    }

    @Override
    public String toString() {
        return name + " (ID " + ID + ") $" + cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(ID, product.ID);
    }

    @Override
    public int hashCode() { return Objects.hash(ID); }

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
