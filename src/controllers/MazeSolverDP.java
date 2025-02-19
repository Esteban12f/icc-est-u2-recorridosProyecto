package controllers;

import java.util.*;

import controllers.interfaces.MazeSolver;
import models.Cell;
import models.Maze;

public class MazeSolverDP implements MazeSolver {
    private Map<Cell, Boolean> memoria = new HashMap<>();
    private Set<Cell> visitadas = new LinkedHashSet<>();

    @Override
    public List<Cell> getPath(Maze maze, boolean[][] grid, Cell start, Cell end) {
        List<Cell> path = new ArrayList<>();
        visitadas.clear();
        if (grid == null || grid.length == 0){
            return path;
        }

        /* if (findPath(grid, start.row, start.col, end, path, visitadas)) {
            return path;
        }*/

        boolean existPath = findPath(maze, grid, start.row, start.col, end, start, path, visitadas);
        
        return existPath? path : Collections.emptyList();
    }

    private boolean findPath(Maze maze, boolean[][] grid, int row, int col, Cell end, Cell start, List<Cell> path,  Set<Cell> visitadas) {
        Cell cell = new Cell(row, col);

        if(row < 0 || col< 0 || row >= grid.length || col >= grid[0].length || !grid[row][col] || memoria.containsKey(cell)){
            return false;
        }

        // validamos la llegada al fin
        if(row == end.row && col == end.col){
            path.add(0, cell);
            return true;
        }

        if(visitadas.contains(cell)){
             return false;
        }
        visitadas.add(cell);
        //maze.updateMaze(cell, start, end);

        if(findPath(maze, grid, row + 1, col, end, start, path, visitadas) || findPath(maze, grid, row, col + 1, end, start, path, visitadas)){
            path.add(0, cell);
            memoria.put(cell, true);
            return true;
        }
        
        if(findPath(maze, grid, row - 1, col, end, start, path, visitadas) || findPath(maze, grid, row, col - 1, end, start, path, visitadas)){
            path.add(0, cell);
            memoria.put(cell, true);
            return true;
        }

        memoria.put(cell, false);
        return false;
    }

    public Set<Cell> getVisitadas() {
        return visitadas;
    }

    

}
