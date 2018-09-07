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
import com.eames.taekwondo.handlers.exception.PatternNotFoundException;
import com.eames.taekwondo.model.Pattern;

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

        // The speech text to return.
        String speechText;

        try {

            // Get the pattern from the input.
            // Throws: PatternNotFoundException
            Pattern pattern = getPattern(input);

            // Construct the answer.
            speechText = new StringBuilder()
                    .append("The ")
                    .append(pattern.getPhoneticName())
                    .append(" pattern has ")
                    .append(pattern.getMovementCount())
                    .append(" movements.")
                    .toString();

        } catch (PatternNotFoundException ex) {

            // Construct the error message.
            speechText = ex.getMessage();
        }

        // Construct a response builder and add the speech text string to it.
        // Keep the session open.
        ResponseBuilder responseBuilder = input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false);


        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        responseBuilder.withSimpleCard("TaeKwon-Do - PatternStepCount - debug", intent.toString());


        // Build and return the response.
        return responseBuilder.build();
    }
 }
