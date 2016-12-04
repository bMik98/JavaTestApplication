package ru.magnit.test.JavaTestApplication;

import ru.magnit.test.JavaTestApplication.entity.Entry;
import ru.magnit.test.JavaTestApplication.dao.util.ConnectionProperties;
import ru.magnit.test.JavaTestApplication.service.TableService;
import ru.magnit.test.JavaTestApplication.service.XmlService;
import ru.magnit.test.JavaTestApplication.service.impl.EntryXmlService;
import ru.magnit.test.JavaTestApplication.service.impl.SerialEntryTableService;

import java.io.File;
import java.util.List;

public class JavaTestApplication implements ProcessingApplication {
    private final String fileName1 = "1.xml";
    private final String fileName2 = "2.xml";
    private final String xslFileName = "transform.xsl";
    private final File file1;
    private final File file2;
    private final File xslFile;
    private ConnectionProperties connectionProperties;
    private int numberN;

    public JavaTestApplication() {
        file1 = new File(locateFile(fileName1));
        file2 = new File(locateFile(fileName2));
        xslFile = new File(locateFile(xslFileName));
    }

    @Override
    public long run() {
        TableService tableService = new SerialEntryTableService(connectionProperties, numberN);
        tableService.clearAndPopulateTable();
        XmlService xmlService = new EntryXmlService();
        xmlService.saveToFile(tableService.getTableContent(), file1);
        xmlService.transform(file1, xslFile, file2);
        List<Entry> entries = xmlService.loadFromFile(file2);
        return sumOfField(entries);
    }

    public void setConnectionProperties(final ConnectionProperties properties) {
        this.connectionProperties = properties;
    }

    @Override
    public void setNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException();
        }
        this.numberN = number;
    }

    private String locateFile(final String fileName) {
        String result = "";
        try {
            result = getClass().getClassLoader().getResource(fileName).getFile();
        } catch (NullPointerException e) {
            System.err.println(String.format("File not found: %s", fileName));
        }
        return result;
    }

    private long sumOfField(List<Entry> entries) {
        long result = 0L;
        for (Entry entry : entries) {
            result += entry.getField();
        }
        return result;
    }
}
