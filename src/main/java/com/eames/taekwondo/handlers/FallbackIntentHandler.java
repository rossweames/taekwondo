package com.eames.taekwondo.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class FallbackIntentHandler extends IntentHandler {

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(FallbackIntentHandler.class);

    /**
     * Default Constructor
     */
    public FallbackIntentHandler() {
        logger.debug("Constructing FallbackIntentHandler");
    }

    /**
     * Determines whether this intent can handle the request.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return {@code True} if this intent can handle the request, {@code false} if not
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.FallbackIntent"));
    }

    /**
     * Gets called by the skill framework when there is something for this intent to do.
     * Creates an Alexa skill card containing the information.
     * Keeps the session open.
     *
     * @param input the {@link HandlerInput} request object to process
     * @return a response containing the speech text
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {

        logger.debug(new StringBuilder()
                .append("FallbackIntentHandler (")
                .append(getRequestClassName(input))
                .append("): dialogState=")
                .append(getDialogState(input).toString())
                .toString());

        // TODO: Need to set the help information string.

        // TODO: Need to understand how fallback works.

        String speechText = "Sorry, I don't know that. You can say try saying help!";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("TaeKwon-Do - Fallback", speechText)
                .withShouldEndSession(false)
                .build();
    }
}
