package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import controllers.interfaces.MazeSolver;
import models.Cell;
import models.Maze;

public class MazeSolverBFS implements MazeSolver{

    private Set<Cell> visitadas = new LinkedHashSet<>();

    @Override
    public List<Cell> getPath(Maze maze, boolean[][] grid, Cell start, Cell end) {
        Queue <Cell> queue = new LinkedList<>();
        Map<Cell, Cell> predecesores = new HashMap<>();
        List<Cell> path = new ArrayList<>();

        queue.add(start);
        predecesores.put(start, null);
        visitadas.clear();
        visitadas.add(start);

        while(!queue.isEmpty()){
            Cell actual = queue.poll();
            //Cuando ya llegó al final
            if(actual.equals(end)){
                //Reconstruir el camino desde el destino hacia el inicio
                while(actual != null){
                    path.add(0, actual);
                    actual = predecesores.get(actual);
                }

                visitadas.retainAll(path);
                return path;
            }
            //Explorar 4 direcciones
            for(int[] dir : new int[][]{{1,0},{0,1},{-1,0},{0,-1}}){
                int newRow = actual.row + dir[0];
                int newCol = actual.col + dir [1];
                Cell vecino = new Cell(newRow, newCol);

                if(isValid(grid, newRow, newCol) && !predecesores.containsKey(vecino)){
                    queue.add(vecino);
                    predecesores.put(vecino, actual);
                    visitadas.add(vecino);
                }
            }
        }

        return path;
    }


    private boolean isValid(boolean[][] grid, int row, int col){
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col];
    }

    public Set<Cell> getVisitadas() {
        return visitadas;
    }
    
}
