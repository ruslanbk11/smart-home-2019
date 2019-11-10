package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class HomeBuilder {

    public static void main(String[] args) throws IOException {
        Room kitchen = new Room(Arrays.asList(
                new Light( false, "1", "kitchen"),
                new Light(true, "2", "kitchen")),
                Arrays.asList(new Door(false, "1", "kitchen")),
                "kitchen");
        Room bathroom = new Room(Arrays.asList(new Light(true, "3", "bathroom")),
                Arrays.asList(new Door(false, "2", "bathroom")),
                "bathroom");
        Room bedroom = new Room(Arrays.asList(
                new Light(false, "4", "bedroom"),
                new Light(false, "5", "bedroom"),
                new Light(false, "6", "bedroom")),
                Arrays.asList(new Door(true, "3", "bedroom")),
                "bedroom");
        Room hall = new Room(Arrays.asList(
                new Light(false, "7", "hall"),
                new Light(false, "8", "hall"),
                new Light(false,"9", "hall")),
                Arrays.asList(new Door(false, "4", "hall")),
                "hall");
        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(smartHome);
        System.out.println(jsonString);
        Path path = Paths.get("output.js");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        }
    }

}
