package ru.magnit.test.JavaTestApplication.dao.util;

public class ConnectionFileProperties extends FileProperties implements ConnectionProperties {
    private String url;
    private String driverName;

    public ConnectionFileProperties(final String fileName) {
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
