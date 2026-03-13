package tictactoe;

import tictactoe.game.TicTacToeGame;
import tictactoe.game.Position;
import tictactoe.game.Token;
import tictactoe.ui.Console;

import static tictactoe.game.TicTacToeGame.Status.*;

class Main {
    static void main() {

        Console.println("Welcome to Tic Tac Toe!\nWhen asked 'who will play #?', enter the name '@Linus' or '@Optimus' to assume a computer player.\n- Linus is a player who picks the first available slot.\n- Optimus? Well, you can't beat Optimus. ");
        var playerX = Console.promptForPlayer(Token.X);
        var playerO = Console.promptForPlayer(Token.O);
        var game = new TicTacToeGame(playerX, playerO);

        while (game.getStatus() == InProgress) {

            var turnData = game.doNextTurn();

            Console.println("%s plays %s at %s %s".formatted(turnData.whoseTurn().name, turnData.whoseTurn().token, turnData.positionPlayed().row(), turnData.positionPlayed().col()));
            Console.showBoard(turnData.newBoardState());

            switch (game.getStatus()) {
                case Draw -> Console.println("It's a draw!");
                case XWins, OWins -> Console.println("%s wins!".formatted(turnData.whoseTurn().name));
            }

        }
    }
}