package com.mahanko.gems.parser;

import java.util.Locale;

public enum GemXmlTag {
    GEMS,
    PRECIOUS_STONE,
    ORIGIN,
    JEWELRY_ORNAMENTAL_STONE,
    VISUAL_PARAMETERS,
    TRANSPARENCY,
    PRECIOUSNESS,
    NAME,
    COLOR,
    COUNTRY,
    MINE_NAME,
    ANIMAL_PRODUCER;

    @Override
    public String toString() {
        return super.toString().toLowerCase(Locale.ROOT).replace('_', '-');
    }
}
