package com.mahanko.gems.entity;

import java.time.YearMonth;
import java.util.Objects;

public class PreciousStoneEntity extends GemEntity {
    private GemOrigin origin;
    private GemPreciousnessType preciousness;
    private int faces;
    private int carats;

    public PreciousStoneEntity() {
        super();
        origin = new GemOrigin();
        preciousness = GemPreciousnessType.NON;
        faces = 0;
        carats = 0;
    }

    public PreciousStoneEntity(String id, String name, GemVisualParameters visualParameters, YearMonth productionDate,
                               GemOrigin origin, GemPreciousnessType preciousness, int faces, int carats) {
        super(id, name, visualParameters, productionDate);
        this.origin = origin;
        this.preciousness = preciousness;
        this.faces = faces;
        this.carats = carats;
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

    public int getCarats() {
        return carats;
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

    public void setCarats(int carats) {
        this.carats = carats;
    }

    @Override
    public String toString() {
        return  origin.toString() + '\n' +
                "preciousness=" + preciousness + '\n' +
                "faces=" + faces + '\n' +
                "carats=" + carats + '\n' +
                super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        PreciousStoneEntity that = (PreciousStoneEntity) obj;
        return super.equals(obj) && origin.equals(that.origin) && preciousness == that.preciousness;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), origin, preciousness);
    }
}
