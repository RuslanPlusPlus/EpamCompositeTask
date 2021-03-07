package by.me.composite.reader.impl;

import by.me.composite.exception.CompositeException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TextReaderTest {

    private TextReader reader;
    private String expectedText;

    @BeforeMethod
    public void setUp() {
        reader = new TextReader();
        StringBuilder builder = new StringBuilder();
        builder.append("Test text. Data, data, data!")
                .append("Bla, bla, bla...")
                .append("\t")
                .append("New paragraph!");
        expectedText = builder.toString();
    }

    @AfterMethod
    public void tearDown() {
        reader = null;
        expectedText = null;
    }

    @Test
    public void testReadText() throws CompositeException {
        String actualText = reader.readText("testData/testText.txt");
        assertEquals(actualText, expectedText);
    }
}