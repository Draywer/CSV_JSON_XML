package ru.idcore.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public abstract class XMLFile<T> {
    public abstract Class<T> getType();

    public Document getXMLDocument(String fileName) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        return documentBuilder.parse(new File(fileName));

    }

    public T getObjFromXMLFile (String xmlFile) {
        T t = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(getType());
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            //We had written this file in marshalling example
            t = (T) jaxbUnmarshaller.unmarshal( new File(xmlFile) );


        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return  t;
    }
}
