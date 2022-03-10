package com.mahanko.gems.entity;

import java.util.Objects;

public class GemVisualParameters {
    private String color;
    private int transparency;

    public GemVisualParameters() {
        color = "";
        transparency = 0;
    }

    public GemVisualParameters(String color, int transparency) {
        this.color = color;
        this.transparency = transparency;
    }

    public String getColor() {
        return color;
    }

    public int getTransparency() {
        return transparency;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTransparency(int transparency) {
        this.transparency = transparency;
    }

    @Override
    public String toString() {
        return "color='" + color + '\'' +
                "transparency=" + transparency;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        GemVisualParameters parameters = (GemVisualParameters) obj;
        return transparency == parameters.transparency && color.equals(parameters.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, transparency);
    }
}
