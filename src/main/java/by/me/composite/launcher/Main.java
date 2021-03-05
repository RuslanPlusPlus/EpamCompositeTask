package by.me.composite.launcher;

import by.me.composite.entity.TextComponent;
import by.me.composite.entity.TextComposite;
import by.me.composite.exception.CompositeException;
import by.me.composite.parser.TextParser;
import by.me.composite.reader.impl.TextReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*StringBuilder builder = new StringBuilder();
        builder.append("awgawg");
        builder.append("\n\t");
        builder.append("0898");
        System.out.println(builder.toString());*/

        String regex = "(?=(\\t))";
        TextReader reader = new TextReader();
        TextParser parser = new TextParser();
        try {
            String text = reader.readText("data/text.txt");
            /*String[] parts = text.split(regex);
            for (String part: parts){
                part = part.replace("\t", "");
                System.out.println(part);
            }*/
            TextComponent composite = parser.parse(text);
            System.out.println(composite);

        } catch (CompositeException e) {
            e.printStackTrace();
        }



    }

}
