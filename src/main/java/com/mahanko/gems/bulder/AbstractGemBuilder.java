package com.mahanko.gems.bulder;

import com.mahanko.gems.entity.GemEntity;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractGemBuilder {
    protected Set<GemEntity> gems;
    protected GemHandler handler;

    protected AbstractGemBuilder() {
        gems = new HashSet<>();
        handler = new GemHandler();
    }
    public abstract Set<GemEntity> getGems();
    public abstract void buildSetGems(String path);
}
