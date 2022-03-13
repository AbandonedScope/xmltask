package com.mahanko.gems.bulder;

import com.mahanko.gems.entity.GemEntity;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractGemBuilder {
    protected Set<GemEntity> gems;

    protected AbstractGemBuilder() {
        gems = new HashSet<>();
    }
    public abstract Set<GemEntity> getGems();
    public abstract void buildSetGems(String path);
}
