import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import controllers.ControllerView;
import controllers.MazeSolverBFS;
import controllers.MazeSolverDP;
import controllers.MazeSolverRecursivo;
import controllers.interfaces.MazeSolver;
import models.Cell;
import models.Maze;
import views.ViewMain;

public class App {
    public static void main(String[] args) throws Exception {
        
        ViewMain viewMain = new ViewMain();
        ControllerView controllerView = new ControllerView(viewMain);
        viewMain.setVisible(true);
        
    }
    
}
