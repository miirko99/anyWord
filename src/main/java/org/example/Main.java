package org.example;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) {
        File folder = new File("C:\\Users\\mirkop\\IdeaProjects\\anyWord");
        File[] listOfFiles = folder.listFiles();
        ExecutorService service= Executors.newCachedThreadPool();
        for(int i=0;i<listOfFiles.length;i++){
            if(!listOfFiles[i].isFile()){continue;}
            if(!listOfFiles[i].getName().endsWith(".txt")){continue;}
            if(listOfFiles[i].getName().endsWith("output.txt")){
                System.out.println("asdf");
                continue;
            }
            service.execute(new myTask(listOfFiles[i].getName()));
            System.out.println(listOfFiles[i].getName());
        }
        service.shutdown();
    }
}