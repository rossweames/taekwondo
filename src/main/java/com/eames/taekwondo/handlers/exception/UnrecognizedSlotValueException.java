package com.eames.taekwondo.handlers.exception;

/**
 * This exception is thrown by the intents whenever they encounter a slot value which is not one of the skill's
 * predefined values.
 */
public class UnrecognizedSlotValueException extends SlotException {

    // The slot value
    private final String slotValue;

    /**
     * Constructor
     *
     * @param slotName the slot name
     * @param slotValue the slot value
     */
    public UnrecognizedSlotValueException(String slotName, String slotValue) {

        // Call the base class constructor.
        super(slotName);

        // Set the value.
        this.slotValue = slotValue;
    }

    public String getSlotValue() {
        return slotValue;
    }
}
