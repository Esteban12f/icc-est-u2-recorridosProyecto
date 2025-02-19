package controllers;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import models.Cell;
import models.Maze;
import views.ViewMain;
import views.ViewMaze;

public class ControllerView {
    private ViewMain viewMain;
    private ViewMaze viewMaze;
    private boolean editable = false;

    public ControllerView(ViewMain viewMain) {
        this.viewMain = viewMain;
        agregarEventosMain();
    }

    private void agregarEventosMain(){
        viewMain.getBtnAceptar().addActionListener(e -> aceptar());
        viewMain.getBtnDefault().addActionListener(e -> defaultMaze());
    }

    public void aceptar(){
        if (viewMain.emptySpaces()){
            try {
                int rows = Integer.parseInt(viewMain.getTxtRow().getText());
                int cols = Integer.parseInt(viewMain.getTxtCol().getText());
                int inicioX = Integer.parseInt(viewMain.getTxtXInicio().getText());
                int inicioY = Integer.parseInt(viewMain.getTxtYInicio().getText());
                int desX = Integer.parseInt(viewMain.getTxtXDes().getText());
                int desY = Integer.parseInt(viewMain.getTxtYDes().getText());
    
                if (inicioX >= 0 && inicioX < rows && inicioY >= 0 && inicioY < cols &&
                    desX >= 0 && desX < rows && desY >= 0 && desY < cols ){
                    viewMaze = new ViewMaze(rows, cols, inicioX, inicioY, desX, desY);
                    agregarEventosMaze();
                    viewMain.dispose();
                    viewMaze.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(viewMain, "Puntos fuera de rango");
                }
    
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(viewMain, "Ingrese valores numericos validos :/", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(viewMain, "Se deben llenar todos los campos :/");
        }
    }

    public void defaultMaze(){
        if (viewMain.emptyDefaultSpaces()){
            try {
                int rows = Integer.parseInt(viewMain.getTxtRow().getText());
                int cols = Integer.parseInt(viewMain.getTxtCol().getText());
                viewMaze = new ViewMaze(rows, cols, 0, 0, rows - 1, cols - 1);
                agregarEventosMaze();
                viewMain.dispose();
                viewMaze.setVisible(true);
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(viewMain, "Ingrese valores numericos validos :/", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(viewMain, "Se debe ingresar el tamaño del laberinto :/");
        }
    }
    
    private void agregarEventosMaze(){
        viewMaze.getBtnObstaculos().addActionListener(e -> obstaculos());
        viewMaze.getBtnSolverBFS().addActionListener(e -> solverBFS());
        viewMaze.getBtnSolverDP().addActionListener(e -> solverDP());
        viewMaze.getBtnSolverRec().addActionListener(e -> solverRec());
        viewMaze.getBtnSolverDFS().addActionListener(e -> solverDFS());
        viewMaze.getBtnReiniciar().addActionListener(e -> reiniciarMaze());
    }

    public void obstaculos(){
        editable = true;
        viewMaze.habilitarBotones();
        viewMaze.mostrarOpciones();

        JButton[][] botones = viewMaze.getMazeButtons();
        boolean[][] maze = viewMaze.getMaze();
        for (int i = 0; i < viewMaze.getRows(); i++){
            for (int j = 0; j < viewMaze.getCols(); j++){
                if (!((i == viewMaze.getInicioX() && j == viewMaze.getInicioY()) || 
                (i == viewMaze.getDesX() && j == viewMaze.getDesY()))){
                    final int row = i;
                    final int col = j;
                    
                    ActionListener[] eventoAsociado = botones[row][col].getActionListeners();
                    for (ActionListener al : eventoAsociado) {
                        botones[row][col].removeActionListener(al);
                    }

                    botones[i][j].addActionListener(e -> {
                        if (editable){
                            boolean defaultValue = maze[row][col];
                            Color defaultColor = botones[row][col].getBackground();
                            maze[row][col] = defaultValue == false ? true : false;
                            botones[row][col].setBackground(defaultColor.equals(Color.BLACK) ? null : Color.BLACK);
                        }
                    });
                }
            }
        }

        viewMaze.getBtnAceptar().addActionListener(e -> cerrarOpciones());
        viewMaze.getBtnLimpiar().addActionListener(e -> {cerrarOpciones();
            defaultBotones();});
    }

    private void cerrarOpciones(){
        editable = false;
        viewMaze.ocultarOpciones();
        viewMaze.deshabilitarBotones();
    }

    private void defaultBotones(){
        JButton[][] botones = viewMaze.getMazeButtons();
        boolean[][] maze = viewMaze.getMaze();
        for (int i = 0; i < viewMaze.getRows(); i++){
            for (int j = 0; j < viewMaze.getCols(); j++){
                if (!((i == viewMaze.getInicioX() && j == viewMaze.getInicioY()) || 
                (i == viewMaze.getDesX() && j == viewMaze.getDesY()))){
                    maze[i][j] = true;
                    botones[i][j].setBackground(null);
                }
            }
        }
    }

    public void reiniciarMaze(){
        viewMain.limpiarTexto();
        viewMaze.dispose();
        viewMain.setVisible(true);
    }

    public void solverBFS(){
        boolean tiempoSeleccionado = viewMaze.getTiempoDemora().isSelected();
        processBFS(tiempoSeleccionado);
    }

    private void processBFS(boolean tiempoSeleccionado){
        MazeSolverBFS mazeSolverBFS = new MazeSolverBFS();
        Cell start = new Cell(viewMaze.getInicioX(), viewMaze.getInicioY());
        Cell end = new Cell(viewMaze.getDesX(), viewMaze.getDesY());
        List<Cell> rutaResultado = mazeSolverBFS.getPath(new Maze(viewMaze.getMaze()), viewMaze.getMaze(), start, end);
        JButton[][] botones = viewMaze.getMazeButtons();
        Set<Cell> visitadas = mazeSolverBFS.getVisitadas();

        reiniciarRecorrido(botones, viewMaze.getMaze());
        
        if (tiempoSeleccionado){
            long startTime = System.nanoTime();
            new Thread(() -> {
                for (Cell celda : visitadas){
                    int x = celda.row;
                    int y = celda.col;
                    if (!(x == viewMaze.getInicioX() && y == viewMaze.getInicioY()) && 
                    !(x == viewMaze.getDesX() && y == viewMaze.getDesY())){
                        if (rutaResultado.isEmpty()){
                            botones[x][y].setBackground(Color.RED);
                        } else {
                            botones[x][y].setBackground(rutaResultado.contains(celda) ? Color.GREEN : Color.RED);
                        }
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
            long endTime = System.nanoTime();
            double tiempoDemora = (endTime - startTime) / 1_000_000.0;
            JOptionPane.showMessageDialog(viewMaze, "Tiempo de ejecucion: " + tiempoDemora + " ms");
            viewMaze.hayCamino(rutaResultado);
        } else {
            for (Cell celda : visitadas) {
                int x = celda.row;
                int y = celda.col;
                if (!(x == viewMaze.getInicioX() && y == viewMaze.getInicioY()) && 
                    !(x == viewMaze.getDesX() && y == viewMaze.getDesY())){
                        if (rutaResultado.isEmpty()){
                            botones[x][y].setBackground(Color.RED);
                        } else {
                            botones[x][y].setBackground(rutaResultado.contains(celda) ? Color.GREEN : Color.RED);
                        }
                    }
            }
            viewMaze.hayCamino(rutaResultado);
        }
    }

    public void solverDP(){
        boolean tiempoSeleccionado = viewMaze.getTiempoDemora().isSelected();
        processDP(tiempoSeleccionado);
    }

    private void processDP(boolean tiempoSeleccionado){
        MazeSolverDP mazeSolverDP = new MazeSolverDP();
        Cell start = new Cell(viewMaze.getInicioX(), viewMaze.getInicioY());
        Cell end = new Cell(viewMaze.getDesX(), viewMaze.getDesY());
        List<Cell> rutaResultado = mazeSolverDP.getPath(new Maze(viewMaze.getMaze()), viewMaze.getMaze(), start, end);
        JButton[][] botones = viewMaze.getMazeButtons();
        Set<Cell> visitados = mazeSolverDP.getVisitadas();

        reiniciarRecorrido(botones, viewMaze.getMaze());
        
        if (tiempoSeleccionado){
            long startTime = System.nanoTime();
            new Thread(() -> {
                for (Cell celda : visitados){
                    int x = celda.row;
                    int y = celda.col;
                    if (!(x == viewMaze.getInicioX() && y == viewMaze.getInicioY()) && 
                    !(x == viewMaze.getDesX() && y == viewMaze.getDesY())){
                        if (rutaResultado.isEmpty()){
                            botones[x][y].setBackground(Color.RED);
                        } else {
                            botones[x][y].setBackground(rutaResultado.contains(celda) ? Color.GREEN : Color.RED);
                        }
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
            long endTime = System.nanoTime();
            double tiempoDemora = (endTime - startTime) / 1_000_000.0;
            JOptionPane.showMessageDialog(viewMaze, "Tiempo de ejecucion: " + tiempoDemora + " ms");
            viewMaze.hayCamino(rutaResultado);
        } else {
            for (Cell celda : visitados) {
                int x = celda.row;
                int y = celda.col;
                if (!(x == viewMaze.getInicioX() && y == viewMaze.getInicioY()) && 
                    !(x == viewMaze.getDesX() && y == viewMaze.getDesY())){
                        if (rutaResultado.isEmpty()){
                            botones[x][y].setBackground(Color.RED);
                        } else {
                            botones[x][y].setBackground(rutaResultado.contains(celda) ? Color.GREEN : Color.RED);
                        }
                    }
                
            }
            viewMaze.hayCamino(rutaResultado);
        }
    }

    public void solverRec(){
        boolean tiempoSeleccionado = viewMaze.getTiempoDemora().isSelected();
        processRec(tiempoSeleccionado);
    }

    private void processRec(boolean tiempoSeleccionado){
        MazeSolverRecursivo mazeSolverRec = new MazeSolverRecursivo();
        Cell start = new Cell(viewMaze.getInicioX(), viewMaze.getInicioY());
        Cell end = new Cell(viewMaze.getDesX(), viewMaze.getDesY());
        List<Cell> rutaResultado = mazeSolverRec.getPath(new Maze(viewMaze.getMaze()), viewMaze.getMaze(), start, end);
        JButton[][] botones = viewMaze.getMazeButtons();
        Set<Cell> visitados = mazeSolverRec.getVisitadas();

        reiniciarRecorrido(botones, viewMaze.getMaze());
        
        if (tiempoSeleccionado){
            long startTime = System.nanoTime();
            new Thread(() -> {
                for (Cell celda : visitados){
                    int x = celda.row;
                    int y = celda.col;
                    if (!(x == viewMaze.getInicioX() && y == viewMaze.getInicioY()) && 
                    !(x == viewMaze.getDesX() && y == viewMaze.getDesY())){
                        if (rutaResultado.isEmpty()){
                            botones[x][y].setBackground(Color.RED);
                        } else {
                            botones[x][y].setBackground(rutaResultado.contains(celda) ? Color.GREEN : Color.RED);
                        }
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
            long endTime = System.nanoTime();
            double tiempoDemora = (endTime - startTime) / 1_000_000.0;
            JOptionPane.showMessageDialog(viewMaze, "Tiempo de ejecucion: " + tiempoDemora + " ms");
            viewMaze.hayCamino(rutaResultado);
        } else {
            for (Cell celda : visitados) {
                int x = celda.row;
                int y = celda.col;
                if (!(x == viewMaze.getInicioX() && y == viewMaze.getInicioY()) && 
                    !(x == viewMaze.getDesX() && y == viewMaze.getDesY())){
                        if (rutaResultado.isEmpty()){
                            botones[x][y].setBackground(Color.RED);
                        } else {
                            botones[x][y].setBackground(rutaResultado.contains(celda) ? Color.GREEN : Color.RED);
                        }
                    }
            }
            viewMaze.hayCamino(rutaResultado);
        }
    }

    public void solverDFS(){
        boolean tiempoSeleccionado = viewMaze.getTiempoDemora().isSelected();
        processDFS(tiempoSeleccionado);
    }

    private void processDFS(boolean tiempoSeleccionado){
        MazeSolverDFS mazeSolverDFS = new MazeSolverDFS();
        Cell start = new Cell(viewMaze.getInicioX(), viewMaze.getInicioY());
        Cell end = new Cell(viewMaze.getDesX(), viewMaze.getDesY());
        List<Cell> rutaResultado = mazeSolverDFS.getPath(new Maze(viewMaze.getMaze()), viewMaze.getMaze(), start, end);
        Set<Cell> visitados = mazeSolverDFS.getVisited();
        JButton[][] botones = viewMaze.getMazeButtons();

        reiniciarRecorrido(botones, viewMaze.getMaze());
        
        if (tiempoSeleccionado){
            long startTime = System.nanoTime();
            new Thread(() -> {
                for (Cell celda : visitados){
                    int x = celda.row;
                    int y = celda.col;
                    if (!(x == viewMaze.getInicioX() && y == viewMaze.getInicioY()) && 
                    !(x == viewMaze.getDesX() && y == viewMaze.getDesY())){
                        if (rutaResultado.isEmpty()){
                            botones[x][y].setBackground(Color.RED);
                        } else {
                            botones[x][y].setBackground(rutaResultado.contains(celda) ? Color.GREEN : Color.RED);
                        }
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
            long endTime = System.nanoTime();
            double tiempoDemora = (endTime - startTime) / 1_000_000.0;
            JOptionPane.showMessageDialog(viewMaze, "Tiempo de ejecucion: " + tiempoDemora + " ms");
            viewMaze.hayCamino(rutaResultado);
        } else {
            for (Cell celda : visitados) {
                int x = celda.row;
                int y = celda.col;
                if (!(x == viewMaze.getInicioX() && y == viewMaze.getInicioY()) && 
                    !(x == viewMaze.getDesX() && y == viewMaze.getDesY())){
                        if (rutaResultado.isEmpty()){
                            botones[x][y].setBackground(Color.RED);
                        } else {
                            botones[x][y].setBackground(rutaResultado.contains(celda) ? Color.GREEN : Color.RED);
                        }
                    }
            }
            viewMaze.hayCamino(rutaResultado);
        }
    }

    public void reiniciarRecorrido(JButton[][] botones, boolean[][] maze){
        for(int i = 0; i < viewMaze.getRows(); i++){
            for (int j = 0; j < viewMaze.getCols(); j++){
                if (!(i == viewMaze.getInicioX() && j == viewMaze.getInicioY()) && 
                    !(i == viewMaze.getDesX() && j == viewMaze.getDesY())){
                        if (maze[i][j]){
                            botones[i][j].setBackground(null);
                        }
                    }
            }
        }
    }

}
