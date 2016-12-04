package ru.magnit.test.JavaTestApplication;

import ru.magnit.test.JavaTestApplication.dao.util.ConnectionFileProperties;
import ru.magnit.test.JavaTestApplication.dao.util.ConnectionProperties;

/**
 * Create instance of ProcessingApplication class, set up parameters and run it
 */
public class Main {
    public final static String DB_CONNECTION_PROP_FILE = "dbconnection.properties";
    private final static int NUMBER_N = 100;

    public static void main(String[] args) {
        new Main().setupAndRunProcessingApplication();
    }

    public void setupAndRunProcessingApplication() {
        ProcessingApplication application = new JavaTestApplication();
        application.setConnectionProperties(loadConnectionProperties(DB_CONNECTION_PROP_FILE));
        application.setNumber(NUMBER_N);
        long result = application.run();
        System.out.println(result);
    }

    public ConnectionProperties loadConnectionProperties(final String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        String propertyFile = "";
        try {
            propertyFile = classLoader.getResource(fileName).getFile();
        } catch (NullPointerException e) {
            System.err.println(String.format("Property file is not found: %s", DB_CONNECTION_PROP_FILE));
        }
        return new ConnectionFileProperties(propertyFile);
    }
}
