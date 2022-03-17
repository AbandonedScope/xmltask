package com.mahanko.gems.handler;

import com.mahanko.gems.parser.GemXmlAttribute;
import com.mahanko.gems.parser.GemXmlTag;
import com.mahanko.gems.entity.GemEntity;
import com.mahanko.gems.entity.GemPreciousnessType;
import com.mahanko.gems.entity.JewelryOrnamentalStoneEntity;
import com.mahanko.gems.entity.PreciousStoneEntity;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class GemHandler extends DefaultHandler {
    private final Set<GemEntity> gems;
    private GemEntity currentGem;
    private GemXmlTag currentXmlTag;
    private final EnumSet<GemXmlTag> withText;
    private static final String ELEMENT_PRECIOUS_STONE = "precious-stone";
    private static final String ELEMENT_JEWELRY_ORNAMENTAL_STONE = "jewelry-ornamental-stone";

    public GemHandler() {
        gems = new HashSet<>();
        withText = EnumSet.range(GemXmlTag.TRANSPARENCY, GemXmlTag.ANIMAL_PRODUCER);
    }

    public Set<GemEntity> getGems() {
        return gems;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (ELEMENT_PRECIOUS_STONE.equals(qName) || ELEMENT_JEWELRY_ORNAMENTAL_STONE.equals(qName)) {
            if (ELEMENT_PRECIOUS_STONE.equals(qName)) {
                currentGem = new PreciousStoneEntity();
                ((PreciousStoneEntity)currentGem).setCarats(Integer.parseInt(attributes.getValue(GemXmlAttribute.VALUE.toString())));
                String faces = attributes.getValue(GemXmlAttribute.FACES.toString());
                if (faces != null) {
                    ((PreciousStoneEntity)currentGem).setFaces(Integer.parseInt(faces));
                }
            } else {
                currentGem = new JewelryOrnamentalStoneEntity();
            }
            currentGem.setId(attributes.getValue(GemXmlAttribute.ID.toString()));
            String productionDate = attributes.getValue(GemXmlAttribute.PRODUCTION_DATE.toString());
            if (productionDate != null) {
                currentGem.setProductionDate(YearMonth.parse(productionDate));
            }
        } else {
            GemXmlTag temp = GemXmlTag.valueOf(qName.toUpperCase(Locale.ROOT).replace('-', '_'));
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (ELEMENT_PRECIOUS_STONE.equals(qName) || ELEMENT_JEWELRY_ORNAMENTAL_STONE.equals(qName)) {
            gems.add(currentGem);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length);
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case NAME:
                    currentGem.setName(data);
                    break;
                case COLOR:
                    currentGem.getVisualParameters().setColor(data);
                    break;
                case TRANSPARENCY:
                    currentGem.getVisualParameters().setTransparency(Integer.parseInt(data));
                    break;
                case PRECIOUSNESS:
                    ((PreciousStoneEntity)currentGem).setPreciousness(GemPreciousnessType.valueOf(data.toUpperCase(Locale.ROOT).replace('-','_')));
                    break;
                case COUNTRY:
                    ((PreciousStoneEntity)currentGem).getOrigin().setCountry(data);
                    break;
                case MINE_NAME:
                    ((PreciousStoneEntity)currentGem).getOrigin().setMineName(data);
                    break;
                case ANIMAL_PRODUCER:
                    ((JewelryOrnamentalStoneEntity)currentGem).setAnimalProducer(data);
                    break;
                default:
                    break;
            }
            currentXmlTag = null;
        }
    }
}
