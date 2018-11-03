package com.eames.taekwondo.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;

/**
 * This abstract class is the base class for all intent handlers for this skill.
 */
public abstract class IntentHandler implements RequestHandler {

    /**
     * Determines whether this intent can handle the request.
     * This is the default implementation that always returns {@code false}.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return {@code True} if this intent can handle the given request, {@code false} if not
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return false;
    }
}
