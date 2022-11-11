import iths.se.tt.lab3.labbration3.Circle;
import iths.se.tt.lab3.labbration3.Model;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTest {

    Model model = new Model();

    @Test
    public void notEmptyList(){
        model.addShape(new Circle(Color.PINK, 60.0, 30.0,30.0));

        var exp = 1;
        var act = model.getShapes().size();

        assertEquals(exp, act);
    }

    @Test
    public void createShapeAndUndo(){
        model.addShape(new Circle(Color.PURPLE,60.0, 20.0, 20.0));

        model.undo();

        var amount = model.getShapes().size();
        assertEquals(0, amount);
    }
}
