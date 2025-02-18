package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.*;

import models.Cell;

public class ViewMaze extends JFrame{
    private JPanel panelMaze, panelInferior, panelButtons, panelOpciones;
    private JButton btnObstaculos, btnSolverBFS, btnSolverDP, btnSolverDFS, btnSolverRec, btnReiniciar, btnAceptar, btnLimpiar;
    private JCheckBox tiempoDemora;
    private JButton[][] mazeButtons;
    private boolean[][] maze;
    private int rows, cols, inicioX, inicioY, desX, desY;

    public ViewMaze(int rows, int cols, int inicioX, int inicioY, int desX, int desY) {
        this.rows = rows;
        this.cols = cols;
        this.inicioX = inicioX;
        this.inicioY = inicioY;
        this.desX = desX;
        this.desY = desY;
        panelMaze = new JPanel();
        panelButtons = new JPanel();
        panelOpciones = new JPanel();
        panelInferior = new JPanel(new BorderLayout());
        mazeButtons = new JButton[rows][cols];
        maze = new boolean[rows][cols];
        
        btnAceptar = new JButton("Aceptar");
        btnLimpiar = new JButton("Limpiar Laberinto");
        btnObstaculos = new JButton("Poner Obstaculos");
        btnReiniciar = new JButton("Reiniciar");
        btnSolverBFS = new JButton("Recorrido BFS");
        btnSolverDP = new JButton("Recorrido DP");
        btnSolverRec = new JButton("Recorrido recursivo");
        btnSolverDFS = new JButton("Recorrido DFS");
        tiempoDemora = new JCheckBox();

        setLayout(new BorderLayout());

        panelMaze.setLayout(new GridLayout(rows, cols));
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                mazeButtons[i][j] = new JButton();
                maze[i][j] = true;
                panelMaze.add(mazeButtons[i][j]);
                if ((i == inicioX && j == inicioY) || (i == desX && j == desY)){
                    if (i == inicioX && j == inicioY){
                        mazeButtons[i][j].setText("A");
                        mazeButtons[i][j].setBackground(Color.BLUE);
                    } else {
                        mazeButtons[i][j].setText("B");
                        mazeButtons[i][j].setBackground(Color.BLUE);
                    }
                    
                }
            }            
        }
        deshabilitarBotones();

        panelOpciones.setLayout(new FlowLayout());
        panelOpciones.add(btnAceptar);
        panelOpciones.add(btnLimpiar);
        panelOpciones.setVisible(false);

        panelButtons.setLayout(new FlowLayout());
        panelButtons.add(btnObstaculos);
        panelButtons.add(btnSolverBFS);
        panelButtons.add(btnSolverDFS);
        panelButtons.add(btnSolverDP);
        panelButtons.add(btnSolverRec);
        panelButtons.add(btnReiniciar);
        panelButtons.add(new JLabel("Tiempo de demora:"));
        panelButtons.add(tiempoDemora);

        panelInferior.add(panelButtons, BorderLayout.CENTER);
        panelInferior.add(panelOpciones, BorderLayout.SOUTH);

        add(panelMaze, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        setSize(900, 500);
        setName("Laberinto Proyecto Final");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void habilitarBotones(){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                mazeButtons[i][j].setEnabled(true);
            }            
        }
    }

    public void deshabilitarBotones(){
        for (JButton[] fila : mazeButtons) {
            for (JButton boton : fila) {
                boton.setEnabled(false);
            }
        }
    }

    public void mostrarOpciones(){
        panelOpciones.setVisible(true);
        panelButtons.setVisible(false);
    }

    public void ocultarOpciones(){
        panelOpciones.setVisible(false);
        panelButtons.setVisible(true);
    }

    public void hayCamino(List<Cell> rutaResultado){
        if (rutaResultado.isEmpty()){
            JOptionPane.showMessageDialog(this, "No se ha encontrado un camino hacia el destino :/");
        }
    }

    public JButton getBtnObstaculos() {
        return btnObstaculos;
    }

    public JButton getBtnSolverBFS() {
        return btnSolverBFS;
    }

    public JButton getBtnSolverDFS() {
        return btnSolverDFS;
    }

    public JButton getBtnSolverDP() {
        return btnSolverDP;
    }

    public JButton getBtnSolverRec() {
        return btnSolverRec;
    }

    public JButton getBtnReiniciar() {
        return btnReiniciar;
    }

    public JButton[][] getMazeButtons() {
        return mazeButtons;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public JPanel getPanelInferior() {
        return panelInferior;
    }

    public JPanel getPanelOpciones() {
        return panelOpciones;
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public int getInicioX() {
        return inicioX;
    }

    public int getInicioY() {
        return inicioY;
    }

    public int getDesX() {
        return desX;
    }

    public int getDesY() {
        return desY;
    }

    public JCheckBox getTiempoDemora(){
        return tiempoDemora;
    }

    public boolean[][] getMaze(){
        return maze;
    }
}
