package iths.se.tt.lab3.labbration3;

import javafx.scene.paint.Color;

public class ShapeFactory {
    public static Circle getCircle(Color color, double size, double x, double y){
        return new Circle(color, size, x,  y);
    }

    public static Square getSquare(Color color, double size, double x, double y){
        return new Square(color, size, x, y);
    }
}
