package com.epam.rd.java.basic.practice4;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part5 {
    private static final Logger LOGGER = Logger.getLogger(Part5.class.getName());
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String key;
        String loc;

        while (!line.equals("stop")) {
            loc = line.split(" ")[0];
            key = line.split(" ")[1];
            System.out.println(getKey(key, loc));
            line = scanner.nextLine();
        }
    }

    private static String getKey(String key, String loc) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(String.format("./src/main/resources_%s.properties", loc)));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,
                    "exception caught:\t", e.getMessage());
            System.exit(2);
        }
        return properties.getProperty(key);
    }
}
