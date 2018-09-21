package com.eames.taekwondo.handlers.pattern;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;
import com.eames.taekwondo.handlers.IntentHandler;
import com.eames.taekwondo.handlers.exception.*;
import com.eames.taekwondo.model.Pattern;
import com.eames.taekwondo.model.Patterns;
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
     * The pattern slot key
     */
    private static final String PATTERN_SLOT = "TKDPattern";

    /**
     * Provides the answer for the pattern intent.
     * All concrete child intents must implement this operation to provide their answer.
     *
     * @param pattern the {@link Pattern} for which to provide the answer
     * @return the speech text answer for the pattern
     */
    protected abstract String getAnswer(Pattern pattern);

    @Override
    public Optional<Response> handle(HandlerInput input) {

        DialogState dialogState = getDialogState(input);

        logger.debug(new StringBuilder()
                .append("PatternIntentHandler (")
                .append(input.getRequestEnvelope().getRequest().getClass().getSimpleName())
                .append("): dialogState=")
                .append(dialogState.toString())
                .toString());

        // Construct a response builder and keep the session open.
        ResponseBuilder responseBuilder = input.getResponseBuilder()
                .withShouldEndSession(false);

        try {

            // Get the pattern from the intent.
            // Throws: SlotNotFoundException, MissingSlotValueException, UnrecognizedSlotValueException,
            //         UnexpectedSlotResolutionStatusException, PatternNotFoundException
            Pattern pattern = getPattern(input);

            logger.debug(new StringBuilder()
                    .append("Found the pattern: ")
                    .append(pattern.getDisplayName())
                    .toString());
            // Add the speech text answer to the response.
            responseBuilder.withSpeech(getAnswer(pattern));

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
            // (The slot value was not provided by the user.)

            logger.error(new StringBuilder()
                    .append(ex.getClass().getSimpleName())
                    .append(": slotName=")
                    .append(ex.getSlotName())
                    .toString());

            // Add a delegate directive to the response.
            responseBuilder.addDelegateDirective(null);

        } catch (UnrecognizedSlotValueException ex) {
            // The pattern name provided did not match any of the patterns defined in the skill.

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
                    .withName(PATTERN_SLOT)
                    .withConfirmationStatus(SlotConfirmationStatus.NONE)
                    .build();

            // Construct an intent that contains the empty slot.
            Intent intent = Intent.builder()
                    .withName(getIntent(input).getName())
                    .withConfirmationStatus(IntentConfirmationStatus.NONE)
                    .putSlotsItem(PATTERN_SLOT, slot)
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

    /**
     * Gets the appropriate {@link Pattern} from the intent.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return the {@link Pattern}
     * @throws SlotNotFoundException if the input json has the wrong format due to a skill configuration error
     * @throws MissingSlotValueException if no value has been included in the input
     * @throws UnrecognizedSlotValueException if the value provided does not match any known values
     * @throws UnexpectedSlotResolutionStatusException if the resolution contains an unexpected status
     * @throws PatternNotFoundException if the pattern cannot be resolved
     */
    private Pattern getPattern(HandlerInput input)
        throws SlotNotFoundException, MissingSlotValueException, UnrecognizedSlotValueException,
            UnexpectedSlotResolutionStatusException, PatternNotFoundException {

        // Get the pattern key from tge request.
        // Throws: SlotNotFoundException, MissingSlotValueException,
        //         UnrecognizedSlotValueException, UnexpectedSlotResolutionStatusException
        String patternKey = getSlotValue(input, PATTERN_SLOT);

        // Get the TKD pattern from the name passed in.
        Pattern pattern = Patterns.getPatternByKey(patternKey);
        if (pattern != null) {

            // Return the pattern.
            return pattern;
        }

        // No such pattern.
        else {

            // Throw an exception.
            throw new PatternNotFoundException(patternKey);
        }
    }
}
