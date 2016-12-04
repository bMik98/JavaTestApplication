package ru.magnit.test.JavaTestApplication.service.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import ru.magnit.test.JavaTestApplication.entity.Entry;

public class EntryXmlService extends AbstractXmlService {
    private final String rootElementName = "entries";
    private final String elementName = "entry";
    private final String fieldName = "field";

    public EntryXmlService() {
        super();
    }

    @Override
    Element createEntryElement(final Document document, Entry entry) {
        Element entryElement = document.createElement(elementName);
        entryElement.appendChild(createFieldElement(document, entry));
        return entryElement;
    }

    @Override
    String getRootElementName() {
        return rootElementName;
    }

    @Override
    String getElementName() {
        return elementName;
    }

    @Override
    Entry toEntry(final Node node) {
        Element eElement = (Element) node;
        return new Entry(Integer.parseInt(eElement.getAttribute(fieldName)));
    }

    private Element createFieldElement(final Document document, final Entry entry) {
        Element fieldElement = document.createElement(fieldName);
        fieldElement.appendChild(document.createTextNode(String.valueOf(entry.getField())));
        return fieldElement;
    }
}
