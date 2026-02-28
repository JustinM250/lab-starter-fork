package tictactoe.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tictactoe.ui.Console;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    // Valids
    @Test
    public void testValidParseInputs_SeparatedNumbers() throws ParseException{

        Assertions.assertEquals(Position.parse("1,1"), new Position(Row.Top, Col.Left ) );
        Assertions.assertEquals(Position.parse("1,2"), new Position(Row.Top, Col.Middle ) );
        Assertions.assertEquals(Position.parse("1;2"), new Position(Row.Top, Col.Middle ) );
        Assertions.assertEquals(Position.parse("1 2"), new Position(Row.Top, Col.Middle ) );
        Assertions.assertEquals(Position.parse("1,3"), new Position(Row.Top, Col.Right ) );
        Assertions.assertEquals(Position.parse("2,1"), new Position(Row.Middle, Col.Left ) );
        Assertions.assertEquals(Position.parse("3,2"), new Position(Row.Bottom, Col.Middle ) );

        Assertions.assertEquals(Position.parse("11"), new Position(Row.Top, Col.Left ) );
        Assertions.assertEquals(Position.parse("21"), new Position(Row.Middle, Col.Left ) );
        Assertions.assertEquals(Position.parse("32"), new Position(Row.Bottom, Col.Middle ) );
    }

    @Test
    public void testValidParseInputs_SeparatedNumbersNotEqual() throws ParseException{

        Assertions.assertNotEquals(Position.parse("1,1"), new Position(Row.Top, Col.Middle ) );
        Assertions.assertNotEquals(Position.parse("2,1"), new Position(Row.Middle, Col.Right ) );
        Assertions.assertNotEquals(Position.parse("3,2"), new Position(Row.Bottom, Col.Left ) );
    }

    @Test
    public void testValidParseInputs_UnseparatedNumbers() throws ParseException{
        Assertions.assertEquals(Position.parse("11"), new Position(Row.Top, Col.Left ) );
        Assertions.assertEquals(Position.parse("21"), new Position(Row.Middle, Col.Left ) );
        Assertions.assertEquals(Position.parse("32"), new Position(Row.Bottom, Col.Middle ) );

    }

    @Test
    public void testValidParseInputs_Words() throws ParseException{
        Assertions.assertEquals(Position.parse("top left"), new Position(Row.Top, Col.Left ) );
        Assertions.assertEquals(Position.parse("top, Left "), new Position(Row.Top, Col.Left ) );
        Assertions.assertEquals(Position.parse("top; left"), new Position(Row.Top, Col.Left ) );
        Assertions.assertEquals(Position.parse("middle; left"), new Position(Row.Middle, Col.Left ) );
        Assertions.assertEquals(Position.parse("bottom; middle"), new Position(Row.Bottom, Col.Middle ) );
        Assertions.assertEquals(Position.parse("bottom          middle"), new Position(Row.Bottom, Col.Middle ) );
        Assertions.assertEquals(Position.parse("bottom , middle"), new Position(Row.Bottom, Col.Middle ) );
    }

    @Test
    public void testValidParseInputs_Abbreviations() throws ParseException{
        Assertions.assertEquals(Position.parse("tM "), new Position(Row.Top, Col.Middle ) );
        Assertions.assertEquals(Position.parse("t,M "), new Position(Row.Top, Col.Middle ) );
        Assertions.assertEquals(Position.parse("tr"), new Position(Row.Top, Col.Right ) );
        Assertions.assertEquals(Position.parse("t r"), new Position(Row.Top, Col.Right ) );
        Assertions.assertEquals(Position.parse("middle; l"), new Position(Row.Middle, Col.Left ) );
        Assertions.assertEquals(Position.parse("bottom m"), new Position(Row.Bottom, Col.Middle ) );
        Assertions.assertEquals(Position.parse("b, m"), new Position(Row.Bottom, Col.Middle ) );
        Assertions.assertEquals(Position.parse("b ; m"), new Position(Row.Bottom, Col.Middle ) );
    }

    // Invalids
    @Test
    public void testInvalidParseInputs_SeparatedNumbers(){

        Assertions.assertThrows(ParseException.class, () -> Position.parse("1,4") );
//        Assertions.assertThrows(ParseException.class, () -> Position.parse("1,2") );
        Assertions.assertThrows(ParseException.class, () -> Position.parse("4,3") );
        Assertions.assertThrows(ParseException.class, () -> Position.parse("0,3") );
        Assertions.assertThrows(ParseException.class, () -> Position.parse("2,4") );
        Assertions.assertThrows(ParseException.class, () -> Position.parse("2 4") );
    }
    @Test
    public void testInvalidParseInputs_Blah(){

        Assertions.assertThrows(ParseException.class, () -> Position.parse("blah blah blah") );
//        Assertions.assertThrows(ParseException.class, () -> Position.parse("1,2") ); // This would fail the test.
        Assertions.assertThrows(ParseException.class, () -> Position.parse("blah,middle") );
//        Assertions.assertThrows(ParseException.class, () -> Position.parse("b,middle") );
        Assertions.assertThrows(ParseException.class, () -> Position.parse("bmiddle") );
        Assertions.assertThrows(ParseException.class, () -> Position.parse("b|middle") );
    }
}

// push failed, sample text.
//