package org.vitaliistf.souvenirs.model;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ManufacturerTest {

    @Test
    public void testEqualsSameObject() {
        Manufacturer manufacturer1 = new Manufacturer("ABC Inc.", "USA");

        Assert.assertEquals(manufacturer1, manufacturer1);
    }

    @Test
    public void testEqualsEqualObjects() {
        Manufacturer manufacturer1 = new Manufacturer("ABC Inc.", "USA");
        Manufacturer manufacturer2 = new Manufacturer("ABC Inc.", "USA");

        Assert.assertEquals(manufacturer1, manufacturer2);
    }

    @Test
    public void testEqualsDifferentObjects() {
        Manufacturer manufacturer1 = new Manufacturer("ABC Inc.", "USA");
        Manufacturer manufacturer2 = new Manufacturer("XYZ Ltd.", "UK");

        Assert.assertNotEquals(manufacturer1, manufacturer2);
    }

    @Test
    public void testHashCodeConsistency() {
        Manufacturer manufacturer1 = new Manufacturer("ABC Inc.", "USA");
        Manufacturer manufacturer2 = new Manufacturer("ABC Inc.", "USA");

        Assert.assertEquals(manufacturer1.hashCode(), manufacturer2.hashCode());
    }
}

