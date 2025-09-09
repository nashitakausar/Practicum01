import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    void constructorAndSetters() {
        Product p = new Product("Widget", "Basic widget", "123456", 9.99);
        assertEquals("Widget", p.getName());
        assertEquals("Basic widget", p.getDescription());
        assertEquals("123456", p.getID());
        assertEquals(9.99, p.getCost(), 1e-6);

        assertThrows(IllegalArgumentException.class, () -> p.setID("abc"));
        assertThrows(IllegalArgumentException.class, () -> p.setCost(-1));
    }

    @Test
    void formats() {
        Product p = new Product("Thing", "Desc", "222222", 3.5);
        assertTrue(p.toCSV().startsWith("222222,"));
        assertTrue(p.toJSON().contains("\"ID\":\"222222\""));
        assertTrue(p.toXML().contains("<ID>222222</ID>"));
    }

    @Test
    void equalsByID() {
        Product a = new Product("A", "D1", "555555", 1.0);
        Product b = new Product("B", "D2", "555555", 5.0);
        assertEquals(a, b);
    }
}
