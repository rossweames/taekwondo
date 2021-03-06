package com.eames.taekwondo.handlers.pattern;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.eames.taekwondo.model.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * This is the handler for the 'pattern step count' skill.
 *
 * TODO: Need unit tests for this class.
 */
public class PatternStepCountIntentHandler extends PatternIntentHandler {

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(PatternStepCountIntentHandler.class);

    /**
     * Default Constructor
     */
    public PatternStepCountIntentHandler() {
        logger.debug("Constructing PatternStepCountIntentHandler");
    }

    /**
     * Determine whether this intent can handle the request.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return {@code True} if this intent can handle the request, {@code false} if not
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("PatternStepCountIntent"));
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

        return new StringBuilder()
                .append("The ")
                .append(pattern.getPhoneticName())
                .append(" pattern has ")
                .append(pattern.getMovementCount())
                .append(" movements.")
                .toString();
    }
 }
