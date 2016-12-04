package ru.magnit.test.JavaTestApplication.property;

import java.util.Properties;

public interface DbConnectionProperties {
    String buildUrl();

    String getDriverName();

    Properties getProperties();
}
