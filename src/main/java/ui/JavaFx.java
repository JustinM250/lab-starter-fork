package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.Group;

import java.awt.*;

public class JavaFx extends Application{ // Application is an abstract class.
    int lScore = 0;
    int rScore = 0;

    double distFromSide = 30.0;

    @Override
    public void start(Stage r){
        r.setTitle("PONG+");
        Paddle lPaddle = new Paddle(0.0 + distFromSide, (ViewportInfo.H/2) );
        Paddle rPaddle = new Paddle(ViewportInfo.W - distFromSide - Paddle.W, (ViewportInfo.H/2) );
        Ball ball = new Ball(15.0);
        ball.reset();

        Label scoreLabel = new Label("0-0");
        scoreLabel.setScaleX(12.0);
        scoreLabel.setScaleY(12.0);
        scoreLabel.setTranslateX(ViewportInfo.W/2 );
        scoreLabel.setTranslateY(140.0);

        Warning lWarning = new Warning(0.0, 0.0);
        Warning rWarning = new Warning(ViewportInfo.W-Warning.W, 0.0);

        // Group mainGroup = new Group(lPaddle, rPaddle, ball, scoreLabel, lWarning, rWarning);
        Scene scene = new Scene(new Group(lPaddle, rPaddle, ball, scoreLabel, lWarning, rWarning), ViewportInfo.W, ViewportInfo.H);
        scene.setFill(Color.BLACK);

        scene.setOnKeyPressed(e -> {
            inputCode(e, lPaddle, rPaddle);
        } );

        Timeline everySecond = new Timeline(
            new KeyFrame(Duration.millis(17.7), e -> {
                // MOVING THE BALL
                /*
                    I was getting the "closer to bottom = closer to 0" problem.
                    It should be other way around, bottom should be closer to 1080.
                    So, I used lerp to 'flip' the value. If the value is closer to 1080, make it closer to 0. Closer to 0, 1080.
                    One could say that this isn't clean code, though this is the simplest solution that came to mind,
                    as the getTranslate methods only show how much they've been translated, not the "position".
                    So if it's at 540 and hasn't been moved, the translate value will be 0.
                    If it's been moved 200 down, the "position" we want is 740, but the translate is still 200.
                */
                double lPaddleYPos = UsefulMath.lerp(ViewportInfo.H, 0.0, (lPaddle.getY() - lPaddle.getTranslateY()) / ViewportInfo.H )  ;
                double rPaddleYPos = UsefulMath.lerp(ViewportInfo.H, 0.0, (rPaddle.getY() - rPaddle.getTranslateY()) / ViewportInfo.H )  ;
                double ballYPos = ball.getTranslateY();

                int ballDirXBefore = ball.dirX;
                int ballDirYBefore = ball.dirY;

                moveShape(ball, (ball.speed * ball.dirX), (ball.speed * ball.dirY) );
                if (ball.getTranslateY() > ViewportInfo.H ){
                    ball.dirY = -1;
                }
                else if (ball.getTranslateY() < 0.0 ){
                    ball.dirY = 1;
                }

                // Check if the ball's translate X goes past the paddle's X, AND if the ball's translate Y is within paddle's 'hitbox'.
//                if ( (ball.getTranslateX() < lPaddle.getX() + paddleW) && (UsefulMath.doubleInRange(ball.getTranslateY(), lPaddleYPos, lPaddleYPos + paddleH)) ){
                if ( (ball.getTranslateX() < lPaddle.getX() + Paddle.W) && (UsefulMath.doubleInRange(ballYPos, lPaddleYPos, lPaddleYPos + Paddle.H)) ){
                    ball.dirX = 1;
                }
//                else if ( (ball.getTranslateX() > rPaddle.getX() - paddleW) && (UsefulMath.doubleInRange(ball.getTranslateY(), rPaddleYPos, rPaddleYPos + paddleH )) ){
                else if ( (ball.getTranslateX() > rPaddle.getX() - Paddle.W) && (UsefulMath.doubleInRange(ballYPos, rPaddleYPos, rPaddleYPos + Paddle.H )) ){
                    ball.dirX = -1;
                }

                if (ball.dirX != ballDirXBefore || ball.dirY != ballDirYBefore ){ ball.speed += 0.9;}

                // SCORING
                boolean scoreAchieved = false;
                if (ball.getTranslateX() < 0.0 ){
                    setScore(lScore,rScore+1,scoreLabel);
                    scoreAchieved = true;
                }
                else if (ball.getTranslateX() > ViewportInfo.W ){
                    setScore(lScore+1,rScore,scoreLabel);
                    scoreAchieved = true;
                }
                if (scoreAchieved){
                    ball.reset();
                }

                ball.incColorTick();
            }) // End of KeyFrame block.
        ); // End of Timeline block
        everySecond.setCycleCount(Animation.INDEFINITE);
        everySecond.play();

        r.setScene(scene);
        r.show();

        //
    }

    // Good DRY example.
    public void moveShape(Shape p, double moveX, double moveY){
        p.setTranslateX(p.getTranslateX() + moveX);
        p.setTranslateY(p.getTranslateY() + moveY);
    }

    public void setScore(int newLScore, int newRScore, Label l){
        lScore = newLScore;
        rScore = newRScore;
        l.setText(lScore + "-" + rScore );
    }

    public void inputCode(KeyEvent e, Paddle lPaddle, Paddle rPaddle){
        double moveIncY = 50.0;
        if (e.getCode() == KeyCode.W){
            moveShape(lPaddle, 0.0, -moveIncY);
        }
        if (e.getCode() == KeyCode.S){
            moveShape(lPaddle, 0.0, moveIncY);
        }
        if (e.getCode() == KeyCode.UP){
            moveShape(rPaddle, 0.0, -moveIncY);
        }
        if (e.getCode() == KeyCode.DOWN){
            moveShape(rPaddle, 0.0, moveIncY);
        }
        if (e.getCode() == KeyCode.L){
            FileOutputter.outputFile(System.getProperty("user.home") + "\\pong_output.txt", "(l) has a score of %d, and (r) has a score of %d ".formatted(lScore, rScore));
        }
    }
}
