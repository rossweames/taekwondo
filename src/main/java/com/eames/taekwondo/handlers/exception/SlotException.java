package com.eames.taekwondo.handlers.exception;

/**
 * This abstract exception is the base class for all skill slot-based exceptions.
 */
public abstract class SlotException extends TKDException {

    // The slot name
    private final String slotName;

    /**
     * Constructor
     *
     * @param slotName the slot name
     */
    public SlotException(String slotName) {
        this.slotName = slotName;
    }

    public String getSlotName() {
        return slotName;
    }
}
