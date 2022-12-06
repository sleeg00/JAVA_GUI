package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.event.*;
public class GraphicsMouse extends JFrame {
    private MyPanel panel = new MyPanel();
    public GraphicsMouse(){
        setTitle("ㅁㅇㄹㅁㄴ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setSize(250, 250);
        setVisible(true);
    }

    class MyPanel extends JPanel{
        private Vector<Point> vStart = new Vector<Point>();
        private Vector<Point> vEnd = new Vector<Point>();

        public MyPanel() {
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    Point startP = e.getPoint();
                    vStart.add(startP);
                }

                public void mouseReleased(MouseEvent e) {
                    Point endP = e.getPoint();
                    vEnd.add(endP);
                    repaint();
                }
            });
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(Color.BLUE);
            for(int i=0; i<vStart.size(); i++){
                Point s= vStart.elementAt(i);
                Point e = vEnd.elementAt(i);
                g.drawLine((int)s.getX(), (int)s.getY(), (int)e.getX(), (int)e.getY());
            }

        }
    }
    public static void main(String[] args){
        new GraphicsMouse();
    }

}
