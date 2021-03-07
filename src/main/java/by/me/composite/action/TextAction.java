package by.me.composite.action;

import by.me.composite.entity.TextComponent;
import by.me.composite.entity.TextComposite;
import by.me.composite.exception.CompositeException;

import java.util.List;
import java.util.Map;

public interface TextAction {
    void sortParagraphsBySentencesAmount(TextComposite text) throws CompositeException;
    Map<String, Integer> findAndCountEqualWords(TextComposite text) throws CompositeException;
    List<TextComponent> findSentenceWithLongestWord(TextComposite text) throws CompositeException;
    void removeSentencesUnderLimit(TextComposite text, int minLimit) throws CompositeException;
}
