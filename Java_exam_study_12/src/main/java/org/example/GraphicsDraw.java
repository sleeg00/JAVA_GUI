package org.example;

import javax.swing.*;
import java.awt.*;

public class GraphicsDraw extends JFrame {

    private MyPanel panel = new MyPanel();
    public GraphicsDraw(){
        setTitle("ㅁㅇㄹㅁㄴ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setSize(250, 250);
        setVisible(true);
    }
    class MyPanel extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.drawLine(20, 20, 100, 100);
        }
    }
    public static void main(String[] args){
        new GraphicsDraw();
    }
}
