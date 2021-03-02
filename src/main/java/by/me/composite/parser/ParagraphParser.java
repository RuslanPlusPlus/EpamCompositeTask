package by.me.composite.parser;

import by.me.composite.entity.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphParser implements TextComponentParser{

    static final Logger logger = LogManager.getLogger();
    private TextComponentParser sentenceParser = new SentenceParser();

    @Override
    public TextComposite parse(String text) {
        //add realization
    }
}
