package com.eames.taekwondo.handlers.exception;

/**
 * This exception is thrown by the intents whenever a required a slot value is missing.
 */
public class MissingSlotValueException extends SlotException {

    /**
     * Constructor
     *
     * @param slotName the slot name
     */
    public MissingSlotValueException(String slotName) {
        super(slotName);
    }
}
