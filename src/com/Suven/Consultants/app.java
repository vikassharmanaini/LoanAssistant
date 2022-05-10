package com.Suven.Consultants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class app{
    private JPanel label1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton xButton;
    private JButton xButton1;
    private JButton computeMonthlyPaymentsButton;
    private JButton newLoanAnalysisButton;
    private JTextArea textArea1;
    private JTextField textField4;
    private JButton exitButton;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;

    public app() {
        xButton1.setVisible(false);
        textField4.setBackground(Color.YELLOW);
        textField4.setEnabled(false);
        xButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                xButton.setVisible(false);
                textField3.setBackground(Color.YELLOW);
                textField3.setEnabled(false);
                xButton1.setVisible(true);
                textField4.setBackground(Color.WHITE);
                textField4.setEnabled(true);
            }
        });
        xButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                xButton1.setVisible(false);
                textField4.setBackground(Color.YELLOW);
                textField4.setEnabled(false);
                xButton.setVisible(true);
                textField3.setBackground(Color.WHITE);
                textField3.setEnabled(true);
            }
        });
    }

    public void lunch(){
        JFrame f=new JFrame();
        xButton1.setVisible(false);
        f.setContentPane(new app().label1);
        f.pack();
        f.setVisible(true);

    }
}
