package tictactoe.game.player;

import tictactoe.game.*;

import java.text.ParseException;
import java.util.Random;

public class Optimus extends Player {

    public Optimus(String name, Token token){
        this.name = name;
        this.token = token;
    }


    public Position getNextMove(Board b){
        Position chosenPos = new Position(Row.Top, Col.Left);

        int emptyCount = 0;

        for(int y = 1; y <= 3; y++){
            for(int x = 1; x <= 3; x++){
                try {
                    if (b.isEmptyAt( Position.parse(String.valueOf(y) + String.valueOf(x) ) ) ){
                        emptyCount += 1;
                    }
                }
                catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (emptyCount == 9){
            Random r = new Random();
            int randX = r.nextInt(4);
            int randY = r.nextInt(4);
            try{
                chosenPos = Position.parse( String.valueOf(randX) + String.valueOf(randY) );
            }
            catch (ParseException e){
                throw new RuntimeException(e);
            }

        }
        else{
            // I used this video: https://www.youtube.com/watch?v=5y2a0Zhgq0U , to help me understand.
            // We're going to assume X is Optimus, O is player.

            chosenPos = minimax(b);
        }



        return chosenPos;

    }

//    public record Minimax(int score, Position p, Board b, Player player ){
    public record Minimax(int score, Position p ){
        Position recursive_call(Board b, Position p, int score){
            if (b.isFull()){
                return recursive_call(b, null, 0);
            }
            else if (b.getWinner().equals(Token.X) ){
                return recursive_call(b, null, 1);
            }
            else if (b.getWinner().equals(Token.X) == false ){
                return recursive_call(b, null, -1);
            }
            else{
                Position bestResult;
                if (b.){ // If turn is X
                    return recursive_call(b, null, -1);
                }
                else if (b.){
                    return recursive_call(b, null, 1);
                }
                Board copiedBoard = new Board(b);
                Position firstEmpty = new Position(Row.Top, Col.Left);

                for(int y = 1; y <= 3; y++){
                    for(int x = 1; x <= 3; x++){

                        try {
                            firstEmpty = Position.parse(String.valueOf(y) + String.valueOf(x) );
                        }
                        catch (ParseException e) {
                            throw new RuntimeException(e);
                        }

                        if(b.isEmptyAt( firstEmpty ) ){
                            y = 4;
                            x = 4;
                            return firstEmpty;
                        }
                    }
                }

                copiedBoard.place(firstEmpty, Token.X  ); // There's no placeNextToken when searching all files?
                // Get the result of minimax on copiedBoard;

                // return bestResult;
            }
        }
    }

}

