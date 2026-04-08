package ui;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCodeCombination;
import javax.sound.midi.ControllerEventListener;
import java.sql.Time;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.Group;

import java.awt.*;

import ui.FileOutputter;
import ui.UsefulMath;

public class JavaFx extends Application{

    int ballDirX = 1;
    int ballDirY = 1;
    double ballSpeedDefault = 5.0;
    double ballSpeed = ballSpeedDefault;

    int lScore = 0;
    int rScore = 0;

    double viewportW = 1920.0;
    double viewportH = 1080.0;

    double ballR = 15.0;
    double paddleW = 10.0;
    double paddleH = 180.0;
    double distFromSide = 30.0;

    int ballColorInc = 0;

    @Override
    public void start(Stage r){
        r.setTitle("PONG+");
        // CREATING THE PADDLES AND THE BALL
        Rectangle lPaddle = new Rectangle(0.0 + distFromSide, (viewportH/2), paddleW, paddleH);
        Rectangle rPaddle = new Rectangle(viewportW - distFromSide - paddleW, (viewportH/2), paddleW, paddleH);
        Circle ball = new Circle(ballR);
        ball.setTranslateX(viewportW/2);
        ball.setTranslateY(viewportH/2);

        // CREATING THE SCORE LABEL
        Label scoreLabel = new Label("0-0");
        scoreLabel.setScaleX(12.0);
        scoreLabel.setScaleY(12.0);
        scoreLabel.setTranslateX(viewportW/2 );
        scoreLabel.setTranslateY(140.0);

        // CREATING THE WARNING VISUALS
        double warningH = viewportH;
        double warningW = 10.0;
        Rectangle lWarning = new Rectangle(0.0, 0.0, warningW, warningH);
        Rectangle rWarning = new Rectangle(viewportW-warningW, 0.0, warningW, warningH);

        // Group mainGroup = new Group(lPaddle, rPaddle, ball, scoreLabel, lWarning, rWarning);
        Scene scene = new Scene(new Group(lPaddle, rPaddle, ball, scoreLabel, lWarning, rWarning), viewportW, viewportH);

        // SETTING FILLS
        scene.setFill(Color.BLACK);
        lPaddle.setFill(Color.WHITE);
        rPaddle.setFill(Color.WHITE);
        lWarning.setFill(Color.RED);
        rWarning.setFill(Color.RED);

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
                double lPaddleYPos = UsefulMath.lerp(viewportH, 0.0, (lPaddle.getY() - lPaddle.getTranslateY()) / viewportH )  ;
                double rPaddleYPos = UsefulMath.lerp(viewportH, 0.0, (rPaddle.getY() - rPaddle.getTranslateY()) / viewportH )  ;
                double ballYPos = ball.getTranslateY();

                int ballDirXBefore = ballDirX;
                int ballDirYBefore = ballDirY;

                moveShape(ball, (ballSpeed * ballDirX), (ballSpeed * ballDirY) );
                if (ball.getTranslateY() > viewportH ){
                    ballDirY = -1;
                }
                else if (ball.getTranslateY() < 0.0 ){
                    ballDirY = 1;
                }

                // Check if the ball's translate X goes past the paddle's X, AND if the ball's translate Y is within paddle's 'hitbox'.
                if ( (ball.getTranslateX() < lPaddle.getX() + paddleW) && (UsefulMath.doubleInRange(ball.getTranslateY(), lPaddleYPos, lPaddleYPos + paddleH)) ){
                    ballDirX = 1;
                }
                else if ( (ball.getTranslateX() > rPaddle.getX() - paddleW) && (UsefulMath.doubleInRange(ball.getTranslateY(), rPaddleYPos, rPaddleYPos + paddleH )) ){
                    ballDirX = -1;
                }

                if (ballDirX != ballDirXBefore || ballDirY != ballDirYBefore ){ ballSpeed += 0.9;}

//                System.out.println(ball.getTranslateX());
//                System.out.println(lPaddle.getX());
                // SCORING
                boolean scoreAchieved = false;
                if (ball.getTranslateX() < 0.0 ){
                    setScore(lScore,rScore+1,scoreLabel);
                    scoreAchieved = true;
                }
                else if (ball.getTranslateX() > viewportW ){
                    setScore(lScore+1,rScore,scoreLabel);
                    scoreAchieved = true;
                }
                if (scoreAchieved){
                    ball.setTranslateX(viewportW/2);
                    ball.setTranslateY(viewportH/2);
                    ballSpeed = ballSpeedDefault;
                }

                ballColorInc += 1;
                if (ballColorInc % 6 == 0){ ball.setFill( ball.getFill() == Color.WHITE ? Color.GREY : Color.WHITE ); }

            }) // End of KeyFrame block.
        ); // End of Timeline block
        everySecond.setCycleCount(Animation.INDEFINITE);
        everySecond.play();

        r.setScene(scene);
        r.show();
    }

    public void moveShape(Shape p, double moveX, double moveY){
        p.setTranslateX(p.getTranslateX() + moveX);
        p.setTranslateY(p.getTranslateY() + moveY);
    }

    public void setScore(int newLScore, int newRScore, Label l){
        lScore = newLScore;
        rScore = newRScore;
        l.setText(lScore + "-" + rScore );
    }

    public void inputCode(KeyEvent e, Rectangle lPaddle, Rectangle rPaddle){
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
