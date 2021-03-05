package by.me.composite.parser;

import by.me.composite.entity.ComponentType;
import by.me.composite.entity.TextComponent;
import by.me.composite.entity.TextComposite;
import by.me.composite.exception.CompositeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextParser implements TextComponentParser{

    static final Logger logger = LogManager.getLogger();
    static final String PARAGRAPH_DELIMITER = "(?=(\\t))";
    private TextComponentParser paragraphParser = new ParagraphParser();

    @Override
    public TextComponent parse(String text) throws CompositeException {
        if (text == null || text.isEmpty()){
            throw new CompositeException("Passed argument is null or empty");
        }
        TextComponent textComponent = new TextComposite(ComponentType.TEXT);
        String[] paragraphs = text.split(PARAGRAPH_DELIMITER);
        for (String paragraph: paragraphs){
            paragraph = paragraph.replace("\t", "");
            TextComponent paragraphComponent = paragraphParser.parse(paragraph);
            textComponent.add(paragraphComponent);
        }
        return textComponent;
    }
}
