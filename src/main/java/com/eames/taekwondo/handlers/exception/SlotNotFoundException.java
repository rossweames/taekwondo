package com.eames.taekwondo.handlers.exception;

/**
 * This exception is thrown by the intents whenever they encounter a request which contains a skill configuration error.
 */
public class SlotNotFoundException extends SlotException {

    /**
     * Constructor
     *
     * @param slotName the slot name
     */
    public SlotNotFoundException(String slotName) {
        super(slotName);
    }
}
