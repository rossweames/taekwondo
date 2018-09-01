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
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;
import com.eames.taekwondo.model.Pattern;
import com.eames.taekwondo.model.Patterns;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * This is the handler for the 'pattern step count' skill.
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

        // Grab the 'pattern' slot from the input.
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot patternSlot = slots.get(PATTERN_SLOT);

        // The speech and display text to return.
        StringBuilder speechSB = new StringBuilder();
        StringBuilder cardSB = null;

        // A pattern was passed in.
        if (patternSlot != null) {

            // Get the pattern key (i.e. the slot value).
            String patternKey = patternSlot.getValue();

            // Get the TKD pattern from the name passed in.
            Pattern pattern = Patterns.getPatternByKey(patternKey);
            if (pattern != null) {

                speechSB.append("The " + pattern.getPhoneticName() + " pattern has " + pattern.getMovementCount() + " movements.");
            }

            // No such pattern.
            else {
                speechSB.append("Sorry, but I do not recognize the " + patternKey + " pattern.");

                cardSB = new StringBuilder();
                cardSB.append("Could not find a pattern with the key: " + patternKey);
            }
        }

        // No pattern was given.
        else {
            speechSB.append("Sorry, but your request did not specify a pattern.");
        }

        // Construct a response builder and add the speech string to it.
        ResponseBuilder responseBuilder = input.getResponseBuilder()
                .withSpeech(speechSB.toString());
        if (cardSB != null)
            responseBuilder.withSimpleCard(SKILL_NAME, cardSB.toString());

        // Build and return the response.
        return responseBuilder.build();
    }
}
