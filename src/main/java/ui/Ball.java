package ui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {

    public double radius;
    public int dirX = 1;
    public int dirY = 1;
    public double speedDefault = 5.0;
    public double speed;
    private int colorTick = 0;

    public Ball(double radius){
        super(radius);
        this.radius = radius;
    }

    public void reset(){
        speed = speedDefault;
        setTranslateX(ViewportInfo.W/2);
        setTranslateY(ViewportInfo.H/2);
    }

    public void incColorTick(){
        colorTick += 1;
        if (colorTick % 6 == 0){ setFill( getFill() == Color.WHITE ? Color.GREY : Color.WHITE ); }
    }
}
