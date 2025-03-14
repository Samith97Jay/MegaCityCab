package AdminLoginTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.megacitycab.dao.AdminDAO;
import com.megacitycab.model.Admin;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
    private AdminDAO adminDAO;

    @Before
    public void setUp() {
        adminDAO = mock(AdminDAO.class); // Correct way to mock AdminDAO
    }

    @Test
    public void testValidLogin() throws SQLException, ClassNotFoundException {
        Admin mockAdmin = new Admin("admin", "admin123");
        when(adminDAO.getAdminByUsername("admin")).thenReturn(mockAdmin);

        boolean result = validateLogin("admin", "admin123", adminDAO);
        assertTrue("Login should succeed with correct credentials.", result);
    }

    @Test
    public void testInvalidPassword() throws SQLException, ClassNotFoundException {
        Admin mockAdmin = new Admin("admin", "admin123");
        when(adminDAO.getAdminByUsername("admin")).thenReturn(mockAdmin);

        boolean result = validateLogin("admin", "wrongpass", adminDAO);
        assertFalse("Login should fail with incorrect password.", result);
    }

    @Test
    public void testNonExistentUsername() throws SQLException, ClassNotFoundException {
        when(adminDAO.getAdminByUsername("unknown")).thenReturn(null);

        boolean result = validateLogin("unknown", "admin123", adminDAO);
        assertFalse("Login should fail for non-existent username.", result);
    }

    @Test
    public void testEmptyUsernameAndPassword() throws SQLException, ClassNotFoundException {
        boolean result = validateLogin("", "", adminDAO);
        assertFalse("Login should fail for empty username and password.", result);
    }

    @Test
    public void testNullUsername() throws SQLException, ClassNotFoundException {
        boolean result = validateLogin(null, "admin123", adminDAO);
        assertFalse("Login should fail for null username.", result);
    }

    @Test
    public void testNullPassword() throws SQLException, ClassNotFoundException {
        boolean result = validateLogin("admin", null, adminDAO);
        assertFalse("Login should fail for null password.", result);
    }

    private static boolean validateLogin(String uname, String pwd, AdminDAO adminDAO) throws SQLException, ClassNotFoundException {
        if (uname == null || pwd == null) {
            return false;
        }
        Admin admin = adminDAO.getAdminByUsername(uname);
        return admin != null && admin.getPassword().equals(pwd);
    }
}
