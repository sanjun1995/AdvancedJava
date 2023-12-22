package com.example.demo.lambda;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CamelCaseConverter implements Iterable<String> {
    private final String[] words;

    public CamelCaseConverter(String input) {
        this.words = input.split("_");
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < words.length;
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return capitalize(words[index++]);
            }

            private String capitalize(String s) {
                return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
            }
        };
    }

    public static void main(String[] args) {
        String input = "underscore_to_camelcase";
        CamelCaseConverter converter = new CamelCaseConverter(input);

        StringBuilder result = new StringBuilder();
        for (String word : converter) {
            result.append(word);
        }
        System.out.println(result);
    }
}
