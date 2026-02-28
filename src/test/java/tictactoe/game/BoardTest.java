package tictactoe.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class BoardTest {

    @Test
    void testEmptyBoard(){
        Board b = new Board();
//        Console.println(b.toString());
        Assertions.assertTrue(b.isEmptyAt( new Position(Row.Top, Col.Left)  ));
        Assertions.assertTrue(b.isEmptyAt( new Position(Row.Top, Col.Middle)  ));
        Assertions.assertTrue(b.isEmptyAt( new Position(Row.Top, Col.Right)  ));
        Assertions.assertTrue(b.isEmptyAt( new Position(Row.Middle, Col.Left)  ));
        Assertions.assertTrue(b.isEmptyAt( new Position(Row.Middle, Col.Middle)  ));
        Assertions.assertTrue(b.isEmptyAt( new Position(Row.Middle, Col.Right)  ));
        Assertions.assertTrue(b.isEmptyAt( new Position(Row.Bottom, Col.Left)  ));
        Assertions.assertTrue(b.isEmptyAt( new Position(Row.Bottom, Col.Middle)  ));
        Assertions.assertTrue(b.isEmptyAt( new Position(Row.Bottom, Col.Right)  ));
    }

    @Test
    void testValid_getWinnerX() {
        String[] boardStrings = {
                "---" +
                "XXX" +
                "---",

                "X--" +
                "XXO" +
                "--X",

                "-X-" +
                "XXX" +
                "-XO",

                "XXX" +
                "XOX" +
                "-XO",

                "---" +
                "---" +
                "XXX",

                "--X" +
                "--X" +
                "-OX",

                "X--" +
                "X-X" +
                "X-O",

                // BUG SEEMS TO BE HERE
                // Now, playing the game I can see that either X or O cannot get a win on a left diagonal.
                // Fixed, see Board.java
                "--X" +
                "-XO" +
                "XOO",

    };
        for (String m: boardStrings){
            Board b = new Board(m);
            Assertions.assertEquals( b.getWinner(), Optional.of(Token.X));
        }
    }

    @Test
    void testValid_getWinnerO() {
        String[] boardStrings = {
                "---" +
                "OOO" +
                "---",

                "OX-" +
                "OOX" +
                "--O",

                "-X-" +
                "OOO" +
                "-XO",

                "OOO" +
                "XOX" +
                "-XO",

                "---" +
                "---" +
                "OOO",

                "--O" +
                "--O" +
                "-OO",

                "O--" +
                "O-X" +
                "O-O",

                "--O" +
                "-OX" +
                "OXO",

        };
        for (String m: boardStrings){
            Board b = new Board(m);
            Assertions.assertEquals( b.getWinner(), Optional.of(Token.O));
        }
    }

    @Test
    void getWinnerTestTie() {
//        Board b = new Board(
//        "OOX" +
//        "XXO" +
//        "OXO");
//        Assertions.assertEquals( Optional.empty(), b.getWinner() );

        Board b1 = new Board(
        "OOX" +
            "XXO" +
            "OXO");
        Assertions.assertEquals( Optional.empty(), b1.getWinner() );
    }

    @Test
    void placeTest() {
        Board b = new Board();
        b.place(new Position(Row.Top, Col.Right), Token.X );
        b.place(new Position(Row.Top, Col.Middle), Token.O );
        Assertions.assertFalse(b.isEmptyAt(new Position(Row.Top, Col.Right) ));
        Assertions.assertFalse(b.isEmptyAt(new Position(Row.Top, Col.Middle) ));
//        Console.println(b.toString());
    }

    @Test
    void isEmptyAtTest() {
        Board b = new Board(
                "-X-" +
                "OOO" +
                "-XO");
        Assertions.assertTrue( b.isEmptyAt( new Position(Row.Top, Col.Left) ) );
        Assertions.assertTrue( b.isEmptyAt( new Position(Row.Bottom, Col.Left) ) );
        Assertions.assertTrue( b.isEmptyAt( new Position(Row.Top, Col.Right) ) );

        Assertions.assertFalse( b.isEmptyAt( new Position(Row.Top, Col.Middle) ) );
        Assertions.assertFalse( b.isEmptyAt( new Position(Row.Middle, Col.Left) ) );
        Assertions.assertFalse( b.isEmptyAt( new Position(Row.Middle, Col.Middle) ) );
        Assertions.assertFalse( b.isEmptyAt( new Position(Row.Middle, Col.Right) ) );
        Assertions.assertFalse( b.isEmptyAt( new Position(Row.Bottom, Col.Middle) ) );
        Assertions.assertFalse( b.isEmptyAt( new Position(Row.Bottom, Col.Right) ) );
    }
}