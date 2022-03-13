package com.mahanko.gems.exception;

public class CustomXmlParserException extends Exception {
    public CustomXmlParserException() {
        super();
    }

    public CustomXmlParserException(String message) {
        super(message);
    }

    public CustomXmlParserException(Throwable cause) {
        super(cause);
    }

    public CustomXmlParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
