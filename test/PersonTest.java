
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    @Test
    void constructorAndGettersWork() {
        Person p = new Person("Ada", "Lovelace", "123456", "Dr.", 1950);
        assertEquals("Ada", p.getFirstName());
        assertEquals("Lovelace", p.getLastName());
        assertEquals("123456", p.getID());
        assertEquals("Dr.", p.getTitle());
        assertEquals(1950, p.getYOB());
    }

    @Test
    void settersValidate() {
        Person p = new Person("123456");
        assertThrows(IllegalArgumentException.class, () -> p.setID("12"));
        assertThrows(IllegalArgumentException.class, () -> p.setYOB(1939));
        assertThrows(IllegalArgumentException.class, () -> p.setYOB(2011));
        p.setFirstName("Alan");
        p.setLastName("Turing");
        p.setTitle("Mr.");
        p.setYOB(1945);
        assertEquals("Alan Turing", p.fullName());
        assertTrue(Integer.parseInt(p.getAge()) > 0);
    }

    @Test
    void csvJsonXmlFormats() {
        Person p = new Person("Grace", "Hopper", "654321", "Adm.", 1940);
        assertTrue(p.toCSV().startsWith("654321,"));
        assertTrue(p.toJSON().contains("\"ID\":\"654321\""));
        assertTrue(p.toXML().contains("<ID>654321</ID>"));
    }

    @Test
    void equalsByID() {
        Person a = new Person("A", "B", "111111", "Dr.", 1960);
        Person b = new Person("X", "Y", "111111", "Ms.", 1970);
        assertEquals(a, b);
    }
}

