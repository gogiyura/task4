package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static final Logger LOGGER = Logger.getLogger(Part1.class.getName());
    public static void main(String[] args) {
        String input;
        input = getInput("part1.txt");
        System.out.println("Входные данные:\n" + input);
        System.out.println("\nРезультаты обработки:\n" + inputProcessing(input));
    }

    private static String getInput(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), "cp1251"); //cp1251 UTF-8
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE,
                    "exception caught", ex);
            System.exit(2);
        }
        return sb.toString();
    }

    public static String inputProcessing(String input) {
        StringBuilder sb = new StringBuilder();
        String regex = "([\\p{L}]+)[^\\p{L}]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input + " ");

        while (matcher.find()) {
            String block = matcher.group();
            String word = block.replaceAll(regex, "$1");
            sb.append((word.length() >= 4 ? block.substring(2) : block));
        }

        return sb.substring(0, sb.length() - 1);
    }
}
