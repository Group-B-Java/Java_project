package com.assignment.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ChainedExceptionExampleTest {

    @Test
    public void testChainedExceptionMessage() {
        ChainedExceptionExample example = new ChainedExceptionExample();
        Exception exception = assertThrows(CustomException.class, () -> {
            example.triggerException();
        });
        assertEquals("Custom Exception caused by another exception", exception.getMessage());
    }

    @Test
    public void testChainedExceptionCause() {
        ChainedExceptionExample example = new ChainedExceptionExample();
        CustomException exception = assertThrows(CustomException.class, () -> {
            example.triggerException();
        });
        assertTrue(exception.getCause() instanceof NullPointerException);
        assertEquals("Null Pointer Exception occurred", exception.getCause().getMessage());
    }
}
