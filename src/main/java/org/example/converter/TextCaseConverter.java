package org.example.converter;

import org.springframework.stereotype.Component;

@Component("caseConverterBean")

public class TextCaseConverter {
    public String convertCase(String input) {
        StringBuilder transformedCase = new StringBuilder();
        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (Character.isLetter(currentChar)) {
                currentWord.append(currentChar);
            } else {
                if (currentWord.length() > 0) {
                    transformedCase.append(convertWord(currentWord.toString()));
                    currentWord.setLength(0);
                }
                transformedCase.append(currentChar);
            }
        }
        return transformedCase.toString();
    }

    public String convertWord(String word) {
        StringBuilder transformedWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            char transformedChar = i % 2 == 0 ? Character.toLowerCase(currentChar) : Character.toUpperCase(currentChar);
            transformedWord.append(transformedChar);
        }
        return transformedWord.toString();
    }

}
