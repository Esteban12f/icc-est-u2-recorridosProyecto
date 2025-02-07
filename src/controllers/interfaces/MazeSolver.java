package controllers.interfaces;

import java.util.List;

import models.Cell;
import models.Maze;

public interface MazeSolver {
    List<Cell> getPath(Maze maze, boolean[][] grid, Cell start, Cell end);
}
