package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CheckBox_1 extends JFrame {
    CheckBox_1(){
        setTitle("체크박스 연습");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c. setLayout(new FlowLayout());

        JCheckBox Button_hide, Button_not_activate;
        Button_not_activate = new JCheckBox("버튼 비활성화");
        Button_hide = new JCheckBox("버튼 활성화");

        JButton button = new JButton("test button");
        Button_not_activate.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED)
                    button.setEnabled(false);
                else button.setEnabled(true);
            }
        });
        Button_hide.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(e.getStateChange()==ItemEvent.SELECTED)
                    button.setVisible(false);
                else
                    button.setVisible(true);
            }
        });

        c.add(Button_not_activate);
        c.add(Button_hide);
        c.add(button);
        setSize(300, 300);
        setVisible(true);
    }
    public static void main(String[] args){
        new CheckBox_1();
    }
}

