package ru.magnit.test.JavaTestApplication.dao.impl;

import ru.magnit.test.JavaTestApplication.dao.EntryTableDao;
import ru.magnit.test.JavaTestApplication.entity.Entry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mbedritskiy
 */
public class JdbcEntryTableDao implements EntryTableDao {
    private final String TABLE_NAME = "TEST";
    private final String FIELD_NAME = "FIELD";
    private Connection connection;
    private int savedCount = 0;

    public JdbcEntryTableDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void deleteAll() {
        final String dropSql = String.format("DROP TABLE IF EXISTS \"%s\"", TABLE_NAME);
        final String createSql = String.format("CREATE TABLE \"%s\" (\"%s\" INTEGER)", TABLE_NAME, FIELD_NAME);
        try {
            Statement statement = connection.createStatement();
            statement.execute(dropSql);
            statement.execute(createSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        savedCount = 0;
    }

    @Override
    public void save(final List<Entry> entries) {
        final String insertSql = String.format("INSERT INTO \"%s\" (\"%s\") VALUES (?)", TABLE_NAME, FIELD_NAME);
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(insertSql);
            for (Entry entry : entries) {
                preparedStatement.setInt(1, entry.getField());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        savedCount = entries.size();
    }

    @Override
    public List<Entry> getAll() {
        ResultSet queryResult = queryAll();
        List<Entry> result = new ArrayList<>(savedCount);
        try {
            int fieldIndex = queryResult.findColumn(FIELD_NAME);
            while (queryResult.next()) {
                Entry entry = new Entry();
                entry.setField(queryResult.getInt(fieldIndex));
                result.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int count() {
        int recordCount = 0;
        final String countQuery = String.format("SELECT COUNT(*) FROM \"%s\"", TABLE_NAME);
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(countQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                recordCount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recordCount;
    }

    private ResultSet queryAll() {
        final String selectSql = String.format("SELECT * FROM \"%s\"", TABLE_NAME);
        ResultSet result = null;
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(selectSql);
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
