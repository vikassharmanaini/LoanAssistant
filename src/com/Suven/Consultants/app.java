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
    private JTextField Balencs;

    public boolean isMethod() {
        return method;
    }

    public void setMethod(boolean method) {
        this.method = method;
    }

    private JTextField intrestrate;
    private JTextField payments;
    private JButton cancelpayment;
    private JButton cancelpaid;
    private JButton computeMonthlyPaymentsButton;
    private JButton newLoanAnalysisButton;
    private JTextArea textArea1;
    private JTextField paid;
    private JButton exitButton;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;

    public app() {
        cancelpaid.setVisible(false);
        paid.setBackground(Color.YELLOW);
        paid.setEnabled(false);
        newLoanAnalysisButton.setEnabled(false);
        textArea1.setFont(new Font("Courier New", Font.PLAIN, 14));
        textArea1.setEditable(false);
        textArea1.setPreferredSize(new Dimension(250, 150));
        textArea1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cancelpayment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cancelpayment.setVisible(false);
                payments.setBackground(Color.YELLOW);
                payments.setEnabled(false);
                payments.setForeground(Color.BLACK);
                cancelpaid.setVisible(true);
                paid.setBackground(Color.WHITE);
                paid.setEnabled(true);
                setMethod(false);
                System.out.println(method);
            }
        });


        cancelpaid.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cancelpaid.setVisible(false);
                paid.setBackground(Color.YELLOW);
                paid.setEnabled(false);
                paid.setForeground(Color.BLACK);
                cancelpayment.setVisible(true);
                payments.setBackground(Color.WHITE);
                payments.setEnabled(true);
                setMethod(true);
                System.out.println(method);
            }
        });
        computeMonthlyPaymentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isMethod()){
                   paid.setText(String.valueOf(new LoanCalculator().paid(Double.parseDouble(intrestrate.getText()),Double.parseDouble(Balencs.getText()),Integer.parseInt(payments.getText()))));
                }
                else {
                    payments.setText(String.valueOf(new LoanCalculator().findhowmuchtimes(Double.parseDouble(intrestrate.getText()),Double.parseDouble(Balencs.getText()),Double.parseDouble(paid.getText()))));
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
                Balencs.setText("");
                intrestrate.setText("");
                paid.setText("");
                payments.setText("");
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
