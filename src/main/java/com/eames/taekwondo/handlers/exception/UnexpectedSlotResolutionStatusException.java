package com.eames.taekwondo.handlers.exception;

/**
 * This exception is thrown by the intents whenever they encounter an unexpected slot resolution status.
 */
public class UnexpectedSlotResolutionStatusException extends SlotException {

    // The status code
    private final String statusCode;

    /**
     * Constructor
     *
     * @param slotName the slot name
     * @param statusCode the status code
     */
    public UnexpectedSlotResolutionStatusException(String slotName, String statusCode) {

        // Call the base class constructor.
        super(slotName);

        // Set the status code.
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
