package ui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.input.KeyCodeCombination;

import javax.sound.midi.ControllerEventListener;
import java.awt.*;
import java.sql.Time;

public class JavaFx extends Application{

    int ballDirX = 1;
    int ballDirY = 1;
    double ballSpeedDefault = 5.0;
    double ballSpeed = ballSpeedDefault;

    int lScore = 0;
    int rScore = 0;

    int ballColorInc = 0;

    @Override
    public void start(Stage r){
//        Rectangle[] paddles = {};
        double viewportW = 1920.0;
        double viewportH = 1080.0;

        double ballR = 15.0;
        double paddleW = 10.0;
        double paddleH = 180.0;
        double distFromSide = 30.0;
        Rectangle lPaddle = new Rectangle(0.0 + distFromSide, (viewportH/2), paddleW, paddleH);
        Rectangle rPaddle = new Rectangle(viewportW - distFromSide - paddleW, (viewportH/2), paddleW, paddleH);
        Circle ball = new Circle(ballR);
        ball.setTranslateX(viewportW/2);
        ball.setTranslateY(viewportH/2);
        lPaddle.setFill(Color.WHITE);
        rPaddle.setFill(Color.WHITE);
        Label scoreLabel = new Label("0-0");
        scoreLabel.setScaleX(12.0);
        scoreLabel.setScaleY(12.0);
        scoreLabel.setTranslateX(viewportW/2 );
        scoreLabel.setTranslateY(140.0);

        double warningH = viewportH;
        double warningW = 10.0;
        Rectangle lWarning = new Rectangle(0.0, 0.0, warningW, warningH);
        Rectangle rWarning = new Rectangle(viewportW-warningW, 0.0, warningW, warningH);
        lWarning.setFill(Color.RED);
        rWarning.setFill(Color.RED);

        Group mainGroup = new Group(lPaddle, rPaddle, ball, scoreLabel, lWarning, rWarning);


        r.setTitle("PONG+");
        Scene scene = new Scene(mainGroup, viewportW, viewportH);
        scene.setFill(Color.BLACK);

        scene.setOnKeyPressed(e -> {
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
        } );

        Timeline everySecond = new Timeline(
            new KeyFrame(Duration.millis(17.7), e -> {
                // MOVING THE BALL
                int ballDirXBefore = ballDirX;
                int ballDirYBefore = ballDirY;

                // double paddleYPos = (rPaddle.getY() - rPaddle.getTranslateY())  ;
                /*
                    I was getting the "closer to bottom = closer to 0" problem.
                    It should be other way around, bottom should be closer to 1080.
                    So, I used lerp to 'flip' the value. If the value is closer to 1080, make it closer to 0. Closer to 0, 1080.
                    One could say that this isn't clean code, though this is the simplest solution that came to mind,
                    as the getTranslate methods only show how much they've been translated, not the "position".
                    So if it's at 540 and hasn't been moved, the translate value will be 0.
                    If it's been moved 200 down, the "position" we want is 740, but the translate is still 200.
                */
                double lPaddleYPos = lerp(viewportH, 0.0, (lPaddle.getY() - lPaddle.getTranslateY()) / viewportH )  ;
                double rPaddleYPos = lerp(viewportH, 0.0, (rPaddle.getY() - rPaddle.getTranslateY()) / viewportH )  ;
                double ballYPos = ball.getTranslateY();
//                System.out.println(paddleYPos + " / " + ballYPos);
                moveShape(ball, (ballSpeed * ballDirX), (ballSpeed * ballDirY) );
                if (ball.getTranslateY() > viewportH ){
                    ballDirY = -1;
                }
                else if (ball.getTranslateY() < 0.0 ){
                    ballDirY = 1;
                }

                if ( (ball.getTranslateX() < lPaddle.getX() + paddleW) && (doubleInRange(ball.getTranslateY(), lPaddleYPos, lPaddleYPos + paddleH)) ){
                    ballDirX = 1;
                }
                else if ( (ball.getTranslateX() > rPaddle.getX() - paddleW) && (doubleInRange(ball.getTranslateY(), rPaddleYPos, rPaddleYPos + paddleH )) ){
                    ballDirX = -1;
                }

                if (ballDirX != ballDirXBefore || ballDirY != ballDirYBefore ){ ballSpeed += 0.9;}

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
                if (ballColorInc % 6 == 0){
                    ball.setFill( ball.getFill() == Color.WHITE ? Color.GREY : Color.WHITE );
                }

            })
        );
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

    public boolean doubleInRange(double v, double min, double max ){
        return (v > min && v < max);
    }

    public double lerp(double min, double max, double weight){
        return min + weight * (max - min);
    }
}
