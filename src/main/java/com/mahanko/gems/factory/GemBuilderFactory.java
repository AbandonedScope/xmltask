package com.mahanko.gems.factory;

import com.mahanko.gems.bulder.*;
import com.mahanko.gems.exception.CustomXmlParserException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GemBuilderFactory {
    private static final Logger logger = LogManager.getLogger();

    private GemBuilderFactory() {
    }

    public static AbstractGemBuilder createGemBuilder(GemXmlParserType parserType) throws CustomXmlParserException {
        AbstractGemBuilder builder;
        switch (parserType) {
            case DOM:
                builder = new GemDomBuilder();
                break;
            case SAX:
                builder = new GemSaxBuilder();
                break;
            case STAX:
                builder = new GemStaxBuilder();
                break;
            default:
                logger.log(Level.WARN, "Unexpected value: {}", parserType);
                throw new CustomXmlParserException("Unexpected value: " + parserType);
        }

        return builder;
    }
}
