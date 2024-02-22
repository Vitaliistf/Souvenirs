package org.vitaliistf.souvenirs.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a manufacturer of souvenirs.
 * (Usage of "Prototype" design pattern.)
 */
@Data
public class Manufacturer implements Serializable, Cloneable {

    @Serial
    private static final long serialVersionUID = -7231515305001224829L;

    @EqualsAndHashCode.Exclude
    private long id;

    private String name;

    @EqualsAndHashCode.Exclude
    private String country;

    /**
     * Constructs a manufacturer with the specified name and country.
     *
     * @param name    The name of the manufacturer.
     * @param country The country of the manufacturer.
     */
    public Manufacturer(String name, String country) {
        this.name = name;
        this.country = country;
    }

    /**
     * Builds a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Manufacturer: " + name + "(" + country + ")" + ", ID: " + id;
    }

    /**
     * Returns a clone of this Manufacturer instance.
     *
     * @return A clone of this Manufacturer instance.
     */
    @Override
    public Manufacturer clone() {
        try {
            return (Manufacturer) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
