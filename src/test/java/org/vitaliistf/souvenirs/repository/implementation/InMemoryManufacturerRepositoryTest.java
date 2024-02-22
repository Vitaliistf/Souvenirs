package org.vitaliistf.souvenirs.repository.implementation;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.vitaliistf.souvenirs.model.Manufacturer;

import java.util.*;

public class InMemoryManufacturerRepositoryTest {

    private InMemoryManufacturerRepository repository;

    @BeforeMethod
    public void setUp() {
        repository = InMemoryManufacturerRepository.getInstance();
    }

    @AfterMethod
    public void tearDown() {
        repository.getAll().stream()
                .map(Manufacturer::getId)
                .forEach(id -> repository.remove(id));
    }

    @DataProvider(name = "manufacturerInput")
    public Object[][] manufacturerInput() {
        return new Object[][] {
                { new Manufacturer("Manufacturer1", "Country1") }
        };
    }

    @Test(dataProvider = "manufacturerInput")
    public void testAdd(Manufacturer manufacturer) {
        boolean result = repository.add(manufacturer);

        Assert.assertTrue(result);
        Assert.assertTrue(repository.getAll().contains(manufacturer));
    }

    @Test(dataProvider = "manufacturerInput")
    public void testAddDuplicate(Manufacturer manufacturer) {
        repository.add(manufacturer);

        boolean result = repository.add(manufacturer);

        Assert.assertFalse(result);
        Assert.assertEquals(repository.getAll().size(), 1);
    }

    @Test(dataProvider = "manufacturerInput")
    public void testUpdate(Manufacturer manufacturer) {
        repository.add(manufacturer);
        manufacturer.setId(1);
        boolean result = repository.update(manufacturer);

        Assert.assertTrue(result);
        Assert.assertTrue(repository.getAll().contains(manufacturer));
    }

    @Test(dataProvider = "manufacturerInput")
    public void testUpdateNonExisting(Manufacturer manufacturer) {
        boolean result = repository.update(manufacturer);

        Assert.assertFalse(result);
        Assert.assertTrue(repository.getAll().isEmpty());
    }

    @Test
    public void testUpdateDuplicate() {
        Manufacturer manufacturer1 = new Manufacturer("Manufacturer1", "Country1");
        Manufacturer manufacturer2 = new Manufacturer("Manufacturer2", "Country2");
        repository.add(manufacturer1);
        repository.add(manufacturer2);
        manufacturer1.setId(1);
        manufacturer1.setName("Manufacturer2");
        boolean result = repository.update(manufacturer1);

        Assert.assertFalse(result);
        Assert.assertTrue(repository.getAll().size() == 2);
    }

    @Test(dataProvider = "manufacturerInput")
    public void testRemove(Manufacturer manufacturer) {
        repository.add(manufacturer);

        boolean result = repository.remove(1L);

        Assert.assertTrue(result);
        Assert.assertFalse(repository.getAll().contains(manufacturer));
    }


    @Test
    public void testRemoveNonExisting() {
        boolean result = repository.remove(1L);

        Assert.assertFalse(result);
        Assert.assertTrue(repository.getAll().isEmpty());
    }


    @Test(dataProvider = "manufacturerInput")
    public void testGetById(Manufacturer manufacturer) {
        repository.add(manufacturer);

        Optional<Manufacturer> result = repository.getById(1L);

        Assert.assertEquals(result, Optional.of(manufacturer));
    }

    @Test
    public void testGetByIdNonExisting() {
        Optional<Manufacturer> result = repository.getById(1L);

        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void testGetAll() {
        Set<Manufacturer> expected = new HashSet<>(Arrays.asList(
                new Manufacturer("Manufacturer1", "Country1"),
                new Manufacturer("Manufacturer2", "Country2")
        ));
        for (Manufacturer manufacturer : expected) {
            repository.add(manufacturer);
        }

        List<Manufacturer> result = repository.getAll();

        Assert.assertEquals(new HashSet<>(result), expected);
    }

    @Test
    public void testGetByCountry() {
        Manufacturer manufacturer1 = new Manufacturer("Manufacturer1", "Country1");
        Manufacturer manufacturer2 = new Manufacturer("Manufacturer2", "Country2");
        repository.add(manufacturer1);
        repository.add(manufacturer2);

        List<Manufacturer> result = repository.getByCountry("Country1");

        Assert.assertEquals(result.size(), 1);
        Assert.assertTrue(result.contains(manufacturer1));
    }

    @Test
    public void testGetByCountryNonExisting() {
        List<Manufacturer> result = repository.getByCountry("NonExistingCountry");

        Assert.assertTrue(result.isEmpty());
    }
}

