package com.mahanko.gems.entity;

import java.util.Objects;

public class GemOrigin {
    private String country;
    private String mineName;

    public GemOrigin() {
        country = mineName = "";
    }

    public  GemOrigin(String country, String mineName) {
        this.country = country;
        this.mineName = mineName;
    }

    public String getCountry() {
        return country;
    }

    public String getMineName() {
        return mineName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setMineName(String mineName) {
        this.mineName = mineName;
    }

    @Override
    public String toString() {
        return "country='" + country + '\'' +
                "mineName='" + mineName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        GemOrigin gemOrigin = (GemOrigin) obj;
        return Objects.equals(country, gemOrigin.country) && Objects.equals(mineName, gemOrigin.mineName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, mineName);
    }
}
