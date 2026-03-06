package tictactoe.game;

import tictactoe.game.player.Player;

/**
 * Represents the current state of a tictactoe game, including which player's turn it is,
 * and the current state of the game board
 */
public class TicTacToeGame {

    /**
     * A record representing the data from a single turn of the game
     * @param whoseTurn The player who took the turn
     * @param positionPlayed The position on the board where the player placed their token
     * @param newBoardState The resulting board after the player's turn
     */
    public record TurnData(Player whoseTurn, Position positionPlayed, Board newBoardState) {}

    /**
     * Represents the high-level status of the game
     */
    public enum Status { InProgress, Draw, XWins, OWins }

    /**
     * The players in the game
     */
    private final Player playerX;
    private final Player playerO;

    /**
     * The current state of the game board
     */
    private final Board board = new Board();

    private Player whoseTurn;

    /**
     * Initialize a new TicTacToe game with the given players
     * @param playerX The X player name
     * @param playerO The O player name
     */
    public TicTacToeGame(Player playerX, Player playerO) {
        this.playerX = playerX;
        this.playerO = playerO;

        this.whoseTurn = playerX;
    }

    /**
     * Performs the next turn of the game by prompting the current player to pick their next move,
     * placing their token at the chosen position, and returning a TurnData record containing the details of the turn
     * @return A TurnData record containing the details of the turn that was just taken
     */
    public TurnData doNextTurn() {
        var player = whoseTurn;

        var pos = player.getNextMove(board);

        placeTokenAt(pos);

        return new TurnData(player, pos, board);
    }

    /**
     * Place the current player's token at the given position, and switch turns if the game is still in progress
     * @param pos The position to place the token at
     */
    private void placeTokenAt(Position pos) {
        board.place(pos, whoseTurn.token);

        // If the game is still in progress, switch turns
        // (If there's a winner, we DON'T want to switch turns so that 'whoseTurn' still reflects the previous
        // turn-taker as the winner)
        if ( getStatus() == Status.InProgress ) {
            if ( whoseTurn == playerX ) {
                whoseTurn = playerO;
            } else {
                whoseTurn = playerX;
            }
        }
    }

    /**
     * @return The current status of the game board
     */
    public Status getStatus() {
        if (board.getWinner().isEmpty()) {    // If there's no winner...
            if (!board.isFull()) {            // ...a non-full board means the game is still in progress
                return Status.InProgress;
            } else {                          // ...while a full board means it's a draw
                return Status.Draw;
            }
        } else {
            return board.getWinner().get() == Token.X ? Status.XWins : Status.OWins;
        }
    }

}