/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.eames.taekwondo.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;
import com.eames.taekwondo.handlers.exception.*;
import com.eames.taekwondo.model.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * This is the handler for the 'pattern step count' skill.
 *
 * TODO: Need unit tests for this class.
 */
public class PatternStepCountIntentHandler extends IntentHandler {

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(PatternStepCountIntentHandler.class);

    /*
     * Implemented {@link RequestHandler} operations
     */

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("PatternStepCountIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        // Get the intent.
        Intent intent = getIntent(input);

        // The response.
        IntentResponse intentResponse;

        try {

            // Get the pattern from the intent.
            // Throws: SlotNotFoundException, MissingSlotValueException, UnrecognizedSlotValueException,
            //         UnexpectedSlotResolutionStatusException, PatternNotFoundException
            Pattern pattern = getPattern(intent);

            // Construct the answer.
            intentResponse = new IntentResponse(
                    new StringBuilder()
                            .append("The ")
                            .append(pattern.getPhoneticName())
                            .append(" pattern has ")
                            .append(pattern.getMovementCount())
                            .append(" movements.")
                            .toString(),
                    false);

        } catch (SlotNotFoundException ex) {
            // The skill is not configured with a pattern slot.

            // Construct the error message.
            intentResponse = new IntentResponse(
                    "Im sorry, but that request not cannot be fulfilled by this skill.",
                    false);

            // TODO: Need to figure out why logging is not working.

            logger.error(new StringBuilder()
                            .append(ex.getClass().getName())
                            .append(": slotName=")
                            .append(ex.getSlotName())
                            .append(".")
                            .toString());

        } catch (MissingSlotValueException ex) {
            // No resolutions are available.
            // (The slot value was not provided by the user.)

            // Construct the prompt.
            intentResponse = new IntentResponse(
                    "Which pattern did you want?",
                    true);

            logger.error(new StringBuilder()
                    .append(ex.getClass().getName())
                    .append(": slotName=")
                    .append(ex.getSlotName())
                    .append(".")
                    .toString());

        } catch (UnrecognizedSlotValueException ex) {
            // The pattern name provided did not match any of the patterns defined in the skill.

            // Construct the prompt.
            intentResponse = new IntentResponse(
                    "I'm sorry, I did not recognize that pattern, can you please repeat the pattern name?.",
                    true);

            logger.error(new StringBuilder()
                    .append(ex.getClass().getName())
                    .append(": slotName=")
                    .append(ex.getSlotName())
                    .append(", slotValue=")
                    .append(ex.getSlotValue())
                    .append(".")
                    .toString());

        } catch (UnexpectedSlotResolutionStatusException ex) {
            // There was an unexpected problem.

            // Construct the error message.
            intentResponse = new IntentResponse(
                    "I'm sorry, I had a problem understanding your request, try asking again.",
                    false);

            logger.error(new StringBuilder()
                    .append(ex.getClass().getName())
                    .append(": slotName=")
                    .append(ex.getSlotName())
                    .append(", slotValue=")
                    .append(ex.getStatusCode())
                    .append(".")
                    .toString());

        } catch (PatternNotFoundException ex) {
            // The requested pattern was resolved by the skill, but the pattern is not one in our backend list.

            intentResponse = new IntentResponse(
                    "I'm sorry, but I could not find that pattern; can you please repeat the pattern name?",
                    true);

            logger.error(new StringBuilder()
                    .append(ex.getClass().getName())
                    .append(": patternName=")
                    .append(ex.getPatternName())
                    .append(".")
                    .toString());
        }

        // Construct a response builder and add the speech text string to it.
        // Keep the session open.
        ResponseBuilder responseBuilder = input.getResponseBuilder()
                .withShouldEndSession(false);

        // If the response needs a prompt, add it.
        if (intentResponse.isNeedsResponse())
            responseBuilder.addDelegateDirective(intent);
        else
            responseBuilder.withSpeech(intentResponse.getSpeechText());

        // Build and return the response.
        return responseBuilder.build();
    }
 }
