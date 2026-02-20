package tictactoe;

import tictactoe.game.TicTacToeGame;
import tictactoe.game.Position;
import tictactoe.ui.Console;

import static tictactoe.game.TicTacToeGame.Status.*;

class Main {
    static void main() {

        Console.println("Welcome to Tic Tac Toe!");
        var nameX = Console.prompt("Player X name: ");
        var nameO = Console.prompt("Player O name: ");
        var game = new TicTacToeGame(nameX, nameO);

        while (game.getStatus() == InProgress) {

            var player = game.whoseTurn();

            var board = game.getBoard();

            Position pos;
            while (true) {
                var prompt = "%s's turn (%s). Enter your move (row column): ".formatted(player.name(), player.token());
                pos = Console.promptForPosition(prompt, board);

                if (game.isValidPosition(pos)) {
                    break;
                }
//              Console.printAlert("That position is not valid. Please enter a valid position.");
                Console.printColoredAlert("That position is not valid. Please enter a valid position.", Console.redFg, Console.blackBg);
            }

            Console.println("%s plays %s at %s %s".formatted(player.name(), player.token(), pos.row(), pos.col()));

            game.placeTokenAt(pos);

            Console.println(game.getBoard().toString());

            switch (game.getStatus()) {
                case Draw -> Console.println("It's a draw!");
                case XWins, OWins -> Console.println("%s wins!".formatted(game.whoseTurn().name()));
            }

        }
    }
}