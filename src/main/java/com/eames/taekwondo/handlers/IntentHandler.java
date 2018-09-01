package com.eames.taekwondo.handlers;

import com.amazon.ask.dispatcher.request.handler.RequestHandler;

/**
 * This abstract class is the base class for all intent handlers for this skill.
 */
abstract class IntentHandler implements RequestHandler {

    /**
     * The skill name.
     */
    static protected final String SKILL_NAME = "Taekwon-Do Patterns";

    /**
     * The pattern slot name.
     */
    static protected final String PATTERN_SLOT = "TKDPattern";
}
