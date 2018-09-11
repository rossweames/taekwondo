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
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;
import com.eames.taekwondo.handlers.exception.*;
import com.eames.taekwondo.model.Pattern;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * This is the handler for the 'pattern step count' skill.
 *
 * TODO: Need unit tests for this class.
 */
public class PatternStepCountIntentHandler extends IntentHandler {

    /*
     * Implemented {@link RequestHandler} operations
     */

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("PatternStepCountIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        // The response.
        IntentResponse intentResponse;

        try {

            // Get the pattern from the input.
            // Throws: SlotNotFoundException, MissingSlotValueException, UnrecognizedSlotValueException,
            //         UnexpectedSlotResolutionStatusException, PatternNotFoundException
            Pattern pattern = getPattern(input);

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

            // TODO: Need to log this exception.

        } catch (MissingSlotValueException ex) {
            // No resolutions are available.
            // (The slot value was not provided by the user.)

            // Construct the prompt.
            intentResponse = new IntentResponse(
                    "Which pattern did you want?",
                    true);

            // TODO: Need to log this exception.

        } catch (UnrecognizedSlotValueException ex) {
            // The pattern name provided did not match any of the patterns defined in the skill.

            // Construct the prompt.
            intentResponse = new IntentResponse(
                    "I'm sorry, I did not recognize that pattern, can you please repeat the pattern name?.",
                    true);

            // TODO: Need to log this exception.

        } catch (UnexpectedSlotResolutionStatusException ex) {
            // There was an unexpected problem.

            // Construct the error message.
            intentResponse = new IntentResponse(
                    "I'm sorry, I had a problem understanding your request, try asking again.",
                    false);

            // TODO: Need to log this exception.

        } catch (PatternNotFoundException ex) {
            // The requested pattern was resolved by the skill, but the pattern is not one in our backend list.

            intentResponse = new IntentResponse(
                    "I'm sorry, but I could not find that pattern; can you please repeat the pattern name?",
                    true);

            // TODO: Need to log this exception.

        }

        // Construct a response builder and add the speech text string to it.
        // Keep the session open.
        ResponseBuilder responseBuilder = input.getResponseBuilder()
                .withShouldEndSession(false);

        // If the response needs a prompt, add it.
        if (intentResponse.isNeedsResponse())
            responseBuilder.withReprompt(intentResponse.getSpeechText());
        else
            responseBuilder.withSpeech(intentResponse.getSpeechText());

        // DEBUG - begin
        // TODO: Need to remove this debug code.
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        responseBuilder.withSimpleCard("TaeKwon-Do - PatternStepCount - debug", intent.toString());
        // DEBUG - end

        // Build and return the response.
        return responseBuilder.build();
    }
 }
