package com.Suven.Consultants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

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
    private JTextArea Analysis;
    private JTextField paid;
    private JButton exitButton;
    private JLabel loanBalenceLabel;
    private JLabel intrestRateLabel;
    private JLabel numberOfPaymentsLabel;
    private JLabel monthlyPaymentsLabel;
    private JLabel loanAnalysisLabel;

    public app() {
        Analysis.setDragEnabled(false);
        cancelpaid.setVisible(false);
        paid.setBackground(Color.YELLOW);
        paid.setEnabled(false);
        newLoanAnalysisButton.setEnabled(false);
        Analysis.setFont(new Font("Courier New", Font.PLAIN, 14));
        Analysis.setEditable(false);
        Analysis.setPreferredSize(new Dimension(250, 150));
        Analysis.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cancelpayment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cancelpayment.setVisible(false);
                payments.setBackground(Color.YELLOW);
                paid.setBackground(Color.getHSBColor(63,66,80));
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
                paid.setBackground(Color.getHSBColor(63,66,80));
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
                    int paidz=new LoanCalculator().paid(Double.parseDouble(intrestrate.getText()),Double.parseDouble(Balencs.getText()),Integer.parseInt(payments.getText()));
                   paid.setText(String.valueOf(paidz));
                    Analysis.setText("Loan Balance: $"+new DecimalFormat("0.00").format(Integer.parseInt( Balencs.getText())));
                    Analysis.append("\nInterest Rate: "+new DecimalFormat("0.00").format(Double.parseDouble(intrestrate.getText())));
                    int month=Integer.parseInt(payments.getText());
                    double Intrest=Double.parseDouble(intrestrate.getText())/1200;
                    double LoanBalence=Double.parseDouble( Balencs.getText());
                    for (int i=1;i<=Integer.parseInt(payments.getText());i++){

                        LoanBalence+=LoanBalence*Intrest-paidz;
                    }
                    double finalpayment=LoanBalence;
                    if (finalpayment>Double.parseDouble(Balencs.getText())){
                        LoanBalence+=LoanBalence*Intrest-Double.parseDouble(payments.getText());
                        finalpayment=LoanBalence;
                        month++;
                    }
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
