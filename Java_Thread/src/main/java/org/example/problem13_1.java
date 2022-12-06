package org.example;

import javax.swing.*;
import java.util.Scanner;

public class problem13_1 extends JFrame {
    public problem13_1() {
        Scanner sc = new Scanner(System.in);
        System.out.println("아무거나 입력");
        sc.nextLine();
        sc.close();

        Thread th = new Thread(new PrintThread());
        th.start();
    }
    class PrintThread implements Runnable{
        public void run(){
            for(int i=0; i<10; i++){
                System.out.println(i+1 + " ");
            }
            System.out.println("\n스레드 종료");
        }
    }
    public static void main(String[] args){
        new problem13_1();
    }
}
