package com.Suven.Consultants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class app{
    private boolean method=true;
    private JPanel label1;
    private JTextField balencs;

    public boolean isMethod() {
        return method;
    }

    public void setMethod(boolean method) {
        this.method = method;
    }

    private JTextField intrestrate;
    private JTextField monthtextfield;
    private JButton cancelpayment;
    private JButton cancelpaid;
    private JButton computeMonthlyPaymentsButton;
    private JButton newLoanAnalysisButton;
    private JTextArea Analysis;
    private JTextField paymentText;
    private JButton exitButton;
    private JLabel loanBalenceLabel;
    private JLabel intrestRateLabel;
    private JLabel numberOfPaymentsLabel;
    private JLabel monthlyPaymentsLabel;
    private JLabel loanAnalysisLabel;
    JFrame f=new JFrame("Loan Assistant");
    public app() {
        balencs.setHorizontalAlignment(SwingConstants.RIGHT);
        intrestrate.setHorizontalAlignment(SwingConstants.RIGHT);
        monthtextfield.setHorizontalAlignment(SwingConstants.RIGHT);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Analysis.setDragEnabled(false);
        cancelpaid.setVisible(false);
        paymentText.setBackground(Color.YELLOW);
        paymentText.setEnabled(false);
        paymentText.setHorizontalAlignment(SwingConstants.RIGHT);
        newLoanAnalysisButton.setEnabled(false);
        Analysis.setFont(new Font("Courier New", Font.PLAIN, 14));
        Analysis.setEditable(false);
        Analysis.setPreferredSize(new Dimension(250, 150));

        cancelpayment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cancelpayment.setVisible(false);

                monthtextfield.setBackground(Color.YELLOW);
                monthtextfield.setEnabled(false);
                monthtextfield.setForeground(Color.BLACK);
                cancelpaid.setVisible(true);
                paymentText.setBackground(Color.WHITE);
                paymentText.setEnabled(true);
                setMethod(false);
                System.out.println(method);
            }
        });


        cancelpaid.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cancelpaid.setVisible(false);
                paymentText.setBackground(Color.yellow);
                paymentText.setEnabled(false);
                paymentText.setForeground(Color.BLACK);
                cancelpayment.setVisible(true);
                monthtextfield.setBackground(Color.WHITE);
                monthtextfield.setEnabled(true);
                setMethod(true);
                System.out.println(method);
            }
        });
        computeMonthlyPaymentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double balance,intrest,payment;
                int months;
                double monthlyintrest,multiplier,loanbalance,finalPaqyment;
                if (validateDecimal(balencs)) {
                    balance = Double.valueOf(balencs.getText()).doubleValue();
                }else{
                    JOptionPane.showConfirmDialog(null,"Invalid or empty Loan Balance entry.\nPlease correct.","Balance Input Error",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (validateDecimal(intrestrate)){
                    intrest=Double.valueOf(intrestrate.getText()).doubleValue();
                }else {
                    JOptionPane.showConfirmDialog(null,"Invalid or empty Interest Rate entry.\nPlease correct.","Interest Input Error",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                monthlyintrest=intrest/1200;
                if (isMethod()){
                    if (validateDecimal(monthtextfield)) {
                        months = Integer.valueOf(monthtextfield.getText()).intValue();
                    }else{
                        JOptionPane.showConfirmDialog(null,"Invalid or empty Number of Payments entry.\nPlease correct.","Number of Payment Input Error",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (intrest == 0){
                        payment=balance/months;
                    }
                    else {
                        multiplier = Math.pow(1 + monthlyintrest, months);
                        payment = balance * monthlyintrest * multiplier / (multiplier - 1);
                    }
                paymentText.setText(new DecimalFormat("0.00").format(payment));

                }
                else {
                    if (validateDecimal(paymentText)) {

                        payment = Double.valueOf(paymentText.getText()).doubleValue();
                        if (payment<=(balance*monthlyintrest+1.0)){
                            if (JOptionPane.showConfirmDialog(null,"Minimum Payment must be $"+new DecimalFormat("0.00").format((int)(balance*monthlyintrest+1.0))+"\nDo you want to use the minimum payment?","Input Error",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION) {
                                paymentText.setText(new DecimalFormat("0.00").format((int) (balance * monthlyintrest + 1.0)));
                                payment = Double.valueOf(paymentText.getText()).doubleValue();
                            }else
                                paymentText.requestFocus();
                                return;
                        }
                    }else{
                        JOptionPane.showConfirmDialog(null,"Invalid or empty Monthly Payments entry.\nPlease correct.","Payment Input Error",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    months = (int) ((Math.log(payment) - Math.log(payment - balance * monthlyintrest)) / Math.log(1 + monthlyintrest));
                    monthtextfield.setText(String.valueOf(months));
                }
                //rest payment to fix decimal value  for loan Analysis
                payment=Double.valueOf(paymentText.getText()).doubleValue();
                Analysis.setText("Loan Balence: $"+new DecimalFormat("0.00").format(balance));
                Analysis.append(String.format("\n" + "Interest Rate:" + new DecimalFormat("0.00").format(intrest)));
                //process all but last payment
                loanbalance=balance;
                for (int paymentnumber = 1; paymentnumber <=months-1; paymentnumber++) {
                    loanbalance+=loanbalance*monthlyintrest-payment;
                }
                finalPaqyment=loanbalance;
                if (finalPaqyment>payment){
                    //apply one more payment
                    loanbalance+=loanbalance*monthlyintrest-payment;
                    finalPaqyment=loanbalance;
                    months++;
                    monthtextfield.setText(String.valueOf(months));
                }
                Analysis.append("\n\n"+String.valueOf(months-1)+" Payment of $"+new DecimalFormat("0.00").format(payment));
                Analysis.append("\nFinal Payment: $"+new DecimalFormat("0.00").format(finalPaqyment));
                Analysis.append("\nTotal Payments: $"+new DecimalFormat("0.00").format((months-1)*payment+finalPaqyment));
                Analysis.append("\nInterest Paid $"+new DecimalFormat("0.00").format((months-1)*payment+finalPaqyment-balance));
                newLoanAnalysisButton.requestFocus();


                newLoanAnalysisButton.setEnabled(true);
                computeMonthlyPaymentsButton.setEnabled(false);
            }
        });
        newLoanAnalysisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newLoanAnalysisButton.setEnabled(false);
                computeMonthlyPaymentsButton.setEnabled(true);
                balencs.setText("");
                intrestrate.setText("");
                paymentText.setText("");
                monthtextfield.setText("");
                Analysis.setText("");
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        balencs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                balencs.transferFocus();
            }
        });
        intrestrate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                intrestrate.transferFocus();
            }
        });
        monthtextfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                monthtextfield.transferFocus();
            }
        });
        paymentText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paymentText.transferFocus();
            }
        });
    }
    boolean validateDecimal(JTextField tf){
        String s=tf.getText().trim();
        boolean hasdecimal=false;
        boolean valid=true;
        if(s.length()==0)
            valid=false;
        else {
            for (int i = 0; i <s.length(); i++) {
                char c=s.charAt(i);
                if(c>='0'&& c<='9')
                    continue;
                else if(c=='.'&&!hasdecimal){
                    hasdecimal=true;
                }
                else {
                    valid=false;
                }
            }
        }
        tf.setText(s);
        if(!valid)
            tf.requestFocus();
        return (valid);
    }

    public void lunch(){

        f.setTitle("Loan Assistant");
        f.setLocation(400,200);
        f.setFocusable(true);
        f.setResizable(false);
        f.setContentPane(new app().label1);
        f.pack();
        f.setVisible(true);
    }
}
