package tictactoe.game;

import java.util.Arrays;
import java.util.Optional;

/**
 * Represents a TicTacToe game board
 */
public class Board {

    /**
     * The current game board state
     */
    private final Token[][] board = new Token[3][3];

    /**
     * Initialize a new, empty tictactoe board
     */
    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = null;
            }
        }
    }

    /**
     * Copy constructor. Creates a new board that is a copy of the given one.
     * @param other The board to copy
     */
    public Board(Board other) {
        for (int i = 0; i < 3; i++) {
            System.arraycopy(other.board[i], 0, board[i], 0, 3);
        }
    }

    /**
     * Initialize a new board with the given state (useful for testing)
     * @param b A string representation of the board state.
     *          The string should be 3 lines long, with each line containing 3 characters,
     *          or one 9 character string.
     *          Any character other than 'X', 'O' is considered an empty spot on the board.
     *          E.g. "XOX-XO--O" or "XOX\n-XO\n--O"
     *               or using a text block:
     *               """
     *               XOX
     *               -XO
     *               --O
     *               """
     */
    public Board(String b) {
        b = b.replace("\n", "").toUpperCase();
        if (b.length() != 9) {
            throw new IllegalArgumentException("Invalid board state string");
        }
        for (int i = 0; i < b.length(); i++) {
            if (b.charAt(i) == 'X') {
                this.place(new Position(Row.values()[i / 3], Col.values()[i % 3]), Token.X);
            } else if (b.charAt(i) == 'O') {
                this.place(new Position(Row.values()[i / 3], Col.values()[i % 3]), Token.O);
            }
        }
    }

    /**
     * @param pos A board position
     * @return The row index in this.board corresponding to the given board position
     */
    private int rowIdx(Position pos) {
        return switch (pos.row()) {
            case Top -> 0;
            case Middle -> 1;
            case Bottom -> 2;
        };
    }

    /**
     * @param pos A board position
     * @return The column index in this.board corresponding to the given board position
     */
    private int colIdx(Position pos) {
        return switch (pos.col()) {
            case Left -> 0;
            case Middle -> 1;
            case Right -> 2;
        };
    }

    /**
     * @return The PlayerToken for the winner of the game, or Optional.empty() if there is currently no winner
     */
    public Optional<Token> getWinner() {
        // Check the rows and columns
        for (int i = 0; i < 3; i++) {
            // Check if the row is all the same
            if (board[i][0] != null && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return Optional.of(board[i][0]);
            }
            // Check if the column is all the same
            if (board[0][i] != null && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return Optional.of(board[0][i]);
            }
        }
        // Check the diagonals
        if (board[0][0] != null && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return Optional.of(board[0][0]);
        }
//        if (board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[1][0]) { // THE BUG (i think)
        if (board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return Optional.of(board[0][2]);
        }
        return Optional.empty();
    }

    /**
     * @return true if the board is full; false otherwise
     */
    public boolean isFull() {
        for (var row : board) {
            for (var cell : row) {
                if (cell == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param pos A game board position
     * @return true if the given board position is empty; false otherwise
     */
    public boolean isEmptyAt(Position pos) {
        return board[rowIdx(pos)][colIdx(pos)] == null;
    }

    /**
     * Places the given token on the game board at the given position.
     * @param pos A game board position
     * @param token The token to place
     */
    public void place(Position pos, Token token) {
        board[rowIdx(pos)][colIdx(pos)] = token;
    }

    /**
     * @return A 3x3 string representation of the game board
     *         E.g.
     *               .XO
     *               .X.
     *               O..
     */
    @Override
    public String toString() {
        var boardString = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardString.append(board[i][j] == null ? '.' : board[i][j].toString());
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Board other)) return false;

        return Arrays.deepEquals(this.board, other.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }
}
