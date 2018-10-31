package com.eames.taekwondo.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

/**
 * This is the handler for the 'session ended' notification.
 *
 * TODO: Need unit tests for this class.
 */
public class SessionEndedRequestHandler implements RequestHandler {

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(SessionEndedRequestHandler.class);

    /**
     * Default Constructor
     */
    public SessionEndedRequestHandler() {
        logger.debug("Constructing SessionEndedRequestHandler");
    }

    /**
     * Determine whether this intent can handle the request.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return {@code True} if this intent can handle the request, {@code false} if not
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(SessionEndedRequest.class));
    }

    /**
     * Gets called by the skill framework when there is something for this intent to do.
     *
     * @param input the {@link HandlerInput} request object to process
     * @return an empty response
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {

        // Get the reason and error.
        Request request = input.getRequestEnvelope().getRequest();
        SessionEndedRequest sessionEndedRequest = (SessionEndedRequest) request;
        SessionEndedReason reason = sessionEndedRequest.getReason();
        SessionEndedError error = sessionEndedRequest.getError();

        logger.debug(new StringBuilder()
                .append("SessionEndedRequestHandler (")
                .append(request.getClass().getSimpleName())
                .append("): reason=")
                .append(reason.toString())
                .append(", errorType=")
                .append((error != null) ? error.getType().toString() : "")
                .append(", errorMessage=")
                .append((error != null) ? error.getMessage() : "")
                .toString());

        // any cleanup logic goes here

        // Return an empty response.
        return Optional.empty();
    }
}
