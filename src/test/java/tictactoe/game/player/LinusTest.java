package tictactoe.game.player;

import org.junit.jupiter.api.Test;
import tictactoe.game.Board;
import tictactoe.game.Token;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import tictactoe.ui.Console;

class LinusTest {

    @Test
    void testGetNextMove(){
//        Linus linus = new Linus("Linus", Token.O);
//        Board b = new Board("X..\n...\n...");
//        b.place(linus.getNextMove(b), linus.token );
//        Console.println( b.toString() );
//        assertTrue( b.equals( new Board("XO.\n...\n...") ) );

        Linus linus = new Linus("Linus", Token.O);
        Board b = new Board(".X.\n...\n...");
        Console.println( b.toString() );
        b.place(linus.getNextMove(b), linus.token );
        Console.println( b.toString() );
        assertTrue( b.equals( new Board("OX.\n...\n...") ) );

    }
}