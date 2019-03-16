package com.eames.taekwondo.model;

/**
 * This enumeration represents a TKD pattern movement.
 *
 * TODO: Need to add the JSON annotations to the Movement class attributes.
 */
public class Movement {

    /**
     * The movement's description.
     */
    private String description;

    /**
     * Constructor
     *
     * @param description the long description
     */
    Movement(String description) {
        this.description = description;
    }

    /**
     * Gets the movement's full description.
     *
     * @return the full description
     */
    public String getDescription() {
        return description;
    }

    /*
     * Overridden {@link Object} operations
     */

    @Override
    public String toString() {
        return "Movement{" +
                "description='" + description + '\'' +
                '}';
    }
}
