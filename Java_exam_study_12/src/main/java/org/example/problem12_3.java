package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class problem12_3 extends JFrame {

    private ImageIcon image = new ImageIcon("/Users/leeseonghyeon/Desktop/다운로드-removebg-preview.png");
    private Image img = image.getImage();
    private JLabel label = new JLabel();
    public problem12_3(){
        setTitle("ㅎ ㅏ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);

        label.setIcon(image);
        label.setSize(image.getIconWidth(), image.getIconHeight());
        c.add(label);
        c.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e){
                Component c = (Component)e.getSource();
                c.setLocation(e.getX(), e.getY());
            }
        });

        setSize(300, 300);
        setVisible(true);
    }
    public static void main(String[] args){
        new problem12_3();
    }
}
