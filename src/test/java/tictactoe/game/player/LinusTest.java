package tictactoe.game.player;

import org.junit.jupiter.api.Test;
import tictactoe.game.Board;
import tictactoe.game.Token;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import tictactoe.ui.Console;

class LinusTest {

    @Test
    void testGetNextMoveO(){
        Board[] boards = {
            new Board(".X.\n...\n..."),
            new Board(".OX\n...\n..."),
            new Board("..X\n.X.\n..."),
            new Board("OXX\nOXO\n.XX"),
        };

        Board[] expectedBoards = {
            new Board("OX.\n...\n..."),
            new Board("OOX\n...\n..."),
            new Board("O.X\n.X.\n..."),
            new Board("OXX\nOXO\nOXX"),
        };

        Linus linus = new Linus("Linus", Token.O);

        int count = 0;
        for (Board b : boards){
            Console.println(count + " --------------------\n" );
            Console.println( b.toString() );
            b.place(linus.getNextMove(b), linus.token );
            Console.println( b.toString() );
            assertTrue( b.equals( expectedBoards[count] ) );
            count += 1;
        }
    }

    @Test
    void testGetNextMoveX(){
        Board[] boards = {
                new Board(".X.\n...\n..."),
                new Board(".OX\n...\n..."),
                new Board("..X\n.X.\n..."),
                new Board("OXX\nOXO\n.XX"),
        };

        Board[] expectedBoards = {
                new Board("XX.\n...\n..."),
                new Board("XOX\n...\n..."),
                new Board("X.X\n.X.\n..."),
                new Board("OXX\nOXO\nXXX"),
        };

        Linus linus = new Linus("Linus", Token.X);

        int count = 0;
        for (Board b : boards){
            Console.println(count + " --------------------\n" );
            Console.println( b.toString() );
            b.place(linus.getNextMove(b), linus.token );
            Console.println( b.toString() );
            assertTrue( b.equals( expectedBoards[count] ) );
            count += 1;
        }
    }


}