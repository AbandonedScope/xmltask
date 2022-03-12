package com.mahanko.gems.bulder;

import java.util.Locale;

public enum GemXmlAttribute {
    ID,
    FACES,
    PRODUCTION_DATE,
    VALUE;

    @Override
    public String toString() {
        return super.toString().toLowerCase(Locale.ROOT).replace('_', '-');
    }
}
