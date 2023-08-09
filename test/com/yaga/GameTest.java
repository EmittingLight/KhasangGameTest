package com.yaga;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        game.initCells();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testArrayUs1() {
        assertEquals(Game.SIZE, game.getCells().length);
        assertEquals(Game.SIZE, game.getCells()[0].length);
    }

    @Test
    public void testInitUs2() {
        for (int i = 0; i < Game.SIZE; i++) {
            for (int j = 0; j < Game.SIZE; j++) {
                assertEquals('.', game.getCharAtPoint(new Point(i, j)));
            }
        }
    }

    @Test
    public void testIsWinUs3() {
        game.setShootPoint(new Point(0, 0), 'X');
        game.setShootPoint(new Point(0, 1), 'X');
        game.setShootPoint(new Point(0, 2), 'X');
        assertEquals(Game.Status.X_WIN, game.getGameStatus());
    }

    @Test
    public void setShootPoint() {
        Point point = new Point(1, 0);
        char x = 'X';
        game.setShootPoint(point, x);
        assertEquals(x, game.getCharAtPoint(point));
    }

    @Test
    public void testGetFieldForOutput() {
        String field = ". . .\n. . .\n. . .\n";
        assertEquals(field, game.getFieldForOutput());
    }

    @Test
    public void testGetFieldForOutputWithOneStep() {
        Point point = new Point(1, 0);
        char x = 'X';
        game.setShootPoint(point, x);
        String field = ". X .\n. . .\n. . .\n";
        assertEquals(field, game.getFieldForOutput());
    }

    @Test
    public void testShootOnlyOnEmptyCellUs6() {
        Point point = new Point(1, 0);
        char x = 'X';
        boolean result;
        result = game.setShootPoint(point, x);
        assertTrue(result);
        result = game.setShootPoint(point, x);
        assertFalse(result);
    }

    @Test
    public void testDraw() {
        char[][] cells = {
                {'X', 'O', 'X'},
                {'O', 'O', 'X'},
                {'X', 'X', 'O'}
        };
        for (int i = 0; i < Game.SIZE; i++) {
            for (int j = 0; j < Game.SIZE; j++) {
                game.setShootPoint(new Point(j, i), cells[j][i]);
            }
        }
        assertEquals(Game.Status.DRAW, game.getGameStatus());
    }

    @Test
    public void testGameStart() {
        String mockInputForUser1 = "0 0\n0 1\n0 2\n";
        String mockInputForUser2 = "2 0\n1 1\n2 2\n";
        InputStream mockInputStream1 = new ByteArrayInputStream(mockInputForUser1.getBytes(StandardCharsets.UTF_8));
        InputStream mockInputStream2 = new ByteArrayInputStream(mockInputForUser2.getBytes(StandardCharsets.UTF_8));

        ConsoleShootStrategy consoleShootStrategy1 = new ConsoleShootStrategy(mockInputStream1);
        ConsoleShootStrategy consoleShootStrategy2 = new ConsoleShootStrategy(mockInputStream2);

        User user1 = new User(consoleShootStrategy1, 'X');
        User user2 = new User(consoleShootStrategy2, 'O');

        game.setUser1(user1);
        game.setUser2(user2);

        game.initCells();
        game.start();

        assertEquals(Game.Status.X_WIN, game.getGameStatus());
    }

    @Test
    public void testVerticalWin() {
        game.setShootPoint(new Point(0, 0), 'X');
        game.setShootPoint(new Point(1, 0), 'X');
        game.setShootPoint(new Point(2, 0), 'X');
        assertEquals(Game.Status.X_WIN, game.getGameStatus());
    }

    @Test
    public void testHorizontalWin() {
        game.setShootPoint(new Point(0, 1), 'O');
        game.setShootPoint(new Point(1, 1), 'O');
        game.setShootPoint(new Point(2, 1), 'O');
        assertEquals(Game.Status.O_WIN, game.getGameStatus());
    }
    @Test
    public void testDiagonalWinLeftToRight() {
        game.setShootPoint(new Point(0, 0), 'O');
        game.setShootPoint(new Point(1, 1), 'O');
        game.setShootPoint(new Point(2, 2), 'O');
        assertEquals(Game.Status.O_WIN, game.getGameStatus());
    }
    @Test
    public void testDiagonalWinRightToLeft() {
        game.setShootPoint(new Point(0, 2), 'X');
        game.setShootPoint(new Point(1, 1), 'X');
        game.setShootPoint(new Point(2, 0), 'X');
        assertEquals(Game.Status.X_WIN, game.getGameStatus());
    }


    @Test
    public void testRowWin() {
        game.setShootPoint(new Point(1, 0), 'X');
        game.setShootPoint(new Point(1, 1), 'X');
        game.setShootPoint(new Point(1, 2), 'X');
        assertEquals(Game.Status.X_WIN, game.getGameStatus());
    }

}