import ui.Console;
import core.Card;
public class Main {

    static void main() {
        Card testCard = new Card();
        Console.println(testCard.cardToString());
        /*
             Place your main game logic here.
             This is the ONLY code file that should have any reference to the Console class.

             The basic flow of the game is as follows:

             1. Prompt for player names
             2. Deal a shuffled deck evenly to each of the players
             3. While the players have cards and wish to continue:
                 b. All players draw one card and reveal them
                 c. The player with the higher card wins the round (or it's a tie)
         */

        /* The requirements are to simply make a draw cards, see who has higher card game. but we can do war optonally.*/

    }
}
