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
    static final String PUNCTUATION = "\\p{Punct}";

    @Override
    public TextComponent parse(String text) throws CompositeException {
        if (text == null || text.isEmpty()){
            throw new CompositeException("Passed argument is null or empty");
        }
        TextComponent sentenceComponent = new TextComposite(ComponentType.LEXEME);
        SymbolComponent symbolComponent;
        char[] symbols = text.toCharArray();
        for (Character symbol: symbols){
            String symbolWrapper = symbol.toString();
            if (symbolWrapper.matches(PUNCTUATION)){
                symbolComponent = new SymbolComponent(symbol, ComponentType.PUNCTUATION_MARK);
            }else {
                symbolComponent = new SymbolComponent(symbol, ComponentType.LETTER);
            }
            sentenceComponent.add(symbolComponent);
        }
        return sentenceComponent;
    }
}
