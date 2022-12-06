package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PLUS extends JFrame {
    PLUS(){
        setTitle("플러스");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Love Java");
        label.setFont(new Font("Arial", Font.PLAIN, 50));
        label.addKeyListener(new KEY());

        add(label);
        setSize(400, 200);
        setVisible(true);
        label.requestFocus();
    }
    public static void main(String[] args){
        new PLUS();
    }
    class KEY extends KeyAdapter {
        public void keyPressed(KeyEvent e){
            JLabel label =(JLabel)e.getSource();
            Font f=label.getFont();
            int size = f.getSize();

            if(e.getKeyCode()==KeyEvent.VK_MINUS){
                if(size>5)
                    label.setFont(new Font("Arial", Font.PLAIN, size-5));
            }
            else {
                label.setFont(new Font("Arial", Font.PLAIN, size+5));
            }
        }
    }
}
