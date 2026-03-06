# Answers to Lab Questions

Place here your answers to the reflection questions in the lab instructions.

## Question 1
Why *can* you change the type of the returned **value** in `promptForPlayer` without changing the return **type** in the function signature?
### Answer
You can change the type returned by the return statement but not the actual return type because 
changing the return type to HumanPlayer would cause all calls of promptForPlayer, like the call in
TicTacToeGame.java, to expect a HumanPlayer. 
That's not what we want, as not every Player here is a HumanPlayer.

## Question 2
Explain why the call to `getNextMove` initially causes an error until you add the abstract method to the `Player` class. Your answer should involve a discussion of static (compile-time) vs dynamic (run-time) types. (HINT: What is the compile-time vs run-time type of the `player` variable in `TicTacToeGame.doNextTurn`?)
### Answer
Adding this abstract method sig: 
    public abstract Position getNextMove(Board b);
to Player.Java fixed the error because the compiler reads this line:
    var pos = player.getNextMove(board);
and expects player to have a method called getNextMove, with a Board as a parameter.
The compiler (explain the rest later)

## Question 3
Explain in detail how it is possible that neither our main game loop nor our TicTacToeGame class need change at all when adding new Player types to our game.  Your discussion must include an explanation of how the single call to getNextMove in TicTacToeGame.doNextTurn works correctly no matter whose turn it is or which types the players are. Your answer should involve discussion of polymorphism and dynamic method dispatch.
### Answer
Your answer here