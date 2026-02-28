package tictactoe.ui;

import java.text.ParseException;
import java.util.Scanner;

import com.diogonunes.jcolor.Attribute;
import tictactoe.game.Board;
import tictactoe.game.Position;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

/**
 * Helper methods for doing console-based user interaction
 */
public class Console {

    public static Attribute whiteFg = WHITE_TEXT();
    public static Attribute redFg = RED_TEXT();
    public static Attribute yellowFg = YELLOW_TEXT();
    public static Attribute blueBg = BLUE_TEXT();
    public static Attribute greenFg = GREEN_TEXT();
    public static Attribute purpleFg = BRIGHT_MAGENTA_TEXT();
    public static Attribute blackBg = BLACK_BACK();

    public static void println(String message) {
        IO.println(message);
    }

    /**
     /**
     * Prompt the user for input using the given promptMessage
     * @param promptMessage The message to prompt the user with
     * @return The user's response
     */
    public static String prompt(String promptMessage) {
        IO.print(promptMessage);
        var scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

     /**
     * Prompt the user for an input, now with colored text!!!
     * @param m The message to prompt the user with
     * @param fg The foreground color
     * @param bg The background color
     * @return The user's response
     */
    public static String promptColored(String m, Attribute fg, Attribute bg) {
        printColoredAlert(m, fg, bg);
        var scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    /**
     * Display an alert message to the user
     * @param message The message to display
     */
    public static void printAlert(String message) {
        IO.println(message);
    }

    /**
     * Display an alert message to the user, now colored!!!
     * @param m The message to display
     * @param fg The foreground color
     * @param bg The background color
     */
    public static void printColoredAlert(String m, Attribute fg, Attribute bg) {
        IO.println(colorize(m, fg, bg));
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
            var input = IO.readln(prompt).trim();

            // The .parse method may throw if the user entered invalid location text, so we try/catch
            try {

                var pos = Position.parse(input);

                if (! board.isEmptyAt(pos)) {
//                  printAlert("That position is already taken.");
                    printColoredAlert("That position is already taken.", redFg, blackBg);
                    continue;
                }

                return pos;
            } catch ( ParseException e ) {
                printColoredAlert(helpMessage, yellowFg, blackBg);
            }
        }
    }
}
