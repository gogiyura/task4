package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    private static final Logger LOGGER = Logger.getLogger(Part3.class.getName());
    private static final String TYPES = "char int double String";

    public static void main(String[] args) {
        String fileName3 = "part3.txt";
        String input3 = getInput3(fileName3);
        run(input3);
    }

    private static void run(String input) {
        Scanner scanner = new Scanner(System.in);
        String dataType = scanner.next();
        String message;
        while (!dataType.equals("stop")) {
            if(TYPES.contains(dataType)) {
                message = getMatches3(input, getPattern(dataType));
            } else {
                message = "Incorrect input";
            }
            System.out.println(message);
            dataType = scanner.next();
        }
    }

    private static Pattern getPattern(String dataType) {
        if(dataType.equals("char")) {
            return Pattern.compile("\\b[^\\s.]{1}\\b");
        } else if (dataType.equals("int")) {
            return Pattern.compile("(?<![-.])\\b[0-9]+\\b(?!\\.[0-9])");
        }
        else if (dataType.equals("double")) {
            return Pattern.compile("((\\b|\\.)([0-9]*)?)?(\\.[0-9]+)\\b");
        }
        else {
            return Pattern.compile("\\b\\p{L}{2,}\\b");
        }
    }

    private static String getMatches3(String input, Pattern pattern) {
        Matcher matcher = pattern.matcher(input);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            sb.append(matcher.group() + " ");
        }
        return sb.toString();
    }

    private static String getInput3(String fileName) {
        StringBuilder sb3 = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), "cp1251"); //cp1251 UTF-8
            while (scanner.hasNextLine()) {
                sb3.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return sb3.toString().trim();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE,
                    "exception caught", ex);
            System.exit(2);
        }
        return sb3.toString();
    }
}


