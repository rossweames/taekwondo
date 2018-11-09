package com.eames.taekwondo.handlers.pattern;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.eames.taekwondo.handlers.pattern.utilities.SessionAttributeUtilities;
import com.eames.taekwondo.model.Movement;
import com.eames.taekwondo.model.Pattern;
import com.eames.taekwondo.model.TeachMode;
import com.eames.taekwondo.model.TeachModes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * This is the handler for the 'teach pattern' skill.
 *
 * TODO: Need unit tests for this class.
 */
public class TeachPatternIntentHandler extends PatternIntentHandler {

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(TeachPatternIntentHandler.class);

    /**
     * Default Constructor
     */
    public TeachPatternIntentHandler() {
        logger.debug("Constructing TeachPatternIntentHandler");
    }

    /**
     * Determine whether this intent can handle the request.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return {@code True} if this intent can handle the request, {@code false} if not
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return (input.matches(intentName("TeachPatternIntent")) ||
                input.matches(intentName("AMAZON.StartOverIntent")));
    }

    /**
     * Processes the intent action and returns the answer speech text.
     *
     * @param input the {@link HandlerInput} request object to process
     * @param pattern the {@link Pattern} to use
     * @return the speech text answer
     */
    @Override
    protected String doProcessing(HandlerInput input, Pattern pattern) {

        // Set the current step to the start movement.
        SessionAttributeUtilities.setCurrentStep(input, 0);

        // Grab the teach mode.
        String teachModeKey = SessionAttributeUtilities.getTeachMode(input);

        // Get the teach mode from the teach mode key.
        TeachMode teachMode = TeachModes.getTeachModeByKey(teachModeKey);

        // There is a teachMode.
        if (teachMode != null) {

            // Get the appropriate movement.
            Movement movement = pattern.getStartMovement();

            // Get the appropriate description from the movement.
            String description;
            if (teachMode == TeachModes.BRIEF)
                description = movement.getShortDescription();
            else
                description = movement.getFullDescription();

            return new StringBuilder()
                    .append("Okay, I'll teach you ")
                    .append(pattern.getPhoneticName())
                    .append(". ")
                    .append(description)
                    .toString();
        }

        // No such teach mode.
        else {

            return "There is a problem with the selected teaching mode.";
        }
    }
 }
