package com.mahanko.gems.bulder;

import com.mahanko.gems.entity.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.YearMonth;
import java.util.Locale;
import java.util.Set;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

public class GemStaxBuilder extends AbstractGemBuilder { // FIXME: 13.03.2022 logging
    private XMLInputFactory factory;

    public GemStaxBuilder() {
        super();
        factory = XMLInputFactory.newInstance();
    }

    @Override
    public Set<GemEntity> getGems() {
        return gems;
    }

    @Override
    public void buildSetGems(String path) { // FIXME: 13.03.2022 exeptions
        XMLStreamReader reader;
        String name;
        try (FileInputStream file = new FileInputStream(new File(path))) {
            reader = factory.createXMLStreamReader(file);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(GemXmlTag.PRECIOUS_STONE.toString()) ||
                            name.equals(GemXmlTag.JEWELRY_ORNAMENTAL_STONE.toString())) {
                        GemEntity gem;
                        if (name.equals(GemXmlTag.PRECIOUS_STONE.toString())) {
                            gem = new PreciousStoneEntity();
                        } else {
                            gem = new JewelryOrnamentalStoneEntity();
                        }
                        buildGem(reader, gem);
                        gems.add(gem);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
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

        String elementName;
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == START_ELEMENT) {
                elementName = reader.getLocalName();
                switch (GemXmlTag.valueOf(elementName.toUpperCase(Locale.ROOT).replace('-', '_'))) {
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
                }
            } else if (type == END_ELEMENT) {
                elementName = reader.getLocalName();
                if (elementName.equals(GemXmlTag.PRECIOUS_STONE.toString()) || elementName.equals(GemXmlTag.JEWELRY_ORNAMENTAL_STONE.toString())) {
                    break;
                }
            }
        }
    }

    private GemVisualParameters getXmlGemVisualParameters(XMLStreamReader reader) throws XMLStreamException {
        GemVisualParameters parameters = new GemVisualParameters();
        String elementName;
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == START_ELEMENT) {
                elementName = reader.getLocalName();
                switch (GemXmlTag.valueOf(elementName.toUpperCase(Locale.ROOT).replace('-', '_'))) {
                    case COLOR:
                        parameters.setColor(reader.getElementText());
                        break;
                    case TRANSPARENCY:
                        parameters.setTransparency(Integer.parseInt(reader.getElementText()));
                }
            } else if (type == END_ELEMENT) {
                elementName = reader.getLocalName();
                if (elementName.equals(GemXmlTag.VISUAL_PARAMETERS.toString())) {
                    break;
                }
            }
        }

        return parameters;
    }

    private GemOrigin getXmlGemOrigin(XMLStreamReader reader) throws XMLStreamException {
        GemOrigin origin = new GemOrigin();
        String elementName;
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == START_ELEMENT) {
                elementName = reader.getLocalName();
                switch (GemXmlTag.valueOf(elementName.toUpperCase(Locale.ROOT).replace('-', '_'))) {
                    case COUNTRY:
                        origin.setCountry(reader.getElementText());
                        break;
                    case MINE_NAME:
                        origin.setMineName(reader.getElementText());
                }
            } else if (type == END_ELEMENT) {
                elementName = reader.getLocalName();
                if (elementName.equals(GemXmlTag.ORIGIN.toString())) {
                    break;
                }
            }
        }

        return origin;
    }
}
