import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class CustomerServiceTest {
  
    private static final String FILE_PATH = "lastCustomerId.txt";
    private static final int initialId = 1000;

    @Before
    public void setUp() throws IOException {
        // Delete existing file before each test
        File file = new File(FILE_PATH);
        if (file.exists()) {
            file.delete();
        }

        // Create a new file with initial customer ID
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(String.valueOf(initialId));
        }
    }

    public CustomerServiceTest() {
    }
    

    @Test
    public void testCustomerIdGeneration() throws IOException {
        int newId = generateCustomerId();
        assertEquals(1001, newId);
    }

    @Test
    public void testCustomerIdIncrement() throws IOException {
        generateCustomerId(); // First increment
        int newId = generateCustomerId(); // Second increment
        assertEquals(1002, newId);
    }

    @Test
    public void testCustomerIdWithMissingFile() throws IOException {
        File file = new File(FILE_PATH);
        file.delete(); // Delete the file
        int newId = generateCustomerId();
        assertEquals(1001, newId);
    }

    @Test
    public void testCustomerIdWithCorruptFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write("INVALID_DATA");
        }
        int newId = generateCustomerId();
        assertEquals(1001, newId);
    }

    @Test
    public void testCustomerIdFormat() {
        String customerId = generateCustomerIdString();
        assertTrue(customerId.matches("C\\d{14}_\\d+"));
    }

    @Test
    public void testFormValidation_NameEmpty() {
        assertFalse(isValidForm("56477", "123 Street", "123456789V", "0712345678"));
    }

    @Test
    public void testFormValidation_InvalidNIC() {
        assertFalse(isValidForm("John Doe", "123 Street", "1234V", "0712345678"));
    }

    @Test
    public void testFormValidation_InvalidPhoneNumber() {
        assertFalse(isValidForm("John Doe", "123 Street", "123456789V", "07123"));
    }

    @Test
    public void testFormValidation_ValidData() {
        assertTrue(isValidForm("John Doe", "123 Street", "123456789V", "0712345678"));
    }

    @Test
    public void testFileWriting() throws IOException {
        generateCustomerId();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String lastLine = reader.readLine().trim();
        reader.close();
        assertEquals("1001", lastLine);
    }

    // Helper function to simulate customer ID generation logic
    private int generateCustomerId() throws IOException {
        File file = new File(FILE_PATH);
        int lastId = initialId;

        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String lastLine = reader.readLine();
            if (lastLine != null && lastLine.matches("\\d+")) {
                lastId = Integer.parseInt(lastLine);
            }
            reader.close();
        }

        int newId = lastId + 1;

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(String.valueOf(newId));
        writer.close();

        return newId;
    }

    // Helper function to generate formatted Customer ID
    private String generateCustomerIdString() {
        int newId = initialId + 1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        return "CU" + timestamp + "_" + newId;
    }

    // Helper function to validate form fields
    private boolean isValidForm(String name, String address, String nic, String phone) {
        if (name == null || name.trim().isEmpty()) return false;
        if (!nic.matches("[0-9]{9}[VvXx]|[0-9]{12}")) return false;
        if (!phone.matches("[0-9]{10}")) return false;
        return true;
    }
    
    
    
    
}

