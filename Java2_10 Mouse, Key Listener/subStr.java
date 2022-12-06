package org.example;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class subStr extends JFrame {
    int cnt=0;
    String text = "Love Java";
    subStr(){
        setTitle("제목");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Love Java");
        label.addKeyListener(new KY());

        add(label);
        setSize(300, 200);
        setVisible(true);
        label.requestFocus();
    }
    public static void main(String[] args){
        new subStr();
    }
    class KY extends KeyAdapter {
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode()==KeyEvent.VK_LEFT){
                cnt++;
                JLabel label = (JLabel)e.getSource();
                label.setText(text.substring(cnt)+text.substring(0, cnt));
            }
        }
    }
}
