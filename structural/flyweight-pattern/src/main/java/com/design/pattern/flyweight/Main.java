package com.design.pattern.flyweight;

public class Main {
    public static void main(String[] args) {
        String text = "AABBA";

        int x = 0;

        for (char c : text.toCharArray()) {
            Letter letter = LetterFactory.getLetter(c);
            letter.display(x++, 10); // extrinsic state
        }
    }
}