package com.mahanko.gems.entity;

import java.time.YearMonth;
import java.util.Objects;

public class JewelryOrnamentalStoneEntity extends GemEntity {
    private String animalProducer;

    public JewelryOrnamentalStoneEntity() {
        super();
        animalProducer = "";
    }

    public JewelryOrnamentalStoneEntity(String id, String name, GemVisualParameters visualParameters,
                                        YearMonth productionDate, String animalProducer) {
        super(id, name, visualParameters, productionDate);
        this.animalProducer = animalProducer;
    }

    public String getAnimalProducer() {
        return animalProducer;
    }

    public void setAnimalProducer(String animalProducer) {
        this.animalProducer = animalProducer;
    }

    @Override
    public String toString() {
        return "animalProducer=" + animalProducer + '\'' + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        JewelryOrnamentalStoneEntity that = (JewelryOrnamentalStoneEntity) obj;
        return super.equals(obj) && Objects.equals(animalProducer, that.animalProducer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), animalProducer);
    }
}
