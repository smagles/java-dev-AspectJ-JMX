package converter;

import org.example.converter.TextCaseConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TextCaseConverterTest {
    @Test
    void testConvertWord() {
        //Given

        TextCaseConverter textCaseConverter = new TextCaseConverter();

        //When
        final String word = "word";

        final String result = textCaseConverter.convertWord(word);

        //Then

        final String expected = "wOrD";
        assertEquals(expected, result);

    }

    @Test
    void testConvertCase() {
        //Given

        TextCaseConverter textCaseConverter = new TextCaseConverter();

        //When

        final String text = "Hello World!";

        final String result = textCaseConverter.convertCase(text);

        //Then

        final String expected = "hElLo wOrLd!";
        assertEquals(expected, result);

    }

    @Test
    void testConvertEmptyString() {
        //Given

        TextCaseConverter textCaseConverter = new TextCaseConverter();


        //When & Then
        final String text = "";
        assertThrows(IllegalArgumentException.class, () -> textCaseConverter.convertCase(text));


    }


    @Test
    void testConvertSingleLetter() {
        //Given

        TextCaseConverter textCaseConverter = new TextCaseConverter();

        //When & Then
        final String text = "A";
        assertThrows(IllegalArgumentException.class, () -> textCaseConverter.convertCase(text));

    }

    @Test
    void testConvertDigitsAndSymbols() {
        //Given

        TextCaseConverter textCaseConverter = new TextCaseConverter();

        //When
        final String input = "123!@#";
        final String result = textCaseConverter.convertCase(input);

        //Then
        String expected = "123!@#";
        assertEquals(expected, result);
    }
}
