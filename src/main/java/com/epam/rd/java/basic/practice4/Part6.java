package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
    private static final Logger LOGGER = Logger.getLogger(Part6.class.getName());
    public static void main(String[] args) {
        String fileName6 = "part6.txt";
        run(getInput6(fileName6));
    }

    private static void run(String input) {
        Scanner scanner = new Scanner(System.in);
        String code = scanner.next();
        while (!code.equals("stop")) {
            if(!code.equalsIgnoreCase("cyrl") && !code.equalsIgnoreCase("latn")) {
                System.out.println(String.format("%s: Incorrect input", code));
            } else if(code.equalsIgnoreCase("cyrl")){
                System.out.println(String.format("Cyrl: %s", getMatchesCyrl(input)));
            } else {
                System.out.println(String.format("Latn: %s", getMatchesLatn(input)));
            }
            code = scanner.next();
        }
    }

    private static String getMatchesLatn(String input) {
        Pattern pattern = Pattern.compile("\\b[A-Za-z]+\\b");
        Matcher matcher = pattern.matcher(input);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            sb.append(matcher.group() + " ");
        }
        return sb.toString();
    }

    private static String getMatchesCyrl(String input) {
        Pattern pattern = Pattern.compile("\\b[^A-Za-z.,\\-\\s]+?\\b");
        Matcher matcher = pattern.matcher(input);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            sb.append(matcher.group() + " ");
        }
        return sb.toString();
    }

    private static String getInput6(String fileName) {
        StringBuilder sb6 = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), "UTF-8"); //cp1251 UTF-8
            while (scanner.hasNextLine()) {
                sb6.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return sb6.toString().trim();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE,
                    "exception caught", ex);
            System.exit(2);
        }
        return sb6.toString();
    }
}
