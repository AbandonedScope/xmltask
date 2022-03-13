package com.mahanko.gems;

import com.mahanko.gems.bulder.*;
import com.mahanko.gems.entity.GemEntity;
import com.mahanko.gems.exception.CustomXmlParserException;
import com.mahanko.gems.factory.GemBuilderFactory;

public class Main {
    public static void main(String[] args) {
        String path = "D:\\Epam Java\\xmltask\\src\\main\\resources\\gems.xml";
        AbstractGemBuilder builder = null;
        try {
            builder = GemBuilderFactory.createGemBuilder(GemXmlParserType.STAX);
            builder.buildSetGems(path);
        } catch (CustomXmlParserException e) {

        }

        for (GemEntity gem: builder.getGems()) {
            System.out.println(gem.toString());
        }
    }
}
