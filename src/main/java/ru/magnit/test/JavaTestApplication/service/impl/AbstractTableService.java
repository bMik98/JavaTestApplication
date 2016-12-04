package ru.magnit.test.JavaTestApplication.service.impl;

import ru.magnit.test.JavaTestApplication.dao.TableDao;
import ru.magnit.test.JavaTestApplication.dao.impl.JdbcEntryTableDao;
import ru.magnit.test.JavaTestApplication.dao.util.ConnectionBuilder;
import ru.magnit.test.JavaTestApplication.entity.Entry;
import ru.magnit.test.JavaTestApplication.dao.util.ConnectionProperties;
import ru.magnit.test.JavaTestApplication.service.TableService;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractTableService<T extends Entry> implements TableService<T> {
    private final TableDao dao;

    public AbstractTableService(final ConnectionProperties connectionProperties) {
        Connection connection = ConnectionBuilder.build(connectionProperties);
        this.dao = new JdbcEntryTableDao(connection);
    }

    public TableDao getDao() {
        return dao;
    }

    @Override
    abstract public void clearAndPopulateTable();

    @Override
    public List<T> getTableContent() {
        return (List<T>) dao.getAll();
    }
}
