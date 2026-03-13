package tictactoe.game.player;

import org.junit.jupiter.api.Test;
import tictactoe.game.Board;
import tictactoe.game.Token;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import tictactoe.ui.Console;

class OptimusTest {
    // Most of my testing here was done by actually playing against the bot. Or having 2 bots against each other.
    @Test
    void testGetNextMoveX(){
        Board[] boards = {
                new Board("XX.\nO.O\nX.."),
                new Board("XO.\nOX.\n..."),
        };

        Board[] expectedBoards = {
                new Board("XXX\nO.O\nX.."),
                new Board("XOX\nOX.\n..."),
        };

        Optimus optimus = new Optimus("Optimus", Token.X);

        int count = 0;
        for (Board b : boards){
            Console.println(count + " --------------------\n" );
            Console.println( b.toString() );
            b.place(optimus.getNextMove(b), optimus.token );
            Console.println( b.toString() );
            assertTrue( b.equals( expectedBoards[count] ) );
            count += 1;
        }
    }

    @Test
    void testGetNextMoveO(){
        Board[] boards = {
                new Board("XX.\nO.O\nX.."),
                new Board("XO.\nOX.\n..."),
        };

        Board[] expectedBoards = {
                new Board("XXO\nO.O\nX.."),
                new Board("XOO\nOX.\n..."),
        };

        Optimus optimus = new Optimus("Optimus", Token.O);

        int count = 0;
        for (Board b : boards){
            Console.println(count + " --------------------\n" );
            Console.println( b.toString() );
            b.place(optimus.getNextMove(b), optimus.token );
            Console.println( b.toString() );
            assertTrue( b.equals( expectedBoards[count] ) );
            count += 1;
        }
    }
}