package com.epam.rd.java.basic.practice4;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part1 {
    private static final Logger LOGGER = Logger.getLogger(Part1.class.getName());
    public static void main(String[] args) {
        String input;
        input = getInput("part1.txt");
        System.out.println(input);
    }

    private static String getInput(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), "cp1251"); //cp1251 UTF-8
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return sb.toString().trim();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE,
                    "exception caught", ex);
            System.exit(2);
        }
        return sb.toString();
    }
}
