package ui;

public class UsefulMath {
    public static boolean doubleInRange(double v, double min, double max ){
        return (v > min && v < max);
    }

    public static double lerp(double min, double max, double weight){
        return min + weight * (max - min);
    }
}
