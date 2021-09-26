package com.epam.rd.java.basic.practice4;

import org.junit.Assert;
import org.junit.Test;

public class Part1Test {
    @Test
    public void testInputProcessing() {
        Assert.assertTrue(Part1.inputProcessing("qwerty").equals("erty"));
        Assert.assertFalse(Part1.inputProcessing("rty").equals("y"));
    }
}