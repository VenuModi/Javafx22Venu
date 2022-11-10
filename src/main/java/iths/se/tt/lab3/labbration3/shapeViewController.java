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
        selectMode.selectedProperty().bindBidirectional(model.inSelectModeProperty());
    }
    public void onCanvasClicked(MouseEvent mouseEvent){
        if (!model.inSelectMode())
            canvasClickedInNormalMode(mouseEvent);
        else
            canvasClickedInSelectMode(mouseEvent);
//        model.isInSelectMode();
        drawShape();
    }

    private void canvasClickedInSelectMode(MouseEvent mouseEvent) {
        model.getShapeByCoordinates(mouseEvent.getX(), mouseEvent.getY())
                .ifPresent(this::setNewShapeFromSelectedMode);
    }

    private void setNewShapeFromSelectedMode(Shape shape) {
        Shape newShape = shape.changeLook(model.getColor(), model.getSize());
        model.replaceShape(newShape, shape);
    }

    private void canvasClickedInNormalMode(MouseEvent mouseEvent) {
        Shape newShape = getNewShapeBySelectedType(mouseEvent);
        model.addShape(newShape);
        drawShape();
    }

    public Shape getNewShapeBySelectedType(MouseEvent mouseEvent){
        return switch (model.getSelectedShapeType()){
            case Circle -> ShapeFactory.getCircle(model.getColor(), model.getSize(), mouseEvent.getX(), mouseEvent.getY());
            case Square -> ShapeFactory.getSquare(model.getColor(), model.getSize(), mouseEvent.getX(), mouseEvent.getY());
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
            model.Undo();
            drawShape();
        } catch (Exception e){
            System.out.println("Draw something on the canvas first!!");
        }
    }
//    public void save(){
//        try {
//            model.writeToSVG();
//            System.out.println("Saved");
//        } catch (Exception e){
//            System.out.println("You have not saved your file.");
//        }
//    }
}