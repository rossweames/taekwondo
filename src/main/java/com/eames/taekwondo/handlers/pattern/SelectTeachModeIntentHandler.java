package com.eames.taekwondo.handlers.pattern;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;
import com.eames.taekwondo.handlers.IntentHandler;
import com.eames.taekwondo.handlers.exception.*;
import com.eames.taekwondo.handlers.pattern.utilities.IntentUtilities;
import com.eames.taekwondo.handlers.pattern.utilities.SessionAttributeUtilities;
import com.eames.taekwondo.handlers.pattern.utilities.TeachModeUtilities;
import com.eames.taekwondo.model.TeachMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * This is the handler for the 'select teach mode' skill.
 *
 * TODO: Need unit tests for this class.
 */
public class SelectTeachModeIntentHandler extends IntentHandler {

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(SelectTeachModeIntentHandler.class);

    /**
     * Default Constructor
     */
    public SelectTeachModeIntentHandler() {
        logger.debug("Constructing SelectTeachModeIntentHandler");
    }

    /**
     * Determine whether this intent can handle the request.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return {@code True} if this intent can handle the request, {@code false} if not
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SelectTeachModeIntent"));
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
                .append("SelectTeachModeIntentHandler (")
                .append(IntentUtilities.getRequestClassName(input))
                .append("): dialogState=")
                .append(IntentUtilities.getDialogState(input).toString())
                .toString());

        // Construct a response builder and keep the session open.
        ResponseBuilder responseBuilder = input.getResponseBuilder()
                .withShouldEndSession(false);

        try {

            // Get the teach mode from the intent.
            // Throws: SlotNotFoundException, MissingSlotValueException, UnrecognizedSlotValueException,
            //         UnexpectedSlotResolutionStatusException, TeachModeNotFoundException
            TeachMode teachMode = TeachModeUtilities.getTeachMode(input);

            logger.debug(new StringBuilder()
                    .append("Found the teach mode: ")
                    .append(teachMode.getDisplayName())
                    .toString());

            // Set the teach mode attribute.
            SessionAttributeUtilities.setTeachMode(input, teachMode.getKey());

            responseBuilder
                    .withSpeech(new StringBuilder()
                            .append("Okay, the teaching mode has been set to ")
                            .append(teachMode.getPhoneticName())
                            .append(".")
                            .toString()
                    );

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
            // The teach mode provided did not match any of the json defined in the skill.

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
             * the skill to prompt for the teach mode.
             */

            // Construct an empty slot.
            Slot slot = Slot.builder()
                    .withName(TeachModeUtilities.TEACH_MODE_SLOT)
                    .withConfirmationStatus(SlotConfirmationStatus.NONE)
                    .build();

            // Construct an intent that contains the empty slot.
            Intent intent = Intent.builder()
                    .withName(IntentUtilities.getIntent(input).getName())
                    .withConfirmationStatus(IntentConfirmationStatus.NONE)
                    .putSlotsItem(TeachModeUtilities.TEACH_MODE_SLOT, slot)
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

        } catch (TeachModeNotFoundException ex) {
            // The requested teach mode was resolved by the skill, but the teach mode is not one in our backend list.

            logger.error(new StringBuilder()
                    .append(ex.getClass().getSimpleName())
                    .append(": teachModeName=")
                    .append(ex.getTeachModeName())
                    .toString());

            // Add the speech text error message to the response.
            responseBuilder.withSpeech("I'm sorry, but I could not find that teaching mode.");
        }

        // Build and return the response (keep the session open).
        return responseBuilder
                .withShouldEndSession(false)
                .build();
    }
}
