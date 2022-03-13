package com.mahanko.gems.bulder;

import com.mahanko.gems.entity.*;
import com.mahanko.gems.exception.CustomXmlParserException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.YearMonth;
import java.util.Locale;

public class GemDomBuilder extends AbstractGemBuilder {
    private static final Logger logger = LogManager.getLogger();
    private final DocumentBuilder docBuilder;

    public GemDomBuilder() throws CustomXmlParserException {
        super();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, e);
            throw new CustomXmlParserException(e);
        }
    }

    @Override
    public void buildSetGems(String path) throws CustomXmlParserException {
        Document doc;
        try {
            doc = docBuilder.parse(path);
            Element root = doc.getDocumentElement();
            var gemList = root.getChildNodes();
            for (int i = 0; i < gemList.getLength(); i++) {
                Node gemNode = gemList.item(i);
                if (gemNode.getNodeType() == Node.ELEMENT_NODE) {
                    gems.add(buildGem((Element) gemNode));
                }
            }
        } catch (IOException | SAXException e) {
            logger.log(Level.ERROR, e);
            throw new CustomXmlParserException(e);
        }
    }

    private GemEntity buildGem(Element gemElement) {
        GemEntity gem;
        if (GemXmlTag.PRECIOUS_STONE.toString().equals(gemElement.getTagName())) {
            gem = new PreciousStoneEntity();
            if (gemElement.hasAttribute(GemXmlAttribute.FACES.toString())) {
                int faces = Integer.parseInt(gemElement.getAttribute(GemXmlAttribute.FACES.toString()));
                ((PreciousStoneEntity) gem).setFaces(faces);
            }

            int carats = Integer.parseInt(gemElement.getAttribute(GemXmlAttribute.VALUE.toString()));
            ((PreciousStoneEntity) gem).setCarats(carats);

            GemOrigin origin = new GemOrigin();
            Element originElement = (Element) gemElement.getElementsByTagName(GemXmlTag.ORIGIN.toString()).item(0);
            origin.setCountry(getElementTextContent(originElement, GemXmlTag.COUNTRY.toString()));
            origin.setMineName(getElementTextContent(originElement, GemXmlTag.MINE_NAME.toString()));
            ((PreciousStoneEntity) gem).setOrigin(origin);

            String gemElementPreciousness = getElementTextContent(gemElement, GemXmlTag.PRECIOUSNESS.toString());
            GemPreciousnessType preciousness = GemPreciousnessType.valueOf(gemElementPreciousness.toUpperCase(Locale.ROOT).replace('-', '_'));
            ((PreciousStoneEntity) gem).setPreciousness(preciousness);
        } else {
            gem = new JewelryOrnamentalStoneEntity();
            ((JewelryOrnamentalStoneEntity) gem).setAnimalProducer(getElementTextContent(gemElement, GemXmlTag.ANIMAL_PRODUCER.toString()));
        }

        if (gemElement.hasAttribute(GemXmlAttribute.PRODUCTION_DATE.toString())) {
            gem.setProductionDate(YearMonth.parse(gemElement.getAttribute(GemXmlAttribute.PRODUCTION_DATE.toString())));
        }

        String id = gemElement.getAttribute(GemXmlAttribute.ID.toString());
        gem.setId(id);

        gem.setName(getElementTextContent(gemElement, GemXmlTag.NAME.toString()));

        GemVisualParameters parameters = new GemVisualParameters();
        Element parametersElement = (Element) gemElement.getElementsByTagName(GemXmlTag.VISUAL_PARAMETERS.toString()).item(0);
        parameters.setColor(getElementTextContent(parametersElement, GemXmlTag.COLOR.toString()));
        int transparency = Integer.parseInt(getElementTextContent(parametersElement, GemXmlTag.TRANSPARENCY.toString()));
        parameters.setTransparency(transparency);
        gem.setVisualParameters(parameters);

        return gem;
    }

    private String getElementTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
