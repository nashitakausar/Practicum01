import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WorkerTest {

    @Test
    void calculateWeeklyPay_noOvertime() {
        Worker w = new Worker("Test", "User", "123456", "Mr.", 1999, 20.0);
        assertEquals(800.0, w.calculateWeeklyPay(40), 1e-6);
    }

    @Test
    void calculateWeeklyPay_withOvertime() {
        Worker w = new Worker("Test", "User", "123456", "Mr.", 1999, 30.0);
        // 40 * 30 + 10 * 30 * 1.5 = 1200 + 450 = 1650
        assertEquals(1650.0, w.calculateWeeklyPay(50), 1e-6);
    }

    @Test
    void serialization_includesHourlyRate() {
        Worker w = new Worker("Test", "User", "123456", "Mr.", 1999, 25.5);
        String json = w.toJSON();
        assertTrue(json.contains("\"hourlyPayRate\":25.5"));
        String csv = w.toCSV();
        assertTrue(csv.endsWith(",25.5"));
        String xml = w.toXML();
        assertTrue(xml.contains("<HourlyPayRate>25.5</HourlyPayRate>"));
    }
}

