package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

//public record Player(String name, Token token) {
// abstract class Player {
public abstract class Player {

    public String name;
    public Token token;
//    boolean alive; test

    public abstract Position getNextMove(Board b);

}
