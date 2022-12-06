package org.example;

import javax.swing.*;
import java.awt.*;

public class GraphicsImage extends JFrame {
    private MyPanel panel = new MyPanel();
    public GraphicsImage(){
        setTitle("ㅁㅇㄹㅁㄴ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setSize(250, 250);
        setVisible(true);
    }

    class MyPanel extends JPanel{
        private ImageIcon Icon = new ImageIcon("/Users/leeseonghyeon/Desktop/다운로드-removebg-preview.png");
        private Image img = Icon.getImage();
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 20, 20, this);

        }
    }
    public static void main(String[] args){
        new GraphicsImage();
    }
}
