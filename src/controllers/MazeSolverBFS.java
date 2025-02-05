package controllers;

import java.util.ArrayList;
import java.util.List;

import controllers.interfaces.MazeSolver;
import models.Cell;

public class MazeSolverBFS implements MazeSolver{

    @Override
    public List<Cell> getPath(boolean[][] grid, Cell start, Cell end) {
        System.out.println("Implementacion con BFS");
        List<Cell> list = new ArrayList<>();
        return list;
    }
    
}
