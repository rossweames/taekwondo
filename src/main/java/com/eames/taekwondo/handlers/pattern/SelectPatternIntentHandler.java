package com.eames.taekwondo.handlers.pattern;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.eames.taekwondo.model.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * This is the handler for the 'select pattern' skill.
 *
 * TODO: Need unit tests for this class.
 */
public class SelectPatternIntentHandler extends PatternIntentHandler {

    private static final Logger logger = LogManager.getLogger(SelectPatternIntentHandler.class);

    /**
     * Default Constructor
     */
    public SelectPatternIntentHandler() {
        logger.debug("Constructing SelectPatternIntentHandler");
    }

    /**
     * Determine whether this intent can handle the request.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return {@code True} if this intent can handle the request, {@code false} if not
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SelectPatternIntent"));
    }

    /**
     * Processes the intent action and returns the answer speech text.
     *
     * @param pattern the {@link Pattern} to use
     * @return the speech text answer
     */
    protected String doProcessing(Pattern pattern) {

        return new StringBuilder()
                .append("The ")
                .append(pattern.getPhoneticName())
                .append(" pattern is now selected.")
                .toString();
    }
}