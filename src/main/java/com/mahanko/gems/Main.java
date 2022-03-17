package com.mahanko.gems;

import com.mahanko.gems.parser.*;
import com.mahanko.gems.entity.GemEntity;
import com.mahanko.gems.exception.CustomXmlParserException;
import com.mahanko.gems.factory.GemBuilderFactory;
import com.mahanko.gems.validator.CustomXmlValidator;

public class Main {
    public static void main(String[] args) {
        String pathXml = "src\\main\\resources\\gems.xml";
        String pathXsd = "src\\main\\resources\\schema\\schema.xsd";
        AbstractGemBuilder builder = null;
        try {
            builder = GemBuilderFactory.createGemBuilder(GemXmlParserType.STAX);
            builder.buildSetGems(pathXml);
        } catch (CustomXmlParserException e) {

        }

        for (GemEntity gem: builder.getGems()) {
            System.out.println(gem.toString());
        }
        CustomXmlValidator validator = new CustomXmlValidator();
        try {
            validator.valid(pathXsd, pathXml);
        } catch (CustomXmlParserException e) {
            e.printStackTrace();
        }
    }
}
