package org.example;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeySlider extends JFrame {
    KeySlider(){
        setTitle("블라블라");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new GridLayout(3,1));

        JTextArea textArea = new JTextArea();
        JSlider slider = new JSlider();

        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);

        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider tmp = (JSlider)e.getSource();
                String text=textArea.getText();
                if(text.length()<=slider.getValue())
                    slider.setValue(text.length());
                else
                    textArea.setText(text.substring(0, tmp.getValue()));
            }
        });
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                JTextArea j = (JTextArea)e.getSource();
                String text = j.getText();
                if(text.length()<=100)
                    slider.setValue(text.length());
                else {
                    text = text.substring(0, 99);
                    textArea.setText(text);
                }
            }
        });
        c.add(textArea);
        c.add(slider);
        setSize(300, 300);
        setVisible(true);
    }
    public static void main(String[] args){
        new KeySlider();
    }
}
