package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements Iterable{
    private static final Logger LOGGER = Logger.getLogger(Part4.class.getName());
    private String input;
    private int chechik = 0;
    public Part4() {
        this.input = getInput("part4.txt");
    }
    public static void main(String[] args) {
        Part4 part4 = new Part4();
        Iterator iterator = part4.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Override
    public Iterator iterator() {
        return new IteratorImpl();
    }
    private class IteratorImpl implements Iterator <String>{
        private Matcher matcher;
        private boolean hasNext;
        public IteratorImpl() {
            this.matcher = Pattern.compile("\\p{Lu}[^.]*\\.").matcher(input); //"(\\p{Lu}[^.]+?)\\."
            this.hasNext = matcher.find();
        }
        @Override
        public boolean hasNext() {
            return hasNext;
        }
        @Override
        public String next() {
            if (hasNext){
                String string = matcher.group();
                hasNext = matcher.find();
                System.out.println(chechik++);
                return string;
            } else {
                throw new NoSuchElementException();
            }
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static String getInput(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), "UTF-8"); //cp1251 UTF-8
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine() + " ");
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
