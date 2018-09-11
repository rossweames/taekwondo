package com.eames.taekwondo.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.slu.entityresolution.*;
import com.eames.taekwondo.handlers.exception.*;
import com.eames.taekwondo.model.Pattern;
import com.eames.taekwondo.model.Patterns;

import java.util.List;
import java.util.Map;

/**
 * This abstract class is the base class for all intent handlers for this skill.
 *
 * TODO: Need unit tests for this class.
 */
abstract class IntentHandler implements RequestHandler {

    /**
     * The skill name
     */
    static protected final String SKILL_NAME = "Taekwon-Do Patterns";

    /**
     * The pattern slot key
     */
    private static final String PATTERN_SLOT = "TKDPattern";

    /**
     * Gets the appropriate {@link Pattern} from the input.
     *
     * @param input the request input.
     * @return the {@link Pattern}
     * @throws SlotNotFoundException if the input json has the wrong format due to a skill configuration error
     * @throws MissingSlotValueException if no value has been included in the input
     * @throws UnrecognizedSlotValueException if the value provided does not match any known values
     * @throws UnexpectedSlotResolutionStatusException if the resolution contains an unexpected status
     * @throws PatternNotFoundException if the pattern cannot be resolved
     */
    static protected Pattern getPattern(HandlerInput input)
        throws SlotNotFoundException, MissingSlotValueException, UnrecognizedSlotValueException,
            UnexpectedSlotResolutionStatusException, PatternNotFoundException {

        // Get the pattern key from the input.
        // Throws: SlotNotFoundException, MissingSlotValueException,
        //         UnrecognizedSlotValueException, UnexpectedSlotResolutionStatusException
        String patternKey = getSlotValue(input, PATTERN_SLOT);

        // Get the TKD pattern from the name passed in.
        Pattern pattern = Patterns.getPatternByKey(patternKey);
        if (pattern != null) {

            // Return the pattern.
            return pattern;
        }

        // No such pattern.
        else {

            // Throw an exception.
            throw new PatternNotFoundException(patternKey);
        }
    }

    /**
     * Gets the value of the given slot.
     *
     * @param input the request input.
     * @param slotName the name of the slot to retrieve
     * @return the slot's value
     * @throws SlotNotFoundException if the input json has the wrong format due to a skill configuration error
     * @throws MissingSlotValueException if no value has been included in the input
     * @throws UnrecognizedSlotValueException if the value provided does not match any known values
     * @throws UnexpectedSlotResolutionStatusException if the resolution contains an unexpected status
     */
    static private String getSlotValue(HandlerInput input, String slotName)
        throws SlotNotFoundException, MissingSlotValueException, UnrecognizedSlotValueException,
            UnexpectedSlotResolutionStatusException {

        // Get the slots collection from the input.
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

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

        // The pattern name provided did not match any of the patterns defined in the skill.
        if (statusCode == StatusCode.ER_SUCCESS_NO_MATCH) {

            // Throw an exception.
            throw new UnrecognizedSlotValueException(slotName, patternSlot.getName());
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
