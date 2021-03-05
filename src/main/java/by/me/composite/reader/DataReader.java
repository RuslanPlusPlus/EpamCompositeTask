package by.me.composite.reader;

import by.me.composite.exception.CompositeException;

import java.util.List;

public interface DataReader {
    String readText(String fileName) throws CompositeException;
}
