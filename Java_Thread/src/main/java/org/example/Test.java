package org.example;

import javax.swing.*;
import java.awt.*;

class T extends JLabel implements Runnable{
    private long delay;
    public T(String Text, long delay){
        super(Text);
        this.delay=delay;
        setOpaque(true);
        Thread th = new Thread(this);
        th.start();
    }
    public void run(){
        int n=0;
        while(true){
            if(n==0)
                setBackground(Color.GREEN);
            else
                setBackground(Color.YELLOW);
            if(n==0)
                n=1;
            else
                n=0;
            try{
                Thread.sleep(delay);
            }catch(InterruptedException e){
                return;
            }
        }
    }
}
public class Test extends JFrame {
    public Test(){
        setTitle("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        T label = new T("깜빡", 500);
        JLabel label1 = new JLabel("안 깜빡");
        T label2 = new T("여기도 깜빡", 300);

        c.add(label);
        c.add(label1);
        c.add(label2);
        setSize(300, 300);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Test();
    }
}
