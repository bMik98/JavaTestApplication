package ru.magnit.test.JavaTestApplication;

import ru.magnit.test.JavaTestApplication.property.DbConnectionProperties;

public interface ProcessingApplication {
    long run();

    void setDbConnectionProperties(DbConnectionProperties properties);

    void setNumber(int number);
}
