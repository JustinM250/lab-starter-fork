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
            int randX = r.nextInt(3) + 1;
            int randY = r.nextInt(3) + 1;
            Console.println(randX + " ");
            Console.println(randY + " ");
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



    public record Minimax(int score, Position p ){

        /**
         * @param b this is the current board.
         * @param tok this is the calling player's token.
         * Call this method to get a Minimax record. That record will contain the winning score and the best position. The winning score will likely be useless, so access .p on the returned record to get the position.
         * */
        static Minimax run(Board b, Token tok){
//            Console.println("RUNNING RECURSION");

            // PRE-AI
//            int worstScore = 0;
//            if (tok.equals(Token.X)){
//                worstScore = -1;
//            }
//            else{
//                worstScore = 1;
//            }
            //


            // AI
            int worstScore = (tok == Token.X) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            //

            Minimax worstCase = new Minimax(worstScore, null);
            return recursive_call( worstCase, worstScore, b, tok );
        }

        static Minimax recursive_call( Minimax best, int current_score, Board b, Token t   ){
//            Console.println("CALL");
            if ( b.getWinner().isPresent() )
            {
//                Console.println("WE HAVE A WINNER");
                // PRE-AI
//                if ( b.getWinner().get() == t )
////                if (b.getWinner().equals(Optional.of(t)) )
//                {
//                    return new Minimax(1, null);
//                }
//                else
//                {
//                    return new Minimax(-1, null);
//                }
                //

                // AI
                //Token prev = (t == Token.X ? Token.O : Token.X); // This line was unecessary;
                if (b.getWinner().get() == Token.X) {  // don't compare previous, compare X.
                    return new Minimax(1, null);
                } else {
                    return new Minimax(-1, null);
                }
                //

            }
            else if (b.isFull() ){
//                Console.println("WE HAVE A DRAW");
                return new Minimax(0, null);
            }
            else{
                // AI
                best = (t == Token.X)
                        ? new Minimax(Integer.MIN_VALUE, null)
                        : new Minimax(Integer.MAX_VALUE, null);
                //

                for (Position p : b.getEmptyCells() ){
                    Board copiedBoard = new Board(b);
                    copiedBoard.place(p, t);

                    Token nextToken = (t == Token.X ? Token.O : Token.X);
                    Minimax m = recursive_call(best, current_score, copiedBoard, nextToken);


                    // PRE-AI
//                    if (nextToken == Token.X ){
//                        if (current_score > best.score ){
//                            best = new Minimax(current_score, p);
//                        }
//                    }
//                    else{
//                        if (current_score < best.score ){
//                            best = new Minimax(current_score, p);
//                        }
//                    }
                    //

                    // AI
                    if (best.p == null) {
                        best = new Minimax(m.score, p);
                    }
                    if (t == Token.X) { // maximizing
                        if (m.score > best.score) {
                            best = new Minimax(m.score, p);
                        }
                    } else { // minimizing
                        if (m.score < best.score) {
                            best = new Minimax(m.score, p);
                        }
                    }
                    //

                }
            }
            return best;
        }
    }
}

