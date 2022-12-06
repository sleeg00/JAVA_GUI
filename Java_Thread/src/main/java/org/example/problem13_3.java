package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class problem13_3 extends JFrame {

    private JLabel label = new JLabel("");

    class MyThread extends Thread{
        private JLabel label=null;
        public MyThread(JLabel label){
            this.label=label;
        }
        public String getNowTime(){
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int min = c.get(Calendar.MINUTE);
            int second = c.get(Calendar.SECOND);

            String ClockText = Integer.toString(hour);
            ClockText = ClockText.concat(":");
            ClockText = ClockText.concat(Integer.toString(min));
            ClockText = ClockText.concat(":");
            ClockText = ClockText.concat(Integer.toString(second));

            return ClockText;
        }
        public void run(){
            while(true){
                label.setText(getNowTime());
                try{
                    sleep(10000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public problem13_3(){
        setTitle("!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        label.setFont(new Font("Gothic", Font.ITALIC, 80));
        c.add(label);

        MyThread th = new MyThread(label);
        th.start();

        setSize(300, 300);
        setVisible(true);
    }
    public static void main(String[] args){
        new problem13_3();
    }
}
