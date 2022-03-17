package com.mahanko.gems.parser;

import com.mahanko.gems.entity.*;
import com.mahanko.gems.exception.CustomXmlParserException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.YearMonth;
import java.util.Locale;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

public class GemStaxBuilder extends AbstractGemBuilder {
    private static final Logger logger = LogManager.getLogger();
    private final XMLInputFactory factory;

    public GemStaxBuilder() {
        super();
        factory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildSetGems(String path) throws CustomXmlParserException {
        XMLStreamReader reader;
        GemXmlTag tagType;
        try (FileInputStream file = new FileInputStream(path)) {
            reader = factory.createXMLStreamReader(file);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == START_ELEMENT) {
                    tagType = getGemXmlTag(reader);
                    if (tagType.equals(GemXmlTag.PRECIOUS_STONE) ||
                            tagType.equals(GemXmlTag.JEWELRY_ORNAMENTAL_STONE)) {
                        GemEntity gem;
                        if (tagType.equals(GemXmlTag.PRECIOUS_STONE)) {
                            gem = new PreciousStoneEntity();
                        } else {
                            gem = new JewelryOrnamentalStoneEntity();
                        }
                        buildGem(reader, gem);
                        gems.add(gem);
                    }
                }
            }
        } catch (IOException | XMLStreamException e) {
            logger.log(Level.ERROR, e);
            throw new CustomXmlParserException(e);
        }
    }

    private void buildGem(XMLStreamReader reader, GemEntity gem) throws XMLStreamException {
        gem.setId(reader.getAttributeValue(null, GemXmlAttribute.ID.toString()));

        String productionDateString = reader.getAttributeValue(null, GemXmlAttribute.PRODUCTION_DATE.toString());
        if (productionDateString != null) {
            YearMonth productionDate = YearMonth.parse(productionDateString);
            gem.setProductionDate(productionDate);
        }

        if (gem instanceof PreciousStoneEntity) {
            int carats = Integer.parseInt(reader.getAttributeValue(null, GemXmlAttribute.VALUE.toString()));
            ((PreciousStoneEntity) gem).setCarats(carats);
            if (reader.getAttributeValue(null, GemXmlAttribute.FACES.toString()) != null) {
                int faces = Integer.parseInt(reader.getAttributeValue(null, GemXmlAttribute.FACES.toString()));
                ((PreciousStoneEntity) gem).setFaces(faces);
            }
        }

        GemXmlTag tagType;
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == START_ELEMENT) {
                tagType = getGemXmlTag(reader);
                switch (tagType) {
                    case NAME:
                        gem.setName(reader.getElementText());
                        break;
                    case VISUAL_PARAMETERS:
                        gem.setVisualParameters(getXmlGemVisualParameters(reader));
                        break;
                    case ORIGIN:
                        ((PreciousStoneEntity) gem).setOrigin(getXmlGemOrigin(reader));
                        break;
                    case PRECIOUSNESS:
                        GemPreciousnessType preciousness = GemPreciousnessType.valueOf(reader.getElementText().toUpperCase(Locale.ROOT).replace('-', '_'));
                        ((PreciousStoneEntity) gem).setPreciousness(preciousness);
                        break;
                    case ANIMAL_PRODUCER:
                        ((JewelryOrnamentalStoneEntity) gem).setAnimalProducer(reader.getElementText());
                        break;
                    default:
                        break;
                }
            } else if (type == END_ELEMENT) {
                tagType = getGemXmlTag(reader);
                if (tagType.equals(GemXmlTag.PRECIOUS_STONE) || tagType.equals(GemXmlTag.JEWELRY_ORNAMENTAL_STONE)) {
                    break;
                }
            }
        }
    }

    private GemVisualParameters getXmlGemVisualParameters(XMLStreamReader reader) throws XMLStreamException {
        GemVisualParameters parameters = new GemVisualParameters();
        GemXmlTag tagType;
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == START_ELEMENT) {
                tagType = getGemXmlTag(reader);
                if (tagType.equals(GemXmlTag.COLOR)) {
                    parameters.setColor(reader.getElementText());
                } else {
                    parameters.setTransparency(Integer.parseInt(reader.getElementText()));
                }
            } else if (type == END_ELEMENT) {
                tagType = getGemXmlTag(reader);
                if (tagType.equals(GemXmlTag.VISUAL_PARAMETERS)) {
                    break;
                }
            }
        }

        return parameters;
    }

    private GemOrigin getXmlGemOrigin(XMLStreamReader reader) throws XMLStreamException {
        GemOrigin origin = new GemOrigin();
        GemXmlTag tagType;
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == START_ELEMENT) {
                tagType = getGemXmlTag(reader);
                if (tagType.equals(GemXmlTag.COUNTRY)) {
                    origin.setCountry(reader.getElementText());
                } else {
                    origin.setMineName(reader.getElementText());
                }
            } else if (type == END_ELEMENT) {
                tagType = getGemXmlTag(reader);
                if (tagType.equals(GemXmlTag.ORIGIN)) {
                    break;
                }
            }
        }

        return origin;
    }

    private GemXmlTag getGemXmlTag(XMLStreamReader reader) {
        String elementName = reader.getLocalName();
        return GemXmlTag.valueOf(elementName.toUpperCase(Locale.ROOT).replace('-', '_'));
    }
}
