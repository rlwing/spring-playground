package com.galvanize.springplayground.services;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class WordCounterTests {

    @Test
    public void testCountWords(){
        Map<String, Integer> tally = WordCounter.countWords("Hello my name is randy and my favorite color is red");
        assertEquals(tally.size(), 9);
        assertEquals(2, tally.get("is").intValue());
        assertEquals(2, tally.get("my").intValue());
    }
}
