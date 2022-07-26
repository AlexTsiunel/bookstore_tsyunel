package com.company.app;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello, world!!!");
        for(String str : args) {
            System.out.println(str);
        }
        System.out.println(System.getenv("TOMCAT_HOME"));
        System.out.println(System.getenv("MY_TEST"));
        
    }

}
