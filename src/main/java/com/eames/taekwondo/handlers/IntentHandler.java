package com.eames.taekwondo.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.slu.entityresolution.*;
import com.eames.taekwondo.handlers.exception.PatternNotFoundException;
import com.eames.taekwondo.model.Pattern;
import com.eames.taekwondo.model.Patterns;

import java.util.List;
import java.util.Map;

/**
 * This abstract class is the base class for all intent handlers for this skill.
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
     * The pattern name slot
     */
    private static final String SLOT_PATTERN_NAME = "TKDPatternName";

    /**
     * Gets the appropriate {@link Pattern} from the input.
     *
     * @param input the request input.
     * @return the {@link Pattern}
     * @throws PatternNotFoundException if the pattern cannot be resolved
     */
    static protected Pattern getPattern(HandlerInput input)
        throws PatternNotFoundException {

        // Get the pattern key from the input.
        String patternKey = getPatternKey(input);

        // A pattern key was passed in.
        if (patternKey != null) {

            // Get the TKD pattern from the name passed in.
            Pattern pattern = Patterns.getPatternByKey(patternKey);
            if (pattern != null) {

                // Return the pattern.
                return pattern;
            }

            // No such pattern.
            else {

                // Construct the error message.
                final StringBuilder sb = new StringBuilder()
                        .append("Sorry, but I do not recognize the ")
                        .append(patternKey)
                        .append(" pattern.");

                // Throw an exception.
                throw new PatternNotFoundException(sb.toString());
            }
        }

        // No pattern was given.
        else {

            // Throw an exception.
            throw new PatternNotFoundException("Sorry, but your request did not specify a pattern.");
        }
    }

    /**
     * Gets the pattern key from the input.
     *
     * @param input the request input.
     * @return the pattern mey
     */
    static private String getPatternKey(HandlerInput input) {

        // Grab the 'pattern' slot from the input.
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        Slot patternSlot = slots.get(PATTERN_SLOT);
        if (patternSlot == null)
            return null;

        Resolutions slotResolutions = patternSlot.getResolutions();
        if (slotResolutions == null)
            return null;

        List<Resolution> resolutions = slotResolutions.getResolutionsPerAuthority();
        if ((resolutions == null) || (resolutions.size() == 0))
            return null;

        // There should only be one.
        Resolution resolution = resolutions.get(0);
        if (resolution == null)
            return null;

        String authority = resolution.getAuthority();
        if ((authority == null) || !authority.endsWith(SLOT_PATTERN_NAME))
            return null;

        Status status = resolution.getStatus();
        if (status == null)
            return null;

        // TODO: The status is 'ERR_NO_MATCH' if a pattern name was provided but it did not match any of the patterns.

        StatusCode statusCode = status.getCode();
        if (statusCode != StatusCode.ER_SUCCESS_MATCH)
            return null;

        List<ValueWrapper> values = resolution.getValues();
        if ((values == null) || (values.size() == 0))
            return null;

        // There should only be one.
        ValueWrapper valueWrapper = values.get(0);
        if (valueWrapper == null)
            return null;

        Value value = valueWrapper.getValue();
        if (value == null)
            return null;

        // Get and return the pattern key
        return value.getName();
    }
}
