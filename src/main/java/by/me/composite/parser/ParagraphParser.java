package by.me.composite.parser;

import by.me.composite.entity.ComponentType;
import by.me.composite.entity.TextComponent;
import by.me.composite.entity.TextComposite;
import by.me.composite.exception.CompositeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphParser implements TextComponentParser{

    static final Logger logger = LogManager.getLogger();
    private TextComponentParser sentenceParser = new SentenceParser();
    static final String SENTENCE_DELIMITER = "(?<=[!?.{1,3}])\\s";

    @Override
    public TextComponent parse(String text) throws CompositeException {
        if (text == null || text.isEmpty()){
            throw new CompositeException("Passed argument is null or empty");
        }
        TextComponent paragraphComponent = new TextComposite(ComponentType.PARAGRAPH);
        String[] sentences = text.split(SENTENCE_DELIMITER);
        for (String sentence: sentences){
            TextComponent sentenceComponent = sentenceParser.parse(sentence);
            paragraphComponent.add(sentenceComponent);
        }
        return paragraphComponent;
    }
}
