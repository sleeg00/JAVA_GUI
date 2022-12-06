package org.example;

import javax.swing.*;
import java.awt.*;

class TimerRunner implements Runnable{
    private JLabel timerLabel1;

    public TimerRunner(JLabel timerLabel1){
        this.timerLabel1=timerLabel1;
    }
    public void run(){
        int n=0;
        while(true){
            timerLabel1.setText(Integer.toString(n));
            n++;
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException e){
                return ;
            }
        }
    }
}
public class RunnableTimer extends JFrame {
    public RunnableTimer(){
        setTitle("Runnable");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        JLabel timerLabel = new JLabel();
        timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));
        c.add(timerLabel);

        TimerRunner runner = new TimerRunner(timerLabel);
        Thread th = new Thread(runner);

        setSize(250, 150);
        setVisible(true);

        th.start();
    }
    public static void main(String[] args){
        new ThreadTimer();
    }
}
