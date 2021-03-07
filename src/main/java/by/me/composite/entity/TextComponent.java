package by.me.composite.entity;

import java.util.List;

public interface TextComponent {
    void add(TextComponent component);
    void remove(TextComponent component);
    int count();
    String toString();
    ComponentType getType();
    List<TextComponent> getComponents();
}
