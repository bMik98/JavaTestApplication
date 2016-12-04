package ru.magnit.test.JavaTestApplication.service.impl;

import ru.magnit.test.JavaTestApplication.entity.Entry;
import ru.magnit.test.JavaTestApplication.dao.util.ConnectionProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mbedritskiy
 */
public class SerialEntryTableService extends AbstractTableService {
    private final int startEntryValue = 1;
    private int numberOfEntries;

    public SerialEntryTableService(final ConnectionProperties properties, final int numberOfEntries) {
        super(properties);
        this.numberOfEntries = numberOfEntries;
    }

    @Override
    public void clearAndPopulateTable() {
        getDao().deleteAll();
        List<Entry> entries = createEntrySequence(this.numberOfEntries);
        getDao().save(entries);
    }

    private List<Entry> createEntrySequence(final int numberOfRecords) {
        List<Entry> result = new ArrayList<>(numberOfRecords);
        for (int i = this.startEntryValue; i <= numberOfRecords; i++) {
            result.add(new Entry(i));
        }
        return result;
    }
}
