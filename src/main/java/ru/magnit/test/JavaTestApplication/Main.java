package ru.magnit.test.JavaTestApplication;

import ru.magnit.test.JavaTestApplication.property.DbConnectionProperties;
import ru.magnit.test.JavaTestApplication.property.impl.DbConnectionFileProperties;

public class Main {
    private final static String DB_CONNECTION_PROP_FILE = "dbconnection.properties";
    private final static int NUMBER_N = 100;

    public static void main(String[] args) {
        new Main().setupAndRunProcessingApplication();
    }

    public void setupAndRunProcessingApplication() {
        ProcessingApplication application = new JavaTestApplication();
        ClassLoader classLoader = getClass().getClassLoader();
        DbConnectionProperties properties = new DbConnectionFileProperties(classLoader.getResource(DB_CONNECTION_PROP_FILE).getFile());
        application.setDbConnectionProperties(properties);
        application.setNumber(NUMBER_N);
        long result = application.run();
        System.out.println(result);
    }
}
