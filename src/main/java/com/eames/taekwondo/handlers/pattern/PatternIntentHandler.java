package com.eames.taekwondo.handlers.pattern;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;
import com.eames.taekwondo.handlers.IntentHandler;
import com.eames.taekwondo.handlers.exception.*;
import com.eames.taekwondo.handlers.pattern.utilities.IntentUtilities;
import com.eames.taekwondo.handlers.pattern.utilities.PatternUtilities;
import com.eames.taekwondo.model.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * This abstract class is the base class for all pattern-based intent handlers for this skill.
 *
 * TODO: Need unit tests for this class.
 */
abstract class PatternIntentHandler extends IntentHandler {

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(PatternIntentHandler.class);

    /**
     * Allows for pre-processing the intent action.
     * Concrete child intents override this operation to perform any pre-processing activities.
     *
     * @param input the {@link HandlerInput} request object to process
     */
    protected void doPreProcessing(HandlerInput input) {}

    /**
     * Processes the intent action and returns the answer speech text.
     * All concrete child intents must implement this operation to provide their answer.
     *
     * @param input the {@link HandlerInput} request object to process
     * @param pattern the {@link Pattern} for which to provide the answer
     * @return the speech text answer for the pattern
     */
    protected abstract String doProcessing(HandlerInput input, Pattern pattern);

    /**
     * Gets called by the skill framework when there is something for this intent to do.
     *
     * @param input the {@link HandlerInput} request object to process
     * @return a response containing either the answer speech text or a delegate directive
     * telling the skill to ask for the pattern name
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {

        logger.debug(new StringBuilder()
                .append("PatternIntentHandler (")
                .append(IntentUtilities.getRequestClassName(input))
                .append("): dialogState=")
                .append(IntentUtilities.getDialogState(input).toString())
                .toString());

        // Perform any pre-processing acitvities.
        doPreProcessing(input);

        // Construct a response builder and keep the session open.
        ResponseBuilder responseBuilder = input.getResponseBuilder()
                .withShouldEndSession(false);

        try {

            // Get the pattern from the intent.
            // Throws: SlotNotFoundException, MissingSlotValueException, UnrecognizedSlotValueException,
            //         UnexpectedSlotResolutionStatusException, PatternNotFoundException
            Pattern pattern = PatternUtilities.getPattern(input);

            logger.debug(new StringBuilder()
                    .append("Found the pattern: ")
                    .append(pattern.getDisplayName())
                    .toString());

            // Add the speech text answer to the response.
            responseBuilder.withSpeech(doProcessing(input, pattern));

        } catch (SlotNotFoundException ex) {
            // The skill is not configured with a pattern slot.

            logger.error(new StringBuilder()
                    .append(ex.getClass().getSimpleName())
                    .append(": slotName=")
                    .append(ex.getSlotName())
                    .toString());

            // Add the speech text error message to the response.
            responseBuilder.withSpeech("Im sorry, but that request not cannot be fulfilled by this skill.");

        } catch (MissingSlotValueException ex) {
            // No resolutions are available.
            // (The slot value was not provided by the user and there is no active pattern.)

            logger.error(new StringBuilder()
                    .append(ex.getClass().getSimpleName())
                    .append(": slotName=")
                    .append(ex.getSlotName())
                    .toString());

            // Add a delegate directive to the response.
            responseBuilder.addDelegateDirective(null);

        } catch (UnrecognizedSlotValueException ex) {
            // The pattern name provided did not match any of the json defined in the skill.

            logger.error(new StringBuilder()
                    .append(ex.getClass().getSimpleName())
                    .append(": slotName=")
                    .append(ex.getSlotName())
                    .append(", slotValue=")
                    .append(ex.getSlotValue())
                    .toString());

            /*
             * We cannot simply return a null intent in the delegate directive.
             * We must pass back an intent that contains an empty slot to get
             * the skill to prompt for the pattern.
             */

            // Construct an empty slot.
            Slot slot = Slot.builder()
                    .withName(PatternUtilities.PATTERN_SLOT)
                    .withConfirmationStatus(SlotConfirmationStatus.NONE)
                    .build();

            // Construct an intent that contains the empty slot.
            Intent intent = Intent.builder()
                    .withName(IntentUtilities.getIntent(input).getName())
                    .withConfirmationStatus(IntentConfirmationStatus.NONE)
                    .putSlotsItem(PatternUtilities.PATTERN_SLOT, slot)
                    .build();

            // Add a delegate directive containing the intent to the response.
            responseBuilder.addDelegateDirective(intent);

        } catch (UnexpectedSlotResolutionStatusException ex) {
            // There was an unexpected problem.

            logger.error(new StringBuilder()
                    .append(ex.getClass().getSimpleName())
                    .append(": slotName=")
                    .append(ex.getSlotName())
                    .append(", slotValue=")
                    .append(ex.getStatusCode())
                    .toString());

            // Add the speech text error message to the response.
            responseBuilder.withSpeech("I'm sorry, I had a problem understanding your request, try asking again.");

        } catch (PatternNotFoundException ex) {
            // The requested pattern was resolved by the skill, but the pattern is not one in our backend list.

            logger.error(new StringBuilder()
                    .append(ex.getClass().getSimpleName())
                    .append(": patternName=")
                    .append(ex.getPatternName())
                    .toString());

            // Add the speech text error message to the response.
            responseBuilder.withSpeech("I'm sorry, but I could not find that pattern.");
        }

        // Build and return the response.
        return responseBuilder.build();
    }
}
