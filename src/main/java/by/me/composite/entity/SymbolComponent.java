package by.me.composite.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

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

    @Override
    public List<TextComponent> getComponents() {
        throw new UnsupportedOperationException("GetComponents operation is no supported by SymbolComponent class");
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

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        SymbolComponent that = (SymbolComponent) o;
        return type == that.type &&
                value.equals(that.value);
    }

    @Override
    public int hashCode() {
        final int hash = 31;
        int result = 1;
        result = hash * result + type.hashCode();
        result = hash * result + value.hashCode();
        return result;
    }
}
