package com.epam.rd.java.basic.practice4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part2 {
    private static final Logger LOGGER = Logger.getLogger(Part2.class.getName());

    public static void main(String[] args) {
        String fileNameIn = "part2.txt";
        String fileNameOut = "part2_sorted.txt";
        int n = 10;
        String sequence = generateSequence(n);
        writeToFile(fileNameIn, sequence);
        writeToFile(fileNameOut, readNSort(fileNameIn));
        System.out.println(String.format(
                "input ==> %s%noutput ==> %s",
                getInput2(fileNameIn), getInput2(fileNameOut)));
    }

    private static void writeToFile(String fileName, String data) {
        try ( BufferedWriter writer = Files.newBufferedWriter (
                Paths.get(fileName), StandardCharsets.UTF_8)) {
            writer.write (data);
            writer.newLine ();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE,"exception caught", ex);
            System.exit(2);
        }
    }

    private static String readNSort(String fileName) {
        int[] data = getInts(getInput2(fileName));
        insertionSort(data);
        return intArrToString(data);
    }

    private static String generateSequence(int n) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++) {
            sb.append(random.nextInt(50)+ " ");
        }
        return sb.substring(0, sb.length() - 1);
    }

    private static String intArrToString(int[] data){
        StringBuilder sb = new StringBuilder();
        for (int i : data) {
            sb.append(i + " ");
        }
        return sb.substring(0, sb.length() - 1);
    }

    private static int[] getInts(String data) {
        String[] strings = data.split(" ");
        int[] ints = new int[strings.length];
        for(int i=0;i<strings.length;i++) {
            ints[i] = Integer.parseInt(strings[i]);
        }
        return ints;
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while(j >= 0 && current < array[j]) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = current;
        }
    }

    private static String getInput2(String fileName) {
        StringBuilder sb2 = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), "cp1251"); //cp1251 UTF-8
            while (scanner.hasNextLine()) {
                sb2.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return sb2.toString().trim();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE,
                    "exception caught", ex);
            System.exit(2);
        }
        return sb2.toString();
    }
}