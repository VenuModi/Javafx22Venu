package iths.se.tt.lab3.labbration3;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class shapeViewController {
    @FXML
    public ChoiceBox<ShapeType> choiceBox;
    @FXML
    public Canvas canvas;
    @FXML
    public ColorPicker colorPicker;
    @FXML
    public Text size;
    @FXML
   public Slider shapeSize;
    @FXML
    public Button Undo;
    @FXML
    public Button save;
    @FXML
    public CheckBox selectMode;

    public Model model;

    public void initialize(){
        this.model = new Model();
        model.initializeValues(ShapeType.Circle, Color.BLACK, 50.0);
        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        shapeSize.valueProperty().bindBidirectional(model.sizeProperty());
        choiceBox.valueProperty().bindBidirectional(model.selectedShapeTypeProperty());
        canvas.heightProperty().addListener(observable -> drawShape());
        canvas.widthProperty().addListener(observable -> drawShape());
        selectMode.selectedProperty().bindBidirectional(model.selectModeProperty());
    }

    /*
    This method is basically to draw shapes on canvas. The if statement is to draw shapes otherwise
    else to change the size or the color of the already drawn shape.
     */
    public void onCanvasClicked(MouseEvent mouseEvent){
        if (!model.inSelectMode())
            createShapes(mouseEvent);
        else
            modifyExistingShapes(mouseEvent);
        drawShape();
    }
    /*
    This method is for changing the color or size on already drawn shape.
     */
    private void modifyExistingShapes(MouseEvent mouseEvent) {
        model.getShapeCoordinates(mouseEvent.getX(), mouseEvent.getY())
                .ifPresent(this::setModifiedShape);
    }

    private void setModifiedShape(Shape shape) {
        Shape newShape = shape.adjustingShapeLook(model.getColor(), model.getSize());
        model.replaceShape(newShape, shape);
    }

    private void createShapes(MouseEvent mouseEvent) {
        Shape newShape = getNewShapeBySelectedType(mouseEvent);
        model.addShape(newShape);
        drawShape();
    }
    /*
    Basically to select the shape to be drawn.
     */
    public Shape getNewShapeBySelectedType(MouseEvent mouseEvent){
        return switch (model.getSelectedShapeType()){
            case Circle -> ShapeFactory.getCircle(model.getColor(), model.getSize(), mouseEvent.getX(), mouseEvent.getY());
            case Square -> ShapeFactory.getSquare(model.getColor(), model.getSize(), mouseEvent.getX(), mouseEvent.getY());
            case Triangle -> ShapeFactory.getTriangle(model.getColor(), model.getSize(), mouseEvent.getX(), mouseEvent.getY());
        };
    }

    public void drawShape(){
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (var shape: model.getShapes()) {
            shape.drawShape(graphicsContext);
        }
    }

    public void undo(){
        try {
            model.undo();
            drawShape();
        } catch (Exception e){
            System.out.println("Draw something on the canvas first!!");
        }
    }
    public void save(){
        try {
            model.writeToSvg();
            System.out.println("Saved");
        }catch (Exception e){
            System.out.println("Save the file first");
        }
    }
}