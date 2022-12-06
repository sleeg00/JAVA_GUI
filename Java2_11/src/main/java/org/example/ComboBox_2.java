package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBox_2 extends JFrame {
    JComboBox<String> comboBox = new JComboBox<String>();
    ComboBox_2(){
        setTitle("JTextField and JCom..........");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        JTextField textField = new JTextField(10);


        textField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JTextField textField = (JTextField)e.getSource();
                String str = textField.getText();
                textField.setText("");

                comboBox.addItem(str);
            }
        });
        c.add(textField);
        c.add(comboBox);

        setSize(300, 300);
        setVisible(true);;
    }
    public static void main(String[] args){
        new ComboBox_2();
    }
}
