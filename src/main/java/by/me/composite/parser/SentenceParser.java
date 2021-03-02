package by.me.composite.parser;

import by.me.composite.entity.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParser implements TextComponentParser{

    static final Logger logger = LogManager.getLogger();
    private TextComponentParser wordParser = new WordParser();

    @Override
    public TextComposite parse(String text) {
        //add realization
    }
}
