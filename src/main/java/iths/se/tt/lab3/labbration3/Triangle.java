package iths.se.tt.lab3.labbration3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {
    public Triangle(Color color, double size, double x, double y) {
        super(color, size, x, y);

    }

    @Override
    public void drawShape(GraphicsContext graphicsContext) {
        graphicsContext.setFill(getColor());
        graphicsContext.fillPolygon(
                new double[]{
                        getX() - getSize(),
                        getX() + getSize(),
                        getX()
                },
                new double[]{
                        getY() + getSize(),
                        getY() + getSize(),
                        getY() - getSize()
                },
                3);
    }

    @Override
    public boolean coordinatesInsideShape(double x, double y) {
        double radius = this.getSize() / 2;
        double distance = Math.sqrt(Math.pow(x - getX(), 2) + Math.pow(y - getY(), 2));
        return distance < radius;
    }

    @Override
    public Shape adjustingShapeLook(Color color, double size) {
        return new Triangle(color, size, this.getX(), this.getY());
    }

    @Override
    public String getAsSvg() {
        String color = "#" + getColor().toString().subSequence(2, 10);

        double x1 = getX() - getSize();
        double x2 = getX() + getSize();

        double y1 = getY() + getSize();
        double y2 = getY() + getSize();
        double y3 = getY() - getSize();

        return "<" + "polygon" + " points=\""
                + x1 + "," + y1
                + " " + x2 + "," + y2
                + " " + getX() + "," + y3
                + "\" " + " fill=\"" + color + "\"" + " />";
    }
}