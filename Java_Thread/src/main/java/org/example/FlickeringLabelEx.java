package org.example;

import javax.swing.*;
import java.awt.*;
class FlickeringLabel extends JLabel implements Runnable{
    private long delay;

    public FlickeringLabel(String text, long delay){
        super(text);
        this.delay=delay;
        setOpaque(true);

        Thread th = new Thread(this);
        th.start();
    }

    public FlickeringLabel(String text) {
        super(text);
        this.delay=delay;
    }

    public void run(){
        int n=0;
        while(true){
            if(n==0)
                setBackground(Color.YELLOW);
            else
                setBackground(Color.GREEN);
            if(n==0) n=1;
            else n=0;
            try{
                Thread.sleep(delay);
            }
            catch(InterruptedException e){
                return ;
            }
        }
    }
}
public class FlickeringLabelEx extends JFrame {
    public FlickeringLabelEx(){
        setTitle("FlickeringLabelEx 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        FlickeringLabel flickeringLabel = new FlickeringLabel("깜박", 500);
        FlickeringLabel flickeringLabel1 = new FlickeringLabel("안깜박");
        FlickeringLabel flickeringLabel2 = new FlickeringLabel("여기도 깜박", 300);

        c.add(flickeringLabel);
        c.add(flickeringLabel1);
        c.add(flickeringLabel2);

        setSize(300, 150);
        setVisible(true);
    }
    public static void main(String[] args){
        new FlickeringLabelEx();
    }
}
