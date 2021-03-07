package by.me.composite.action;

import by.me.composite.action.impl.TextActionImpl;
import by.me.composite.entity.TextComponent;
import by.me.composite.entity.TextComposite;
import by.me.composite.exception.CompositeException;
import by.me.composite.parser.TextParser;
import by.me.composite.reader.impl.TextReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class TextActionTest {

    private TextAction action;
    private String expectedSortedText;
    private Map<String, Integer> expectedWordsAndNumbers;
    List<String> expectedSentences;
    private String sourceText;
    private TextReader reader;
    TextParser parser;
    TextComponent parsedTextComponent;

    @BeforeMethod
    public void setUp() throws CompositeException {
        reader = new TextReader();
        parser = new TextParser();
        sourceText = reader.readText("testData/testText.txt");
        action = new TextActionImpl();
        parsedTextComponent = parser.parse(sourceText);

        expectedWordsAndNumbers = new HashMap<>();
        expectedWordsAndNumbers.put("data", 3);
        expectedWordsAndNumbers.put("bla", 3);

        expectedSentences = new ArrayList<>();
        expectedSentences.add("New paragraph! ");

    }

    @AfterMethod
    public void tearDown() {
        action = null;
        reader = null;
        parser = null;
        parsedTextComponent = null;
        expectedWordsAndNumbers = null;
        expectedSentences = null;
        sourceText = null;
    }

    @Test
    public void testSortParagraphsBySentencesAmount() throws CompositeException {
        StringBuilder builder = new StringBuilder();
        builder.append("\n").append("\t").append("New paragraph! ").append("\n")
                .append("\t").append("Test text. Data, data, data!Bla, bla, bla... ");
        expectedSortedText = builder.toString();
        action.sortParagraphsBySentencesAmount((TextComposite) parsedTextComponent);
        assertEquals(parsedTextComponent.toString(), expectedSortedText);
    }

    @Test
    public void testFindAndCountEqualWords() throws CompositeException {
        Map<String, Integer> actual = action.findAndCountEqualWords((TextComposite) parsedTextComponent);
        assertEquals(actual, expectedWordsAndNumbers);
    }

    @Test
    public void testFindSentenceWithLongestWord() throws CompositeException {
        List<TextComponent> sentencesList = action.findSentenceWithLongestWord((TextComposite) parsedTextComponent);
        List<String> actualList = new ArrayList<>();
        for(TextComponent sentence: sentencesList){
            actualList.add(sentence.toString());
        }
        assertEquals(actualList, expectedSentences);
    }

}