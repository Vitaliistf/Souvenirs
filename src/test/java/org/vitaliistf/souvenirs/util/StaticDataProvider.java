package org.vitaliistf.souvenirs.util;

import org.testng.annotations.DataProvider;

import java.time.LocalDate;

public class StaticDataProvider {

    @DataProvider(name = "integerInput")
    public Object[][] integerInput() {
        return new Object[][]{
                {"10\n", 10},
                {"-5\n", -5},
                {"abc\n5\n", 5} // For invalid input followed by valid input
        };
    }

    @DataProvider(name = "longInput")
    public Object[][] longInput() {
        return new Object[][]{
                {"10000000000\n", 10000000000L},
                {"-5000000000\n", -5000000000L},
                {"xyz\n2000000000\n", 2000000000L} // For invalid input followed by valid input
        };
    }

    @DataProvider(name = "doubleInput")
    public Object[][] doubleInput() {
        return new Object[][]{
                {"3.14\n", 3.14},
                {"-2.5\n", -2.5},
                {"xyz\n2.78\n", 2.78} // For invalid input followed by valid input
        };
    }


    @DataProvider(name = "stringInput")
    public Object[][] stringInput() {
        return new Object[][]{
                {"Hello\n", "Hello"},
                {" Test String\n", "Test String"},
                {"\n", ""} // For empty input
        };
    }


    @DataProvider(name = "dateInput")
    public Object[][] dateInput() {
        return new Object[][]{
                {"15-02-2024\n", LocalDate.of(2024, 2, 15)},
                {"31-12-2023\n", LocalDate.of(2023, 12, 31)},
                {"abc\n01-01-2025\n", LocalDate.of(2025, 1, 1)} // For invalid input followed by valid input
        };
    }

}
