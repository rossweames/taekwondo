package com.eames.taekwondo.handlers.pattern;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;
import com.eames.taekwondo.handlers.IntentHandler;
import com.eames.taekwondo.handlers.pattern.utilities.ActivePatternUtilities;
import com.eames.taekwondo.handlers.pattern.utilities.IntentUtilities;
import com.eames.taekwondo.model.Pattern;
import com.eames.taekwondo.model.Patterns;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * This is the handler for the 'current pattern step' skill.
 *
 * TODO: Need unit tests for this class.
 */
public class CurrentPatternStepIntentHandler extends IntentHandler {

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(CurrentPatternStepIntentHandler.class);

    /**
     * Default Constructor
     */
    public CurrentPatternStepIntentHandler() {
        logger.debug("Constructing CurrentPatternStepIntentHandler");
    }

    /**
     * Determine whether this intent can handle the request.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return {@code True} if this intent can handle the request, {@code false} if not
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("CurrentPatternStepIntent"));
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
                .append("CurrentPatternSteIntentHandler (")
                .append(IntentUtilities.getRequestClassName(input))
                .append("): dialogState=")
                .append(IntentUtilities.getDialogState(input).toString())
                .toString());

        // Construct a response builder and keep the session open.
        ResponseBuilder responseBuilder = input.getResponseBuilder()
                .withShouldEndSession(false);

        // First, grab the active pattern.
        String activePattern = ActivePatternUtilities.getActivePattern(input);

        // There is an active pattern.
        if (activePattern != null) {

            // Get the TKD pattern from the active patter..
            Pattern pattern = Patterns.getPatternByKey(activePattern);

            // There is an active pattern.
            if (pattern != null) {

                // Get the current step.
                Integer currentStep = ActivePatternUtilities.getCurrentStep(input);

                // There is a current step.
                if (currentStep != null) {

                    // get the current step's value.
                    int stepValue = currentStep;

                    // Construct the step message appropriately.
                    StringBuilder sb = new StringBuilder()
                        .append("We are currently on ");
                    if (stepValue == 0)
                        sb.append("the starting step ");
                    else if (stepValue == pattern.getMovementCount() + 1)
                        sb.append("the finishing step ");
                    else {
                        sb.append("step ")
                            .append(currentStep);
                    }
                    sb.append(" of ")
                        .append(pattern.getPhoneticName())
                        .append(".");

                    responseBuilder
                        .withSpeech(sb.toString());
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
                    .withSpeech("There is no pattern selected.");
        }

        // Build and return the response (keep the session open).
        return responseBuilder
                .withShouldEndSession(false)
                .build();
    }
 }
