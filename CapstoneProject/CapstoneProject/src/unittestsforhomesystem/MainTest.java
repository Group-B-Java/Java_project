package unittestsforhomesystem;

import org.junit.Test;
import static org.junit.Assert.*;
import smarthomesystem.Main;

public class MainTest {
    @Test
    public void testMain() {
        try {
            Main.main(new String[]{});
        } catch (Exception e) {
            fail("Main method threw an exception: " + e.getMessage());
        }
    }
}
