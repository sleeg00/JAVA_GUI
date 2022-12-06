package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Wheel extends JFrame {
    Wheel(){
        setTitle("Wheel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Love Java");
        label.addMouseWheelListener(new MWL());
        add(label);

        setSize(200, 100);
        setVisible(true);
    }
    public static void main(String[] args){
        new Wheel();
    }
    class MWL implements MouseWheelListener {
        public void mouseWheelMoved(MouseWheelEvent e){
            int n = e.getWheelRotation();
            JLabel label = (JLabel)e.getSource();
            Font f;
            f=label.getFont();
            int size = f.getSize();
            if(n<0){
                if(size>5)
                label.setFont(new Font("Arial", Font.PLAIN, size-10));
            }
            else
                label.setFont(new Font("Arial", Font.PLAIN, size+10));
        }
    }
}
