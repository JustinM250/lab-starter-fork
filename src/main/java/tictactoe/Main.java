package tictactoe;

import tictactoe.game.TicTacToeGame;
import tictactoe.game.Position;
import tictactoe.game.Token;
import tictactoe.ui.Console;

import javax.swing.*;
import java.lang.classfile.Attribute;

import static tictactoe.game.TicTacToeGame.Status.*;

class Main {
    static void main() {
        com.diogonunes.jcolor.Attribute xCol = Console.blueBg;
        com.diogonunes.jcolor.Attribute oCol = Console.purpleFg;
        Console.println("Welcome to Tic Tac Toe!");
        var nameX = Console.promptColored("Player X name: ", xCol, Console.blackBg);
        var nameO = Console.promptColored("Player O name: ", oCol, Console.blackBg);
        var game = new TicTacToeGame(nameX, nameO);

        while (game.getStatus() == InProgress) {

            var player = game.whoseTurn();

            var board = game.getBoard();

            Position pos;
            while (true) {
                com.diogonunes.jcolor.Attribute col = Console.whiteFg;
                if (player.token() == Token.X){
                    col = xCol;
                }
                else if (player.token() == Token.O){
                    col = oCol;
                }
                Console.printColoredAlert("%s's turn (%s)!: ".formatted(player.name(), player.token()), col, Console.blackBg  );
                var prompt = "Enter your move (row column): ";
                pos = Console.promptForPosition(prompt, board);

                if (game.isValidPosition(pos)) {
                    break;
                }
//              Console.printAlert("That position is not valid. Please enter a valid position.");
                Console.printColoredAlert("That position is not valid. Please enter a valid position.", Console.redFg, Console.blackBg);
            }

            Console.printColoredAlert("%s plays %s at %s %s".formatted(player.name(), player.token(), pos.row(), pos.col()), Console.yellowFg, Console.blackBg );

            game.placeTokenAt(pos);

            Console.println(game.getBoard().toString());

            switch (game.getStatus()) {
                case Draw -> Console.println("It's a draw!");
                case XWins, OWins -> Console.println("%s wins!".formatted(game.whoseTurn().name()));
            }

        }
    }
}