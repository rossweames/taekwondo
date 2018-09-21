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
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

public class SessionEndedRequestHandler implements RequestHandler {

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(SessionEndedRequestHandler.class);

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(SessionEndedRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        Request request = input.getRequestEnvelope().getRequest();
        SessionEndedRequest sessionEndedRequest = (SessionEndedRequest) request;
        SessionEndedReason reason = sessionEndedRequest.getReason();
        SessionEndedError error = sessionEndedRequest.getError();

        logger.debug(new StringBuilder()
                .append("SessionEndedRequestHandler (")
                .append(request.getClass().getSimpleName())
                .append("): reason=")
                .append(reason.toString())
                .append(", errorType=")
                .append((error != null) ? error.getType().toString() : "")
                .append(", errorMessage=")
                .append((error != null) ? error.getMessage() : "")
                .toString());

        // any cleanup logic goes here

        String speechText = "Goodbye from TaeKwon-Do Patterns";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("TaeKwon-Do - Session Ended", speechText)
                .withShouldEndSession(true)
                .build();
    }
}
