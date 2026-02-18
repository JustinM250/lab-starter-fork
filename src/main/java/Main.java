import ui.Console;
import core.Card;
import core.CardStack;
import core.Player;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    /**
     * @param the message to prompt the user.
     * @return returns the integer the user provided.
     */
    public static int getInt(String m)
    {
        Scanner inputScanner = new Scanner(System.in);
        while(true)
        {
            System.out.println(m);
            String input = inputScanner.nextLine();
            try {
                // System.out.println(input.length());
                if (input.length() < 3){ // This is to avoid the for loop taking too long.
                    int returnValue = Integer.parseInt(input);
                    return returnValue;
                }
                else
                {
                    System.out.println("Please enter a # less than 100");
                    // throw new RuntimeException("Please enter a number less than 100."); // Testing error throwing.
                }

            } catch (NumberFormatException fail) {
                System.out.println("ERROR, TRY AGAIN");
                continue;
            }
        }
    }

    static void main() {
        ArrayList<Player> players = new ArrayList<Player>();

        Console.println("WELCOME TO THE GAME OF WAR");
        int numPlayers;
        while(true){
            numPlayers = getInt("ENTER NUMBER OF PLAYERS (min 2, max 4):");
            if (numPlayers >= 2 && numPlayers < 5){
                break;
            }
            else{
                Console.println("ERROR, TRY AGAIN");
                continue;
            }
        }

        for (int i = 0; i < numPlayers; i++) {
            Console.println("ENTER PLAYER %d NAME: ".formatted(i+1));
            Scanner nameScanner = new Scanner(System.in);
            String inputtedName = nameScanner.nextLine();
            players.add(new Player(inputtedName));
        }

        CardStack gameStack = new CardStack(true);

        boolean gameLoopActive = true;
        while(gameLoopActive){
//            Console.println("This is the game's stack: [DEBUG]");
//            Console.println(gameStack.stackToString() );
            Console.println("CARDS LEFT: %d \n".formatted(gameStack.getStackSize() ));

            ArrayList<Card> playerDrawnCards = new ArrayList<Card>();
            Console.println("All players draweth one card from thee stack!");
            for (int i = 0; i < players.size(); i++) {
                Card pulledCard = gameStack.pullCardFromTop();
                Console.println(players.get(i).getName()  + ": "+ pulledCard.cardToString() );
                playerDrawnCards.add(pulledCard );
            }

            boolean tie_found = false;
            int highestValue = -1;
            int highestValuedPlayer = -1;
            for (int i = 0; i < playerDrawnCards.size(); i++) {
                int v = playerDrawnCards.get(i).rankToInt();
                if (v > highestValue){
                    highestValue = v;
                    highestValuedPlayer = i;
                }
                else if (v == highestValue){
                    tie_found = true;
                }
            }

            // Yes, I'm aware of the reroll mechanics in the actual game. For mine, I decided to discard all cards if equal.
            if (tie_found){
                Console.println("Oh my, a tie! All cards discardeth!\n");
            }
            else {
                for (int i = 0; i < playerDrawnCards.size(); i++) {
                    players.get(highestValuedPlayer).stack.addToStack(playerDrawnCards.get(i)) ;
                }
                Console.println("Winner for thy round is %s, thou taketh all!\n".formatted(players.get(highestValuedPlayer).getName() ) );
            }

            for (int i = 0; i < players.size(); i++){
                Console.println(players.get(i).getName() + ": " + players.get(i).stack.getStackValue() + " ( "  + players.get(i).stack.stackToString()  + ")" );
            }

            Console.println("CARDS LEFT NOW: %d".formatted(gameStack.getStackSize() ));

            Scanner readKeyScanner = new Scanner(System.in);
            Console.println("(press any key to continue)");
            readKeyScanner.nextLine();
            Console.println("-------------------------------------------------------------------------------------");
            if (gameStack.getStackSize() == 0 ){
                gameLoopActive = false;
            }
            else if (numPlayers == 3 && gameStack.getStackSize() < 3 ){
                gameLoopActive = false;
            }

            if (gameLoopActive == false){
                int highestStackValue = -1;
                int winningPlayer = -1;
                for (int i = 0; i < players.size(); i++) {
                    int v = players.get(i).stack.getStackValue();
                    if (v > highestStackValue){
                        highestStackValue = v;
                        winningPlayer = i;
                    }
                }
                Console.println(players.get(winningPlayer).getName() + " WINS THE WAR WITH A STACK VALUE OF " + highestStackValue );
            }
        }
    }
}
// END -------------------------------------------------------------------------------