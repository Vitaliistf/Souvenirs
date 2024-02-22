package org.vitaliistf.souvenirs.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;

public class InputReaderTest {

    @Test(dataProviderClass = StaticDataProvider.class, dataProvider = "integerInput")
    public void testGetInt(String input, int expected) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        InputReader inputReader = new InputReader(inputStream);

        int result = inputReader.getInt("Enter an integer: ");
        inputReader.close();

        Assert.assertEquals(result, expected);
    }

    @Test(dataProviderClass = StaticDataProvider.class, dataProvider = "longInput")
    public void testGetLong(String input, long expected) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        InputReader inputReader = new InputReader(inputStream);

        long result = inputReader.getLong("Enter a long integer: ");
        inputReader.close();

        Assert.assertEquals(result, expected);
    }

    @Test(dataProviderClass = StaticDataProvider.class, dataProvider = "doubleInput")
    public void testGetDouble(String input, double expected) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        InputReader inputReader = new InputReader(inputStream);

        double result = inputReader.getDouble("Enter a double: ");
        inputReader.close();

        Assert.assertEquals(result, expected, 0.001); // delta for double comparison
    }

    @Test(dataProviderClass = StaticDataProvider.class, dataProvider = "stringInput")
    public void testGetString(String input, String expected) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        InputReader inputReader = new InputReader(inputStream);

        String result = inputReader.getString("Enter a string: ");
        inputReader.close();

        Assert.assertEquals(result, expected);
    }

    @Test(dataProviderClass = StaticDataProvider.class, dataProvider = "dateInput")
    public void testGetDate(String input, LocalDate expected) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        InputReader inputReader = new InputReader(inputStream);

        LocalDate result = inputReader.getDate("Enter a date (dd-mm-yyyy): ");
        inputReader.close();

        Assert.assertEquals(result, expected);
    }
}
