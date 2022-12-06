package org.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HashMap2{
    public static void main(String[] args) {
        HashMap<String, Integer> H = new HashMap<String,Integer>();

        H.put("김선동", 97);
        H.put("황기태", 88);
        H.put("김남윤", 98);
        H.put("이재문", 79);
        H.put("김완선", 99);
        System.out.println("HaspMap 개수 : "+ H.size());

        Set<String> keys = H.keySet();
        Iterator<String> it = keys.iterator();

        while(it.hasNext()){
            String name = it.next();
            int score = H.get(name);
            System.out.println(name+" : " + score);
        }
    }
}
