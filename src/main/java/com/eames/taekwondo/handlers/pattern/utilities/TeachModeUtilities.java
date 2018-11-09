package com.eames.taekwondo.handlers.pattern.utilities;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.eames.taekwondo.handlers.exception.*;
import com.eames.taekwondo.model.TeachMode;
import com.eames.taekwondo.model.TeachModes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Provides a set of utilities for working with teaching modes.
 */
public abstract class TeachModeUtilities {

    /**
     * The teach mode slot key
     */
    public static final String TEACH_MODE_SLOT = "TKDTeachMode";

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(TeachModeUtilities.class);

    /**
     * Gets the appropriate {@link TeachMode} from the intent.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return the {@link TeachMode}
     * @throws SlotNotFoundException if the input json has the wrong format due to a skill configuration error
     * @throws MissingSlotValueException if no value has been included in the input
     * @throws UnrecognizedSlotValueException if the value provided does not match any known values
     * @throws UnexpectedSlotResolutionStatusException if the resolution contains an unexpected status
     * @throws TeachModeNotFoundException if the teach mode cannot be resolved
     */
    public static TeachMode getTeachMode(HandlerInput input)
            throws SlotNotFoundException, MissingSlotValueException, UnrecognizedSlotValueException,
            UnexpectedSlotResolutionStatusException, TeachModeNotFoundException {

        // Get the teach mode key from the request.
        // Throws: SlotNotFoundException, MissingSlotValueException,
        //         UnrecognizedSlotValueException, UnexpectedSlotResolutionStatusException
        String teachModeKey = IntentUtilities.getSlotValue(input, TEACH_MODE_SLOT, null);

        // Get the teach mode from the name passed in.
        TeachMode teachMode = TeachModes.getTeachModeByKey(teachModeKey);
        if (teachMode != null) {

            // Return the teach mode.
            return teachMode;
        }

        // No such pattern.
        else {

            // Throw an exception.
            throw new TeachModeNotFoundException(teachModeKey);
        }
    }
}
