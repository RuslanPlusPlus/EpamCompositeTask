package by.me.composite.entity;

public interface TextComponent {
    void add(TextComponent component);
    void remove(TextComponent component);
    int count();
    String toString();
    ComponentType getType();
}
