package ru.magnit.test.JavaTestApplication;

import org.junit.Before;
import org.junit.Test;
import ru.magnit.test.JavaTestApplication.dao.util.ConnectionFileProperties;
import ru.magnit.test.JavaTestApplication.dao.util.ConnectionProperties;

import static org.junit.Assert.*;

public class JavaTestApplicationTest {
    private ConnectionProperties properties;

    @Before
    public void setUp() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String propertyFile = classLoader.getResource(Main.DB_CONNECTION_PROP_FILE).getFile();
        properties = new ConnectionFileProperties(propertyFile);
    }

    @Test
    public void run() throws Exception {
        int numberN = 1000000;
        double expected = ((1.0 + numberN) / 2) * numberN;
        long startTime = System.currentTimeMillis();
        ProcessingApplication application = new JavaTestApplication();
        application.setNumber(numberN);
        application.setConnectionProperties(properties);
        long result = application.run();
        long timeSpent = System.currentTimeMillis() - startTime;
        assertTrue("Runtime less than 5 min.", timeSpent <= 5 * 60 * 1000);
        assertEquals("Sum of values", (long) expected, result);
    }
}