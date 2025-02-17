package views;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.*;

public class ViewMain extends JFrame{
    private JPanel panel;
    private JLabel tituloInicio, tituloDes, tituloCol, tituloRow;
    private JButton btnAceptar, btnDefault;
    private JTextField txtXInicio, txtYInicio, txtXDes, txtYDes, txtCol, txtRow;


    public ViewMain() {
        panel = new JPanel();
        tituloInicio = new JLabel("INICIO");
        tituloDes = new JLabel("DESTINO");
        tituloCol = new JLabel("Numero de columnas:");
        tituloRow = new JLabel("Numero de filas:");

        btnAceptar = new JButton("Aceptar");
        btnDefault = new JButton("Default");

        txtXInicio = new JTextField("X");
        txtYInicio = new JTextField("Y");
        txtXDes = new JTextField("X");
        txtYDes = new JTextField("Y");
        txtCol = new JTextField();
        txtRow = new JTextField();

        tituloInicio.setBounds(75, 150, 100, 100);
        tituloDes.setBounds(250, 150, 100, 100);
        tituloCol.setBounds(75, 50, 150, 100);
        tituloRow.setBounds(250, 50, 100, 100);

        btnAceptar.setBounds(75, 300, 100, 30);
        btnDefault.setBounds(250, 300, 100, 30);

        txtXInicio.setBounds(75, 225, 100, 20);
        txtYInicio.setBounds(75, 250, 100, 20);
        txtXDes.setBounds(250, 225, 100, 20);
        txtYDes.setBounds(250, 250, 100, 20);
        focusListener(txtXInicio, "X");
        focusListener(txtYInicio, "Y");
        focusListener(txtXDes, "X");
        focusListener(txtYDes, "Y");

        txtCol.setBounds(75, 125, 100, 30);
        txtRow.setBounds(250, 125, 100, 30);

        panel.setLayout(null);
        panel.add(tituloInicio);
        panel.add(tituloDes);
        panel.add(tituloCol);
        panel.add(tituloRow);

        panel.add(btnAceptar);
        panel.add(btnDefault);

        panel.add(txtXInicio);
        panel.add(txtYInicio);
        panel.add(txtXDes);
        panel.add(txtYDes);
        panel.add(txtCol);
        panel.add(txtRow);

        add(panel);
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public boolean emptySpaces(){
        return !(txtXInicio.getText().isEmpty() || txtYInicio.getText().isEmpty()|| txtXDes.getText().isEmpty() 
        || txtYDes.getText().isEmpty() || txtRow.getText().isEmpty() || txtCol.getText().isEmpty());
    }

    private void focusListener(JTextField campo, String s){
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e){
                if (campo.getText().equals(s)){
                    campo.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e){
                if (campo.getText().isEmpty()){
                    campo.setText(s);
                }
            }
        });
    }

    public void limpiarTexto(){
        txtCol.setText("");
        txtRow.setText("");
        txtXInicio.setText("");
        txtYInicio.setText("");
        txtXDes.setText("");
        txtYDes.setText("");
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public JButton getBtnDefault() {
        return btnDefault;
    }

    public JTextField getTxtCol() {
        return txtCol;
    }


    public JTextField getTxtRow() {
        return txtRow;
    }

    public JLabel getTituloRow() {
        return tituloRow;
    }

    public JTextField getTxtXInicio() {
        return txtXInicio;
    }

    public JTextField getTxtYInicio() {
        return txtYInicio;
    }

    public JTextField getTxtXDes() {
        return txtXDes;
    }

    public JTextField getTxtYDes() {
        return txtYDes;
    }

}
