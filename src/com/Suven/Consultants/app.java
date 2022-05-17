package com.Suven.Consultants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class app{
    private boolean method=true;
    private JPanel label1;
    private JTextField textField1;

    public boolean isMethod() {
        return method;
    }

    public void setMethod(boolean method) {
        this.method = method;
    }

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
        newLoanAnalysisButton.setEnabled(false);
        textArea1.setFont(new Font("Courier New", Font.PLAIN, 14));
        textArea1.setEditable(false);
        textArea1.setPreferredSize(new Dimension(250, 150));
        textArea1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
                setMethod(true);
                System.out.println(method);
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
                setMethod(false);
                System.out.println(method);
            }
        });
        computeMonthlyPaymentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (method){

                   textField4.setText(String.valueOf(new LoanCalculator().paid(Double.parseDouble(textField2.getText()),Double.parseDouble(textField1.getText()),Integer.parseInt(textField3.getText()))));
                }
                else {
                    textField3.setText(String.valueOf(new LoanCalculator().findhowmuchtimes(Double.parseDouble(textField2.getText()),Double.parseDouble(textField1.getText()),Double.parseDouble(textField4.getText()))));
                }
                newLoanAnalysisButton.setEnabled(true);
                computeMonthlyPaymentsButton.setEnabled(false);
            }
        });
        newLoanAnalysisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newLoanAnalysisButton.setEnabled(false);
                computeMonthlyPaymentsButton.setEnabled(true);
                textField1.setText("");
                textField2.setText("");
                textField4.setText("");
                textField3.setText("");
            }
        });
    }

    public void lunch(){
        JFrame f=new JFrame();
        f.setLocation(400,200);
        f.setFocusable(true);
        f.setResizable(false);
        f.setContentPane(new app().label1);
        f.pack();
        f.setVisible(true);

    }
}
