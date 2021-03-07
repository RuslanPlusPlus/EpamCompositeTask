package by.me.composite.comparator;

import by.me.composite.entity.TextComponent;

import java.util.Comparator;

public class ComponentSizeComparator implements Comparator<TextComponent> {
    @Override
    public int compare(TextComponent component1, TextComponent component2) {
        int size1 = component1.getComponents().size();
        int size2 = component2.getComponents().size();
        return size1-size2;
    }
}
