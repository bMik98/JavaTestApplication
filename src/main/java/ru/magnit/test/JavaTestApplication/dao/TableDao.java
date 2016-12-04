package ru.magnit.test.JavaTestApplication.dao;

import ru.magnit.test.JavaTestApplication.entity.Entry;

import java.util.List;

public interface TableDao<T extends Entry> {
    void deleteAll();

    void save(List<T> entries);

    List<T> getAll();

    int count();
}
