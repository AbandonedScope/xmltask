package test.mahanko.gems.parser;

import com.mahanko.gems.entity.*;
import com.mahanko.gems.exception.CustomXmlParserException;
import com.mahanko.gems.factory.GemBuilderFactory;
import com.mahanko.gems.parser.AbstractGemBuilder;
import com.mahanko.gems.parser.GemXmlParserType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.YearMonth;
import java.util.*;

public class GemParsersTest {
    private final String gemXmlPath = "src/test/resources/gems.xml";
    private final String emptyXmlPath = "emptyFile.xml";
    private final Set<GemEntity> gemsList = new HashSet<>( List.of(
            new PreciousStoneEntity("fb8cvb13r", "Opal",
                    new GemVisualParameters("Blue", 0),
                    YearMonth.parse("2020-07"),
                    new GemOrigin("Australia", "Coober Pedy"),
                    GemPreciousnessType.PRECIOUS,
                    0, 5),
            new PreciousStoneEntity("cv58vb13d", "Diamond",
                    new GemVisualParameters("Colorless", 100),
                    YearMonth.now(),
                    new GemOrigin("Republic of South Africa", "Kimberley"),
                    GemPreciousnessType.PRECIOUS,
                    15, 7),
            new JewelryOrnamentalStoneEntity("v7891vxf2", "Pearl",
                    new GemVisualParameters("Mother of Pearl", 0),
                    YearMonth.now(),
                    "Pinctada margaritifera"),
            new PreciousStoneEntity("fsgdf43r", "Malachite",
                    new GemVisualParameters("Green", 0),
                    YearMonth.now(),
                    new GemOrigin("United States", "Arizona"),
                    GemPreciousnessType.PRECIOUS,
                    0, 12),
            new PreciousStoneEntity("cvx21egvf", "Elbaite",
                    new GemVisualParameters("Green", 30),
                    YearMonth.now(),
                    new GemOrigin("Brazil", "Cruziero pegmatite"),
                    GemPreciousnessType.PRECIOUS,
                    12, 3),
            new JewelryOrnamentalStoneEntity("urigkf5551", "Amber",
                    new GemVisualParameters("Yellow", 20),
                    YearMonth.parse("1970-05"),
                    "Sequoia sempervirens"),
            new JewelryOrnamentalStoneEntity("fdxfc21ms", "Bone",
                    new GemVisualParameters("White", 0),
                    YearMonth.parse("1890-03"),
                    "Elephant"),
            new PreciousStoneEntity("rty54vkh56", "Quartz",
                    new GemVisualParameters("Rose", 70),
                    YearMonth.parse("1993-07"),
                    new GemOrigin("Spain", "Caldoveiro Peak"),
                    GemPreciousnessType.SEMI_PRECIOUS,
                    0, 6),
            new PreciousStoneEntity("jjfv25xbsd", "Quartz",
                    new GemVisualParameters("Brown-grey", 50),
                    YearMonth.now(),
                    new GemOrigin("Scotland", "Cairngorm Mountains"),
                    GemPreciousnessType.SEMI_PRECIOUS,
                    0, 13),
            new JewelryOrnamentalStoneEntity("sdgf231dfg", "Pearl",
                    new GemVisualParameters("Mother of Pearl", 0),
                    YearMonth.parse("2003-06"),
                    "Indian volute"),
            new PreciousStoneEntity("tsdfjb544d", "Ruby",
                    new GemVisualParameters("Purplish red", 40),
                    YearMonth.now(),
                    new GemOrigin("Shan State", "Mogok Valley"),
                    GemPreciousnessType.PRECIOUS,
                    0, 5),
            new PreciousStoneEntity("vbvNgf65NKL", "Pyrite",
                    new GemVisualParameters("Pale brass-yellow reflective", 0),
                    YearMonth.now(),
                    new GemOrigin("India", "Jharkhand"),
                    GemPreciousnessType.SEMI_PRECIOUS,
                    0, 8),
            new PreciousStoneEntity("hgDmFRkBgv65", "Aquamarine",
                    new GemVisualParameters("Pale-blue", 10),
                    YearMonth.parse("2006-05"),
                    new GemOrigin("Brazil", "Bahia"),
                    GemPreciousnessType.PRECIOUS,
                    0, 12),
            new PreciousStoneEntity("nbG15nFk", "Opal",
                    new GemVisualParameters("Green", 0),
                    YearMonth.parse("2000-07"),
                    new GemOrigin("Australia", "Coober Pedy"),
                    GemPreciousnessType.PRECIOUS,
                    0, 5),
            new PreciousStoneEntity("bDjgC415V", "Quartz",
                    new GemVisualParameters("Yellow", 70),
                    YearMonth.now(),
                    new GemOrigin("Scotland", "Cairngorm Mountains"),
                    GemPreciousnessType.SEMI_PRECIOUS,
                    0, 12),
            new PreciousStoneEntity("ZXjhf458dvj", "Adamite",
                    new GemVisualParameters("Honey-yellow", 10),
                    YearMonth.parse("2008-02"),
                    new GemOrigin("United States", "Sierra Nevada"),
                    GemPreciousnessType.PRECIOUS,
                    0, 10)));

    @Test(expectedExceptions = CustomXmlParserException.class)
    public void parseEmptyXmlSaxTest() throws CustomXmlParserException {
        AbstractGemBuilder saxBuilder = GemBuilderFactory.createGemBuilder(GemXmlParserType.SAX);
        saxBuilder.buildSetGems(emptyXmlPath);
    }

    @Test(expectedExceptions = CustomXmlParserException.class)
    public void parseEmptyXmlStaxTest() throws CustomXmlParserException {
        AbstractGemBuilder saxBuilder = GemBuilderFactory.createGemBuilder(GemXmlParserType.STAX);
        saxBuilder.buildSetGems(emptyXmlPath);
    }

    @Test(expectedExceptions = CustomXmlParserException.class)
    public void parseEmptyXmlDomTest() throws CustomXmlParserException {
        AbstractGemBuilder saxBuilder = GemBuilderFactory.createGemBuilder(GemXmlParserType.DOM);
        saxBuilder.buildSetGems(emptyXmlPath);
    }

    @Test
    public void parseXmlSaxTest() throws CustomXmlParserException {
        AbstractGemBuilder saxBuilder = GemBuilderFactory.createGemBuilder(GemXmlParserType.SAX);
        saxBuilder.buildSetGems(gemXmlPath);
        Set<GemEntity> actual = saxBuilder.getGems();
        Set<GemEntity> expected = new HashSet<>(gemsList);
        Assert.assertEqualsDeep(actual, expected, null);
    }

    @Test
    public void parseXmlStaxTest() throws CustomXmlParserException {
        AbstractGemBuilder saxBuilder = GemBuilderFactory.createGemBuilder(GemXmlParserType.STAX);
        saxBuilder.buildSetGems(gemXmlPath);
        Set<GemEntity> actual = saxBuilder.getGems();
        Set<GemEntity> expected = new HashSet<>(gemsList);
        Assert.assertEqualsDeep(actual, expected, null);
    }

    @Test
    public void parseXmlDomTest() throws CustomXmlParserException {
        AbstractGemBuilder saxBuilder = GemBuilderFactory.createGemBuilder(GemXmlParserType.DOM);
        saxBuilder.buildSetGems(gemXmlPath);
        Set<GemEntity> actual = saxBuilder.getGems();
        Set<GemEntity> expected = new HashSet<>(gemsList);
        Assert.assertEqualsDeep(actual, expected, null);
    }
}
