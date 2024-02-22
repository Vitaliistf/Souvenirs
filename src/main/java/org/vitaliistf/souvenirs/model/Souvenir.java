package org.vitaliistf.souvenirs.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a souvenir.
 * (Usage of "Prototype" design pattern.)
 */
@Data
public class Souvenir implements Serializable, Cloneable {

    @Serial
    private static final long serialVersionUID = 282206854078300717L;

    @EqualsAndHashCode.Exclude
    private long id;

    private String name;

    private long manufacturerId;

    @EqualsAndHashCode.Exclude
    private LocalDate productionDate;

    @EqualsAndHashCode.Exclude
    private double price;

    /**
     * Constructs a souvenir with the specified name, manufacturer ID, production date, and price.
     *
     * @param name            The name of the souvenir.
     * @param manufacturerId  The ID of the manufacturer of the souvenir.
     * @param productionDate  The production date of the souvenir.
     * @param price           The price of the souvenir.
     */
    public Souvenir(String name, long manufacturerId, LocalDate productionDate, double price) {
        this.name = name;
        this.manufacturerId = manufacturerId;
        this.productionDate = productionDate;
        this.price = price;
    }

    /**
     * Returns a clone of this Souvenir instance.
     *
     * @return A clone of this Souvenir instance.
     */
    @Override
    public Souvenir clone() {
        try {
            return (Souvenir) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
