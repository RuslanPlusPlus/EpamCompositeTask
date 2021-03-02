package by.me.composite.entity.reader;

import by.me.composite.exception.CompositeException;

import java.util.List;

public interface DataReader {
    List<String> readAllLines(String fileName) throws CompositeException;
}
