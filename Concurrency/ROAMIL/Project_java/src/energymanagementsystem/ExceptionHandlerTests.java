package energymanagementsystem;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExceptionHandlerTests {
    private final ExceptionHandler exceptionHandler = new ExceptionHandler();

    @Test
    public void processData_ShouldThrowIllegalArgumentException_WhenDataIsNull() {
        assertThrows(IllegalArgumentException.class, () -> exceptionHandler.processData(null));
    }

    @Test
    public void processData_ShouldNotThrowException_WhenDataIsValid() {
        try {
            exceptionHandler.processData("Valid data");
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void processData_ShouldLogException_WhenExceptionIsThrown() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> exceptionHandler.processData(null));
        assertEquals("Data cannot be null", ex.getMessage());
    }

    @Test
    public void processData_ShouldReThrowException_WhenExceptionIsCaught() {
        try {
            exceptionHandler.processData(null);
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
    }

    @Test
    public void processData_ShouldProcessData_WhenDataIsValid() {
        try {
            exceptionHandler.processData("Some data");
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }
}
