package by.me.composite.parser;

import by.me.composite.entity.TextComposite;
import by.me.composite.exception.CompositeException;

public interface TextComponentParser {
    TextComposite parse(String text) throws CompositeException;
}
