package com.yaga;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testGetShootPoint() {
        user = new User(new MockShootStrategy(), 'X');
        assertEquals(new Point(0, 0), user.getShootPoint());
    }
    @Test
    public void testConsoleInputStrategy() {
        String mockInput = "0 0\n0 1\n0 2\n";
        InputStream mockInputStream = new ByteArrayInputStream(mockInput.getBytes(StandardCharsets.UTF_8));
        ConsoleShootStrategy consoleShootStrategy = new ConsoleShootStrategy(mockInputStream);
        assertEquals(new Point(0,0), consoleShootStrategy.getShootPoint());
        assertEquals(new Point(0,1), consoleShootStrategy.getShootPoint());
        assertEquals(new Point(0,2), consoleShootStrategy.getShootPoint());
    }
}