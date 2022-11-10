package iths.se.tt.lab3.labbration3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape{

    public Square(Color color,  double size, double x, double y) {
        super(color, size, x, y);
    }

    @Override
    public void drawShape(GraphicsContext graphicsContext) {
        double squareSize = this.getSize()/2;
        graphicsContext.setFill(this.getColor());
        graphicsContext.fillRect(this.getX()-squareSize, this.getY()-squareSize, this.getSize(), this.getSize());
    }

    @Override
    public String getAsSvg() {
        String color = "#" + getColor().toString().subSequence(2,10);

        return "<" + "rect" + "x=\"" + getX() + "\"" + "y=\"" + getY() + "\""
                + "width=\"" + getSize() + "\"" + "height=\"" + getSize() + "\""
                +"fill=\"" + color + "\"" + "/>";
    }

    @Override
    public boolean coordinatesInShapeArea(double x, double y) {
        double squareSize = this.getSize()/2;
        return isBetween(x, getX()-squareSize, getX() + squareSize ) &&
                isBetween(y, getY() - squareSize, getY() + squareSize);
    }

    private boolean isBetween(double value, double minValueInclusive, double maxValueInclusive) {
        return value >= minValueInclusive && value<= maxValueInclusive;
    }

    @Override
    public Shape changeLook(Color color, double size) {
        return new Square(color, size, this.getX(), this.getY());
    }
}
