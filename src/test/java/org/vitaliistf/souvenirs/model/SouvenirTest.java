package org.vitaliistf.souvenirs.model;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class SouvenirTest {

    @Test
    public void testEqualsSameObject() {
        Souvenir souvenir1 = new Souvenir("Keychain", 1, LocalDate.of(2023, 5, 15), 10.99);

        Assert.assertEquals(souvenir1, souvenir1);
    }

    @Test
    public void testEqualsEqualObjects() {
        Souvenir souvenir1 = new Souvenir("Keychain", 1, LocalDate.of(2023, 5, 15), 10.99);
        Souvenir souvenir2 = new Souvenir("Keychain", 1, LocalDate.of(2023, 5, 15), 10.99);

        Assert.assertEquals(souvenir1, souvenir2);
    }

    @Test
    public void testEqualsDifferentObjects() {
        Souvenir souvenir1 = new Souvenir("Keychain", 1, LocalDate.of(2023, 5, 15), 10.99);
        Souvenir souvenir2 = new Souvenir("Mug", 2, LocalDate.of(2023, 5, 15), 12.49);

        Assert.assertNotEquals(souvenir1, souvenir2);
    }

    @Test
    public void testHashCodeConsistency() {
        Souvenir souvenir1 = new Souvenir("Keychain", 1, LocalDate.of(2023, 5, 15), 10.99);
        Souvenir souvenir2 = new Souvenir("Keychain", 1, LocalDate.of(2023, 5, 15), 10.99);

        Assert.assertEquals(souvenir1.hashCode(), souvenir2.hashCode());
    }
}

