package com.eames.taekwondo.handlers.pattern;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;
import com.eames.taekwondo.handlers.IntentHandler;
import com.eames.taekwondo.handlers.pattern.utilities.IntentUtilities;
import com.eames.taekwondo.handlers.pattern.utilities.SessionAttributeUtilities;
import com.eames.taekwondo.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * This is the handler for the 'pattern step details' skill.
 *
 * TODO: Need unit tests for this class.
 */
public class PatternStepRepeatIntentHandler extends IntentHandler {

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(PatternStepRepeatIntentHandler.class);

    /**
     * Default Constructor
     */
    public PatternStepRepeatIntentHandler() {
        logger.debug("Constructing PatternStepRepeatIntentHandler");
    }

    /**
     * Determine whether this intent can handle the request.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return {@code True} if this intent can handle the request, {@code false} if not
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.RepeatIntent"));
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
                .append("PatternStepRepeatIntentHandler (")
                .append(IntentUtilities.getRequestClassName(input))
                .append("): dialogState=")
                .append(IntentUtilities.getDialogState(input).toString())
                .toString());

        // Construct a response builder and keep the session open.
        ResponseBuilder responseBuilder = input.getResponseBuilder()
                .withShouldEndSession(false);

        // Grab the active pattern.
        String activePattern = SessionAttributeUtilities.getActivePattern(input);

        // There is an active pattern.
        if (activePattern != null) {

            // Get the TKD pattern from the active pattern.
            Pattern pattern = Patterns.getPatternByKey(activePattern);

            // There is an active pattern.
            if (pattern != null) {

                // Get the current step.
                Integer currentStep = SessionAttributeUtilities.getCurrentStep(input);

                // There is a current step.
                if (currentStep != null) {

                    // Get the appropriate movement.
                    Movement movement = pattern.getNthMovement(currentStep);

                    // Get the movement's description.
                    String description= movement.getDescription();

                    responseBuilder
                            .withSpeech(new StringBuilder()
                                    .append(description)
                                    .toString()
                            );
                }

                // There is no current step.
                else {

                    responseBuilder
                            .withSpeech(new StringBuilder()
                                    .append("We have not started the ")
                                    .append(pattern.getPhoneticName())
                                    .append(" pattern yet.")
                                    .toString()
                            );
                }
            }

            // No such pattern.
            else {

                responseBuilder
                        .withSpeech("There is a problem with the selected pattern.");
            }
        }

        // There is no active pattern.
        else  {

            responseBuilder
                    .withSpeech("There is no pattern selected; please select one.");
        }

        // Build and return the response (keep the session open).
        return responseBuilder
                .withShouldEndSession(false)
                .build();
    }
 }
