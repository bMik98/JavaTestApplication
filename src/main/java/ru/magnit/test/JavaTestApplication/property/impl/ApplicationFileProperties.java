package ru.magnit.test.JavaTestApplication.property.impl;

import ru.magnit.test.JavaTestApplication.property.ApplicationProperties;

public class ApplicationFileProperties extends FileProperties implements ApplicationProperties {
    private int numberN;

    public ApplicationFileProperties(final String fileName) {
        super(fileName);
        numberN = Integer.parseInt(getProperties().getProperty("numberN"));
    }

    @Override
    public int getNumberN() {
        return numberN;
    }
}
