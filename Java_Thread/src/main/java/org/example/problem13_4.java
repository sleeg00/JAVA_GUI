package org.example;

import javax.swing.*;
import java.awt.*;

public class problem13_4 extends JFrame {
    public problem13_4(){

        super("!!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();

        JLabel label = new JLabel("진동 레이블");
        label.setFont(new Font("Gothic", Font.ITALIC, 50));
        c.add(label);
        label.setLocation(50, 50);

        MyThread th = new MyThread(label);
        th.start();

        setSize(500, 500);
        setVisible(true);
    }
    class MyThread extends Thread{
        private JLabel label =null;
        public MyThread(JLabel label){
            this.label = label;
        }
        public void run(){
            while(true){
                int x = label.getX();
                if(x==50) x+=5;
                else x-=5;

                label.setLocation(x,x);
            }
        }
    }
    public static void main(String[] args) {
        new problem13_4();
    }
}
