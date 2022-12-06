package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CLICK extends JFrame {
    CLICK(){
        setTitle("클릭");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
        JLabel label = new JLabel("C");

        label.setSize(100, 50);
        label.setFont(new Font("Arial", Font.PLAIN, 50));
        label.setLocation(100, 100);
        label.addMouseListener(new ML());

        add(label);
        setSize(500, 500);
        setVisible(true);
        label.requestFocus();
    }
    public static void main(String[] args){
        new CLICK();
    }
    class ML extends MouseAdapter {
        public void mousePressed(MouseEvent e){
            JLabel label = (JLabel)e.getSource();
            if(label.getText().equals("C")){
                int x=(int)(Math.random()*300);
                int y=(int)(Math.random()*300);
                label.setLocation(x, y);
            }
        }
    }
}
