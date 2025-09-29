import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SalaryWorkerTest {

    @Test
    void calculateWeeklyPay_ignoresHours() {
        SalaryWorker sw = new SalaryWorker("Test", "User", "654321", "Ms.", 2000, 104000); // 2k/week
        assertEquals(2000.0, sw.calculateWeeklyPay(10), 1e-6);
        assertEquals(2000.0, sw.calculateWeeklyPay(60), 1e-6);
    }

    @Test
    void serialization_includesAnnualSalary() {
        SalaryWorker sw = new SalaryWorker("654321", 78000);
        String json = sw.toJSON();
        assertTrue(json.contains("\"annualSalary\":78000"));
        String csv = sw.toCSV();
        // CSV order: ID, firstName, lastName, title, YOB, hourlyPayRate, annualSalary
        assertTrue(csv.endsWith(",0.0,78000.0") || csv.endsWith(",0.0,78000"));
        String xml = sw.toXML();
        assertTrue(xml.contains("<AnnualSalary>78000.0</AnnualSalary>") || xml.contains("<AnnualSalary>78000</AnnualSalary>"));
    }
}

