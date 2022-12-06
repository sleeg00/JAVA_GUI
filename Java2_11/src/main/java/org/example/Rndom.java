package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rndom extends JFrame {
    JLabel num[] = new JLabel[10];
    int nowindex;
    Rndom(){
        setTitle("제발");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(null);

        for(int i=0; i<10; i++){
            num[i]=new JLabel(Integer.toString(i));
            num[i].setSize(10, 10);
            num[i].addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e){
                    JLabel tmp =(JLabel)e.getSource();
                    int num=Integer.parseInt(tmp.getText());
                    if(num==nowindex){
                        tmp.setVisible(false);
                        nowindex++;
                    }
                    if(nowindex==10)
                        setting();
                }
            });
            c.add(num[i]);
        }
        setting();
    }
    public void setting(){
        nowindex=0;
        for(int i=0; i<10; i++){
            int x=(int)(Math.random()*100);
            int y=(int)(Math.random()*100);

            num[i].setLocation(x,y);
            num[i].setVisible(true);
        }
    }
    public static void main(String[] args){
        new Rndom();
    }
}
