package com.eames.taekwondo.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.eames.taekwondo.handlers.pattern.utilities.IntentUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * This is the handler for the 'cancel' command.
 *
 * TODO: Need unit tests for this class.
 */
public class CancelIntentHandler extends IntentHandler {

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(CancelIntentHandler.class);

    /**
     * Default Constructor
     */
    public CancelIntentHandler() {
        logger.debug("Constructing CancelIntentHandler");
    }

    /**
     * Determines whether this intent can handle the request.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return {@code True} if this intent can handle the request, {@code false} if not
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.CancelIntent"));
    }

    /**
     * Gets called by the skill framework when there is something for this intent to do.
     * Keeps the session open.
     *
     * @param input the {@link HandlerInput} request object to process
     * @return a response containing either the answer speech text
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {

        logger.debug(new StringBuilder()
                .append("CancelIntentHandler (")
                .append(IntentUtilities.getRequestClassName(input))
                .append("): dialogState=")
                .append(IntentUtilities.getDialogState(input).toString())
                .toString());

        // Return a silent response and keep the session open.
        return input.getResponseBuilder()
                .withShouldEndSession(false)
                .build();
    }
}
