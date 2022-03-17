package com.mahanko.gems.validator;

import com.mahanko.gems.exception.CustomXmlParserException;
import com.mahanko.gems.handler.GemErrorHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class CustomXmlValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private SchemaFactory factory;

    public CustomXmlValidator() {
        factory = SchemaFactory.newInstance(LANGUAGE);
    }

    public void valid(String pathXsd, String pathXml) throws CustomXmlParserException {
        File schemaLocation = new File(pathXsd);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(pathXml);
            validator.setErrorHandler(new GemErrorHandler());
            validator.validate(source);
        } catch (SAXException | IOException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new CustomXmlParserException(e);
        }
    }
}
