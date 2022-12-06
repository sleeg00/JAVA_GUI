package org.example;
import javax.swing.*;
import java.awt.*;

public class GraphicsDrawStringEx extends JFrame{
    private MyPanel panel = new MyPanel();

    public GraphicsDrawStringEx(){
        setTitle("ㅁㅇㄹㅁㄴ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setSize(250, 250);
        setVisible(true);
    }
    class MyPanel extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawString("자바는 재밌다.~~", 30, 30);
            g.drawString("얼마나? 태공이만큼~", 60 ,60);
        }
    }
    public static void main(String[] args){
        new GraphicsDrawStringEx();
    }
}
