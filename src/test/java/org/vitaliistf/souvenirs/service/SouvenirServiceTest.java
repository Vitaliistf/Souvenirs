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
import org.vitaliistf.souvenirs.validation.SouvenirValidator;
import org.vitaliistf.souvenirs.validation.ValidationResult;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SouvenirServiceTest {

    private SouvenirRepository souvenirRepository;

    private ManufacturerRepository manufacturerRepository;

    private SouvenirService service;

    @BeforeMethod
    public void setUp() {
        manufacturerRepository = InMemoryManufacturerRepository.getInstance();
        souvenirRepository = InMemorySouvenirRepository.getInstance();
        SouvenirValidator souvenirValidator = new SouvenirValidator();
        service = new SouvenirService(souvenirValidator, souvenirRepository, manufacturerRepository);
    }

    @AfterMethod
    public void tearDown() {
        manufacturerRepository.getAll()
                .forEach(m -> manufacturerRepository.remove(m.getId()));

        souvenirRepository.getAll()
                .forEach(s -> souvenirRepository.remove(s.getId()));
    }

    @Test(dataProviderClass = StaticDataProvider.class, dataProvider = "validSouvenirs")
    public void testAddSouvenir(Souvenir souvenir) {
        manufacturerRepository.add(new Manufacturer("Manufacturer", "Ukraine"));
        ValidationResult result = service.addSouvenir(souvenir);

        Assert.assertTrue(result.isSuccessful());
        Assert.assertTrue(souvenirRepository.getAll().contains(souvenir));
    }

    @Test(dataProviderClass = StaticDataProvider.class, dataProvider = "invalidSouvenirs")
    public void testAddSouvenirInvalid(Souvenir souvenir) {
        ValidationResult result = service.addSouvenir(souvenir);

        Assert.assertFalse(result.isSuccessful());
        Assert.assertEquals(result.getErrors().size(), 1);
        Assert.assertFalse(souvenirRepository.getAll().contains(souvenir));
    }

    @Test(dataProviderClass = StaticDataProvider.class, dataProvider = "validSouvenirs")
    public void testUpdateSouvenir(Souvenir souvenir) {
        manufacturerRepository.add(new Manufacturer("Manufacturer", "Ukraine"));
        souvenirRepository.add(souvenir);
        souvenir.setId(1);
        souvenir.setPrice(25.0);
        ValidationResult result = service.updateSouvenir(souvenir);

        Assert.assertTrue(result.isSuccessful());
        Assert.assertTrue(souvenirRepository.getAll().contains(souvenir));
    }

    @Test(dataProviderClass = StaticDataProvider.class, dataProvider = "invalidSouvenirs")
    public void testUpdateSouvenirInvalid(Souvenir souvenir) {
        ValidationResult result = service.updateSouvenir(souvenir);

        Assert.assertFalse(result.isSuccessful());
        Assert.assertEquals(result.getErrors().size(), 1);
        Assert.assertFalse(souvenirRepository.getAll().contains(souvenir));
    }

    @Test
    public void testDeleteSouvenir() {
        long souvenirId = 1L;
        Souvenir souvenir = new Souvenir("Souvenir A", 1L, LocalDate.now(), 10.0);
        souvenirRepository.add(souvenir);

        boolean result = service.deleteSouvenir(souvenirId);

        Assert.assertTrue(result);
        Assert.assertFalse(souvenirRepository.getAll().contains(souvenir));
    }

    @Test
    public void testDeleteSouvenirNonExisting() {
        long souvenirId = 1L;

        boolean result = service.deleteSouvenir(souvenirId);

        Assert.assertFalse(result);
    }

    @Test
    public void testGetAllSouvenirs() {
        Souvenir souvenir1 = new Souvenir("Souvenir A", 1L, LocalDate.now(), 10.0);
        Souvenir souvenir2 = new Souvenir("Souvenir B", 2L, LocalDate.now(), 15.0);
        souvenirRepository.add(souvenir1);
        souvenirRepository.add(souvenir2);

        List<Souvenir> result = service.getAllSouvenirs();

        Assert.assertEquals(new HashSet<>(result), Set.of(souvenir1, souvenir2));
    }

    @Test
    public void testGetSouvenirsByManufacturer() {
        long manufacturerId = 1L;
        Souvenir souvenir1 = new Souvenir("Souvenir A", manufacturerId, LocalDate.now(), 10.0);
        Souvenir souvenir2 = new Souvenir("Souvenir B", manufacturerId, LocalDate.now(), 15.0);
        souvenirRepository.add(souvenir1);
        souvenirRepository.add(souvenir2);

        List<Souvenir> result = service.getSouvenirsByManufacturer(manufacturerId);

        Assert.assertEquals(new HashSet<>(result), Set.of(souvenir1, souvenir2));
    }

    @Test
    public void testGetSouvenirsByCountry() {
        Manufacturer manufacturer1 = new Manufacturer("Manufacturer A", "USA");
        Manufacturer manufacturer2 = new Manufacturer("Manufacturer B", "Canada");
        manufacturerRepository.add(manufacturer1);
        manufacturerRepository.add(manufacturer2);

        Souvenir souvenir1 = new Souvenir("Souvenir A", 1L, LocalDate.now(), 10.0);
        Souvenir souvenir2 = new Souvenir("Souvenir B", 2L, LocalDate.now(), 15.0);
        souvenirRepository.add(souvenir1);
        souvenirRepository.add(souvenir2);

        List<Souvenir> result = service.getSouvenirsByCountry("USA");

        Assert.assertEquals(new HashSet<>(result), Set.of(souvenir1));
    }

    @Test
    public void testGetSouvenirsByYear() {
        Souvenir souvenir1 = new Souvenir("Souvenir A", 1L, LocalDate.of(2022, 1, 1), 10.0);
        Souvenir souvenir2 = new Souvenir("Souvenir B", 1L, LocalDate.of(2023, 1, 1), 15.0);
        souvenirRepository.add(souvenir1);
        souvenirRepository.add(souvenir2);

        Map<Integer, List<Souvenir>> result = service.getSouvenirsByYear();

        Assert.assertEquals(result.size(), 2);
    }
}