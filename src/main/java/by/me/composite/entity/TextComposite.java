package by.me.composite.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

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
        logger.info(type + " component added");
    }

    @Override
    public void remove(TextComponent component) {
        components.remove(component);
        logger.info(type + " component removed");
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

    public String toString(){
        // fix (add position check)
        StringBuilder builder = new StringBuilder();
        for (TextComponent component: components){
            builder.append(component.toString());
            if (component.getType() == ComponentType.PARAGRAPH){
                builder.append(NEW_LINE);
                builder.append(TABULATION);
            }
            if (component.getType() == ComponentType.SENTENCE){
                builder.append(SPACE);
            }
        }
        return builder.toString();
    }
}
