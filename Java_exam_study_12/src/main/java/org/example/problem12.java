package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class problem12 extends JFrame {
    private MyPanel panel = new MyPanel();
    private ImageIcon image = new ImageIcon("/Users/leeseonghyeon/Desktop/다운로드-removebg-preview.png");
    private Image img = image.getImage();

    public problem12(){
        setTitle("one");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);


        setLayout(new FlowLayout());
        setSize(300, 300);
        setVisible(true);
    }
    class MyPanel extends JPanel{
        int x = 100, y=100;
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);		// 화면에 꽉 차게 설정
            g.setColor(Color.GREEN);
            g.fillOval(x, y, 20, 20);
            addMouseMotionListener(new MouseAdapter(){
                public void mouseDragged(MouseEvent e){
                    x=e.getX();
                    y=e.getY();
                    repaint();
                }
            });
        }
    }

    public static void main(String[] args) {
        new problem12();
    }
}
