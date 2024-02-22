package org.vitaliistf.souvenirs.service;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.vitaliistf.souvenirs.model.Manufacturer;
import org.vitaliistf.souvenirs.model.Souvenir;
import org.vitaliistf.souvenirs.repository.ManufacturerRepository;
import org.vitaliistf.souvenirs.repository.SouvenirRepository;
import org.vitaliistf.souvenirs.repository.implementation.InMemoryManufacturerRepository;
import org.vitaliistf.souvenirs.repository.implementation.InMemorySouvenirRepository;
import org.vitaliistf.souvenirs.validation.ManufacturerValidator;
import org.vitaliistf.souvenirs.validation.ValidationResult;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ManufacturerServiceTest {

    private SouvenirRepository souvenirRepository;

    private ManufacturerRepository manufacturerRepository;

    private ManufacturerService service;


    @BeforeMethod
    public void setUp() {
        manufacturerRepository = InMemoryManufacturerRepository.getInstance();
        souvenirRepository = InMemorySouvenirRepository.getInstance();
        ManufacturerValidator manufacturerValidator = new ManufacturerValidator();
        service = new ManufacturerService(manufacturerValidator, souvenirRepository, manufacturerRepository);
    }

    @AfterMethod
    public void tearDown() {
        manufacturerRepository.getAll()
                .forEach(m -> service.deleteManufacturer(m.getId()));

        souvenirRepository.getAll()
                .forEach(m -> souvenirRepository.remove(m.getId()));
    }

    @Test(dataProviderClass = StaticDataProvider.class, dataProvider = "validManufacturers")
    public void testAddManufacturer(Manufacturer manufacturer) {
        ValidationResult result = service.addManufacturer(manufacturer);

        Assert.assertTrue(result.isSuccessful());
        Assert.assertTrue(manufacturerRepository.getAll().contains(manufacturer));
    }

    @Test(dataProviderClass = StaticDataProvider.class, dataProvider = "invalidManufacturers")
    public void testAddManufacturerInvalid(Manufacturer manufacturer) {
        ValidationResult result = service.addManufacturer(manufacturer);

        Assert.assertFalse(result.isSuccessful());
        Assert.assertEquals(result.getErrors().size(), 1);
        Assert.assertFalse(manufacturerRepository.getAll().contains(manufacturer));
    }

    @Test(dataProviderClass = StaticDataProvider.class, dataProvider = "validManufacturers")
    public void testUpdateManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.add(manufacturer);
        manufacturer.setId(1L);
        ValidationResult result = service.updateManufacturer(manufacturer);

        Assert.assertTrue(result.isSuccessful());
        Assert.assertTrue(manufacturerRepository.getAll().contains(manufacturer));
    }

    @Test(dataProviderClass = StaticDataProvider.class, dataProvider = "invalidManufacturers")
    public void testUpdateManufacturerInvalid(Manufacturer manufacturer) {
        ValidationResult result = service.updateManufacturer(manufacturer);

        Assert.assertFalse(result.isSuccessful());
        Assert.assertEquals(result.getErrors().size(), 1);
        Assert.assertFalse(manufacturerRepository.getAll().contains(manufacturer));
    }

    @Test
    public void testDeleteManufacturer() {
        long manufacturerId = 1L;
        Manufacturer manufacturer = new Manufacturer("Manufacturer A", "USA");
        manufacturerRepository.add(manufacturer);
        Souvenir souvenir = new Souvenir("Souvenir A", manufacturerId, LocalDate.now(), 10.0);
        souvenirRepository.add(souvenir);

        boolean result = service.deleteManufacturer(manufacturerId);

        Assert.assertTrue(result);
        Assert.assertFalse(manufacturerRepository.getAll().contains(manufacturer));
        Assert.assertTrue(souvenirRepository.getByManufacturerId(manufacturerId).isEmpty());
    }

    @Test
    public void testDeleteManufacturerNonExisting() {
        long manufacturerId = 1L;

        boolean result = service.deleteManufacturer(manufacturerId);

        Assert.assertFalse(result);
    }

    @Test
    public void testGetAllManufacturers() {
        Manufacturer manufacturer1 = new Manufacturer("Manufacturer A", "USA");
        Manufacturer manufacturer2 = new Manufacturer("Manufacturer B", "Canada");
        manufacturerRepository.add(manufacturer1);
        manufacturerRepository.add(manufacturer2);

        List<Manufacturer> result = service.getAllManufacturers();

        Assert.assertEquals(new HashSet<>(result), Set.of(manufacturer1, manufacturer2));
    }

    @Test(dataProviderClass = StaticDataProvider.class, dataProvider = "manufacturersWithSouvenirs")
    public void testGetManufacturersWithSouvenirs(Map<Manufacturer, List<Souvenir>> expected) {
        expected.forEach((key, value) -> {
            manufacturerRepository.add(key);
            value.forEach(souvenir -> souvenirRepository.add(souvenir));
        });

        Map<Manufacturer, List<Souvenir>> result = service.getManufacturersWithSouvenirs();

        Assert.assertEquals(result.size(), expected.size());

        Assert.assertTrue(expected.entrySet()
                .stream()
                .allMatch(entry -> result.containsKey(entry.getKey()) &&
                        result.get(entry.getKey()).containsAll(entry.getValue())));
    }

    @Test(dataProviderClass = StaticDataProvider.class, dataProvider = "manufacturersByMaxPrice")
    public void testGetManufacturersByMaxPrice(Set<Manufacturer> expectedManufacturers,
                                               double maxPrice, Map<Manufacturer, List<Souvenir>> data) {
        data.forEach((key, value) -> {
            manufacturerRepository.add(key);
            value.forEach(souvenir -> souvenirRepository.add(souvenir));
        });

        List<Manufacturer> result = service.getManufacturersByMaxPrice(maxPrice);

        Assert.assertEquals(new HashSet<>(result), expectedManufacturers);
    }

    @Test(dataProviderClass = StaticDataProvider.class, dataProvider = "manufacturersOfSouvenirByYear")
    public void testGetManufacturersOfSouvenirByYear(List<Manufacturer> expectedManufacturers, String souvenirName,
                                                     int year, Map<Manufacturer, List<Souvenir>> data) {
        data.forEach((key, value) -> {
            manufacturerRepository.add(key);
            value.forEach(souvenir -> souvenirRepository.add(souvenir));
        });

        List<Manufacturer> result = service.getManufacturersOfSouvenirByYear(souvenirName, year);

        Assert.assertEquals(new HashSet<>(result), new HashSet<>(expectedManufacturers));
    }

    @Test
    public void testGetManufacturersCountries() {
        List<Manufacturer> manufacturers = List.of(
                new Manufacturer("Manufacturer A", "USA"),
                new Manufacturer("Manufacturer B", "Canada"),
                new Manufacturer("Manufacturer C", "Germany"),
                new Manufacturer("Manufacturer D", "USA")
        );
       manufacturers.forEach(m -> manufacturerRepository.add(m));

        List<String> result = service.getManufacturersCountries();

        Assert.assertEquals(new HashSet<>(result), Set.of("USA", "Canada", "Germany"));
    }

}