package com.mahanko.gems.bulder;

import com.mahanko.gems.entity.GemEntity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class GemSaxBuilder {
    private Set<GemEntity> gems;
    private GemHandler handler = new GemHandler();
    private org.xml.sax.XMLReader reader;

    public GemSaxBuilder() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        reader.setContentHandler(handler);
    }

    public Set<GemEntity> getGems() {
        return gems;
    }

    public void buildSetGems(String path) {
        try {
            reader.parse(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        gems = handler.getGems();
    }
}
