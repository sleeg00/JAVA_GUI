package org.example;
import javax.swing.*;
import java.awt.*;

public class GraphicsEx extends JFrame{
    private MyPanel panel = new MyPanel();
    public GraphicsEx(){
        setTitle("ㅁㅇㄹㅁㄴ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setSize(350, 470);
        setVisible(true);
    }
    class MyPanel extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(Color.BLUE);
            g.drawString("I SIBAL", 30, 30);
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.ITALIC, 30));
            g.drawString("HHH", 30, 60);
            for(int i=1; i<=5; i++){
                g.setColor(Color.GREEN);
                g.setFont(new Font("Arial", Font.ITALIC, i*10));
                g.drawString("W", 30, 60+i*60);
            }
        }
    }
    public static void main(String[] args){
        new GraphicsEx();
    }
}
