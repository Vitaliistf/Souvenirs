package org.vitaliistf.souvenirs.service;

import org.testng.annotations.DataProvider;
import org.vitaliistf.souvenirs.model.Manufacturer;
import org.vitaliistf.souvenirs.model.Souvenir;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StaticDataProvider {

    @DataProvider(name = "validSouvenirs")
    public static Object[][] validSouvenirs() {
        return new Object[][]{
                {new Souvenir("Souvenir A", 1L, LocalDate.now(), 10.0)},
                {new Souvenir("Souvenir B", 1L, LocalDate.now(), 15.0)},
                {new Souvenir("Souvenir C", 1L, LocalDate.now(), 20.0)}
        };
    }

    @DataProvider(name = "invalidSouvenirs")
    public static Object[][] invalidSouvenirs() {
        return new Object[][]{
                {new Souvenir("", 1L, LocalDate.now(), 10.0)},
                {new Souvenir("Souvenir B", 0, LocalDate.now(), 15.0)}
        };
    }

    @DataProvider(name = "validManufacturers")
    public static Object[][] validManufacturers() {
        return new Object[][]{
                {new Manufacturer("Manufacturer A", "USA")},
                {new Manufacturer("Manufacturer B", "Canada")},
                {new Manufacturer("Manufacturer C", "Germany")}
        };
    }

    @DataProvider(name = "invalidManufacturers")
    public static Object[][] invalidManufacturers() {
        return new Object[][]{
                {new Manufacturer("", "Canada")},
                {new Manufacturer("Manufacturer C", "")}
        };
    }

    @DataProvider(name = "manufacturersWithSouvenirs")
    public static Object[][] manufacturersWithSouvenirs() {
        Manufacturer manufacturer1 = new Manufacturer("Manufacturer A", "USA");
        manufacturer1.setId(1);
        Manufacturer manufacturer2 = new Manufacturer("Manufacturer B", "Canada");
        manufacturer2.setId(2);
        Souvenir souvenir1 = new Souvenir("Souvenir 1", 1L, LocalDate.now(), 10.0);
        souvenir1.setId(1);
        Souvenir souvenir2 = new Souvenir("Souvenir 2", 1L, LocalDate.now(), 15.0);
        souvenir2.setId(2);
        Souvenir souvenir3 = new Souvenir("Souvenir 3", 2L, LocalDate.now(), 20.0);
        souvenir3.setId(3);

        return new Object[][]{
                {Map.of(manufacturer1, List.of(souvenir1, souvenir2), manufacturer2, List.of(souvenir3))},
                {Map.of(manufacturer1, List.of(), manufacturer2, List.of())}
        };
    }

    @DataProvider(name = "manufacturersByMaxPrice")
    public static Object[][] manufacturersByMaxPrice() {
        Manufacturer manufacturer1 = new Manufacturer("Manufacturer A", "USA");
        manufacturer1.setId(1);
        Manufacturer manufacturer2 = new Manufacturer("Manufacturer B", "Canada");
        manufacturer2.setId(2);
        Souvenir souvenir1 = new Souvenir("Souvenir 1", 1L, LocalDate.now(), 10.0);
        souvenir1.setId(1);
        Souvenir souvenir2 = new Souvenir("Souvenir 2", 1L, LocalDate.now(), 15.0);
        souvenir2.setId(2);
        Souvenir souvenir3 = new Souvenir("Souvenir 3", 2L, LocalDate.now(), 20.0);
        souvenir3.setId(3);

        LinkedHashMap<Manufacturer, List<Souvenir>> map = new LinkedHashMap<>(Map.of(
                manufacturer1, List.of(souvenir1, souvenir2), manufacturer2, List.of(souvenir3)
        ));

        return new Object[][]{
                { Set.of(manufacturer1), 15.0, map },
                { Set.of(manufacturer1, manufacturer2), 20.0, map },
                { Set.of(), 5.0, map }
        };
    }


    @DataProvider(name = "manufacturersOfSouvenirByYear")
    public static Object[][] manufacturersOfSouvenirByYear() {
        Manufacturer manufacturer1 = new Manufacturer("Manufacturer A", "USA");
        manufacturer1.setId(1);
        Manufacturer manufacturer2 = new Manufacturer("Manufacturer B", "Canada");
        manufacturer2.setId(2);
        Souvenir souvenir1 = new Souvenir("Souvenir 1", 1L, LocalDate.of(2022, 1, 1), 10.0);
        souvenir1.setId(1);
        Souvenir souvenir2 = new Souvenir("Souvenir 2", 1L, LocalDate.of(2023, 1, 1), 15.0);
        souvenir2.setId(2);
        Souvenir souvenir3 = new Souvenir("Souvenir 1", 2L, LocalDate.of(2022, 1, 1), 20.0);
        souvenir3.setId(3);

        LinkedHashMap<Manufacturer, List<Souvenir>> map = new LinkedHashMap<>(Map.of(
                manufacturer1, List.of(souvenir1, souvenir2), manufacturer2, List.of(souvenir3)
        ));
        return new Object[][]{
                { List.of(manufacturer1, manufacturer2), "Souvenir 1", 2022, map },
                { List.of(manufacturer1), "Souvenir 2", 2023, map },
                { List.of(), "Souvenir 2", 2022, map }
        };
    }

}
