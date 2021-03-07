package by.me.composite.action.impl;

import by.me.composite.action.TextAction;
import by.me.composite.comparator.ComponentSizeComparator;
import by.me.composite.entity.ComponentType;
import by.me.composite.entity.TextComponent;
import by.me.composite.entity.TextComposite;
import by.me.composite.exception.CompositeException;

import java.util.*;
import java.util.stream.Collectors;

public class TextActionImpl implements TextAction {

    private static final int MIN_WORD_COUNTER = 1;

    @Override
    public void sortParagraphsBySentencesAmount(TextComposite text) throws CompositeException {
        if (text == null){
            throw new CompositeException("Passed TextComposite object is null");
        }
        if (text.getType() != ComponentType.TEXT){
            throw new CompositeException("Illegal type of TextComposite passed! Text type required");
        }
        List<TextComponent> components = text.getComponents();
        List<TextComponent> sortedList = new ArrayList<>(components);
        ComponentSizeComparator comparator = new ComponentSizeComparator();
        sortedList.sort(comparator);
        text.setComponents(sortedList);
    }

    @Override
    public Map<String, Integer> findAndCountEqualWords(TextComposite text) throws CompositeException {
        if (text == null){
            throw new CompositeException("Passed TextComposite object is null");
        }
        if (text.getType() != ComponentType.TEXT){
            throw new CompositeException("Illegal type of TextComposite passed! Text type required");
        }
        Map<String, Integer> map = new HashMap<>();
        List<TextComponent> words = findAllWords(text);
        for (TextComponent word: words){
            String wordKey = word.toString().toLowerCase();
            Integer previousValue = map.put(wordKey, MIN_WORD_COUNTER);
            if (previousValue != null){
                map.put(wordKey, previousValue + MIN_WORD_COUNTER);
            }
        }
        Set<Map.Entry<String, Integer>> resultSet = map.entrySet().stream()
                                                    .filter(o -> o.getValue() > 1).collect(Collectors.toSet());
        Map<String, Integer> resultMap = resultSet.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return resultMap;
    }

    @Override
    public List<TextComponent> findSentenceWithLongestWord(TextComposite text) throws CompositeException {
        List<TextComponent> words = findAllWords(text);
        TextComponent maxWord = words.get(0);
        List<TextComponent> sentencesList = new ArrayList<>();
        for (TextComponent word: words){
            if (word.count() > maxWord.count()){
                maxWord = word;
            }
        }
        for (TextComponent paragraphs: text.getComponents()){
            for (TextComponent sentences: paragraphs.getComponents()){
                for(TextComponent lexemes: sentences.getComponents()){
                    for (TextComponent lexemeElement: lexemes.getComponents()){
                        if (lexemeElement.getType() == ComponentType.WORD && lexemeElement.equals(maxWord)){
                            sentencesList.add(sentences);
                        }
                    }
                }
            }
        }
        return sentencesList;
    }

    @Override
    public void removeSentencesUnderLimit(TextComposite text, int minLimit) throws CompositeException {
        if (text == null){
            throw new CompositeException("Passed TextComposite object is null");
        }
        if (text.getType() != ComponentType.TEXT){
            throw new CompositeException("Illegal type of TextComposite passed! Text type required");
        }
        List<TextComponent> updatedList = new ArrayList<>();
        for (TextComponent paragraph: text.getComponents()){
            for (TextComponent sentence: paragraph.getComponents()) {
                if (countWordsInSentence(sentence) >= minLimit){
                    updatedList.add(sentence);
                }
            }
            ((TextComposite)paragraph).setComponents(updatedList);
            updatedList = new ArrayList<>();
        }
    }

    private int countWordsInSentence(TextComponent text) throws CompositeException{
        if (text == null){
            throw new CompositeException("Passed TextComposite object is null");
        }
        if (text.getType() != ComponentType.SENTENCE){
            throw new CompositeException("Illegal type of TextComposite passed! SENTENCE type required");
        }
        int counter = 0;
        for (TextComponent lexeme: text.getComponents()){
            for (TextComponent lexemeElement: lexeme.getComponents()){
                if (lexemeElement.getType() == ComponentType.WORD){
                    counter++;
                }
            }
        }
        return counter;
    }

    private List<TextComponent> findAllWords(TextComponent text) throws CompositeException {
        if (text == null){
            throw new CompositeException("Passed TextComposite object is null");
        }
        if (text.getType() != ComponentType.TEXT){
            throw new CompositeException("Illegal type of TextComposite passed! Text type required");
        }
        List<TextComponent> words = new ArrayList<>();
        for (TextComponent paragraphs: text.getComponents()){
            for (TextComponent sentences: paragraphs.getComponents()){
                for(TextComponent lexemes: sentences.getComponents()){
                    for (TextComponent lexemeElement: lexemes.getComponents()){
                        if (lexemeElement.getType() == ComponentType.WORD){
                            words.add(lexemeElement);
                        }
                    }
                }
            }
        }
        return words;
    }
}
