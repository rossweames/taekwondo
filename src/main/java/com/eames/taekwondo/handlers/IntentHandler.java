package com.eames.taekwondo.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.model.slu.entityresolution.*;
import com.eames.taekwondo.handlers.exception.MissingSlotValueException;
import com.eames.taekwondo.handlers.exception.SlotNotFoundException;
import com.eames.taekwondo.handlers.exception.UnexpectedSlotResolutionStatusException;
import com.eames.taekwondo.handlers.exception.UnrecognizedSlotValueException;

import java.util.List;
import java.util.Map;

/**
 * This abstract class is the base class for all intent handlers for this skill.
 *
 * TODO: Need unit tests for this class.
 */
public abstract class IntentHandler implements RequestHandler {

    /**
     * Determines whether this intent can handle the request.
     * This is the default implementation that always returns {@code false}.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return {@code True} if this intent can handle the given request, {@code false} if not
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return false;
    }

    /**
     * Gets the request input's {@link Intent}.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return the intent
     */
    protected Intent getIntent(HandlerInput input) {

        // Get the intent from the request input.
        return getRequest(input).getIntent();
    }

    /**
     * Gets the name of the request class.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return the name of the class
     */
    protected String getRequestClassName(HandlerInput input) {

        return input.getRequestEnvelope().getRequest().getClass().getSimpleName();
    }

    /**
     * Gets the request input's {@link IntentRequest}.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return the intent request
     */
    protected IntentRequest getRequest(HandlerInput input) {

        // Get the dialog request from the request input.
        Request request = input.getRequestEnvelope().getRequest();
        return (IntentRequest) request;
    }

    /**
     * Gets the request input's {@link DialogState}.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return the dialog state
     */
    protected DialogState getDialogState(HandlerInput input) {

        // Get the dialog state from the request input.
        return getRequest(input).getDialogState();
    }

    /**
     * Gets the value of the given slot from the intent.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @param slotName the name of the slot to retrieve
     * @return the slot's value
     * @throws SlotNotFoundException if the input json has the wrong format due to a skill configuration error
     * @throws MissingSlotValueException if no value has been included in the input
     * @throws UnrecognizedSlotValueException if the value provided does not match any known values
     * @throws UnexpectedSlotResolutionStatusException if the resolution contains an unexpected status
     */
    protected String getSlotValue(HandlerInput input, String slotName)
            throws SlotNotFoundException, MissingSlotValueException, UnrecognizedSlotValueException,
            UnexpectedSlotResolutionStatusException {

        // Get the slots collection from the intent.
        Map<String, Slot> slots = getIntent(input).getSlots();

        // Get the desired slot.
        Slot patternSlot = slots.get(slotName);

        // The skill is not configured with a pattern slot.
        if (patternSlot == null) {

            // Throw an exception.
            throw new SlotNotFoundException(slotName);
        }

        // Get the slot resolution collection.
        Resolutions slotResolutions = patternSlot.getResolutions();

        // No resolutions are available.
        // (The slot value was not provided by the user.)
        if (slotResolutions == null) {

            // Throw an exception.
            throw new MissingSlotValueException(slotName);
        }

        // Get the resolution status code from the first (and only) resolution.
        List<Resolution> resolutions = slotResolutions.getResolutionsPerAuthority();
        Resolution resolution = resolutions.get(0);
        Status status = resolution.getStatus();
        StatusCode statusCode = status.getCode();

        // The pattern name provided did not match any of the json defined in the skill.
        if (statusCode == StatusCode.ER_SUCCESS_NO_MATCH) {

            // Throw an exception.
            throw new UnrecognizedSlotValueException(slotName, patternSlot.getValue());
        }

        // There was an unexpected problem.
        else if (statusCode != StatusCode.ER_SUCCESS_MATCH) {

            // Throw an exception.
            throw new UnexpectedSlotResolutionStatusException(slotName, statusCode.toString());
        }

        // Get the slot value from the first (and only) value.
        List<ValueWrapper> values = resolution.getValues();
        ValueWrapper valueWrapper = values.get(0);
        Value value = valueWrapper.getValue();

        // Get and return the value's name.
        return value.getName();
    }
}
