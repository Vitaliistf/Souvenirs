package org.vitaliistf.souvenirs.repository.implementation;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.vitaliistf.souvenirs.model.Souvenir;

import java.time.LocalDate;
import java.util.*;

public class InMemorySouvenirRepositoryTest {

    private InMemorySouvenirRepository repository;

    @BeforeMethod
    public void setUp() {
        repository = InMemorySouvenirRepository.getInstance();
    }

    @AfterMethod
    public void tearDown() {
        repository.getAll().stream()
                .map(Souvenir::getId)
                .forEach(id -> repository.remove(id));
    }

    @DataProvider(name = "souvenirInput")
    public Object[][] souvenirInput() {
        return new Object[][]{
                { new Souvenir("Souvenir1", 1L, LocalDate.now(), 10.0) }
        };
    }

    @Test(dataProvider = "souvenirInput")
    public void testAdd(Souvenir souvenir) {
        boolean result = repository.add(souvenir);

        Assert.assertTrue(result);
        Assert.assertTrue(repository.getAll().contains(souvenir));
    }

    @Test(dataProvider = "souvenirInput")
    public void testAddDuplicate(Souvenir souvenir) {
        repository.add(souvenir);

        boolean result = repository.add(souvenir);

        Assert.assertFalse(result);
        Assert.assertEquals(repository.getAll().size(), 1);
    }

    @Test(dataProvider = "souvenirInput")
    public void testUpdate(Souvenir souvenir) {
        repository.add(souvenir);
        souvenir.setId(1);
        boolean result = repository.update(souvenir);

        Assert.assertTrue(result);
        Assert.assertTrue(repository.getAll().contains(souvenir));
    }

    @Test(dataProvider = "souvenirInput")
    public void testUpdateNonExisting(Souvenir souvenir) {
        boolean result = repository.update(souvenir);

        Assert.assertFalse(result);
        Assert.assertTrue(repository.getAll().isEmpty());
    }

    @Test
    public void testUpdateDuplicate() {
        Souvenir souvenir1 = new Souvenir("Souvenir1", 1L, LocalDate.now(), 10.0);
        Souvenir souvenir2 = new Souvenir("Souvenir2", 1L, LocalDate.now(), 9.0);
        repository.add(souvenir1);
        repository.add(souvenir2);

        souvenir1.setId(1);
        souvenir1.setName("Souvenir2");
        souvenir1.setManufacturerId(1L);

        boolean result = repository.update(souvenir1);

        Assert.assertFalse(result);
        Assert.assertEquals(repository.getAll().size(), 2);
    }

    @Test(dataProvider = "souvenirInput")
    public void testRemove(Souvenir souvenir) {
        repository.add(souvenir);

        boolean result = repository.remove(1L);

        Assert.assertTrue(result);
        Assert.assertFalse(repository.getAll().contains(souvenir));
    }

    @Test
    public void testRemoveNonExisting() {
        boolean result = repository.remove(1L);

        Assert.assertFalse(result);
        Assert.assertTrue(repository.getAll().isEmpty());
    }

    @Test(dataProvider = "souvenirInput")
    public void testGetById(Souvenir souvenir) {
        repository.add(souvenir);

        Optional<Souvenir> result = repository.getById(1L);

        Assert.assertEquals(result, Optional.of(souvenir));
    }

    @Test
    public void testGetByIdNonExisting() {
        Optional<Souvenir> result = repository.getById(1L);

        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void testGetAll() {
        Set<Souvenir> expected = new HashSet<>(Arrays.asList(
                new Souvenir("Souvenir1", 1L, LocalDate.now(), 10.0),
                new Souvenir("Souvenir2", 1L, LocalDate.now(), 9.0)
        ));
        for (Souvenir souvenir : expected) {
            repository.add(souvenir);
        }

        List<Souvenir> result = repository.getAll();

        Assert.assertEquals(new HashSet<>(result), expected);
    }

    @Test
    public void testGetByName() {
        Souvenir souvenir1 = new Souvenir("Souvenir1", 1L, LocalDate.now(), 10.0);
        Souvenir souvenir2 = new Souvenir("Souvenir2", 1L, LocalDate.now(), 9.0);
        repository.add(souvenir1);
        repository.add(souvenir2);

        List<Souvenir> result = repository.getByName("Souvenir1");

        Assert.assertEquals(result.size(), 1);
        Assert.assertTrue(result.contains(souvenir1));
    }

    @Test
    public void testGetByNameNonExisting() {
        Assert.assertTrue(repository.getByName("NonExistingName").isEmpty());
    }

    @Test
    public void testGetByManufacturerId() {
        Souvenir souvenir1 = new Souvenir("Souvenir1", 1L, LocalDate.now(), 10.0);
        Souvenir souvenir2 = new Souvenir("Souvenir2", 2L, LocalDate.now(), 9.0);
        repository.add(souvenir1);
        repository.add(souvenir2);

        List<Souvenir> result = repository.getByManufacturerId(2L);

        Assert.assertEquals(result.size(), 1);
        Assert.assertTrue(result.contains(souvenir2));
    }

    @Test
    public void testGetByManufacturerIdNonExisting() {
        Assert.assertTrue(repository.getByManufacturerId(1L).isEmpty());
    }
}

