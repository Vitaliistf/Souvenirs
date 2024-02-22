package org.vitaliistf.souvenirs.menu.command;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.vitaliistf.souvenirs.menu.MenuOptions;

import java.util.Optional;

public class CommandPoolTest {
    private CommandPool commandPool;

    @BeforeClass
    public void setUp() {
        commandPool = new CommandPool(null, null, null, null);
    }

    @DataProvider(name = "data")
    public Object[][] data() {
        return new Object[][]{
                {MenuOptions.ADD_MANUFACTURER, true},
                {MenuOptions.UPDATE_MANUFACTURER, true},
                {MenuOptions.DELETE_MANUFACTURER, true},
                {MenuOptions.GET_ALL_MANUFACTURERS, true},
                {MenuOptions.ADD_SOUVENIR, true},
                {MenuOptions.UPDATE_SOUVENIR, true},
                {MenuOptions.DELETE_SOUVENIR, true},
                {MenuOptions.GET_ALL_SOUVENIRS, true},
                {MenuOptions.GET_SOUVENIRS_BY_MANUFACTURER, true},
                {MenuOptions.GET_SOUVENIRS_BY_COUNTRY, true},
                {MenuOptions.GET_MANUFACTURERS_BY_MAX_PRICE, true},
                {MenuOptions.GET_ALL_MANUFACTURERS_WITH_SOUVENIRS, true},
                {MenuOptions.GET_MANUFACTURERS_BY_SOUVENIR_AND_YEAR, true},
                {MenuOptions.GET_ALL_SOUVENIRS_BY_YEARS, true},
                {100, false},
                {-1, false},
                {50, false}
        };
    }

    @Test(dataProvider = "data")
    public void testGetCommand(int choice, boolean expected) {
        Optional<Command> commandOptional = commandPool.getCommand(choice);
        Assert.assertEquals(expected, commandOptional.isPresent());
    }

}

