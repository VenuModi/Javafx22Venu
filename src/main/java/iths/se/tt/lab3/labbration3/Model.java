package iths.se.tt.lab3.labbration3;

import javafx.beans.property.*;
import javafx.scene.paint.Color;

import java.util.*;

public class Model {
    private final ObjectProperty<Color> color;
    private final DoubleProperty size;
    private final List<Shape> shapes = new ArrayList<>();
    private final Deque<Shape> history = new ArrayDeque<>();
    private final Map<Shape, Shape> replacements = new HashMap<>(); // for replacing the old shape with the new shape.
    private final ObjectProperty <ShapeType> selectedShapeType;//selecting types of shapes from choicebox.
    private final BooleanProperty inSelectMode;
    private final SvgWriter svgWriter;

    public Model(){
        this.color = new SimpleObjectProperty<>();
        this.size = new SimpleDoubleProperty();
        inSelectMode = new SimpleBooleanProperty();
        this.selectedShapeType = new SimpleObjectProperty<>();
        svgWriter = new SvgWriter();
    }

    public void initializeValues(ShapeType shapeType, Color color, double size){
        this.selectedShapeType.setValue(shapeType);
        this.color.setValue(color);
        this.size.setValue(size);
    }

    public ShapeType getSelectedShapeType() {
        return selectedShapeType.getValue();
    }

    public Property<ShapeType> selectedShapeTypeProperty(){
        return selectedShapeType;
    }

    public List<Shape> getShapes(){
        return shapes;
    }

    public Color getColor(){
        return color.getValue();
    }

    public ObjectProperty<Color> colorProperty(){
        return color;
    }

    public double getSize() {
        return size.getValue();
    }

    public DoubleProperty sizeProperty(){
        return size;
    }

    public void addShape(Shape newShape){
        shapes.add(newShape);
        history.push(newShape);
    }

    /*
    used when the user wants to change the color or the size of the existing shape
     */
    public BooleanProperty selectModeProperty() {
        return inSelectMode;
    }
    public boolean inSelectMode(){
        return inSelectMode.getValue();
    }

    public Optional<Shape> getShapeCoordinates(double x, double y) {
        return shapes.stream()
                .filter(shape -> shape.coordinatesInsideShape(x,y))
                .reduce((first, second) -> second);
    }

    public void replaceShape(Shape newShape, Shape oldShape) {
        shapes.add(newShape);
        shapes.remove(oldShape);
        history.push(newShape);
        replacements.put(newShape, oldShape);
    }

    public void undo(){
        Shape lastAddedShape = history.pop();
        shapes.remove(lastAddedShape);

        if (replacements.containsKey(lastAddedShape)){
            shapes.add(replacements.get(lastAddedShape));
        }else {
            System.out.println("Undo to previous");
        }
    }


    public void writeToSvg() {
        svgWriter.save(this);
    }
}
