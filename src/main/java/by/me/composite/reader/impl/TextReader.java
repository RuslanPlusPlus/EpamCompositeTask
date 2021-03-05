package by.me.composite.reader.impl;
import by.me.composite.exception.CompositeException;
import by.me.composite.reader.DataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class TextReader implements DataReader {
    static final Logger logger = LogManager.getLogger();

    @Override
    public String readText(String fileName) throws CompositeException {
        if (fileName == null || fileName.isEmpty()){
            throw new CompositeException("Passed filename is null or empty");
        }
        String text;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URI resource = classLoader.getResource(fileName).toURI();
            Path path = Paths.get(resource);
            text = Files.lines(path, Charset.defaultCharset()).collect(Collectors.joining());
        } catch (IOException | URISyntaxException e) {
            logger.error("Failed to read file: " + fileName);
            throw new CompositeException("Failed to read file: " + fileName, e);
        }
        logger.info("File " + fileName + " is successfully read");
        return text;
    }
}
