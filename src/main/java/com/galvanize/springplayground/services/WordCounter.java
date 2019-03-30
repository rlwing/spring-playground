package com.galvanize.springplayground.services;

import java.util.HashMap;
import java.util.Map;

public class WordCounter {

    public static Map<String, Integer> countWords(String phrase){
        String[] split = phrase.split(" ");
        Map<String, Integer> tally = new HashMap<>();
        int tmpInt = 0;
        for(String word : split){
            tmpInt = tally.get(word)==null ? 0 : tally.get(word);
            tally.put(word, tmpInt+1);
        }

        return tally;
    }
}
