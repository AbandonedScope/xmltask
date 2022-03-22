package test.mahanko.gems.parser;

import com.mahanko.gems.entity.*;
import com.mahanko.gems.exception.CustomXmlParserException;
import com.mahanko.gems.factory.GemBuilderFactory;
import com.mahanko.gems.parser.AbstractGemBuilder;
import com.mahanko.gems.parser.GemXmlParserType;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.YearMonth;
import java.util.*;

public class GemParsersTest {
    private final String gemXmlPath = "src/test/resources/gems.xml";
    private final String emptyXmlPath = "emptyFile.xml";
    private final List firstGemsList = Arrays.asList(
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
                    "Pinctada margaritifera"));

    @DataProvider
    public Object[][] gemLocalData() {
        return new Object[][]{
                {GemXmlParserType.SAX, gemXmlPath, firstGemsList},
                {GemXmlParserType.DOM, gemXmlPath, firstGemsList},
                {GemXmlParserType.STAX, gemXmlPath, firstGemsList},
        };
    }

    @DataProvider
    public Object[][] gemEmpty() {
        return new Object[][]{
                {GemXmlParserType.SAX, emptyXmlPath},
                {GemXmlParserType.DOM, emptyXmlPath},
                {GemXmlParserType.STAX, emptyXmlPath}
        };
    }

    @Test(expectedExceptions = CustomXmlParserException.class, dataProvider = "gemEmpty")
    public void parseEmptyXmlTest(GemXmlParserType parserType, String path) throws CustomXmlParserException {
        AbstractGemBuilder saxBuilder = GemBuilderFactory.createGemBuilder(parserType);
        saxBuilder.buildSetGems(path);
    }

    @Test(dataProvider = "gemLocalData")
    public void parseXmlTest(GemXmlParserType parserType, String path, List gemList) throws CustomXmlParserException {
        AbstractGemBuilder saxBuilder = GemBuilderFactory.createGemBuilder(parserType);
        saxBuilder.buildSetGems(path);
        Set<GemEntity> actual = saxBuilder.getGems();
        Set<GemEntity> expected = new HashSet<GemEntity>(gemList);
        Assert.assertEqualsDeep(actual, expected, null);
    }
}
