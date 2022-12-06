package org.example;
import java.util.*;
public class HashMap_1{
    public static void main(String[] args){
        HashMap<String, String> H = new HashMap<String, String>();

        H.put("baby", "아기");
        H.put("love", "사랑");
        H.put("apple", "사과");

        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("단어 입력 바람");
            String eng = sc.next();
            if(eng.equals("exit")){
                System.out.println("종료");
                break;
            }
            String kor = H.get(eng);
            if(kor==null)
                System.out.println("없다");
            else
                System.out.println(kor);
        }
        sc.close();
    }
}
