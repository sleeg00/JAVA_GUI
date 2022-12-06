package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class problem12_4 extends JFrame{
    private ImageIcon image = new ImageIcon("/Users/leeseonghyeon/Desktop/다운로드-removebg-preview.png");
    private Image img = image.getImage();
    private JLabel label = new JLabel();
    private MyPanel panel = new MyPanel();
    public problem12_4(){
        setTitle("!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setVisible(true);
        setSize(500, 500);
    }
    class MyPanel extends JPanel{
        int x=0;
        int y=0;
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, x, y, getWidth()/4, getHeight()/4,this);
            addMouseMotionListener(new MouseAdapter(){
                public void mouseDragged(MouseEvent e){
                    x=e.getX();
                    y=e.getY();
                    repaint();
                }
            });
        }
    }
    public static void main(String[] args){
        new problem12_4();
    }
}
