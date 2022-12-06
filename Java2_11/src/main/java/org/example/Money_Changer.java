package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Money_Changer extends JFrame {
    JButton cal;
    JTextField input;
    JLabel money[]=new JLabel[8];
    String moneykor[]={"오만원", "만원", "천원", "500원", "100원", "50원", "10원", "1원"};
    int moneys[] ={50000, 10000, 1000, 500, 100, 50, 10, 1};
    JTextField result[]=new JTextField[8];

    Money_Changer(){
        setTitle("Money Changer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.setBackground(Color.PINK);

        JPanel North = new JPanel();

        North.setLayout(new FlowLayout());
        North.add(new JLabel("금액"));

        input=new JTextField(10);
        cal=new JButton("계산");

        cal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                int total = Integer.parseInt(input.getText());
                for(int i=0; i<8; i++){
                    result[i].setText(Integer.toString(total/moneys[i]));
                    total%=moneys[i];
                }
            }
        });
        North.add(input);
        North.add(cal);
        North.setBackground(Color.PINK);
        c.add(North, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(8,2));
        center.setBackground(Color.PINK);
        for(int i=0; i<8; i++){
            money[i]=new JLabel(moneykor[i]);
            result[i]=new JTextField();
            center.add(money[i]);
            center.add(result[i]);
        }
        c.add(center, BorderLayout.CENTER);

        JPanel west=new JPanel();
        west.setBackground(Color.pink);
        c.add(west,BorderLayout.WEST);

        JPanel east=new JPanel();
        east.setBackground(Color.pink);
        c.add(west,BorderLayout.EAST);

        JPanel south=new JPanel();
        south.setBackground(Color.pink);
        c.add(west,BorderLayout.SOUTH);

        setSize(300,300);
        setVisible(true);
    }
    public static void main(String[] args){
        new Money_Changer();
    }
}
