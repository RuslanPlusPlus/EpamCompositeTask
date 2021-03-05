package by.me.composite.parser;

import by.me.composite.entity.ComponentType;
import by.me.composite.entity.TextComponent;
import by.me.composite.entity.TextComposite;
import by.me.composite.exception.CompositeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParser implements TextComponentParser{

    static final Logger logger = LogManager.getLogger();
    private TextComponentParser lexemeParser = new LexemeParser();
    static final String LEXEME_DELIMITER = "\\s";

    @Override
    public TextComponent parse(String text) throws CompositeException {
        if (text == null || text.isEmpty()){
            throw new CompositeException("Passed argument is null or empty");
        }
        TextComponent sentenceComponent = new TextComposite(ComponentType.SENTENCE);
        String[] lexemes = text.split(LEXEME_DELIMITER);
        for (String lexeme: lexemes){
            TextComponent lexemeComponent = lexemeParser.parse(lexeme);
            sentenceComponent.add(lexemeComponent);
        }
        return sentenceComponent;
    }
}
