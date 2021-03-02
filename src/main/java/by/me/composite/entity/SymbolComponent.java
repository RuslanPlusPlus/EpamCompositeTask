package by.me.composite.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SymbolComponent implements TextComponent{
    static final Logger logger = LogManager.getLogger();
    static final int SYMBOL_COUNTER = 1;
    private ComponentType type;
    private Character value;

    public SymbolComponent(Character value, ComponentType type){
        this.type = type;
        this.value = value;
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("Add operation is not supported by SymbolComponent class");
    }

    @Override
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException("Remove operation is not supported by SymbolComponent class");
    }

    @Override
    public int count() {
        return SYMBOL_COUNTER;
    }

    public ComponentType getType() {
        return type;
    }

    public Character getValue() {
        return value;
    }

    public void setValue(Character value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
