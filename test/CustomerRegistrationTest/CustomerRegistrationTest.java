package CustomerRegistrationTest;

import com.megacitycab.dao.AdminDAO;
import com.megacitycab.dao.CustomerDAO;
import com.megacitycab.model.Customer;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class CustomerRegistrationTest {
    public static void main(String[] args) {
        
    
    

        
        CustomerDAO customerDAO = CustomerDAO.getInstance(); // Get singleton instance
        
        @Test
        System.out.println("Test Case 1: Successful Customer Registration");
        if (registerCustomerTest("CUS_10001", "John Doe", "123 Main St", "987654321V", "0712345678", customerDAO)) {
            System.out.println("✅ Customer Registration Successful - Passed");
        } else {
            System.out.println("❌ Customer Registration Failed - Failed");
        }

        // Test Case 1: Duplicate NIC Registration (Should Fail)
        System.out.println("\nTest Case 2: Duplicate NIC Registration");
        if (!registerCustomerTest("CUS_10002", "Jane Doe", "456 High St", "987654321V", "0776543210", customerDAO)) {
            System.out.println("✅ Duplicate NIC Rejected - Passed");
        } else {
            System.out.println("❌ Duplicate NIC Allowed - Failed");
        }

        // Test Case 6: Duplicate Registration Number (Should Fail)
        System.out.println("\nTest Case 3: Duplicate Registration Number");
        if (!registerCustomerTest("CUS_10001", "Mark Smith", "789 Oak St", "123456789V", "0789998887", customerDAO)) {
            System.out.println("✅ Duplicate Registration Number Rejected - Passed");
        } else {
            System.out.println("❌ Duplicate Registration Number Allowed - Failed");
        }

        // Test Case 7: Empty Name (Should Fail)
        System.out.println("\nTest Case 4: Empty Name");
        if (!registerCustomerTest("CUS_10003", "", "987 Elm St", "654987321V", "0761112233", customerDAO)) {
            System.out.println("✅ Empty Name Rejected - Passed");
        } else {
            System.out.println("❌ Empty Name Allowed - Failed");
        }

        // Test Case 4: Invalid NIC Format (Should Fail)
        System.out.println("\nTest Case 5: Invalid NIC Format");
        if (!registerCustomerTest("CUS_10004", "Michael Lee", "222 Maple St", "NIC12345", "0719876543", customerDAO)) {
            System.out.println("✅ Invalid NIC Format Rejected - Passed");
        } else {
            System.out.println("❌ Invalid NIC Format Allowed - Failed");
        }

        // Test Case 6: Invalid Telephone Number Format (Should Fail)
        System.out.println("\nTest Case 6: Invalid Telephone Number Format");
        if (!registerCustomerTest("CUS_10005", "Emily Watson", "555 Birch St", "458796321V", "12345", customerDAO)) {
            System.out.println("✅ Invalid Telephone Number Rejected - Passed");
        } else {
            System.out.println("❌ Invalid Telephone Number Allowed - Failed");
        }
    }

    // Function to Test Customer Registration using DAO
    public static boolean registerCustomerTest(String customerId, String name, String address, String nic, String contactNumber, CustomerDAO customerDAO) {
        // Validate NIC format (Assuming NIC should be 9 digits + 'V' or 12 digits)
        if (!nic.matches("^\\d{9}V$|^\\d{12}$")) {
            System.out.println("⚠️ Invalid NIC Format: " + nic);
            return false;
        }

        // Validate Telephone Number (Assuming it should have 10 digits)
        if (!contactNumber.matches("^\\d{10}$")) {
            System.out.println("⚠️ Invalid Telephone Number: " + contactNumber);
            return false;
        }

        // Validate Name (Should not be empty)
        if (name == null || name.trim().isEmpty()) {
            System.out.println("⚠️ Invalid Name: Cannot be empty.");
            return false;
        }

        // Create Customer using Builder Pattern
        Customer customer = new Customer.Builder(customerId)
                .name(name)
                .address(address)
                .nic(nic)
                .phoneno(contactNumber)
                .build();
        
        try {
            return customerDAO.addCustomer(customer);
        } catch (Exception e) {
            System.out.println("❌ Error during customer registration: " + e.getMessage());
            return false;
        }
    }
}
