package com.eames.taekwondo.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

/**
 * This is the handler for the launch skill notification.
 *
 * TODO: Need unit tests for this class.
 */
public class LaunchRequestHandler implements RequestHandler {

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(LaunchRequestHandler.class);

    /**
     * Default Constructor
     */
    public LaunchRequestHandler() {
        logger.debug("Constructing LaunchRequestHandler");
    }

    /**
     * Determine whether this intent can handle the request.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return {@code True} if this intent can handle the request, {@code false} if not
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    /**
     * Gets called by the skill framework when there is something for this intent to do.
     * Keeps the session open.
     *
     * @param input the {@link HandlerInput} request object to process
     * @return a response containing the welcome speech text
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {

        logger.debug(new StringBuilder()
                .append("LaunchRequestHandler (")
                .append(input.getRequestEnvelope().getRequest().getClass().getSimpleName())
                .append(")")
                .toString());

        String speechText = "Welcome to the I.T.F. Tie-Kwon-Doe Master skill. You can say 'help' to get a list of actions.";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }
}
