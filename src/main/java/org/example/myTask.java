package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class myTask implements Runnable {
private String fileName;
private int minimum;
    @Override
    public void run() {
        try {
            Scanner s=new Scanner(new File(fileName));
            String target=s.next();
            HashMap<Character,Integer> targetMap=new HashMap<>();
            int len=target.length();
            for(int i=0;i<len;i++){
                if(targetMap.containsKey(target.charAt(i))){
                    targetMap.replace(target.charAt(i),targetMap.get(target.charAt(i))+1);
                }else{
                    targetMap.put(target.charAt(i),1);
                }
            }
            while(s.hasNext()){
                count(targetMap,target,s.next());
            }
        } catch (FileNotFoundException e) {
            System.out.println("input File nije pronadjen");;
        }
    }
    myTask(String fileName){
        this.fileName =fileName;
    }
    void count(HashMap<Character,Integer> targetMap,String target,String source){
        HashMap<Character,Integer> sourceMap=new HashMap<>();
        targetMap.forEach((key,val)->{sourceMap.put(key,0);});
        for (char ch:source.toCharArray()) {
            if(sourceMap.containsKey(ch)){
                sourceMap.replace(ch,sourceMap.get(ch)+1);
            }
        }
        minimum = source.length();
        //System.out.println(minimum);
        targetMap.forEach((key,val)->{
            if(minimum>(int)sourceMap.get(key)/val){
                minimum=(int)sourceMap.get(key)/val;
            }
        });
        System.out.println(String.format("%S in %S %d times",target,source,minimum));
        toFile(String.format("%S in %S %d times",target,source,minimum));
    }
    synchronized void toFile(String s){
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output.txt", true)));
            out.println(s);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
