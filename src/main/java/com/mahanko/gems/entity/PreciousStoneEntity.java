package com.mahanko.gems.entity;

import java.time.YearMonth;
import java.util.Objects;

public class PreciousStoneEntity extends GemEntity {
    private GemOrigin origin;
    private GemPreciousnessType preciousness;
    private int faces;

    public PreciousStoneEntity() {
        super();
        origin = new GemOrigin();
        preciousness = GemPreciousnessType.NON;
        faces = 0;
    }

    public PreciousStoneEntity(String id, String name, GemVisualParameters visualParameters, YearMonth productionDate,
                               GemOrigin origin, GemPreciousnessType preciousness, int faces) {
        super(id, name, visualParameters, productionDate);
        this.origin = origin;
        this.preciousness = preciousness;
        this.faces = faces;
    }

    public GemOrigin getOrigin() {
        return origin;
    }

    public GemPreciousnessType getPreciousness() {
        return preciousness;
    }

    public int getFaces() {
        return faces;
    }

    public void setOrigin(GemOrigin origin) {
        this.origin = origin;
    }

    public void setPreciousness(GemPreciousnessType preciousness) {
        this.preciousness = preciousness;
    }

    public void setFaces(int faces) {
        this.faces = faces;
    }

    @Override
    public String toString() {
        return "origin=" + origin +
                "preciousness=" + preciousness +
                "faces=" + faces + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        PreciousStoneEntity that = (PreciousStoneEntity) obj;
        return super.equals(obj) && Objects.equals(origin, that.origin) && preciousness == that.preciousness;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), origin, preciousness);
    }
}
