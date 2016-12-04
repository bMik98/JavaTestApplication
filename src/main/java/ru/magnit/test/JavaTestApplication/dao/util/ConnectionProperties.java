package ru.magnit.test.JavaTestApplication.dao.util;

import java.util.Properties;

public interface ConnectionProperties {
    String buildUrl();

    String getDriverName();

    Properties getProperties();
}
