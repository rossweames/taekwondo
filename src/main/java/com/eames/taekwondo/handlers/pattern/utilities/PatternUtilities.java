package com.eames.taekwondo.handlers.pattern.utilities;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.eames.taekwondo.handlers.exception.*;
import com.eames.taekwondo.model.Pattern;
import com.eames.taekwondo.model.Patterns;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Provides a set of utilities for worhing with patterns.
 */
public abstract class PatternUtilities {

    /**
     * The pattern slot key
     */
    public static final String PATTERN_SLOT = "TKDPattern";

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(PatternUtilities.class);

    /**
     * Gets the appropriate {@link Pattern} from the intent.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return the {@link Pattern}
     * @throws SlotNotFoundException if the input json has the wrong format due to a skill configuration error
     * @throws MissingSlotValueException if no value has been included in the input
     * @throws UnrecognizedSlotValueException if the value provided does not match any known values
     * @throws UnexpectedSlotResolutionStatusException if the resolution contains an unexpected status
     * @throws PatternNotFoundException if the pattern cannot be resolved
     */
    public static Pattern getPattern(HandlerInput input)
            throws SlotNotFoundException, MissingSlotValueException, UnrecognizedSlotValueException,
            UnexpectedSlotResolutionStatusException, PatternNotFoundException {

        // First, grab the active pattern.
        String activePatternKey = ActivePatternUtilities.getActivePattern(input);

        logger.debug(new StringBuilder()
                .append("activePattern=")
                .append((activePatternKey != null) ? activePatternKey : "null")
                .toString());

        // Get the pattern key from the request.
        // Throws: SlotNotFoundException, MissingSlotValueException,
        //         UnrecognizedSlotValueException, UnexpectedSlotResolutionStatusException
        String patternKey = IntentUtilities.getSlotValue(input, PATTERN_SLOT, activePatternKey);

        // There is an active pattern, and new pattern is different from the active pattern,
        // so clear the active pattern before switching patterns.
        if ((activePatternKey != null) && !activePatternKey.equals(patternKey))
            ActivePatternUtilities.clearActivePattern(input);

        // Get the TKD pattern from the name passed in.
        Pattern pattern = Patterns.getPatternByKey(patternKey);
        if (pattern != null) {

            // The new pattern is different from the active pattern, so set the active pattern to the new one.
            if (!patternKey.equals(activePatternKey))
                ActivePatternUtilities.setActivePattern(input, patternKey);

            // Return the pattern.
            return pattern;
        }

        // No such pattern.
        else {

            // Throw an exception.
            throw new PatternNotFoundException(patternKey);
        }
    }
}
