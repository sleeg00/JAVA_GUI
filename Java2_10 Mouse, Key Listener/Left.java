package org.example;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Left extends JFrame {
    StringBuffer s = new StringBuffer("Love Java");
    Left(){
        setTitle("LEFT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Love Java");
        label.addKeyListener(new Key());


        add(label);
        setSize(200,200);
        setVisible(true);
        label.requestFocus();
    }
    public static void main(String[] args){
        new Left();
    }
    class Key extends KeyAdapter {
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode()==KeyEvent.VK_LEFT){
                JLabel label=(JLabel)e.getSource();
                label.setText(s.reverse().toString());
            }
        }
    }
}
