package com.mahanko.gems.entity;

import java.time.YearMonth;
import java.util.Objects;

public abstract class GemEntity {
    private String id;
    private String name;
    private GemVisualParameters visualParameters;
    private YearMonth productionDate;

    protected GemEntity() {
        id = name = "";
        visualParameters = new GemVisualParameters();
        productionDate = YearMonth.now();
    }

    protected GemEntity(String id, String name, GemVisualParameters visualParameters, YearMonth productionDate) {
        this.id = id;
        this.name = name;
        this.visualParameters = visualParameters;
        this.productionDate = productionDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GemVisualParameters getVisualParameters() {
        return visualParameters;
    }

    public YearMonth getProductionDate() {
        return productionDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductionDate(YearMonth productionDate) {
        this.productionDate = productionDate;
    }

    public void setVisualParameters(GemVisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    @Override
    public String toString() {
        return "name='" + name + "'\n" +
                visualParameters.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        GemEntity gemEntity = (GemEntity) obj;
        return name.equals(gemEntity.name) && visualParameters.equals(gemEntity.visualParameters) && productionDate.equals(gemEntity.productionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, visualParameters, productionDate);
    }
}
