package ru.magnit.test.JavaTestApplication;

import ru.magnit.test.JavaTestApplication.entity.Entry;
import ru.magnit.test.JavaTestApplication.property.DbConnectionProperties;
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
    private DbConnectionProperties dbConnectionProperties;
    private int numberN;

    public JavaTestApplication() {
        file1 = new File(locateFile(fileName1));
        file2 = new File(locateFile(fileName2));
        xslFile = new File(locateFile(xslFileName));
    }

    @Override
    public long run() {
        TableService tableService = new SerialEntryTableService(dbConnectionProperties, numberN);
        tableService.clearAndPopulateTable();
        XmlService xmlService = new EntryXmlService();
        xmlService.saveToFile(tableService.getTableContent(), file1);
        xmlService.transform(file1, xslFile, file2);
        List<Entry> entries = xmlService.loadFromFile(file2);
        return sumOfField(entries);
    }

    @Override
    public void setDbConnectionProperties(final DbConnectionProperties properties) {
        this.dbConnectionProperties = properties;
    }

    @Override
    public void setNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException();
        }
        this.numberN = number;
    }

    private String locateFile(final String fileName) {
        return getClass().getClassLoader().getResource(fileName).getFile();
    }

    private long sumOfField(List<Entry> entries) {
        long result = 0L;
        for (Entry entry : entries) {
            result += entry.getField();
        }
        return result;
    }
}
