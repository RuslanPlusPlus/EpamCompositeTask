package by.me.composite.parser;

import by.me.composite.entity.TextComponent;
import by.me.composite.exception.CompositeException;

public interface TextComponentParser {
    TextComponent parse(String text) throws CompositeException;
}
