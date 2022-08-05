package com.exercise;

import com.exercise.service.RoverService;

public class Application {
    private static final RoverService service = new RoverService();

    public static void main(String[] args) {
        System.out.println("Hello from Mars! Enter your commands ...");
        service.execute();
    }
}
