package tictactoe.game;

import java.text.ParseException;

/**
 * Represents a game board position
 * @param row
 * @param col
 */

public record Position(Row row, Col col) {

    /**
     * Parses a string representation of a position on a TicTacToe board and returns the corresponding Position object.
     * <ul>
     * <li>The string representation can be in various formats, including numeric and various abbreviated forms for rows and columns.
     *     (See the {@link Col#parse} and {@link Row#parse} methods for valid row and column string representations.)
     * <li>The position string can be in the form of two characters, where the first character represents the row
     *     and the second character represents the column. For example, "tl" or "11" represents the top-left position,
     *     "mc" or "22" represents the middle-center position, and "bm" or "32" represents the bottom-centre position.
     * <li>Alternatively, the position string can consist of two parts separated by commas, semicolons, or spaces.
     *     The first part represents the row and the second part represents the column.
     *     Examples: "top,left", "middle;center", and "1 3" are all valid representations.
     * <li>The position string is case-insensitive, meaning that "TL", "tL", and "tl" are all  valid and equivalent.
     * <li>Extra spaces before, after, or between the row and column parts are ignored.
     * </ul>
     * @param positionStr A string representation of a position on a TicTacToe board (See additional notes below)
     * @return The position corresponding to the given string representation
     * @throws ParseException if the given string is not a valid representation for a position
     */
    public static Position parse(String positionStr) throws ParseException {
        // make things case-insensitive and remove extra spaces
        positionStr = positionStr.trim().toLowerCase();

        // There's no valid position string with less than 2 characters
        if ( positionStr.length() < 2 ) {
            throw new ParseException("Invalid position: " + positionStr, 0);
        }

        Row row;
        Col col;

        // Handle "tl", "br", etc.
        if ( positionStr.length() == 2 ) {
            row = Row.parse(positionStr.substring(0,1));
            col = Col.parse(positionStr.substring(1));
        } else {

            // Allow for separating position parts using one or more comma, semi-colon, or space
            var parts = positionStr.split("[,; ]+");

            if (parts.length != 2) {
                throw new ParseException("Invalid position: " + positionStr, 0);
            }

            row = Row.parse(parts[0]);
            col = Col.parse(parts[1]);
        }

        return new Position(row, col);
    }
}
