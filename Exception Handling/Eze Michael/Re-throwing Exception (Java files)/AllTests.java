package energymanagementsystem;

import org.junit.Test;

public class AllTests {
    @Test
    public void runAllTests() {
        ExceptionHandlerTests tests = new ExceptionHandlerTests();
        tests.processData_ShouldThrowIllegalArgumentException_WhenDataIsNull();
        tests.processData_ShouldNotThrowException_WhenDataIsValid();
        tests.processData_ShouldLogException_WhenExceptionIsThrown();
        tests.processData_ShouldReThrowException_WhenExceptionIsCaught();
        tests.processData_ShouldProcessData_WhenDataIsValid();
    }
}
