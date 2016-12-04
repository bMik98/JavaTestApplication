package ru.magnit.test.JavaTestApplication.property.impl;

import ru.magnit.test.JavaTestApplication.property.DbConnectionProperties;

public class DbConnectionFileProperties extends FileProperties implements DbConnectionProperties {
    private String url;
    private String driverName;

    public DbConnectionFileProperties(final String fileName) {
        super(fileName);
        url = getProperties().getProperty("url");
        getProperties().remove("url");
        driverName = getProperties().getProperty("driverName");
        getProperties().remove("driverName");
    }

    @Override
    public String buildUrl() {
        return url;
    }

    @Override
    public String getDriverName() {
        return driverName;
    }
}
