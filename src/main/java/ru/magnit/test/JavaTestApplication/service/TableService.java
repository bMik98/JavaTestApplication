package ru.magnit.test.JavaTestApplication.service;

import ru.magnit.test.JavaTestApplication.entity.Entry;

import java.util.List;

/**
 * @author Mbedritskiy
 */
public interface TableService<T extends Entry> {
    void clearAndPopulateTable();

    List<T> getTableContent();
}
