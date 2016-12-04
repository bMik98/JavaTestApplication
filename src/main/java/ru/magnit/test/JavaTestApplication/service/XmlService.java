package ru.magnit.test.JavaTestApplication.service;

import ru.magnit.test.JavaTestApplication.entity.Entry;

import java.io.File;
import java.util.List;

public interface XmlService<T extends Entry> {
    void saveToFile(List<T> entries, File file);

    List<T> loadFromFile(File file);

    void transform(File inputFile, File xslFile, File outputFile);
}
