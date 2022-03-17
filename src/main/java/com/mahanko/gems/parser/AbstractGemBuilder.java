package com.mahanko.gems.parser;
import com.mahanko.gems.entity.GemEntity;
import com.mahanko.gems.exception.CustomXmlParserException;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractGemBuilder {
    protected Set<GemEntity> gems;

    protected AbstractGemBuilder() {
        gems = new HashSet<>();
    }
    public Set<GemEntity> getGems() {
        return gems;
    }
    public abstract void buildSetGems(String path) throws CustomXmlParserException;
}
