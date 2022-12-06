package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

    Main(){
        setTitle("마우스 올리기 내리기 예제");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Love Java");

        label.addMouseListener(new MyMouseAdapter());
        //MyMouseAdapter을 label에 부착!
        label.setSize(200, 50);
        add(label);
        setSize(400, 200);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Main();
    }
}
//MouseAdapter을 상속받아 추상 메소드 구현 X
class MyMouseAdapter extends MouseAdapter{
    public void mouseEntered(MouseEvent e){
        JLabel l =(JLabel)e.getSource();
        l.setText("사랑해");
        //마우스가 해당 컴포넌트 안으로 들어올 때
    }
    public void mouseExited(MouseEvent e){
        JLabel l = (JLabel)e.getSource();
        //마우스가 해당 컴포넌트 밖으로 나갈
        l.setText("Love Java");
    }
}