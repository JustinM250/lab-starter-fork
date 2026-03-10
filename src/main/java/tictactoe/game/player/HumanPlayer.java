package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;
import tictactoe.ui.Console;

public class HumanPlayer extends Player {

    /**
     * Prompts the player to pick their next move.
     * Will continue to prompt until the player picks a valid move (i.e. an empty position on the board)
     * @param board The current state of the board
     * @return The (valid) position on the board where the player wants to place their token
     */
    public Position getNextMove(Board board) {
        while (true) {
            var prompt = "%s's turn (%s). Enter your move (row column): ".formatted(this.name, this.token);
            var pos = Console.promptForPosition(prompt, board);

            if (board.isEmptyAt(pos)) {
                return pos;
            }
            Console.printAlert("That position is not valid. Please enter a valid position.");
        }
    }

    // 2.4. Create the necessary constructor method to allow a HumanPlayer with a specific name and token to be created.
    // (You will need to use the ‘super’ keyword.) <<< Didn't end up using 'super'.
    public HumanPlayer(String name, Token token){
        this.name = name;
        this.token = token;
    }
}
