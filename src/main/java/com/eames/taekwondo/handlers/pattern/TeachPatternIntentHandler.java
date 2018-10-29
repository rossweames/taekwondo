package com.eames.taekwondo.handlers.pattern;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.eames.taekwondo.handlers.pattern.utilities.ActivePatternUtilities;
import com.eames.taekwondo.model.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

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
        return input.matches(intentName("TeachPatternIntent"));
    }

    /**
     * Gets called by the skill framework when there is something for this intent to do.
     * Sets the current step to the start movement then calls the base class handler.
     *
     * @param input the {@link HandlerInput} request object to process
     * @return a response containing either the answer speech text or a delegate directive
     * telling the skill to ask for the pattern name
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {

        // Set the current step to the start movement.
        ActivePatternUtilities.setCurrentStep(input, 0);

        // Call the base class handler.
        return super.handle(input);
    }

    /**
     * Processes the intent action and returns the answer speech text.
     *
     * @param pattern the {@link Pattern} to use
     * @return the speech text answer
     */
    protected String doProcessing(Pattern pattern) {

        return new StringBuilder()
                .append("O.K. I'll teach you ")
                .append(pattern.getPhoneticName())
                .append(". ")
                .append(pattern.getStartMovement().getShortDescription())
                .toString();
    }
 }
