package com.mahanko.gems.entity;

import java.util.Locale;

public enum GemPreciousnessType {
    PRECIOUS,
    SEMI_PRECIOUS,
    NON;

    @Override
    public String toString() {
        return super.toString().toLowerCase(Locale.ROOT).replace('_', '-');
    }
}
