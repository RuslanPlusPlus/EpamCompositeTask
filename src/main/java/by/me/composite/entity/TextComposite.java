package by.me.composite.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TextComposite implements TextComponent{

    static final Logger logger = LogManager.getLogger();
    static final String SPACE = " ";
    static final String TABULATION = "\t";
    static final String NEW_LINE = "\n";
    private ComponentType type;
    private List<TextComponent> components = new ArrayList<>();

    public TextComposite(ComponentType type){
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        components.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        components.remove(component);
    }

    @Override
    public int count() {
        int counter = 0;
        for (TextComponent component: components){
            counter += component.count();
        }
        return counter;
    }

    public ComponentType getType() {
        return type;
    }

    @Override
    public List<TextComponent> getComponents() {
        return Collections.unmodifiableList(components);
    }

    public void setComponents(List<TextComponent> components){
        this.components = components;
    }

    public String toString(){
        // fix (add position check), check LexemeParser
        StringBuilder builder = new StringBuilder();
        for (TextComponent component: components){
            if (component.getType() == ComponentType.PARAGRAPH){
                builder.append(NEW_LINE);
                builder.append(TABULATION);
            }
            builder.append(component.toString());
            if (component.getType() == ComponentType.LEXEME){
                builder.append(SPACE);
            }
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        TextComposite that = (TextComposite) o;
        if (components == null || that.components == null){
            return false;
        }
        return type == that.type &&
                components.equals(that.components);

    }

    @Override
    public int hashCode() {
        final int hash = 31;
        int result = 1;
        result = hash * result + components.hashCode();
        result = hash * result + type.hashCode();
        return result;
    }
}
