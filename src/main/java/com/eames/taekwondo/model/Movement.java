package com.eames.taekwondo.model;

/**
 * This enumeration represents a TKD pattern movement.
 *
 * TODO: Need to add the JSON annotations to the Movement class attributes.
 */
public class Movement {

    /**
     * The movement's short description.
     */
    private String shortDescription;

    /**
     * The movement's full description.
     */
    private String fullDescription;

    /**
     * Constructor
     *
     * @param shortDescription the short description
     * @param fullDescription the long description
     */
    Movement(String shortDescription, String fullDescription) {
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
    }

    /**
     * Gets the movement's short description.
     *
     * @return the short description
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Gets the movement's full description.
     *
     * @return the full description
     */
    public String getFullDescription() {
        return fullDescription;
    }

    /*
     * Overridden {@link Object} operations
     */

    @Override
    public String toString() {
        return "Movement{" +
                "shortDescription='" + shortDescription + '\'' +
                '}';
    }
}
