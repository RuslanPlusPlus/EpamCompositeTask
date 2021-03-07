package by.me.composite.launcher;

import by.me.composite.action.TextAction;
import by.me.composite.action.impl.TextActionImpl;
import by.me.composite.entity.TextComponent;
import by.me.composite.entity.TextComposite;
import by.me.composite.exception.CompositeException;
import by.me.composite.parser.TextParser;
import by.me.composite.reader.impl.TextReader;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        /*StringBuilder builder = new StringBuilder();
        builder.append("awgawg");
        builder.append("\n\t");
        builder.append("0898");
        System.out.println(builder.toString());*/

        TextReader reader = new TextReader();
        TextParser parser = new TextParser();
        TextActionImpl action = new TextActionImpl();
        try {
            String text = reader.readText("testData/testText.txt");
            TextComponent composite = parser.parse(text);
            //System.out.println(action.findSentenceWithLongestWord((TextComposite) composite));
            //action.removeSentencesUnderLimit((TextComposite) composite, 6);
            //System.out.println(composite);
            Map<String, Integer> map = action.findAndCountEqualWords((TextComposite) composite);
            System.out.println(map.entrySet());
            //TextActionImpl compositeAction = new TextActionImpl();
            //compositeAction.sortParagraphsBySentencesAmount((TextComposite) composite);
            //System.out.println(composite);

        } catch (CompositeException e) {
            e.printStackTrace();
        }

    }

}
