package org.example;

import javax.swing.*;
import java.awt.*;

public class GraphicsClip extends JFrame {
    private MyPanel panel = new MyPanel();
    public GraphicsClip(){
        setTitle("ㅁㅇㄹㅁㄴ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setSize(250, 250);
        setVisible(true);
    }

    class MyPanel extends JPanel{
        private ImageIcon icon = new ImageIcon("/Users/leeseonghyeon/Desktop/다운로드-removebg-preview.png");
        private Image img = icon.getImage();

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setClip(100, 20, 150, 150);
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            g.setFont(new Font("Arial", Font.ITALIC, 40));
            g.drawString("LA", 10, 150);
        }
    }
    public static void main(String[] args){
        new GraphicsClip();
    }
}
