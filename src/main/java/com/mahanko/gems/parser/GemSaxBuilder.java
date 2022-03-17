package com.mahanko.gems.parser;
import com.mahanko.gems.exception.CustomXmlParserException;
import com.mahanko.gems.handler.GemHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class GemSaxBuilder extends AbstractGemBuilder {
    private static final Logger logger = LogManager.getLogger();
    private final org.xml.sax.XMLReader reader;
    protected GemHandler handler;

    public GemSaxBuilder() throws CustomXmlParserException {
        super();
        handler = new GemHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            logger.log(Level.ERROR, e);
            throw new CustomXmlParserException(e);
        }

        reader.setContentHandler(handler);
    }

    @Override
    public void buildSetGems(String path) throws CustomXmlParserException {
        try {
            reader.parse(path);
        } catch (IOException | SAXException e) {
            logger.log(Level.ERROR, e);
            throw new CustomXmlParserException(e);
        }

        gems = handler.getGems();
    }
}
