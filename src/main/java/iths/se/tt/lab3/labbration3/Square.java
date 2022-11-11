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

    /*
    isInside is the coordinates of a shape
     */
    @Override
    public boolean coordinatesInsideShape(double x, double y) {
        double squareSize = this.getSize()/2;
        return isInside(x, getX()-squareSize, getX() + squareSize ) &&
                isInside(y, getY() - squareSize, getY() + squareSize);
    }

    private boolean isInside(double value, double minValueInclusive, double maxValueInclusive) {
        return value >= minValueInclusive && value<= maxValueInclusive;
    }

    /*
    The new square created after changing size or color on an already drawn square.
     */
    @Override
    public Shape adjustingShapeLook(Color color, double size) {
        return new Square(color, size, this.getX(), this.getY());
    }

    @Override
    public String getAsSvg() {
        String color = "#" + getColor().toString().subSequence(2,10);

        return "<" + "rect" + " x=\"" + getX() + "\"" + " y=\"" + getY() + "\""
                + " width=\"" + getSize() + "\"" + " height=\"" + getSize() + "\""
                + " fill=\"" + color + "\"" + " />";
    }
}
