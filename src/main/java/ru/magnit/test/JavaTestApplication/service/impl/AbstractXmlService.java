package ru.magnit.test.JavaTestApplication.service.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.magnit.test.JavaTestApplication.entity.Entry;
import ru.magnit.test.JavaTestApplication.service.XmlService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractXmlService implements XmlService<Entry> {
    private DocumentBuilder documentBuilder;
    private TransformerFactory transformerFactory = TransformerFactory.newInstance();

    public AbstractXmlService() {
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Document parseXmlFile(final File file) {
        Document document = null;
        try {
            document = documentBuilder.parse(file);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public Document createDocument() {
        return documentBuilder.newDocument();
    }

    @Override
    public void saveToFile(final List<Entry> entries, final File file) {
        Document document = createDocument();
        Element rootElement = document.createElement(getRootElementName());
        document.appendChild(rootElement);
        for (Entry entry : entries) {
            rootElement.appendChild(createEntryElement(document, entry));
        }
        transform(new DOMSource(document), new StreamResult(file));
    }

    @Override
    public List<Entry> loadFromFile(final File file) {
        List<Entry> result = new ArrayList<>();
        Document doc = parseXmlFile(file);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName(getElementName());
        for (int i = 0; i < nodeList.getLength(); i++) {
            result.add(toEntry(nodeList.item(i)));
        }
        return result;
    }

    @Override
    public void transform(File inputFile, File xslFile, File outputFile) {
        StreamSource xslStream = new StreamSource(xslFile);
        StreamSource inStream = new StreamSource(inputFile);
        StreamResult outStream = new StreamResult(outputFile);
        transform(inStream, xslStream, outStream);
    }

    abstract Entry toEntry(final Node node);

    abstract Element createEntryElement(final Document document, Entry entry);

    abstract String getRootElementName();

    abstract String getElementName();

    private void transform(Source in, Source xsl, Result out) {
        try {
            Transformer transformer = transformerFactory.newTransformer(xsl);
            transformer.transform(in, out);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void transform(Source in, Result out) {
        try {
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(in, out);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
