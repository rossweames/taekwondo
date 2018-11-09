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
 * This is the handler for the 'pattern previous step' skill.
 *
 * TODO: Need unit tests for this class.
 */
public class PatternStepPreviousIntentHandler extends IntentHandler {

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(PatternStepPreviousIntentHandler.class);

    /**
     * Default Constructor
     */
    public PatternStepPreviousIntentHandler() {
        logger.debug("Constructing PatternStepPreviousIntentHandler");
    }

    /**
     * Determine whether this intent can handle the request.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return {@code True} if this intent can handle the request, {@code false} if not
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.PreviousIntent"));
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
                .append("PatternStepPreviousIntentHandler (")
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

                    // There are more steps in the pattern.
                    if (currentStep > 0) {

                        // Increment and update the current step.
                        SessionAttributeUtilities.setCurrentStep(input, --currentStep);

                        // Grab the teach mode.
                        String teachModeKey = SessionAttributeUtilities.getTeachMode(input);

                        // Get the teach mode from the teach mode key.
                        TeachMode teachMode = TeachModes.getTeachModeByKey(teachModeKey);

                        // There is a teachMode.
                        if (teachMode != null) {

                            // Get the appropriate movement.
                            Movement movement = pattern.getNthMovement(currentStep);

                            // Get the appropriate description from the movement.
                            String description;
                            if (teachMode == TeachModes.BRIEF)
                                description = movement.getShortDescription();
                            else
                                description = movement.getFullDescription();

                            responseBuilder
                                    .withSpeech(new StringBuilder()
                                            .append(description)
                                            .toString()
                                    );
                        }

                        // No such teach mode.
                        else {

                            responseBuilder
                                    .withSpeech("There is a problem with the selected teaching mode.");
                        }
                    }

                    // There are no more steps.
                    else {

                        responseBuilder
                                .withSpeech(new StringBuilder()
                                        .append("We are at the starting position.")
                                        .toString()
                                );
                    }
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
