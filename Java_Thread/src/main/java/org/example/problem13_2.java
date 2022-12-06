package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class problem13_2 extends JFrame {
    private MyPanel panel = new MyPanel();
    public problem13_2(){
        setTitle("!!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setSize(400, 400);
        setVisible(true);

        Container c = getContentPane();

        MyThread th = new MyThread(panel);
        c.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                th.start();
            }
        });

        setFocusable(true);
        requestFocus();
    }
    class MyPanel extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(Color.MAGENTA);
            int x = (int)(Math.random()*300);
            int y = (int)(Math.random()*300);
            g.drawOval(x, y, 50, 50);
        }
    }
    class MyThread extends Thread{
        private MyPanel panel;

        public MyThread(MyPanel panel){
            this.panel = panel;
        }

        public void run(){
            while(true){
                panel.repaint();
                try{
                    sleep(400);
                }catch(InterruptedException e){
                    return;
                }
            }
        }
    }
    public static void main(String[] args) {
        new problem13_2();
    }
}
