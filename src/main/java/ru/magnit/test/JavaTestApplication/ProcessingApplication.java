package ru.magnit.test.JavaTestApplication;

import ru.magnit.test.JavaTestApplication.dao.util.ConnectionProperties;

public interface ProcessingApplication {
    long run();

    void setConnectionProperties(ConnectionProperties properties);

    void setNumber(int number);
}
