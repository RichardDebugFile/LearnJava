package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalGUI {
    private JPanel PrincipalGUI;
    private JButton btnConceptos;
    private JButton btnHolaMundo;
    private JButton btnBucles;
    private JButton declaraciónDeVariablesButton;
    private JButton btnRobertIA;
    private JButton btnExamen;

    public PrincipalGUI(JFrame frame) {
        btnConceptos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnConceptos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnHolaMundo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnBucles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getPanel() {
        return PrincipalGUI;
    }
}
