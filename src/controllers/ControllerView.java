package controllers;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

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
        viewMain.getBtnAceptar().addActionListener(_ -> aceptar());
        viewMain.getBtnDefault().addActionListener(_ -> defaultMaze());
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
        viewMaze = new ViewMaze(10, 10, 0, 0, 9, 9);
        agregarEventosMaze();
        viewMain.dispose();
        viewMaze.setVisible(true);
    }
    
    private void agregarEventosMaze(){
        viewMaze.getBtnObstaculos().addActionListener(_ -> obstaculos());
        viewMaze.getBtnSolverBFS().addActionListener(_ -> solverBFS());
        viewMaze.getBtnSolverDP().addActionListener(_ -> solverDP());
        viewMaze.getBtnSolverRec().addActionListener(_ -> solverRec());
        viewMaze.getBtnReiniciar().addActionListener(_ -> reiniciarMaze());
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

                    botones[i][j].addActionListener(_ -> {
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

        viewMaze.getBtnAceptar().addActionListener(_ -> cerrarOpciones());
        viewMaze.getBtnLimpiar().addActionListener(_ -> {cerrarOpciones();
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
        if (viewMaze.getTiempoDemora().isSelected()){
            long startTime = System.nanoTime();
            processBFS();
            long endTime = System.nanoTime();
            double tiempoDemora = (endTime - startTime) / 1_000_000.0;
            JOptionPane.showMessageDialog(viewMaze, "Tiempo de ejecucion: " + tiempoDemora + " ms");
        } else {
            processBFS();
        }
    }

    private void processBFS(){
        
    }

    public void solverDP(){
        if (viewMaze.getTiempoDemora().isSelected()){
            long startTime = System.nanoTime();
            processDP();
            long endTime = System.nanoTime();
            double tiempoDemora = (endTime - startTime) / 1_000_000.0;
            JOptionPane.showMessageDialog(viewMaze, "Tiempo de ejecucion: " + tiempoDemora + " ms");
        } else {
            processDP();
        }
    }

    private void processDP(){
        
    }

    public void solverRec(){
        if (viewMaze.getTiempoDemora().isSelected()){
            long startTime = System.nanoTime();
            processRec();
            long endTime = System.nanoTime();
            double tiempoDemora = (endTime - startTime) / 1_000_000.0;
            JOptionPane.showMessageDialog(viewMaze, "Tiempo de ejecucion: " + tiempoDemora + " ms");
        } else {
            processRec();
        }
    }

    private void processRec(){

    }

}
