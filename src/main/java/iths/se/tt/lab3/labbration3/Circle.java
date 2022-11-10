package iths.se.tt.lab3.labbration3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape{
    public Circle(Color color, double size, double x, double y) {
        super(color, size, x, y);
    }

    @Override
    public void drawShape(GraphicsContext graphicsContext) {
        double radius = getSize()/2;
        graphicsContext.setFill(this.getColor());
        graphicsContext.fillOval(this.getX() - radius, this.getY()-radius, this.getSize(), this.getSize());
    }

    @Override
    public String getAsSvg() {
        String color = "#" + getColor().toString().subSequence(2,10);
        return "<" + "circle" + "cx=\"" + getX() + "\"" + "cy=\"" + getY() + "\""
                + "r=\"" + getSize()/2 + "\"" + "fill=\"" + color + "\"" + "/>";
    }

    @Override
    public boolean coordinatesInShapeArea(double x, double y) {
        double radius = this.getSize()/2;
        double distance = Math.sqrt(Math.pow(x-getX(), 2) + Math.pow(y - getY(),2));
        return distance < radius;
    }

    @Override
    public Shape changeLook(Color color, double size) {
        return new Circle(color, size,this.getX(), this.getY());
    }
}
