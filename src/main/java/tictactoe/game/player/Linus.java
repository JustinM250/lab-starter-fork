package tictactoe.game.player;

import tictactoe.game.*;

import java.text.ParseException;

public class Linus extends Player {


    public Linus(String name, Token token){
        this.name = name;
        this.token = token;
    }

    public Position getNextMove(Board b){
        Position currentPos = new Position(Row.Top, Col.Left);
        for(int y = 1; y <= 3; y++){
            for(int x = 1; x <= 3; x++){

//              currentPos = Position.parse(String.valueOf(y) + "," + String.valueOf(x) );
//              this, in my mind, should work but there's an unhandled exception. the IDE said to add a try catch.
                try {
                    currentPos = Position.parse(String.valueOf(y) + String.valueOf(x) );
                }
                catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                if(b.isEmptyAt( currentPos ) ){
                    return currentPos;
                }
            }
        }
        return currentPos;

    }
}

