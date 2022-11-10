package iths.se.tt.lab3.labbration3;

import javafx.beans.property.*;
import javafx.scene.paint.Color;

import java.util.*;

public class Model {
    private final ObjectProperty<Color> color;
    private final DoubleProperty size;
   // private final SvgWriter svgWriter;
    private final List<Shape> shapes = new ArrayList<>();
    private final Deque<Shape> history = new ArrayDeque<>();
    private final Map<Shape, Shape> replacements = new HashMap<>();
    private final ObjectProperty <ShapeType> selectedShapeType;
    private final BooleanProperty inSelectMode;

    public Model(){
        this.color = new SimpleObjectProperty<>();
        this.size = new SimpleDoubleProperty();
        inSelectMode = new SimpleBooleanProperty();
        this.selectedShapeType = new SimpleObjectProperty<>();
       // svgWriter = new SvgWriter();
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

    public void isInSelectMode(){
        inSelectMode.getValue();
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
    public void Undo(){
        Shape lastAddedShape = history.pop();
        shapes.remove(lastAddedShape);

        if (replacements.containsKey(lastAddedShape)){
            shapes.add(replacements.get(lastAddedShape));
        }else {
            System.out.println("One Step Back");
        }
    }

    public BooleanProperty inSelectModeProperty() {
        return inSelectMode;
    }
    public boolean inSelectMode(){
        return inSelectMode.getValue();
    }

    public Optional<Shape> getShapeByCoordinates(double x, double y) {
        return shapes.stream()
                .filter(shape -> shape.coordinatesInShapeArea(x,y))
                .reduce((first, second) -> second);
    }

    public void replaceShape(Shape newShape, Shape oldShape) {
        shapes.add(newShape);
        shapes.remove(oldShape);
        history.push(newShape);
        replacements.put(newShape, oldShape);
    }

//    public void writeToSvg(){
//        svgWriter.save(this);
//    }
}
