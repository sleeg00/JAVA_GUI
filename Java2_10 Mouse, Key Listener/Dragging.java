package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Dragging extends JFrame {
    Dragging(){
        setTitle("드래기동안~");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();

        c.setLayout(new FlowLayout());
        c.setBackground(Color.GREEN);
        c.addMouseMotionListener(new MyMouseMotionAdapter());
        c.addMouseListener(new MyMouseAdapter2());
        setSize(400, 200);
        setVisible(true);
    }
    public class MyMouseMotionAdapter extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e){
            Container c = (Container)e.getSource();
            c.setBackground(Color.GREEN);
        }
    }
    public class MyMouseAdapter2 extends MouseAdapter{
        public void mouseReleased(MouseEvent e){
            Container c = (Container)e.getSource();
            setBackground(Color.YELLOW);
        }
    }
    public static void main(String[] args){
        new Dragging();
    }
}

