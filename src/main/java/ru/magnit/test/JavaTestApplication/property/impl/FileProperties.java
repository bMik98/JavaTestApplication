package ru.magnit.test.JavaTestApplication.property.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileProperties {
    private Properties properties;

    public FileProperties(final String fileName) {
        properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(fileName);
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
