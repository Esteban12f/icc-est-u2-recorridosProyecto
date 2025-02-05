package models;

import java.util.Arrays;
import java.util.List;

public class Maze {
    private boolean[][] grid;
    private int size;

    public Maze(int size){
        this.size = size;
        grid = new boolean[size][size];

        for (int i = 0; i < size; i++){
            Arrays.fill(grid[i], true);
        }
    }

    public Maze(boolean[][] predefingrid){
        this.size = predefingrid.length;
        this.grid = predefingrid;
    }

    public boolean[][] getGrid() {
        return grid;
    }

    public void printMaze(){
        for (boolean[] row : grid){
            for (boolean celda : row){
                System.out.print(celda ? " - " : " * ");
            }
            System.out.println();
        }
    }

    public void printMazeSolution(List<Cell> path){
        for (int j = 0; j < grid.length; j++){
            for (int k = 0; k < grid[j].length; k++){
                boolean celda = grid[j][k];
                if (celda && isInPath(j, k, path)){
                    System.out.print(" > ");
                } else if  (celda) {
                    System.out.print(" - ");
                } else {
                    System.out.print(" * ");
                }
            }
            System.out.println();
        }
    }

    private boolean isInPath(int j, int k, List<Cell> path){
        for (Cell celda : path){
            if (celda.row == j && celda.col == k){
                return true;
            }
        }
        return false;
    }
    
}
