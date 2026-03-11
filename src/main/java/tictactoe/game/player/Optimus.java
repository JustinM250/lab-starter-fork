package tictactoe.game.player;

import tictactoe.game.*;
import tictactoe.ui.Console;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Random;

public class Optimus extends Player {

    public Optimus(String name, Token token){
        this.name = name;
        this.token = token;
    }


//    public Position getNextMove(Board b){
//        Position currentPos = new Position(Row.Top, Col.Left);
//        for(int y = 1; y <= 3; y++){
//            for(int x = 1; x <= 3; x++){
//
////              currentPos = Position.parse(String.valueOf(y) + "," + String.valueOf(x) );
////              this, in my mind, should work but there's an unhandled exception. the IDE said to add a try catch.
//                try {
//                    currentPos = Position.parse(String.valueOf(y) + String.valueOf(x) );
//                }
//                catch (ParseException e) {
//                    throw new RuntimeException(e);
//                }
//
//                if(b.isEmptyAt( currentPos ) ){
//                    return currentPos;
//                }
//            }
//        }
//        return currentPos;
//
//    }

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
            // This too: https://www.youtube.com/watch?v=SLgZhpDsrfc

            chosenPos = Minimax.run(b, token).p;
        }
        return chosenPos;

    }

//    public record Minimax(int score, Position p, Board b, Player player ){
    public record Minimax(int score, Position p ){

        static Minimax run(Board b, Token tok){

            Console.println("RUNNING RECURSION");
            Minimax worstCase = new Minimax(-1, null);
            return recursive_call( worstCase, -1, b, tok );

//            return new Minimax(1, bestResult);
        }

        static Minimax recursive_call( Minimax best, int current_score, Board b, Token t   ){

            Console.println("CALL");
            if ( !b.getWinner().equals(Optional.empty()) )
            {
                Console.println("WE HAVE A WINNER");
                if ( b.getWinner().equals(t) )
                {
                    return new Minimax(1, null);
                }
                else
                {
                    return new Minimax(-1, null);
                }
            }
            else if (b.isFull() ){
                Console.println("WE HAVE A DRAW");
                return new Minimax(0, null);
            }
            else{
                for (Position p : b.getEmptyCells() ){
                    Board copiedBoard = new Board(b);
                    // There is no board.placeNextToken method (?)
                    copiedBoard.place(p, t);
                    Minimax m = recursive_call(best, current_score, copiedBoard, t);

                    if (current_score > best.score ){
                        best = new Minimax(current_score, p);
                    }
                }
            }


            return best;
        }
    }
}

