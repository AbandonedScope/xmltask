package com.mahanko.gems.handler;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class GemErrorHandler implements ErrorHandler {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        logger.log(Level.WARN, "{} - {}", getErrorPosition(exception), exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        logger.log(Level.ERROR, "{} - {}", getErrorPosition(exception), exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        logger.log(Level.FATAL, "{} - {}", getErrorPosition(exception), exception.getMessage());
    }

    private String getErrorPosition(SAXParseException exception) {
        return exception.getLineNumber() + " line " + exception.getColumnNumber() + " column";
    }
}
