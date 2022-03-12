package com.mahanko.gems;

import com.mahanko.gems.bulder.GemSaxBuilder;
import com.mahanko.gems.entity.GemEntity;

public class main {
    public static void main(String[] args) {
        GemSaxBuilder builder = new GemSaxBuilder();
        builder.buildSetGems("D:\\Epam Java\\xmltask\\src\\main\\resources\\gems.xml");
        for (GemEntity gem: builder.getGems()) {
            System.out.println(gem.toString());
        }
    }
}
