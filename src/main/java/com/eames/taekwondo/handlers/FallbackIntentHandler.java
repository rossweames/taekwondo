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

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.FallbackIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        logger.debug(new StringBuilder()
                .append("FallbackIntentHandler (")
                .append(getRequestClassName(input))
                .append("): dialogState=")
                .append(getDialogState(input).toString())
                .toString());

        // TODO: Need to understand how fallback works.

        String speechText = "Sorry, I don't know that. You can say try saying help!";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("TaeKwon-Do - Fallback", speechText)
                .withShouldEndSession(false)
                .build();
    }
}
