package tictactoe.ui;

import tictactoe.game.*;
import com.diogonunes.jcolor.AnsiFormat;
import tictactoe.game.player.Optimus;
import tictactoe.game.player.Player;
import tictactoe.game.player.HumanPlayer;
import tictactoe.game.player.Linus;

import java.text.ParseException;

import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.Ansi.colorize;

/**
 * Helper methods for doing console-based user interaction
 */
public class Console {

    // Define some colors and text styles for use in the console
    private static final AnsiFormat fPrompt = new AnsiFormat(GREEN_TEXT(), BOLD());
    private static final AnsiFormat fAlert = new AnsiFormat(YELLOW_TEXT());

    public static void println(String message) {
        IO.println(message);
    }

    /**
     * Prompt the user for input using the given promptMessage
     * @param promptMessage The message to prompt the user with
     * @return The user's response
     */
    public static String prompt(String promptMessage) {
        return IO.readln(fPrompt.format(promptMessage));
    }

    /**
     * Display an alert message to the user
     * @param message The message to display
     */
    public static void printAlert(String message) {
        IO.println(fAlert.format(message));
    }

    /**
     * Format the given game board for color display in an ANSI terminal
     * @param board A tictactoe game board
     */
    public static void showBoard(Board board) {
        var sb = new StringBuilder();
        for (var c : board.toString().toCharArray()) {
            if ( c == 'X' ) {
                sb.append(colorize("X", BRIGHT_CYAN_TEXT()));
            } else if ( c == 'O' ) {
                sb.append(colorize("O", BRIGHT_MAGENTA_TEXT()));
            } else {
                sb.append(c);
            }
        }
        IO.println(sb.toString());
    }

    /**
     * Repeatedly prompt the user to select a player for the given token
     * until the select a valid one of a set of valid players
     * @param whichPlayer The player for which to prompt
     * @return A player object representing the user's chosen player
     */
    public static Player promptForPlayer(Token whichPlayer) {

        var helpMessage = "To make a computer player, use the format '@<name>' where <name> is either Linus or Optimus.";

        while ( true ) {
            var input = prompt(fPrompt.format("Who will play " + whichPlayer + "? "));

            // Handle computer players
            if ( input.startsWith("@") ) {
                input = input.substring(1).toLowerCase(); // remove the '@' prefix

                switch ( input ) {
                    case "linus": return new Linus(input, whichPlayer);
                    case "optimus": return new Optimus(input, whichPlayer);
//                    default -> printAlert(helpMessage);
                    default: printAlert(helpMessage);
                }
            } else {
                return new HumanPlayer(input, whichPlayer);
            }
        }
    }


    /**
     * Repeatedly prompt the user for a position on which to place their next token.
     * If they enter an invalid response or an already-taken position they are re-prompted.
     * @param prompt The prompt to display to the user
     * @param board The current state of the game board
     * @return The position selected by the user
     */
    public static Position promptForPosition(String prompt, Board board) {

        final String helpMessage = "Input must be in the format 'row column', e.g., '1 2' or 't m' for the top middle cell.";

        while ( true ) {
            var input = IO.readln(fPrompt.format(prompt)).trim();

            // The .parse method may throw if the user entered invalid location text, so we try/catch
            try {

                var pos = Position.parse(input);

                if (! board.isEmptyAt(pos)) {
                    printAlert("That position is already taken.");
                    continue;
                }

                return pos;
            } catch ( ParseException e ) {
                printAlert(helpMessage);
            }
        }
    }
}
