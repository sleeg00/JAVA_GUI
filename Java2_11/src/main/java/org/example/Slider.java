package org.example;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Slider extends JFrame {
    Slider(){
        setTitle("슬라이더");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        JSlider slider = new JSlider(100, 200, 120);
        JLabel label = new JLabel("120");

        slider.setMajorTickSpacing(20);//큰 눈금 크기
        slider.setPaintTicks(true);//눈금보이게
        slider.setPaintLabels(true);//슬라이더 라벨 보이게
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider tmp =(JSlider)e.getSource();
                label.setText(Integer.toString(tmp.getValue()));
            }
        });
        c.add(slider);
        label.setBackground(Color.GREEN);
        label.setOpaque(true);
        c.add(label);

        setSize(300, 300);
        setVisible(true);
    }
    public static void main(String[] args){
        new Slider();
    }
}
