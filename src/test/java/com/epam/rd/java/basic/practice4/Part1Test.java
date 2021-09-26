package com.epam.rd.java.basic.practice4;

import org.junit.Assert;
import org.junit.Test;

public class Part1Test {
    @Test
    public void testInputProcessing() {
        String res = Part1.inputProcessing("qwerty");
        Assert.assertTrue(res.equals("erty"));

    }
}