package by.me.composite.parser;

import by.me.composite.entity.ComponentType;
import by.me.composite.entity.SymbolComponent;
import by.me.composite.entity.TextComponent;
import by.me.composite.entity.TextComposite;
import by.me.composite.exception.CompositeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeParser implements TextComponentParser{

    static final Logger logger = LogManager.getLogger();
    //String WORD_WITH_PUNCTUATION  = ".+\\p{Punct}";
    static final String WORD_DELIMITER  = "(?=[,.!?)])";
    static final String PUNCTUATION = "\\p{Punct}";
    private TextComponentParser wordParser = new WordParser();

    @Override
    public TextComponent parse(String text) throws CompositeException {
        if (text == null || text.isEmpty()){
            throw new CompositeException("Passed argument is null or empty");
        }
        TextComponent lexemeComponent = new TextComposite(ComponentType.LEXEME);
        String[] words = text.split(WORD_DELIMITER);
        for (String word: words){
            if (word.matches(PUNCTUATION)){
                Character value = word.charAt(0);
                SymbolComponent symbolComponent = new SymbolComponent(value, ComponentType.PUNCTUATION_MARK);
                lexemeComponent.add(symbolComponent);
            }else {
                TextComponent wordComponent = wordParser.parse(word);
                lexemeComponent.add(wordComponent);
            }
        }
        return lexemeComponent;
    }
}
