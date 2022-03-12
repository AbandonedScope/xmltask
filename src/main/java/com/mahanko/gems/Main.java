package com.mahanko.gems;

import com.mahanko.gems.bulder.GemDomBuilder;
import com.mahanko.gems.bulder.GemSaxBuilder;
import com.mahanko.gems.entity.GemEntity;

public class Main {
    public static void main(String[] args) {
        String path = "D:\\Epam Java\\xmltask\\src\\main\\resources\\gems.xml";
        GemSaxBuilder saxBuilder = new GemSaxBuilder();
        saxBuilder.buildSetGems(path);
        GemDomBuilder domBuilder = new GemDomBuilder();
        domBuilder.buildSetGems(path);
        for (GemEntity gem: domBuilder.getGems()) {
            System.out.println(gem.toString());
        }
    }
}
