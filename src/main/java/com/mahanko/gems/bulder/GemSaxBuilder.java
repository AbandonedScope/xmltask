package com.mahanko.gems.bulder;

import com.mahanko.gems.entity.GemEntity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class GemSaxBuilder extends AbstractGemBuilder { // FIXME: 12.03.2022 logger
    private org.xml.sax.XMLReader reader;

    public GemSaxBuilder() {
        super();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException e) { // FIXME: 12.03.2022 
            e.printStackTrace();
        } catch (SAXException e) { // FIXME: 12.03.2022 
            e.printStackTrace();
        }

        reader.setContentHandler(handler);
    }

    @Override
    public Set<GemEntity> getGems() {
        return gems;
    }

    @Override
    public void buildSetGems(String path) {
        try {
            reader.parse(path);
        } catch (IOException e) { // FIXME: 12.03.2022 
            e.printStackTrace();
        } catch (SAXException e) { // FIXME: 12.03.2022 
            e.printStackTrace();
        }

        gems = handler.getGems();
    }
}
