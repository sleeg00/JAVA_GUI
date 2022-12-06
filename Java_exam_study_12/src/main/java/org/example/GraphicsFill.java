package org.example;

import javax.swing.*;
import java.awt.*;

public class GraphicsFill extends JFrame {
    private MyPanel panel = new MyPanel();
    public GraphicsFill(){
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
            g.fillRect(10, 10, 50, 50);
            g.setColor(Color.BLUE);
            g.fillOval(10, 70, 50, 50);
            g.setColor(Color.GREEN);
            g.fillRoundRect(10, 130, 50, 50, 20, 20);
            g.setColor(Color.YELLOW);
            g.fillArc(10, 190, 50, 50, 0, 270);
            g.setColor(Color.ORANGE);
            int[]x={30, 10, 30, 60};
            int[]y={250, 275, 300, 275};
            g.fillPolygon(x,y,4);
        }
    }
    public static void main(String[] args){
        new GraphicsFill();
    }
}
