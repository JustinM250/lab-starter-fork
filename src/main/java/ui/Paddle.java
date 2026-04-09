package ui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paddle extends Rectangle {
    public static double W = 10.0;
    public static double H = 180.0;

    public Paddle(double posX, double posY){
        super(posX, posY, W, H);
        setFill(Color.WHITE);
    }
}
