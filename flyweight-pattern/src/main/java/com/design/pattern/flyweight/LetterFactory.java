package com.design.pattern.flyweight;

import java.util.HashMap;
import java.util.Map;

// Flyweight Factory
public class LetterFactory {
    private static final Map<Character, Letter> letterMap = new HashMap<>();

    public static Letter getLetter(char c) {
        if (!letterMap.containsKey(c)) {
            letterMap.put(c, new ConcreteLetter(c));
            System.out.println("\nCreating letter: " + c + "\n");
        }

        return letterMap.get(c);
    }
}
