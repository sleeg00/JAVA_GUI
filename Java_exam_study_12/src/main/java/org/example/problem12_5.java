package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class problem12_5 extends JFrame {
    private MyPanel panel = new MyPanel();
    private ImageIcon image = new ImageIcon("/Users/leeseonghyeon/Desktop/다운로드-removebg-preview.png");
    private Image img = image.getImage();
    private int width = img.getWidth(this);
    private int height = img.getHeight(this);
    public problem12_5(){
        setTitle("!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setSize(300, 300);
        setVisible(true);
    }
    class MyPanel extends JPanel{
        public MyPanel(){
            setFocusable(true);
            requestFocus();

            addKeyListener(new KeyAdapter(){
                public void keyTyped(KeyEvent e){
                    char key = e.getKeyChar();
                    if(key=='+'){
                        width=(int)(width*1.1);
                        height=(int)(height*1.1);
                    }
                    else{
                        width=(int)(width*0.9);
                        height=(int)(height*0.9);
                    }
                    repaint();
                }
            });
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 10, 10, width, height, this);
        }


    }

    public static void main(String[] args){
        new problem12_5();
    }
}
