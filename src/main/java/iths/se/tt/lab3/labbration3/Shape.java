package iths.se.tt.lab3.labbration3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape{
    private final Color color;
    private final double x;
    private final double y;
    private final double size;


    protected Shape(Color color, double size, double x, double y) {
        this.color = color;
        this.size = size;
        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSize() {
        return size;
    }

    public abstract void drawShape(GraphicsContext graphicsContext);
    public abstract boolean coordinatesInsideShape(double x, double y);
    public abstract Shape adjustingShapeLook(Color color, double size);


    public abstract String getAsSvg();
}
